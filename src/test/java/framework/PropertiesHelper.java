package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper extends BaseEntity {
    private static final String PROPERTY_PATH = "src/test/resources/";
    private String propertyFileName;

    public PropertiesHelper(String propertyFileName){
        this.propertyFileName = propertyFileName;
    }

    public String getProperty(String name){
        FileInputStream fileInputStream;
        Properties property = new Properties();
        try {
            fileInputStream = new FileInputStream(PROPERTY_PATH + propertyFileName);
            property.load(fileInputStream);
            return property.getProperty(name);
        } catch (IOException e) {
            assertFail(String.format("Cannot load property by name = '%s' in property file = '%s'", name, propertyFileName));
        }
        return null;
    }
}
