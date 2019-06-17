package base;

import java.io.IOException;

import utils.ReadPropertyFile;

public class BaseTest {
    public ReadPropertyFile propertyFile = new ReadPropertyFile();
    public static String ausPostURL;
    public static String apiKey;
    public String sourceArea;
    public String sourceState;
    public String destinationArea;
    public String destinationState;


    public void setup() throws IOException {
        propertyFile.loadPropertyFile();
        ausPostURL = propertyFile.getAusPostURI();
        apiKey = propertyFile.getAPIKey();
        sourceArea = propertyFile.getSourceArea();
        sourceState = propertyFile.getSourceState();
        destinationArea = propertyFile.getDestinationArea();
        destinationState = propertyFile.getDestinationState();
    }
}
