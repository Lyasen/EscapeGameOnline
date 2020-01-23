package gameMode;

import domaine.properties.ConfigurationGame;
import gameHome.PlayAgain;
import player.HumanPlayer;
import player.IAPlayer;
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
    public void bonus() {
        HumanPlayer humanPlay = new HumanPlayer(config, scan);
        IAPlayer IAPlay = new IAPlayer(config, scan);
        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];
        int counter = config.getMaxTries();

        System.out.println("Now, computer and human deliver a real fight !\nYou'll have " + config.getMaxTries() + " tries");
        int[] hazard = IAPlay.random();
        int[] combinationPlayer;
        int[] combinationIA;
        do {
            /*
             * Player's proposition
             */
            combinationPlayer = humanPlay.propositionPlayer();
            String[] compareCombinations = IAPlay.compare_result(combinationPlayer, hazard);
            if (counter > 1)
                System.out.println("The clues are  : " + Arrays.toString(compareCombinations));

            if (IsWin.winIf(compareCombinations)) {
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }

            /*
             * AI's proposition
             */
            combinationIA = IAPlay.dichotomousResearch(hazard, compareCombinations, mini, maxi);
            System.out.println("\n\nIA's proposition : " + Arrays.toString(combinationIA));
            String[] secondCompareCombinations = IAPlay.compare_result(combinationPlayer, combinationIA);
            if (counter > 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(secondCompareCombinations));
            counter--;
            System.out.printf("It stays %d tries", counter);

            if (IsWin.winIf(secondCompareCombinations)) {
                System.out.println("\nWell done ! IA wins !");
                break;
            } else if (counter == 0){
                System.out.println("\nSorry, no more tries ! The secret number was : "
                        + Arrays.toString(combinationPlayer) + " ! Try next time !");
                break;
            }
        } while (counter > 0 || Arrays.equals(combinationPlayer, hazard) || Arrays.equals(combinationIA, hazard));

        new PlayAgain(config).playOneMore(scan);
        System.exit(0);
    }
}
