package gameMode;

import domaine.properties.ConfigurationGame;
import player.PropositionPlayer;
import utils.*;

import java.util.Arrays;
import java.util.Scanner;

public class DuelMode {
    /**
     * Player have a secret number and try to find the AI's secret combination
     * IA have a secret number too and try to find the player's secret combination. No really ! He does all the same, it's really a copier !
     * @param scan : input data
     * @param config: settings to play
     */
    public void duel(Scanner scan, ConfigurationGame config) {
        PropositionPlayer playerPlay = new PropositionPlayer();
        RandomNumber random = new RandomNumber();
        DichotomousSearch IAPlay = new DichotomousSearch();
        CompareResult compare = new CompareResult();
        GiveClues clues = new GiveClues();

        int[] combinationPlayer = new int[config.getDigitsCombination()], dichotomousCombinationIA = new int[config.getDigitsCombination()];
        String[] comparePlayerIA = new String[config.getDigitsCombination()];
        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];
        int counter = config.getMaxTries();

        System.out.println("Now, let's fight ! " +
                "You'll have " + config.getMaxTries() + " tries");
        int[] IANumber = random.randomSecret(config);
        System.out.println("\nHere's the secret number that the IA must find :");
        int[] playerNumber = playerPlay.propositionPlayer(scan, config);
        int[] IARandomProposition = random.randomSecret(config);
        scan.nextLine();
        System.out.println("\nHere's Ai's proposition to find your secret combination : " + Arrays.toString(IARandomProposition) +
                "\nBut before giving clues, the human player needs concentration ! Sooooo that's...");
        String[] index = clues.giveClues(scan, config);
        int[] combinationIA = IAPlay.IASearch(IARandomProposition, index, mini, maxi);
        System.out.println("AI's proposition : " + Arrays.toString(combinationIA));
        do {
            /*
             * Player's proposition
             */
            System.out.println("Player's turn : try to find the IA secret number !" +
                    "\nI remember you have chosen the following last combination : " + Arrays.toString(combinationPlayer) +
                    "\nand the last clues from the IA was : " + Arrays.toString(comparePlayerIA));
            combinationPlayer = playerPlay.propositionPlayer(scan, config);
            comparePlayerIA = compare.compareDigits(combinationPlayer, IANumber, config);
            if (counter > 1) {
                System.out.println("The clues are  : " + Arrays.toString(comparePlayerIA));
                scan.nextLine();
            }

            if (IsWin.winIf(comparePlayerIA)) {
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }

            /*
             * AI's proposition
             */
            System.out.println("\nSee the clues for our nice IA !" +
                    "\nI remember you have chosen the following combination : " + Arrays.toString(playerNumber) +
                    "\nHere's the last combination from the IA : " + Arrays.toString(combinationIA));
            index = clues.giveClues(scan, config);
            dichotomousCombinationIA = IAPlay.IASearch(combinationIA, index, mini, maxi);
            System.out.println("AI's proposition : " + Arrays.toString(dichotomousCombinationIA));

            if (IsWin.winIf(index)) {
                System.out.println("\nWell done ! AI win !");
                break;
            }
            counter--;
            System.out.printf("\nIt stays %d tries\r\n\r\n", counter);
        } while (counter > 0 || Arrays.equals(combinationPlayer, IANumber) || Arrays.equals(combinationIA, playerNumber));

        if (counter == 0) {
            System.out.println("\nSorry, no more tries ! The secret number was : "
                    + Arrays.toString(combinationPlayer) + " ! Try next time !");
        }
    }
}
