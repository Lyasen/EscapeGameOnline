import domaine.properties.ConfigurationGame;
import gameHome.Home;
import gameMessage.MessageError;
import gameMessage.MessageInfo;
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
        MessageInfo mi = new MessageInfo();
        mi.welcomeInGame();
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("config.properties"));
        } catch (IOException e) {
            MessageError me = new MessageError();
            me.errorLoadFile();
        }
        ConfigurationGame config = new ConfigurationGame(properties);
        config.configGame();

        Player player1 = new HumanPlayer(config,scan);
        Player player2 = new IAPlayer(config);
        Home choice = new Home(player1, player2, config, scan);
        choice.menu();
    }
}