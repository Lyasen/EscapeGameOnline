package gameMode;

import domaine.properties.ConfigurationGame;
import player.HumanPlayer;
import player.IAPlayer;
import player.Player;
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
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        System.out.println("You have choice the game mode : Bonus mode\nChallenge the computer");
        player1 = new HumanPlayer(config, scan);
        player2 = new IAPlayer(config);
        int counter = config.getMaxTries();

        System.out.println("Now, computer and human deliver a real fight !\nYou'll have " + config.getMaxTries() + " tries");
        IAPlayer ia = new IAPlayer(config);
        int[] hazard = ia.random();
        int[] combinationPlayer;
        int[] combinationIA;
        String[] clues = new String[config.getDigitsCombination()];
        do {
            /*
             * Player's proposition
             */
            combinationPlayer = player1.research(clues);
            clues = player2.clues(combinationPlayer);
            if (counter >= 1)
                System.out.println("The clues are  : " + Arrays.toString(clues));

            if (IsWin.winIf(clues)) {
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }

            /*
             * AI's proposition
             */
            combinationIA = player2.research(clues);
            System.out.println("\nIA's proposition : " + Arrays.toString(combinationIA));
            clues = player2.clues(combinationIA);
            if (counter >= 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(clues));
            counter--;
            System.out.printf("It stays %d tries ", counter);

            if (IsWin.winIf(clues)) {
                System.out.println("\nWell done ! IA wins !");
                break;
            } else if (counter == 0){
                System.out.println("\nSorry, no more tries ! The secret number was : "
                        + Arrays.toString(hazard) + " ! Try next time !");
                break;
            }
        } while (counter > 0 || Arrays.equals(hazard, combinationPlayer) || Arrays.equals(hazard, combinationIA));
    }
}
