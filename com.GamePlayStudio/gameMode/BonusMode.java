package gameMode;

import player.HumanPlayer;
import player.IAPlayer;
import utils.IsWin;

import java.util.Arrays;

public class BonusMode extends Mode {
    /**
     * A special mode where player and IA try to find the secret combination of the computer
     */
    public void bonus() {
        HumanPlayer humanPlay = new HumanPlayer(config, scan);
        IAPlayer IAplay = new IAPlayer(config);
        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];
        int counter = config.getMaxTries();

        System.out.println("Now, computer and human deliver a real fight !" +
                "\nYou'll have " + config.getMaxTries() + " tries");
        int[] hazard = IAplay.random();
        int[] combinationPlayer;
        int[] combinationAi;
        do {
            /*
             * Player's proposition
             */
            combinationPlayer = humanPlay.propositionPlayer();
            String[] compareCombinations = IAplay.compare_result(combinationPlayer, hazard);
            if (counter > 1)
                System.out.println("The clues are  : " + Arrays.toString(compareCombinations));

            if (IsWin.winIf(compareCombinations)) {
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }

            /*
             * AI's proposition
             */
            combinationAi = IAplay.dichotomousResearch(combinationPlayer, compareCombinations, mini, maxi);
            System.out.println("\n\nAI's proposition : " + Arrays.toString(combinationAi));
            compareCombinations = IAplay.compare_result(combinationPlayer, hazard);
            if (counter > 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(compareCombinations));
            counter--;
            System.out.printf("It stays %d tries", counter);

            if (IsWin.winIf(compareCombinations)) {
                System.out.println("\nWell done ! AI win !");
                break;
            }

            if (counter < 0) {
                System.out.println("\nSorry, no more tries ! The secret number was : "
                        + Arrays.toString(combinationPlayer) + " ! Try next time !");
                break;
            }
        } while (counter > 0 || Arrays.equals(combinationPlayer, hazard) || Arrays.equals(combinationAi, hazard));
    }
}
