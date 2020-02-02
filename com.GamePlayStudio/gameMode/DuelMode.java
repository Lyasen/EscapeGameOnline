package gameMode;

import domaine.properties.ConfigurationGame;
import player.HumanPlayer;
import player.IAPlayer;
import player.Player;
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
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        System.out.println("You have choice the game mode : Duel\nWho will be faster to find each other’s secret combination ?");
        player1 = new HumanPlayer(config, scan);
        player2 = new IAPlayer(config);

        int[] combinationPlayer = new int[config.getDigitsCombination()];
        int[] playerNumberToFind;
        String[] clues = new String[config.getDigitsCombination()];
        String[] comparePlayerIA = new String[config.getDigitsCombination()];

        int counter = config.getMaxTries();
        System.out.println("Now, let's fight ! You'll have " + config.getMaxTries() + " tries");

        //  random number generated to be found by player 1
        IAPlayer ia = new IAPlayer(config);
        int[] IANumberToFind = ia.random();

        System.out.print("\nHere's the secret number that the IA must find ! ");
        playerNumberToFind = player1.research(clues);
        //  First random proposition to begin with
        IAPlayer ib = new IAPlayer(config);
        int[] IARandomProposition = ib.random();
        scan.nextLine();
        System.out.println("Here's IA's proposition to find your secret combination : " + Arrays.toString(IARandomProposition));

        clues = player1.clues(IARandomProposition);
        int[] combinationIA = player2.research(clues);
        System.out.println("IA's proposition : " + Arrays.toString(combinationIA));
        System.out.println("\nThe human player needs concentration ! Sooooo that's...");

        do {
            /*
             * Player's proposition
             */
            System.out.println("Player's turn : try to find the IA secret number !" +
                    "\nI remember you have chosen the following last combination : " + Arrays.toString(combinationPlayer) +
                    "\nand the last clues from the IA was : " + Arrays.toString(comparePlayerIA));
            combinationPlayer = player1.research(clues);
            comparePlayerIA = player2.clues(combinationPlayer);
            if (counter >= 1) {
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
            System.out.println("\nSee the clues for our nice IA !\nI remember you have chosen the following combination : " + Arrays.toString(playerNumberToFind) +
                    "\nHere's the last combination from the IA : " + Arrays.toString(combinationIA));
            clues = player1.clues(combinationIA);
            combinationIA = player2.research(clues);
            System.out.println("IA's proposition : " + Arrays.toString(combinationIA));

            if (IsWin.winIf(clues)) {
                System.out.println("\nWell done ! IA wins !");
                break;
            }
            counter--;
            System.out.printf("\nIt stays %d tries\r\n\r\n", counter);
        } while (counter > 0 || Arrays.equals(combinationPlayer, IANumberToFind) || Arrays.equals(combinationIA, playerNumberToFind));

        if (counter == 0 && !Arrays.equals(combinationPlayer, IANumberToFind) && !Arrays.equals(combinationIA, playerNumberToFind))
            System.out.println("\nSorry, no more tries ! IA and Player don't found the each other combination ! Try next time !");
    }
}
