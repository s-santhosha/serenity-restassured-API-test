package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {
    Properties properties = new Properties();

    public void loadPropertyFile() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/java/config/config.properties";
        File file = new File(filePath);
        properties.load(new FileInputStream(file));
    }

    public String getAusPostURI() {
        return properties.getProperty("ausPostURI");
    }

    public String getAPIKey() {
        return properties.getProperty("APIKey");
    }

    public String getSourceArea() {
        return properties.getProperty("sourceArea");
    }

    public String getSourceState() {
        return properties.getProperty("sourceState");
    }

    public String getDestinationArea() {
        return properties.getProperty("destinationArea");
    }

    public String getDestinationState() {
        return properties.getProperty("destinationState");
    }


}

