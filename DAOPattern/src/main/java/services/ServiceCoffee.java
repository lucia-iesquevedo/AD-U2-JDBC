package services;

import dao.DAOFactory;
import java.util.List;
import model.Coffee;

/**
 *
 * @author lucia
 */
public class ServiceCoffee {
    
	private DAOFactory dao;
	
        public ServiceCoffee() {
            dao = new DAOFactory();
        }
	
	public List<Coffee> listCoffees() {
            return dao.getDAOCoffee().getAll();
        }
    
}