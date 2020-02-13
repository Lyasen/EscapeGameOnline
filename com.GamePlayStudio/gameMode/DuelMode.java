package gameMode;

import domaine.properties.ConfigurationGame;
import player.Player;
import utils.IsWin;

import java.util.Arrays;
import java.util.Scanner;

public class DuelMode extends Mode {public DuelMode(ConfigurationGame config, Scanner scan) {
        super(config, scan);
    }

    /**
     * Both player have a secret number and try to find the each other's secret combination
     */
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        System.out.println("You have choice the game mode : Duel\nWho will be faster to find each other’s secret combination ?");
        int counter = config.getMaxTries();
        System.out.println("Now, let's fight ! You'll have " + counter + " tries");

        //  Secret combination from player1
        System.out.println("\nSecret combination player1");
        int[] secretCombinationPlayer1 = player1.initialiseCombination();
        if (config.isDevMode())
            System.out.println("DevMode: " + Arrays.toString(secretCombinationPlayer1));
        int[] propositionPlayer2 = player2.initialiseCombination();
        System.out.println("Proposition from player2 : " + Arrays.toString(propositionPlayer2));
        scan.nextLine();
        String[] answerPlayer1 = player1.clues(propositionPlayer2);
        System.out.println("Clues from player1 to player2 : " + Arrays.toString(answerPlayer1));
        if (IsWin.winIf(answerPlayer1)) {
            System.out.println("\nWell done ! Player2 WIN !");
        }

        //  Secret random combination from player2
        System.out.println("\nSecret combination player2");
        int[] secretCombinationPlayer2 = player2.initialiseCombination();
        if (config.isDevMode())
            System.out.println("DevMode: " + Arrays.toString(secretCombinationPlayer2));
        int[] propositionPlayer1 = player1.initialiseCombination();
        System.out.println("Proposition from player1 " + Arrays.toString(propositionPlayer1));
        String[] answerPlayer2 = player2.clues(propositionPlayer1);
        System.out.println("Clues from player2 to player1 : " + Arrays.toString(answerPlayer2));
        if (IsWin.winIf(answerPlayer2)) {
            System.out.println("\nWell done ! Player1 WIN !");
        }
        counter--;
        System.out.printf("\nIt stays %d tries\n", counter);

        do {
            /*
             * Player2's proposition
             */
            System.out.println("\nPlayer2's turn !\nI remember you have chosen the following last combination : " + Arrays.toString(propositionPlayer2) +
                    "\nand the last clues was : " + Arrays.toString(answerPlayer1));
            propositionPlayer2 = player2.research(answerPlayer1);
            scan.nextLine();
            System.out.println("Your answer is " + Arrays.toString(propositionPlayer2));
            answerPlayer1 = player1.clues(propositionPlayer2);
            if (counter >= 1) {
                System.out.println("The clues are  : " + Arrays.toString(answerPlayer1));
            }

            if (IsWin.winIf(answerPlayer1)) {
                System.out.println("\nWell done ! Player2 WIN !");
                break;
            }

            /*
             * Player1's proposition
             */
            System.out.println("\nPlayer1's turn !\nI remember you have chosen the following last combination : " + Arrays.toString(propositionPlayer1) +
                    "\nand the last clues was : " + Arrays.toString(answerPlayer2));
            propositionPlayer1 = player1.research(answerPlayer2);
            scan.nextLine();
            System.out.println("Your answer is " + Arrays.toString(propositionPlayer1));
            answerPlayer2 = player2.clues(propositionPlayer1);
            if (counter >= 1) {
                System.out.println("The clues are  : " + Arrays.toString(answerPlayer2));
            }

            if (IsWin.winIf(answerPlayer2)) {
                System.out.println("\nWell done ! Player1 WIN !");
                break;
            }
            counter--;
            System.out.printf("It stays %d tries\n", counter);

            if (counter == 0 && !Arrays.equals(secretCombinationPlayer1, propositionPlayer2) && !Arrays.equals(propositionPlayer1, secretCombinationPlayer1))
                System.out.println("\nSorry, no more tries ! IA and Player don't found the each other combination ! Try next time !");
        } while (counter > 0 || Arrays.equals(secretCombinationPlayer1, propositionPlayer2) || Arrays.equals(propositionPlayer1, secretCombinationPlayer2));
    }
}
