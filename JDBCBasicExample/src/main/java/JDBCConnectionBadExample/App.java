package JDBCConnectionBadExample;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  @description Class for testing MySQL JDBC connection using Driver Manager
 */

public class App {
	
	 /* Make sure the mysql driver is installed
	  */
	 public static void main(String[] args) {
		    Connection myConnection=null;
		    String user="root";
		    String passwd="root";
		    
		    
		    try {
		    	myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffeecompanydb",user,passwd);
		    	System.out.println("Connected to DB");
		    } catch (SQLException e) {
		    	e.printStackTrace(System.err);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    } finally {
		    	System.out.println("Releasing all open resources ...");
				try {
					if (myConnection != null) {
						myConnection.close();
					}
				} catch (SQLException sqle) {
					System.err.println(sqle);
				}
		    }

		  }

}


