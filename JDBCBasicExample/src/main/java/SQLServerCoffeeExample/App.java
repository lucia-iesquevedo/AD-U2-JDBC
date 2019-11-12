package SQLServerCoffeeExample;

import JDBCCoffeeExample.*;
import java.util.HashMap;



public class App {

	public static void main(String[] args) {
	
			
            CoffeeDAO myCoffee = new CoffeeDAO();
            System.out.println("List of coffees:");

            myCoffee.listTable();

            //Example JDBC-Prepared statements
            myCoffee.updateCoffeeSales("Colombian", 678);

            myCoffee.updateCoffeePrices(5);

            myCoffee.insertNewRow("Sudafrica2", 101, 200, 10, 470);

            HashMap<String, Integer> sales = new HashMap<String, Integer>();
            sales.put("Colombian", 175);
            sales.put("French_Roast", 150);
            sales.put("Espresso", 60);
            sales.put("Colombian_Decaf", 155);
            sales.put("French_Roast_Decaf", 90);

            myCoffee.updateSales(sales);
            
            myCoffee.listTable();

        }
}
