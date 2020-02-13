package gameMode;

import domaine.properties.ConfigurationGame;
import player.IAPlayer;
import player.Player;
import utils.IsWin;

import java.util.Arrays;
import java.util.Scanner;

public class BonusMode extends Mode {
    public BonusMode(ConfigurationGame config, Scanner scan) {
        super(config, scan);
    }

    /**
     * A special mode where player and IA try to find the secret combination of the computer
     */
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        int[] combinationPlayer2;
        int counter = config.getMaxTries();

        System.out.println("You have choice the game mode : Bonus mode\nChallenge the computer\nNow, computer and human deliver a real fight !\nYou'll have " +
                counter + " tries\n");
        //  initialize a random number that both players will try to find
        IAPlayer ia = new IAPlayer(config);
        int[] defenseCombination = ia.initialiseCombination();
        if (config.isDevMode())
            System.out.println("DevMode: " + Arrays.toString(defenseCombination));


        //  Proposition player1
        System.out.println("Proposition Player1");
        int[] combinationPlayer1 = player1.initialiseCombination();
        System.out.println("Your answer is " + Arrays.toString(combinationPlayer1));
        String[] clues = ia.clues(combinationPlayer1);
        if (IsWin.winIf(clues)) {
            System.out.println("Weeell done Player1 ! You're theeee Mastermind !");
        }
        System.out.println("\rThe clues are  : " + Arrays.toString(clues));

        do {
            //  Proposition player2
            System.out.println("\nProposition Player2");
            combinationPlayer2 = player2.research(clues);
            System.out.println("Your answer is " + Arrays.toString(combinationPlayer2));
            clues = ia.clues(combinationPlayer2);
            if (IsWin.winIf(clues)) {
                System.out.println("Weeell done Player2 ! You're theeee Mastermind !");
                break;
            }
            System.out.println("\rThe clues are  : " + Arrays.toString(clues));

            //  Proposition player1
            System.out.println("\nProposition Player1");
            combinationPlayer1 = player1.research(clues);
            System.out.println("Your answer is " + Arrays.toString(combinationPlayer1));
            clues = ia.clues(combinationPlayer1);
            counter--;
            if (IsWin.winIf(clues)) {
                System.out.println("Weeell done Player1 ! You're theeee Mastermind !");
                break;
            } else if (counter > 1) {
                System.out.println("\rThe clues are  : " + Arrays.toString(clues));
            } else if (counter == 1) {
                System.out.print("It was the last time to find the secret number, Soooo...");
            } else {
                System.out.printf("There are %s tries left\n", counter);
            }
        } while (counter > 0 || Arrays.equals(defenseCombination, combinationPlayer1) || Arrays.equals(defenseCombination, combinationPlayer2));
    }
}