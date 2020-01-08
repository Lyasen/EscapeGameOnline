package domaine.properties;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationGame {
    private int maxTries, digitsCombination, minValue, maxValue;
    private boolean devMode;

    public String configGame() {
        Properties properties = new Properties();
        try {
            properties.load(ConfigurationGame.class.getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Loading file failed");
        }

        String host = properties.getProperty("host");

        System.out.println("Here are the rules to play ! Good Luck !");
        System.out.println("Number of digits in the combination : " + properties.getProperty("digitsCombination", "4"));
        System.out.println("Number of tries for a game : " + properties.getProperty("maxTries", "10"));
        System.out.println("Developer mode active : " + properties.getProperty("devmode", "false"));

        return host;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public void setMaxTries(int maxTries) {
        this.maxTries = maxTries;
    }

    public int getDigitsCombination() {
        return digitsCombination;
    }

    public void setDigitsCombination(int digitsCombination) {
        this.digitsCombination = digitsCombination;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }
}
