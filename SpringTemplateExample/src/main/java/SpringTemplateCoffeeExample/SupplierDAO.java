package SpringTemplateCoffeeExample;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class SupplierDAO {

	// SQL statements - Store into a file
	private static final String SELECT_suppliers_QUERY = "select * from suppliers";
        private static final String SELECT_supplier_QUERY = "select * from suppliers where supp_id=?";
        private static final String UPDATE_supplier_QUERY = "update suppliers set STREET = ?, COUNTRY=? where SUPP_ID = ?";
        private static final String DELETE_supplier_QUERY = "delete from suppliers where supp_id = ?";
        private static final String DELETE_coffee_QUERY = "delete from coffees where supp_id = ?";
        
        
        public List<Supplier> getAllSuppliers() throws Exception {
            JdbcTemplate jtm = new JdbcTemplate(DBConnPool.getInstance().getDataSource());
            List l= jtm.query(SELECT_suppliers_QUERY, BeanPropertyRowMapper.newInstance(Supplier.class));
             return l;
    }
        
        public Supplier getSupplier(int id) {

            JdbcTemplate jtm = new JdbcTemplate(
                DBConnPool.getInstance().getDataSource());
            List<Supplier> sup = jtm.query(SELECT_supplier_QUERY, new Object[]{id},
                BeanPropertyRowMapper.newInstance(Supplier.class));
            return sup.isEmpty() ? null : sup.get(0);
  }
        
        public int updateSupplier(Supplier s) {

            JdbcTemplate jtm = new JdbcTemplate(
                DBConnPool.getInstance().getDataSource());
            return jtm.update(UPDATE_supplier_QUERY, s.getStreet(), s.getCountry(), s.getSupp_id());
          }
        
        public int deleteSupplier(int id) {
            int res = -1;

            try {
              JdbcTemplate jtm = new JdbcTemplate(
                  DBConnPool.getInstance().getDataSource());

              res = jtm.update(DELETE_supplier_QUERY, id);
            } catch (DataIntegrityViolationException e) {
              if (e.getMessage().contains("IntegrityConstraintViolation")) {
                res = -2;
              }
            } catch (Exception ex) {
              Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return res;
          }

        public int addSupplier(Supplier s) {

            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                DBConnPool.getInstance().getDataSource()).withTableName("suppliers");
            
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("SUPP_ID", s.getSupp_id());
            parameters.put("STREET", s.getStreet());
            parameters.put("COUNTRY", s.getCountry());
             int res = jdbcInsert.execute(parameters);
            return res;
        }
        
         public Supplier addSupplierGK(Supplier s) {

            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                DBConnPool.getInstance().getDataSource()).withTableName("suppliers")
                    .usingGeneratedKeyColumns("SUPP_ID");
            
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("STREET", s.getStreet());
            parameters.put("COUNTRY", s.getCountry());
            s.setSupp_id((int) jdbcInsert.executeAndReturnKey(parameters).longValue());
            return s;
  }
        
         public int deleteSupplierGK(int id) {
            int res = -1;
            TransactionDefinition txDef = new DefaultTransactionDefinition();
            DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(DBConnPool.getInstance().getDataSource());
            TransactionStatus txStatus = transactionManager.getTransaction(txDef);

            try {
              JdbcTemplate jtm = new JdbcTemplate(
                  transactionManager.getDataSource());
              res = jtm.update(DELETE_coffee_QUERY, id);
              res = jtm.update(DELETE_supplier_QUERY, id);

              transactionManager.commit(txStatus);

            } catch (Exception e) {

              transactionManager.rollback(txStatus);

              throw e;

            }

            return res;
          }
}
