package gameMode;

import domaine.properties.ConfigurationGame;
import player.PropositionPlayer;
import utils.DichotomousSearch;
import utils.GiveClues;
import utils.RandomNumber;

import java.util.Arrays;
import java.util.Scanner;

public class DefenderMode {
    /**
     * Setting up the defender mode
     */
    public void defender(Scanner scan, ConfigurationGame config) {
        PropositionPlayer combinationPlayer = new PropositionPlayer();
        RandomNumber combinationAi = new RandomNumber();
        GiveClues clues = new GiveClues();
        DichotomousSearch search = new DichotomousSearch();
        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];

        int[] playerProposition = combinationPlayer.propositionPlayer(scan, config);
        int counter = config.getMaxTries();
        System.out.println("\nAi have " + counter + " tries");

        int[] aiRandomProposition = combinationAi.randomSecret(config);
        scan.nextLine();
        System.out.println("\nAi's proposition : " + Arrays.toString(aiRandomProposition));

        int[] searching;
        do {
            String[] index = clues.giveClues(scan, config);
            searching = search.AiSearch(aiRandomProposition, index, mini, maxi);
            System.out.println("\nNew AI's proposition : " + Arrays.toString(searching));
            counter--;

            if (Arrays.equals(playerProposition, searching)) {
                System.out.println("Ho ho ! AI has found the secret number ! Human is near extinction !");
            } else if (counter == 0) {
                System.out.println("No more tries ! Computer has lost !");
            } else {
                System.out.println("It stays " + counter + " tries for the AI");
            }
        } while (counter != 0 || Arrays.equals(playerProposition, searching));
    }
}