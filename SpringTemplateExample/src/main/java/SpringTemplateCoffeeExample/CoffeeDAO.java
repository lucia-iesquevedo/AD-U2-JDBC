package SpringTemplateCoffeeExample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class CoffeeDAO {

	// SQL statements - Store into a file
	private static final String SELECT_coffees_QUERY = "select * from coffees inner join products on coffees.id_prod = products.id_prod";
	private static final String SELECT_coffeesConEUCode_QUERY = "select * from coffees inner join products on coffees.id_prod = products.id_prod inner join eucodes on products.firstdig = eucodes.firstdig";
        
        
        public List<Coffee> getAllCoffees() throws Exception {
            JdbcTemplate jtm = new JdbcTemplate(DBConnPool.getInstance().getDataSource());
            List l= jtm.query(SELECT_coffees_QUERY, BeanPropertyRowMapper.newInstance(Coffee.class));
             return l;
    }
        
        public List<Coffee> getAllCoffeesWithEUCode() throws Exception {
            JdbcTemplate jtm = new JdbcTemplate(DBConnPool.getInstance().getDataSource());
            List l= jtm.query(SELECT_coffeesConEUCode_QUERY, new RowMapper<Coffee>() {
                @Override
                public Coffee mapRow(ResultSet rs, int rowNum) throws SQLException {
                    
                    Coffee c = new Coffee();
                    c.setId_prod(rs.getInt("id_prod"));
                    c.setCof_name(rs.getString("cof_name"));
                    c.setPrice(rs.getInt("price"));
                    c.setSales(rs.getInt("sales"));
                    c.setSupp_id(rs.getInt("supp_id"));
                    c.setCountry(rs.getString("country"));
                    EuropeanCode eu= new EuropeanCode(rs.getInt("firstdig"),rs.getInt("seconddig"), rs.getInt("thirddig") );
                    c.setEu(eu);                 
                   return c;
                }
            });
             return l;

        }
}