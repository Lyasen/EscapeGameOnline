import domaine.properties.ConfigurationGame;
import gameHome.Home;
import gameMessage.MsgError;
import gameMessage.MsgInfo;
import player.HumanPlayer;
import player.IAPlayer;
import player.Player;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main implements MsgError, MsgInfo {
    static final Logger log = Logger.getLogger("log4j.properties");
    /**
     * Used Main to launch the game mode
     *  @param args : A welcome menu
     */
    public static void main(String[] args) {
        log.info("Message de test");
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

        Player player1 = new HumanPlayer(config,scan);
        Player player2 = new IAPlayer(config);
        Home choice = new Home(player1, player2, config, scan);
        choice.menu();
    }
}