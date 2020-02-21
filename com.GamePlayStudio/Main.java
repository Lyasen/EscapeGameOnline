import domaine.properties.ConfigurationGame;
import gameHome.Home;
import gameMessage.MsgError;
import gameMessage.MsgInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import player.IAPlayer;
import player.Player;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main implements MsgError, MsgInfo {
    static final Logger log = LogManager.getLogger();
    /**
     * Used Main to launch the game mode
     *  @param args : A welcome menu
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MsgInfo.welcomeInGame();
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("config.properties"));
        } catch (IOException e) {
            MsgError.errorLoadFile();
        }
        ConfigurationGame config = new ConfigurationGame(properties);
        config.configGame();

        Player player1 = new IAPlayer(config);
        Player player2 = new IAPlayer(config);
        Home choice = new Home(player1, player2, config, scan);
        choice.menu();
    }
}