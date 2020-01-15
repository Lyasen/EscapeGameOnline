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
        do {
            /*
             * Player's proposition
             */
            int[] combinationPlayer = playerPlay.propositionPlayer(scan, config);
            String[] comparePlayer = compare.compareDigits(combinationPlayer, hazard, config);
            if (counter > 1)
                System.out.println("The clues are  : " + Arrays.toString(comparePlayer));

            if (IsWin.winIf(comparePlayer)) {
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }
            counter--;
            System.out.println("It stays " + counter + " tries");

            /*
             * AI's proposition
             */
            int[] combinationAi = aiPlay.AiSearch(combinationPlayer, comparePlayer, mini, maxi);
            System.out.println("\nAI's proposition : " + Arrays.toString(combinationAi));
            String[] compareAi = compare.compareDigits(hazard, combinationAi, config);
            if (counter > 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(compareAi));

            if (IsWin.winIf(compareAi)) {
                System.out.println("\nWell done ! AI win !");
                break;
            }

            if (counter == 0) {
                System.out.println("Sorry, no more tries ! The secret number was : "
                        + Arrays.toString(combinationPlayer) + " ! Try next time !");
                break;
            } else {
                System.out.printf("\nWell ! You'll have played one time each other, it stays %d tries", counter);
            }
        } while (true);
    }

}
