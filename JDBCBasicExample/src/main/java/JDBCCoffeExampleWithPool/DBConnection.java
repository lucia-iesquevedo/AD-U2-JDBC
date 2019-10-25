package JDBCCoffeExampleWithPool;

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
        public Connection getConnection() throws Exception {
            Connection connection = null;
            connection = DBConnPool.getInstance().getConnection();
            return connection;
        }
	
        /**
	 * Closes connection
	 */
	public void closeConnection(Connection connection) {
            try {
                if (connection != null) {
                    connection.close();
                } 
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
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
