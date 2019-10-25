package JDBCCoffeExampleWithPool;

import JDBCCoffeeExample.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoffeeDAO {

	// SQL statements - Store into a file
	private static final String SELECT_coffees_QUERY = "select * from coffees";
	
	private static final String SELECT_coffee_QUERY = "select * from coffees where COF_NAME= ";
	// SQL prepared statement 
	private static final String UPDATE_SALES_coffees = "update coffees set SALES = ? where COF_NAME = ?";
        
	private static final String UPDATE_TOTAL_COFFEE = "update coffees "
			+ "set TOTAL = TOTAL + ? where COF_NAME = ?";

        private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	private PreparedStatement pstmt2;
        private DBConnection db;
                        
	/**
             * Lists all coffees using Statement Class
	 */
	public void listTable() throws Exception {
            
            stmt = null;
            rs = null;
            db = new DBConnection();
            
            try {
                // Opens connection
		con = db.getConnection();
                stmt = con.createStatement();			
                // Executing the statement. The result will be stored in the ResultSet object
                rs = stmt.executeQuery(SELECT_coffees_QUERY);

                // Reading the ResultSet with field names
//			while (rs.next()) {
//				String coffeeName = rs.getString("COF_NAME");
//				int supplierID = rs.getInt("SUPP_ID");
//				float PRICE = rs.getFloat("PRICE");
//				int SALES = rs.getInt("SALES");
//				int total = rs.getInt("TOTAL");
//				System.out.println(coffeeName + ", " + supplierID + ", "
//						+ PRICE + ", " + SALES + ", " + total);
//			}

                // Reading the ResultSet with indexes
                while (rs.next()) {
                        String coffeeName = rs.getString(1);
                        int supplierID = rs.getInt(2);
                        float PRICE = rs.getFloat(3);
                        int SALES = rs.getInt(4);
                        int total = rs.getInt(5);
                        System.out.println(coffeeName + ", " + supplierID + ", "
                                        + PRICE + ", " + SALES + ", " + total);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.releaseResource(rs);
                db.releaseResource(stmt);
                db.closeConnection(con);
            }
    }
        
    /**
	 * Method for updating coffee sales using PreparedStatement class
	 */
	public void updateCoffeeSales(String coffee, int sales) throws Exception {

            pstmt = null;
            rs = null;
            db = new DBConnection();
            
            try {
                // Opens connection
                this.con = db.getConnection();
                
// Creates a PreparedStatement object with the parameterized query
		pstmt = con.prepareStatement(UPDATE_SALES_coffees);
			
                // Assigns values to query parameters, indicating the order number
                // and using the appropriate method depending on the data type
                pstmt.setFloat(1, sales);
                pstmt.setString(2, coffee);

                // executeUpdate method for INSERT, UPDATE and DELETE
                pstmt.executeUpdate();
			
            } catch (SQLException sqle) {
                Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, sqle);
            } finally {
                db.releaseResource(rs);
                db.releaseResource(pstmt);
                db.closeConnection(con);
            }
	} 
        
        /**
	 * updateCoffeePrices method: Updates prices given a new percentage
	 */
	public void updateCoffeePrices(float percentage) throws Exception {
            stmt = null;
            rs = null;
            db = new DBConnection();
            
            try {
                // Opens connection
                this.con = db.getConnection();
                // Associates a ResultSet for scrolling, updatable and sensitive to DB changes 

                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery(SELECT_coffees_QUERY);

                while (rs.next()) {
                        float f = rs.getFloat("PRICE");
                        // Updates ResultSet price column
                        rs.updateFloat("PRICE", f * percentage);
                        // Updates ResultSet row. Changes are saved in DB 
                        rs.updateRow();
                }
                // Sets the cursor before first row
                rs.beforeFirst();
                // Scrolls through the updated ResultSet
                System.out.println("\nAfter modifying the price:\n");
                while (rs.next()) {
                        String coffeeName = rs.getString("COF_NAME");
                        int supplierID = rs.getInt("SUPP_ID");
                        float PRICE = rs.getFloat("PRICE");
                        int SALES = rs.getInt("SALES");
                        int total = rs.getInt("TOTAL");
                        System.out.println(coffeeName + ", " + supplierID + ", "
                                        + PRICE + ", " + SALES + ", " + total);
                }

            } catch (SQLException sqle) {
            Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, sqle);

            } finally {
                db.releaseResource(rs);
                db.releaseResource(stmt);
                db.closeConnection(con);
            }

        }
        
        /**
	 * insertNewRow method: Inserts a new row using ResultSet
	 */
	
	public void insertNewRow (String coffeeName, int supplierID, float PRICE,
			int SALES, int total) throws Exception  {
            stmt = null;
            db = new DBConnection();
            
            try {
                // Opens connection
                this.con = db.getConnection();
                stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery(SELECT_coffees_QUERY);

                /*
                 * For inserting a row within the ResultSet, the cursor has to be situated
                 * on a special row named "insert row", where values can be assigned
                 */
                rs.moveToInsertRow();

                rs.updateString("COF_NAME", coffeeName);
                rs.updateInt("SUPP_ID", supplierID);
                rs.updateFloat("PRICE", PRICE);
                rs.updateInt("SALES", SALES);
                rs.updateInt("TOTAL", total);

                // Inserts ResultSet row. Changes are saved in DB 		
                rs.insertRow();
                rs.beforeFirst();

                // Scrolls through the updated ResultSet
                System.out.println("\nAfter inserting new row:\n");
                while (rs.next()) {
                        String coffeeName1 = rs.getString("COF_NAME");
                        int supplierID1 = rs.getInt("SUPP_ID");
                        float PRICE1 = rs.getFloat("PRICE");
                        int SALES1 = rs.getInt("SALES");
                        int total1 = rs.getInt("TOTAL");
                        System.out.println(coffeeName1 + ", " + supplierID1 + ", "
                                        + PRICE1 + ", " + SALES1 + ", " + total1);
                }

            } catch (SQLException sqle) {
            Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, sqle);

            } finally {
                db.releaseResource(rs);
                db.releaseResource(stmt);
                db.closeConnection(con);
            }
	}
        
        /**
	 * UpdateSales method: Updates coffee sales and total using a transaction
	 */
        public void updateSales(HashMap<String, Integer> sales) throws Exception
	{
            pstmt = null;
            pstmt2 = null;
            db = new DBConnection();
            
            try {
                // Opens connection
                this.con = db.getConnection();
                // Disables autocommit
                con.setAutoCommit(false);
                pstmt = con.prepareStatement(UPDATE_SALES_coffees);
                pstmt2 = con.prepareStatement(UPDATE_TOTAL_COFFEE);

                for (Map.Entry<String, Integer> e : sales.entrySet()) {
                        pstmt.setInt(1, e.getValue().intValue());
                        pstmt.setString(2, e.getKey());
                        pstmt.executeUpdate();
                        pstmt2.setInt(1, e.getValue().intValue());
                        pstmt2.setString(2, e.getKey());
                        pstmt2.executeUpdate();

                        // Commit when all movements have been done
                        con.commit();
                }
            } catch (SQLException sqle) {
                Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, sqle);

            } finally {
                try {
                            // Enables autocommit
                            con.setAutoCommit(true);
                } catch (SQLException sqle) {
                            Logger.getLogger(CoffeeDAO.class.getName()).log(Level.SEVERE, null, sqle);
                }
                db.releaseResource(pstmt);
                db.releaseResource(pstmt2);
                db.closeConnection(con);

            }
        }

}
