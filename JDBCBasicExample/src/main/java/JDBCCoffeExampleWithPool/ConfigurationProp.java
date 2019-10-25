package JDBCCoffeExampleWithPool;

import JDBCCoffeeExample.*;
import JDBCConnectionExample.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 *
 * @author Lucia
 */
public class ConfigurationProp {

    private static ConfigurationProp instance=null;
    private Properties p;

    private ConfigurationProp() {
        Path p1 = Paths.get("src/main/java/JDBCCoffeeExample/mysql-properties.xml");
        p= new Properties();
        InputStream propertiesStream=null;
        try {
            propertiesStream = Files.newInputStream(p1);
            p.loadFromXML(propertiesStream);
        } catch (IOException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ConfigurationProp getInstance() {

        if (instance==null) {
            instance=new ConfigurationProp();
        }
        return instance;
    }

    public String getProperty(String clave) {
        return p.getProperty(clave);
    }

}
