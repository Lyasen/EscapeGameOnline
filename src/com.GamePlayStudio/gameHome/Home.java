package gameHome;

import domaine.properties.ConfigurationGame;
import gameMode.BonusMode;
import gameMode.ChallengerMode;
import gameMode.DefenderMode;
import gameMode.DuelMode;
import player.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    private final Player player1, player2;
    protected int response;
    protected ConfigurationGame config;
    private final Scanner scan;

    public Home(Player player1, Player player2, ConfigurationGame config, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.config = config;
        this.scan = scan;
    }

    /**
     * Choose between the different game modes
     */
    public void menu() {
        config.getMsgInfo().chooseGameMode();
        do {
            try {
                response = scan.nextInt();
                if (relaunchGame())
                    return;
            } catch (InputMismatchException e) {
                config.getMsgError().noLetters();
            }
        } while (true);
    }

    /**
     * Relaunch a game mode
     */
    public boolean relaunchGame() {
        boolean correctResponse = true;
        switch (response) {
            case 1:
                new ChallengerMode(config).playWithTwoPlayers(player1, player2);
                break;
            case 2:
                new DefenderMode(config).playWithTwoPlayers(player1, player2);
                break;
            case 3:
                new DuelMode(config).playWithTwoPlayers(player1, player2);
                break;
            case 4:
                new BonusMode(config).playWithTwoPlayers(player1, player2);
                break;
            default:
                correctResponse = false;
                config.getMsgError().wasteMyTime();
        }
        if (correctResponse)
            playOneMore();
        return correctResponse;
}

    /**
     * choice between restarting the game, returning to the menu or quitting the game
     */
    public void playOneMore() {
        config.getMsgInfo().chooseEndGame();
        int playAgain;
        do {
            try {
                playAgain = scan.nextInt();
                switch (playAgain) {
                    case 1:
                        config.getMsgInfo().playSameGame();
                        relaunchGame();
                        return;
                    case 2:
                        config.getMsgInfo().backMenu();
                        menu();
                        return;
                    case 3:
                        config.getMsgInfo().stopGame();
                        return;
                    default:
                        config.getMsgError().wasteMyTime();
                }
            } catch (InputMismatchException e) {
                config.getMsgError().wasteMyTime();
                scan.nextLine();
            }
        } while (true);
    }
}