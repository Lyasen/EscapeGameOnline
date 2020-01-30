import domaine.properties.ConfigurationGame;
import gameHome.Home;
import player.Player;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private static Player player_1;
    private static Player player_2;

    /**
     * Used Main to launch the game mode
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
            System.err.println("File not found in classpath");
        }
        ConfigurationGame config = new ConfigurationGame(properties);
        config.configGame();

        Home choice = new Home(config);
        choice.menu(scan, setPlayer_1(player_1), setPlayer_2(player_2));
    }

    public static Player setPlayer_1(Player player_1) {
        Main.player_1 = player_1;
        return player_1;
    }

    public static Player setPlayer_2(Player player_2) {
        Main.player_2 = player_2;
        return player_2;
    }
}