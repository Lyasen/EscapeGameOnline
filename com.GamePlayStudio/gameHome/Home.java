package gameHome;

import domaine.properties.ConfigurationGame;
import gameMessage.MsgError;
import gameMessage.MsgInfo;
import gameMode.BonusMode;
import gameMode.ChallengerMode;
import gameMode.DefenderMode;
import gameMode.DuelMode;
import player.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home implements MsgError, MsgInfo {
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
        chooseGameMode();
        do {
            try {
                response = scan.nextInt();
                if (relaunchGame())
                    return;
            } catch (InputMismatchException e) {
                noLetters();
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
                wasteMyTime();
        }
        if (correctResponse)
            playOneMore();
        return correctResponse;
}

    /**
     * choice between restarting the game, returning to the menu or quitting the game
     */
    public void playOneMore() {
        chooseEndGame();
        int playAgain;
        do {
            try {
                playAgain = scan.nextInt();
                switch (playAgain) {
                    case 1:
                        playSameGame();
                        relaunchGame();
                        return;
                    case 2:
                        backMenu();
                        menu();
                        return;
                    case 3:
                        stopGame();
                        return;
                    default:
                        wasteMyTime();
                }
            } catch (InputMismatchException e) {
                wasteMyTime();
                scan.nextLine();
            }
        } while (true);
    }
}