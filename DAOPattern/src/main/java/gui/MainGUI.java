package gui;

import java.util.List;
import model.Coffee;
import services.ServiceCoffee;

public class MainGUI {

	public static void main(String[] args) {
            // This code implements the GUI layer
            ServiceCoffee sc = new ServiceCoffee();
            
            for (int i=0; i<3; i++) {
                System.out.println("List of coffees:");
                List<Coffee> list = sc.listCoffees();
                System.out.println(list);
            }
        }
		
}
