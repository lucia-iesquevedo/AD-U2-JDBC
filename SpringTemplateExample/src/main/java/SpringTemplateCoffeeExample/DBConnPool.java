
package SpringTemplateCoffeeExample;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *  @description Class for creating a connection to a DB using DriverManager class.
 *  Reads the information from a property file (mysql-properties.xml)
 */
public class DBConnPool {

        public static DBConnPool dbConnectionPool=null;
        public BasicDataSource dataSource=null;
        public String driver;
	public String urlDB;
	public String userName;
	public String password;
	
	private DBConnPool(){
		super();
                dataSource=this.getPool();
	}

        public static DBConnPool getInstance() {
        if (dbConnectionPool == null) {
            dbConnectionPool = new DBConnPool();
        }

        return dbConnectionPool;
    }
	
	/**
	 * Creates connection dbConnectionPool
	 */
	protected BasicDataSource getPool() {
            
                driver=ConfigurationProp.getInstance().getProperty("driver");    
                urlDB=ConfigurationProp.getInstance().getProperty("urlDB");
                userName=ConfigurationProp.getInstance().getProperty("user_name");
                password=ConfigurationProp.getInstance().getProperty("password");
		
                BasicDataSource basicDataSource = new BasicDataSource();		
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUsername(userName);
		basicDataSource.setPassword(password);
		basicDataSource.setUrl(urlDB);
		
		// Sets the size of the dbConnectionPool. Optional, default vaule is 10
		basicDataSource.setInitialSize(4);

		// Optional. For validating connection
		basicDataSource.setValidationQuery("select 1");
		
		System.out.println("Pool created");
		return basicDataSource;
	}
        
        protected BasicDataSource getDataSource() {
		return dataSource;
	}
	/**
	 * Closes dbConnectionPool
	 */
	public static void closePool(BasicDataSource dbConnectionPool) throws SQLException {
		System.out.println("Releasing all open resources ...");
                if (dbConnectionPool != null) {
                    dbConnectionPool.close();
                    dbConnectionPool = null;
                }
	}
	
        public Connection getConnection() throws SQLException {
              return dataSource.getConnection();
    }

}


