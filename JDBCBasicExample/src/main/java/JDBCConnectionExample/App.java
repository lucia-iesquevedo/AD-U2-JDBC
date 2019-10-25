package JDBCConnectionExample;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *  @description Class for testing MySQL JDBC connection using Driver Manager
 */

public class App {
	
	 /* Make sure the mysql driver is installed
	  */
	 public static void main(String[] args) {
		    DBConnection myConexionDriverManager;
		    Connection myConnection = null;
		    
		    
		    try {
		      myConexionDriverManager = new DBConnection();
		      myConnection = myConexionDriverManager.getConnection();   

		    } catch (SQLException e) {
		    	e.printStackTrace(System.err);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    } finally {
		      DBConnection.closeConnection(myConnection);
		    }

		  }

}


