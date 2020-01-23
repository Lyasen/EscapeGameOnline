package gameMode;

import domaine.properties.ConfigurationGame;
import gameHome.PlayAgain;
import player.HumanPlayer;
import player.IAPlayer;
import utils.IsWin;

import java.util.Arrays;
import java.util.Scanner;

public class DuelMode extends Mode {
    public DuelMode(ConfigurationGame config, Scanner scan) {
        super(config, scan);
    }

    /**
     * Player have a secret number and try to find the IA's secret combination
     * IA have a secret number too and try to find the player's secret combination. No really ! He does all the same, it's really a copier !
     */
    public void duel() {
        HumanPlayer humanPlay = new HumanPlayer(config, scan);
        IAPlayer IAPlay = new IAPlayer(config, scan);
        int[] combinationPlayer = new int[config.getDigitsCombination()];
        String[] comparePlayerIA = new String[config.getDigitsCombination()];
        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];
        int counter = config.getMaxTries();

        System.out.println("Now, let's fight ! " +
                "You'll have " + config.getMaxTries() + " tries");
        int[] IANumber = IAPlay.random();
        System.out.print("\nHere's the secret number that the IA must find ! ");
        int[] playerNumber = humanPlay.propositionPlayer();
        int[] IARandomProposition = IAPlay.random();
        scan.nextLine();
        System.out.println("Here's IA's proposition to find your secret combination : " + Arrays.toString(IARandomProposition));
        String[] index = humanPlay.clues();
        int[] combinationIA = IAPlay.dichotomousResearch(IARandomProposition,index, mini, maxi);
        System.out.println("IA's proposition : " + Arrays.toString(combinationIA));
        System.out.println("\nThe human player needs concentration ! Sooooo that's...");

        do {
            /*
             * Player's proposition
             */
            System.out.println("Player's turn : try to find the IA secret number !" +
                    "\nI remember you have chosen the following last combination : " + Arrays.toString(combinationPlayer) +
                    "\nand the last clues from the IA was : " + Arrays.toString(comparePlayerIA));
            combinationPlayer = humanPlay.propositionPlayer();
            comparePlayerIA = IAPlay.compare_result(combinationPlayer, IANumber);
            if (counter > 1) {
                System.out.println("The clues are  : " + Arrays.toString(comparePlayerIA));
                scan.nextLine();
            }

            if (IsWin.winIf(comparePlayerIA)) {
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }

            /*
             * IA's proposition
             */
            System.out.println("\nSee the clues for our nice IA !\nI remember you have chosen the following combination : " + Arrays.toString(playerNumber) +
                    "\nHere's the last combination from the IA : " + Arrays.toString(combinationIA));
            scan.nextLine();
            index = humanPlay.clues();
            combinationIA = IAPlay.dichotomousResearch(combinationIA, index, mini, maxi);
            System.out.println("IA's proposition : " + Arrays.toString(combinationIA));

            if (IsWin.winIf(index)) {
                System.out.println("\nWell done ! IA wins !");
                break;
            }
            counter--;
            System.out.printf("\nIt stays %d tries\r\n\r\n", counter);
        } while (counter > 0 || Arrays.equals(combinationPlayer, IANumber) || Arrays.equals(combinationIA, playerNumber));

        if (counter == 0 && !Arrays.equals(combinationPlayer, IANumber) && !Arrays.equals(combinationIA, playerNumber))
            System.out.println("\nSorry, no more tries ! IA and Player don't found the each other combination ! Try next time !");

        new PlayAgain(config).playOneMore(scan);
    }
}
