import domaine.properties.ConfigurationGame;
import gameMode.ChallengerMode;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
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

        ChallengerMode challenge = new ChallengerMode();
        challenge.challenge(scan, config);
    }
}
