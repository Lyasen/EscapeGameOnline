import domaine.properties.ConfigurationGame;
import gameHome.Home;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    /**
     * Used the main to launch the game mode
     *
     * @param args : A welcome menu
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Escape Game Online");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("config.properties"));
        } catch (IOException e) {
            System.out.println("File not found in classpath");
        }
        ConfigurationGame config = new ConfigurationGame(properties);
        config.configGame(properties);

        Home choice = new Home();
        choice.menu(scan, config);
    }
}
