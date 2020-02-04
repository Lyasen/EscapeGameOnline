import domaine.properties.ConfigurationGame;
import gameHome.Home;
import player.HumanPlayer;
import player.IAPlayer;
import player.Player;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    /**
     * Used Main to launch the game mode
     *  @param args : A welcome menu
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Escape Game Online");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("config.properties"));
        } catch (IOException e) {
            System.err.println("File not found in classpath");
        }
        ConfigurationGame config = new ConfigurationGame(properties);
        config.configGame();

        String[] symbols = new String[config.getDigitsCombination()];
        int[] returnedCombination = new int[config.getDigitsCombination()];
        Player player1 = new HumanPlayer(config, scan, symbols, returnedCombination);
        int[] combination = new int[config.getDigitsCombination()];

        Player player2 = new IAPlayer(config, combination, symbols);
        Home choice = new Home(player1, player2, config, scan);
        choice.menu();
    }
}