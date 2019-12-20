package com.ocr.GamePlay_Studio.Domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private int digitsCombination, maxTries;
    private int[] minValue, maxValue;
    private boolean devMode;

    static Properties properties;

    public static void configGame() {
        properties = new Properties();
        Config.class.getResourceAsStream("config.properties");

        try {
            properties.load(new FileInputStream("config.properties"));
            System.out.println("Welcome on EscapeGame Online, here are the rules to play ! Good Luck !");
            System.out.println("Number of digits in the combination : " + properties.getProperty("digitsCombination", "4"));
            System.out.println("Number of tries for a game : " + properties.getProperty("maxTries", "10"));
            System.out.println("MinValue is : " + properties.getProperty("minValue", "0"));
            System.out.println("MaxValue is : " + properties.getProperty("maxValue", "9"));
            System.out.println("Developer mode active : " + properties.getProperty("devmode", "false"));
        } catch (IOException e){
            System.out.println("Exception occured " + e.getMessage());
        }
    }

    public Config(int digitsCombination, int maxTries, int[] minValue, int[] maxValue,boolean devMode) {
        this.digitsCombination = digitsCombination;
        this.maxTries = maxTries;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.devMode = devMode;
    }

    public boolean getDevMode() {
        return !devMode;
    }

    public int getDigitsCombination() {
        return digitsCombination;
    }

    public int getMaxTries() { return maxTries; }

    public int[] getMinValue() {
        return minValue;
    }

    public int[] getMaxValue() {
        return maxValue;
    }
}
