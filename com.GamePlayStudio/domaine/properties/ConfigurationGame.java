package domaine.properties;

import java.util.Properties;

public class ConfigurationGame {
    private final int digitsCombination, maxTries, minValue, maxValue;
    private final boolean devMode;

    public ConfigurationGame(Properties properties) {
        digitsCombination = Integer.parseInt(properties.getProperty("digitsCombination", "4"));
        maxTries = Integer.parseInt(properties.getProperty("maxTries", "10"));
        minValue = Integer.parseInt(properties.getProperty("minValue", "0"));
        maxValue = Integer.parseInt(properties.getProperty("maxValue", "9"));
        devMode = Boolean.parseBoolean(properties.getProperty("devMode", "false"));
    }

    public int getDigitsCombination() {
        return digitsCombination;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void configGame(Properties properties) {
        //  get the property value and print it out
        System.out.println("Here are the rules to play ! Good Luck !");
        String digitsCombination = properties.getProperty("digitsCombination", "4");
        String maxTries = properties.getProperty("maxTries", "10");
        String devMode = properties.getProperty("devmode", "false");

        System.out.println("Number of digits in the combination : " + digitsCombination +
                "\nNumber of tries for a game : " + maxTries +
                "\nDeveloper mode active : " + devMode);
    }
}
