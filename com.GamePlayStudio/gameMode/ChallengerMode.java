package gameMode;

import domaine.properties.ConfigurationGame;
import player.Player;
import utils.IsWin;

import java.util.Arrays;
import java.util.Scanner;

public class ChallengerMode extends Mode {
    public ChallengerMode(ConfigurationGame config, Scanner scan) {
        super(config, scan);
    }

    /**
     * Player suggest combinations
     * IA give clues in order to help player finding the secret combination
     */
    @Override
    public void playWithTwoPlayers(Player attack, Player defense) {
        System.out.println("You have choice the game mode : Challenger\nTry to find the secret number !");
        int counter = config.getMaxTries();
        int[] attackCombination;
        int[] defenseCombination = defense.initialiseCombination();
        if (config.isDevMode())
            System.out.println("DevMode: " + Arrays.toString(defenseCombination));

        do {
            attackCombination = attack.initialiseCombination();
            System.out.println("Your answer is " + Arrays.toString(attackCombination));
            String[] clues = defense.clues(attackCombination);

            if (counter > 1) {
                System.out.println("\rThe clues are  : " + Arrays.toString(clues));
                counter--;
            } else if (counter == 1) {
                System.out.print("It was the last time to find the secret number, Soooo...");
                counter--;
            }

            if (IsWin.winIf(clues)) {
                System.out.println("Weeell done ! You're theeee Mastermind !");
                break;
            } else if (counter == 0) {
                System.out.println("What a pity ! It's lost ! The secret number was : "
                        + Arrays.toString(defenseCombination) + " ! Try next time !");
            } else {
                System.out.printf("There are %s tries left ", counter);
            }
        } while (counter > 0 || defenseCombination == attackCombination);
    }
}