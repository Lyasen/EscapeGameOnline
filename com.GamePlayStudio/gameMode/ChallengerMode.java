package gameMode;

import domaine.properties.ConfigurationGame;
import player.IAPlayer;
import player.Player;
import utils.IsWin;

import java.util.Arrays;
import java.util.Scanner;

public class ChallengerMode extends Mode {
    protected final Scanner scan;

    public ChallengerMode(ConfigurationGame config, Scanner scan) {
        super(config);
        this.scan = scan;
    }

    /**
     * Player suggest combinations
     * IA give clues in order to help player finding the secret combination
     */
    @Override
    public void playWithTwoPlayers(Player attack, Player defense) {
        System.out.println("You have choice the game mode : Challenger\nTry to find the secret number !");

        int counter = config.getMaxTries();
        int[] IACombination = IAPlayer.random;
        int[] combinationPlayer;
        String[] clues = new String[config.getDigitsCombination()];

        do {
            combinationPlayer = attack.research(clues);
            clues = defense.clues(combinationPlayer);

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
                        + Arrays.toString(IACombination) + " ! Try next time !");
            } else {
                System.out.printf("There are %s tries left ", counter);
            }
        } while (counter > 0 || IACombination == combinationPlayer);
    }
}