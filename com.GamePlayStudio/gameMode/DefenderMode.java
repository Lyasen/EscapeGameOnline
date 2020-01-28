package gameMode;

import domaine.properties.ConfigurationGame;
import player.HumanPlayer;
import player.IAPlayer;
import player.Player;

import java.util.Arrays;
import java.util.Scanner;

public class DefenderMode extends Mode {

    public DefenderMode(ConfigurationGame config, Scanner scan) {
        super(config, scan);
    }

    /**
     * Player suggest a secret combination
     * IA try to find the secret number
     */
    @Override
    public void playWithTwoPlayers(Player player_1, Player player_2) {
        player_1 = new HumanPlayer(config, scan);
        player_2 = new IAPlayer(config);
        int[] combinationPlayer = new int[config.getDigitsCombination()];
        String[] clew = new String[config.getDigitsCombination()];

        combinationPlayer = player_1.research(combinationPlayer, clew);
        int counter = config.getMaxTries();
        System.out.println("\nIA has " + counter + " tries");

        int[] IAProposition = IAPlayer.random();
        scan.nextLine();
        System.out.println("\nIA's proposition : " + Arrays.toString(IAProposition));

        do {
            clew = player_1.clues(IAProposition, combinationPlayer);
            IAProposition = player_2.research(IAProposition, clew);
            System.out.println("\nNew IA's proposition : " + Arrays.toString(IAProposition));
            counter--;

            if (Arrays.equals(IAProposition, combinationPlayer)) {
                System.out.println("Ho ho ! IA has found the secret number ! Human is near extinction !");
            } else if (counter == 0) {
                System.out.println("No more tries ! Computer has lost !");
            } else {
                System.out.println("It stays " + counter + " tries for the AI");
            }
        } while (counter != 0 || Arrays.equals(IAProposition, combinationPlayer));
    }
}