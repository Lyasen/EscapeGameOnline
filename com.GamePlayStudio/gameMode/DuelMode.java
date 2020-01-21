package gameMode;

import player.HumanPlayer;
import player.IAPlayer;
import utils.IsWin;

import java.util.Arrays;

public class DuelMode extends Mode {
    /**
     * Player have a secret number and try to find the AI's secret combination
     * IA have a secret number too and try to find the player's secret combination. No really ! He does all the same, it's really a copier !
     */
    public void duel() {
        HumanPlayer humanPlay = new HumanPlayer(config, scan);
        IAPlayer IAplay = new IAPlayer(config);
        int[] combinationPlayer = new int[config.getDigitsCombination()], dichotomousCombinationIA = new int[config.getDigitsCombination()];
        String[] comparePlayerIA = new String[config.getDigitsCombination()];
        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];
        int counter = config.getMaxTries();

        System.out.println("Now, let's fight ! " +
                "You'll have " + config.getMaxTries() + " tries");
        int[] IANumber = IAplay.random();
        System.out.print("\nHere's the secret number that the IA must find ! ");
        int[] playerNumber = humanPlay.propositionPlayer();
        int[] IARandomProposition = IAplay.random();
        scan.nextLine();
        System.out.println("Here's Ai's proposition to find your secret combination : " + Arrays.toString(IARandomProposition));
        String[] index = humanPlay.clues();
        int[] combinationIA = IAplay.dichotomousResearch(IARandomProposition,index, mini, maxi);
        System.out.println("AI's proposition : " + Arrays.toString(combinationIA));
        System.out.println("\nThe human player needs concentration ! Sooooo that's...");

        do {
            /*
             * Player's proposition
             */
            System.out.println("Player's turn : try to find the IA secret number !" +
                    "\nI remember you have chosen the following last combination : " + Arrays.toString(combinationPlayer) +
                    "\nand the last clues from the IA was : " + Arrays.toString(comparePlayerIA));
            combinationPlayer = humanPlay.propositionPlayer();
            comparePlayerIA = IAplay.compare_result(combinationPlayer, IANumber);
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
            index = humanPlay.clues();
            dichotomousCombinationIA = IAplay.dichotomousResearch(combinationIA, index, mini, maxi);
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
