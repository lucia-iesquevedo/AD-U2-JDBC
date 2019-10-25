package SpringTemplateCoffeeExample;

import java.util.HashMap;
import java.util.List;



public class App {

	public static void main(String[] args) throws Exception {
	
			
            SupplierDAO mySupplier = new SupplierDAO();
            System.out.println("List of suppliers:");

            mySupplier.getAllSuppliers().forEach(System.out::println);
            
            Supplier s= mySupplier.getSupplier(49);
            System.out.println(s);
            
            s.setStreet("145-67 St");
            s.setCountry("US");
            
            int res= mySupplier.updateSupplier(s);
            if (res>0)
                System.out.println("Successful update");
            else
                System.out.println("Error when updating");
            
            res= mySupplier.deleteSupplier(150);
            if (res>0)
                System.out.println("Successful delete");
            else if (res == -2)
                System.out.println("Integrity violation");
                else
                System.out.println("Error when deleting");
         
            res = mySupplier.deleteSupplierGK(150);
            if (res>0)
                System.out.println("Successful delete");
            else
                System.out.println("Error when deleting");
            
            Supplier s2 = new Supplier();
            s2.setSupp_id(3);
            s2.setStreet("1-67 St");
            s2.setCountry("UK");
            
            res= mySupplier.addSupplier(s2);
            
            if (res>0)
                System.out.println("Successful insertion");
            else
                System.out.println("Error when inserting");   
        }
}
