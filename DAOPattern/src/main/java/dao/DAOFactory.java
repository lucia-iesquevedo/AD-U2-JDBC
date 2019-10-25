package dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class DAOFactory {

    private static DAOFactory instance;
    private String sourceCoffee;
    private Properties daoProps;

    private static final String PROPERTIES_FILE = "config\\dao-properties.xml";
    
    public DAOFactory() {
        try {
            setDAOType(PROPERTIES_FILE);
        } catch (Exception e) {
        }
    }

    private void setDAOType(String configFile) throws IOException, InvalidPropertiesFormatException {
        daoProps = new Properties();
        daoProps.loadFromXML(Files.newInputStream(Paths.get(configFile)));     
    }
    
    public DAOCoffee getDAOCoffee() {
        DAOCoffee dao = null;
        sourceCoffee = daoProps.getProperty("daocoffee");
        //Un if por cada tipo de DAO posible a instanciar
        if (sourceCoffee.equals("DAOJDBCCoffee")) {
            dao = new DAOJDBCCoffee();
        } else if (sourceCoffee.equals("DAOXMLCoffee")) {
            // dao= new DAOXMLCoffee();
        } else if (sourceCoffee.equals("DAODBUtilsCoffee"))  {
            // dao= new DAODBUtilsCoffee();
        }
        //... 

        return dao;
    }

}
