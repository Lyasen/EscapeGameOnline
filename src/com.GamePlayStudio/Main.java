package com.GamePlayStudio;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.gameHome.Home;
import com.GamePlayStudio.gameMessage.MsgError;
import com.GamePlayStudio.player.HumanPlayer;
import com.GamePlayStudio.player.IAPlayer;
import com.GamePlayStudio.player.Player;

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

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("config.properties"));
        } catch (IOException e) {
            MsgError.errorLoadFile();
        }
        ConfigurationGame config = new ConfigurationGame(properties);
        config.getMsgInfo().welcomeInGame();
        config.configGame();

        Player player1 = new HumanPlayer(config, scan);
        Player player2 = new IAPlayer(config);
        Home choice = new Home(player1, player2, config, scan);
        choice.menu();
    }
}