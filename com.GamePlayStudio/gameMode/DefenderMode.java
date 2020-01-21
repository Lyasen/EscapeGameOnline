package gameMode;

import player.HumanPlayer;
import player.IAPlayer;

import java.util.Arrays;

public class DefenderMode extends Mode{
    /**
     * Player suggest a secret combination
     * IA try to find the secret number
     */
    public void defender() {
        HumanPlayer humanPlay = new HumanPlayer(config, scan);
        IAPlayer IAplay = new IAPlayer(config);
        int[] mini = new int[config.getDigitsCombination()];
        int[] maxi = new int[config.getDigitsCombination()];

        int[] playerProposition = humanPlay.propositionPlayer();
        int counter = config.getMaxTries();
        System.out.println("\nAi have " + counter + " tries");

        int[] IARandomProposition = IAplay.random();
        scan.nextLine();
        System.out.println("\nAi's proposition : " + Arrays.toString(IARandomProposition));
        String[] index = humanPlay.clues();
        int[] searching = IAplay.dichotomousResearch(IARandomProposition, index, mini, maxi);
        System.out.println("\nNew AI's proposition : " + Arrays.toString(searching));
        counter--;

        do {
            index = humanPlay.clues();
            searching = IAplay.dichotomousResearch(searching, index, mini, maxi);
            System.out.println("\nNew AI's proposition : " + Arrays.toString(searching));
            counter--;

            if (Arrays.equals(playerProposition, searching)) {
                System.out.println("Ho ho ! AI has found the secret number ! Human is near extinction !");
            } else if (counter == 0) {
                System.out.println("No more tries ! Computer has lost !");
            } else {
                System.out.println("It stays " + counter + " tries for the AI");
            }
        } while (counter != 0 || Arrays.equals(playerProposition, searching));
    }
}