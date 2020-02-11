package gameMode;

import domaine.properties.ConfigurationGame;
import player.Player;

import java.util.Arrays;
import java.util.Scanner;

public class DefenderMode extends Mode {
    private String[] clues = new String[config.getDigitsCombination()];

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
        int counter = config.getMaxTries();
        System.out.println("\nIA has " + counter + " tries");
        int[] combinationPlayer = defense.research(clues);
        System.out.println("Your answer is " + Arrays.toString(combinationPlayer));

        int[] IACombination = attack.initialiseCombination();
        System.out.println("\nIA's proposition : " + Arrays.toString(IACombination));
        scan.nextLine();

        do {
            String[] fink = defense.clues(IACombination);
            IACombination = attack.research(fink);
            System.out.println("\nNew IA's proposition : " + Arrays.toString(IACombination));
            counter--;

            if (Arrays.equals(IACombination, combinationPlayer)) {
                System.out.println("Ho ho ! IA has found the secret number ! Human is near extinction !");
                break;
            } else if (counter == 0) {
                System.out.println("No more tries ! Computer has lost !");
            } else {
                System.out.println("It stays " + counter + " tries for the AI");
            }
        } while (counter != 0 || Arrays.equals(IACombination, combinationPlayer));
    }
}