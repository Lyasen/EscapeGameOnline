package gameMode;

import domaine.properties.ConfigurationGame;
import player.HumanPlayer;
import player.IAPlayer;
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
    public void playWithTwoPlayers(Player player_1, Player player_2) {
        System.out.println("You have choice the game mode : Challenger\nTry to find the secret number !");
        player_1 = new HumanPlayer(config, scan);
        player_2 = new IAPlayer(config);

        int counter = config.getMaxTries();
        int[] IACombination = IAPlayer.random();
        int[] combinationPlayer = new int[config.getDigitsCombination()];
        String[] clew = new String[config.getDigitsCombination()];

        do {
            combinationPlayer = player_1.research(combinationPlayer, clew);
            clew = player_2.clues(IACombination, combinationPlayer);

            if (counter > 1) {
                System.out.println("\rThe clues are  : " + Arrays.toString(clew));
                counter--;
            } else if (counter == 1) {
                System.out.print("It was the last time to find the secret number, Soooo...");
                counter--;
            }

            if (IsWin.winIf(clew)) {
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
