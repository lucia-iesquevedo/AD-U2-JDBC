package JDBCConnectionExample;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *  @description Class for creating a connection to a DB using DriverManager class.
 *  Reads the information from a property file (mysql-properties.xml)
 */
public class DBConnection {

	public String urlDB;
	public String userName;
	public String password;

	/**
	 * Opens Database connection
	 */
	public Connection getConnection() throws SQLException {

                urlDB=ConfigurationProp.getInstance().getProperty("urlDB");
                userName=ConfigurationProp.getInstance().getProperty("user_name");
                password=ConfigurationProp.getInstance().getProperty("password");
                
		Connection conn = DriverManager.getConnection(urlDB, userName, password);	
		System.out.println("Connected to DB");
		return conn;
	}

	/**
	 * Closes connection
	 */
	public static void closeConnection(Connection connArg) {
		System.out.println("Releasing all open resources ...");
		try {
			if (connArg != null) {
				connArg.close();
				connArg = null;
			}
		} catch (SQLException sqle) {
			System.err.println(sqle);
		}
	}
	
	
}
