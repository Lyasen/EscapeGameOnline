package gameMode;

import domaine.properties.ConfigurationGame;
import gameHome.PlayAgain;
import player.HumanPlayer;
import player.IAPlayer;

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
    public void defender() {
        HumanPlayer humanPlay = new HumanPlayer(config, scan);
        IAPlayer IAplay = new IAPlayer(config, scan);
        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];

        int[] playerProposition = humanPlay.propositionPlayer();
        int counter = config.getMaxTries();
        System.out.println("\nIA has " + counter + " tries");

        int[] IAProposition = IAplay.random();
        scan.nextLine();
        System.out.println("\nIA's proposition : " + Arrays.toString(IAProposition));

        do {
            String[] index = humanPlay.clues();
            IAProposition = IAplay.dichotomousResearch(IAProposition, index, mini, maxi);
            System.out.println("\nNew IA's proposition : " + Arrays.toString(IAProposition));
            counter--;

            if (Arrays.equals(playerProposition, IAProposition)) {
                System.out.println("Ho ho ! IA has found the secret number ! Human is near extinction !");
            } else if (counter == 0) {
                System.out.println("No more tries ! Computer has lost !");
            } else {
                System.out.println("It stays " + counter + " tries for the AI");
            }
        } while (counter != 0 || Arrays.equals(playerProposition, IAProposition));

        new PlayAgain(config).playOneMore(scan);
    }
}