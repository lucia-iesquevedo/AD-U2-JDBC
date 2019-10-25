/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coffee;

/**
 *
 * @author Lucia
 */
public class DAOJDBCCoffee implements DAOCoffee{
    
    // SQL statements - Store into a file
    private static final String SELECT_coffees_QUERY = "select COF_NAME, SUPP_ID, PRICE, SALES, TOTAL from coffees";
    
    private Connection con = null;
    private PreparedStatement stmt = null;	
    private ResultSet resultSet = null;
    private DBConnection db=null;
    
    public DAOJDBCCoffee(){
        db = new DBConnection();
    }

    @Override
    public List<Coffee> getAll() {
		List<Coffee> coffees = new ArrayList();
                
		try {
			//Gets a new connection from the pool
                        con = db.getConnection();
			
			stmt = con.prepareStatement(SELECT_coffees_QUERY);
                        resultSet = stmt.executeQuery();

			while (resultSet.next()) {
                            Coffee c = new Coffee(resultSet.getString(1), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5) );
                            coffees.add(c);
                        }
                } catch (Exception ex) {
                        Logger.getLogger(DAOJDBCCoffee.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    db.releaseResources(stmt, resultSet);
                    db.closeConnection(con);
                }
                return coffees;
    }
   
    @Override
    public Coffee get(int id) {
        Coffee c=null;
        return c;
    };
     
    @Override
    public void save(Coffee t) {};
     
    @Override
    public void update(Coffee t) {};
     
    @Override
    public void delete(Coffee t) {};
}
