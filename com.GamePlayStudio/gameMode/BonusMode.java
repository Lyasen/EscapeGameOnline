package gameMode;

import domaine.properties.ConfigurationGame;
import gameHome.PlayAgain;
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
    public void playWithTwoPlayers(Player player_1, Player player_2) {
        System.out.println("You have choice the game mode : Bonus mode\nChallenge the computer");
        player_1 = new HumanPlayer(config, scan);
        player_2 = new IAPlayer(config);
        int counter = config.getMaxTries();

        System.out.println("Now, computer and human deliver a real fight !\nYou'll have " + config.getMaxTries() + " tries");
        int[] hazard = IAPlayer.random();
        int[] combinationPlayer = new int[config.getDigitsCombination()];
        int[] combinationIA;
        String[] clew = new String[config.getDigitsCombination()];
        do {
            /*
             * Player's proposition
             */
            combinationPlayer = player_1.research(combinationPlayer, clew);
            clew = player_2.clues(hazard, combinationPlayer);
            if (counter >= 1)
                System.out.println("The clues are  : " + Arrays.toString(clew));

            if (IsWin.winIf(clew)) {
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }

            /*
             * AI's proposition
             */
            combinationIA = player_2.research(combinationPlayer, clew);
            System.out.println("\nIA's proposition : " + Arrays.toString(combinationIA));
            clew = player_2.clues(hazard, combinationIA);
            if (counter >= 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(clew));
            counter--;
            System.out.printf("It stays %d tries ", counter);

            if (IsWin.winIf(clew)) {
                System.out.println("\nWell done ! IA wins !");
                break;
            } else if (counter == 0){
                System.out.println("\nSorry, no more tries ! The secret number was : "
                        + Arrays.toString(hazard) + " ! Try next time !");
                break;
            }
        } while (counter > 0 || Arrays.equals(hazard, combinationPlayer) || Arrays.equals(hazard, combinationIA));

        new PlayAgain(config).playOneMore(scan, player_1, player_2);
    }
}
