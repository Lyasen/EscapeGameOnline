package com.ocr.GamePlay_Studio.Domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    public static void configGame() throws IOException {
        Path path = Paths.get("c:/Users/remsh.DESKTOP-H6AOVMI/IdeaProjects/EscapeGameOnline/src/com/ocr/GamePlay_Studio/Domain/config.properties");
        Properties properties = new Properties();

        try (FileInputStream file = new FileInputStream(String.valueOf(path))) {
            properties.load(file);
            file.close();
            System.out.println("Welcome on EscapeGame Online, here are the rules to play ! Good Luck !");
            System.out.println("Number of digits in the combination : " + properties.getProperty("digitsCombination", "4"));
            System.out.println("Number of tries for a game : " + properties.getProperty("maxTries", "10"));
            System.out.println("MinValue is : " + properties.getProperty("minValue", "0"));
            System.out.println("MaxValue is : " + properties.getProperty("maxValue", "9"));
            System.out.println("Developer mode active : " + properties.getProperty("devmode", "false"));
        } catch (IOException e) {
            System.out.println("Unable to load config file.");
        }
    }
}
