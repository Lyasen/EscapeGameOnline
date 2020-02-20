package com.GamePlayStudio;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.gameHome.Home;
import com.GamePlayStudio.gameMessage.MsgError;
import com.GamePlayStudio.gameMessage.MsgInfo;
import com.GamePlayStudio.player.IAPlayer;
import com.GamePlayStudio.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main implements MsgError, MsgInfo {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Used Main to launch the game mode
     *
     * @param args : A welcome menu
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
