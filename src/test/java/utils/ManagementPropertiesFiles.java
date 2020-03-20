package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ManagementPropertiesFiles {

    private static ManagementPropertiesFiles instance;

    static String PROPERTIES_FILE = "src/test/resources/properties/config.properties";
    public ManagementPropertiesFiles() {

    }

    public static ManagementPropertiesFiles getInstance(){
        if (instance==null)
        {
            instance= new ManagementPropertiesFiles();
        }
        return instance;
    }

    private static Properties initProperties() {
        Properties prop = new Properties();
        FileInputStream is = null;

        try {
            is = new FileInputStream(PROPERTIES_FILE);
            prop.load(is);
        } catch (IOException var4) {
            System.out.println(var4.toString());
        }

        return prop;
    }

    public static String getFieldProperties(String clave) {
        Properties prop = initProperties();
        return prop.getProperty(clave);
    }

    public void setFieldProperties(String clave, String newProperty) {
        Properties prop = initProperties();
        prop.setProperty(clave, newProperty);
    }
}