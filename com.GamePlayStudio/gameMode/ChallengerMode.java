package gameMode;

import domaine.properties.ConfigurationGame;
import player.HumanPlayer;
import player.IAPlayer;
import utils.IsWin;

import java.util.Arrays;
import java.util.Scanner;

public class ChallengerMode {
    /**
     * Player suggest combinations
     * IA give clues in order to help player finding the secret combination
     */
    public void challenge(ConfigurationGame config, Scanner scan) {
        IAPlayer IAPlay = new IAPlayer(config, scan);
        HumanPlayer humanPlay = new HumanPlayer(config, scan);

        int counter = config.getMaxTries();
        int[] combinationAi = IAPlay.randomResearch();
        int[] combinationPlayer;
        do {
            combinationPlayer = humanPlay.research();
            String[] clew = IAPlay.compareResult(combinationPlayer, combinationAi);

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
                        + Arrays.toString(combinationAi) + " ! Try next time !");
            } else {
                System.out.printf("There are %s tries left", counter);
            }
        } while (counter > 0 || combinationAi == combinationPlayer);
    }
}
