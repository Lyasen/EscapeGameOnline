package domaine.properties;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationGame {
    String result = "";
    InputStream inputStream;

    public void configGame() {
        try {
            Properties properties = new Properties();
            String propertiesFileName = "config.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

            if (inputStream != null)
                properties.load(inputStream);
            else
                throw new FileNotFoundException("Property file '" + propertiesFileName + "' not found in the classpath");

            //  get the property value and print it out
            String digitsCombination = properties.getProperty("digitsCombination", "4");
            String maxTries = properties.getProperty("maxTries", "10");
            String devMode = properties.getProperty("devmode", "false");

            System.out.println(result = "Number of digits in the combination : " + digitsCombination +
                    "\nNumber of tries for a game : " + maxTries +
                    "\nDeveloper mode active : " + devMode);
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
    }
}
