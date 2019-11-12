package SQLServerCoffeeExample;

import JDBCCoffeeExample.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                //userName=ConfigurationProp.getInstance().getProperty("user_name");
                //password=ConfigurationProp.getInstance().getProperty("password");
                
		//Connection conn = DriverManager.getConnection(urlDB, userName, password);	
                Connection conn = DriverManager.getConnection(urlDB);	
		System.out.println("Connected to DB");
		return conn;
	}

	/**
	 * Closes connection
	 */
	public void closeConnection(Connection connArg) {
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
	
	public void releaseResource(PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException sqle) {
			Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}
        
        public void releaseResource(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			
		} catch (SQLException sqle) {
			Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}
        
        public void releaseResource(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException sqle) {
			Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, sqle);
		}
	}
}
