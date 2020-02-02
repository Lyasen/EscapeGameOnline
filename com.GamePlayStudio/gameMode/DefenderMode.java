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
    public void playWithTwoPlayers(Player defense, Player attack) {
        System.out.println("You have choice the game mode : Defender\nWill the computer regain your secret combination ?");
        defense = new HumanPlayer(config, scan);
        attack = new IAPlayer(config);
        int[] combinationPlayer;
        String[] clues = new String[config.getDigitsCombination()];

        combinationPlayer = defense.research(clues);
        int counter = config.getMaxTries();
        System.out.println("\nIA has " + counter + " tries");

        IAPlayer ia = new IAPlayer(config);
        int[] IAProposition = ia.random();
        scan.nextLine();
        System.out.println("\nIA's proposition : " + Arrays.toString(IAProposition));

        do {
            clues = defense.clues(combinationPlayer);
            IAProposition = attack.research(clues);
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