package gameMode;

import domaine.properties.ConfigurationGame;
import player.PropositionPlayer;
import utils.CompareResult;
import utils.DichotomousSearch;
import utils.IsWin;
import utils.RandomNumber;

import java.util.Arrays;
import java.util.Scanner;

public class DuelMode {
    /**
     * Setting up the duel mode
     */
    public void duel(Scanner scan, ConfigurationGame config) {
        RandomNumber random = new RandomNumber();
        CompareResult compare = new CompareResult();
        PropositionPlayer playerPlay = new PropositionPlayer();
        DichotomousSearch aiPlay = new DichotomousSearch();

        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];
        int counter = config.getMaxTries();

        System.out.println("Now, computer and human deliver a real fight !" +
                "\nYou'll have " + config.getMaxTries() + " tries");
        int[] hazard = random.randomSecret(config);
        int[] combinationPlayer;
        int[] combinationAi;
        do {
            /*
             * Player's proposition
             */
            combinationPlayer = playerPlay.propositionPlayer(scan, config);
            String[] compareCombinations = compare.compareDigits(combinationPlayer, hazard, config);
            if (counter > 1)
                System.out.println("The clues are  : " + Arrays.toString(compareCombinations));

            if (IsWin.winIf(compareCombinations)) {
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }
            counter--;
            System.out.printf("It stays %d tries", counter);

            /*
             * AI's proposition
             */
            combinationAi = aiPlay.AiSearch(combinationPlayer, compareCombinations, mini, maxi);
            System.out.println("\n\nAI's proposition : " + Arrays.toString(combinationAi));
            compareCombinations = compare.compareDigits(combinationPlayer, hazard, config);
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
