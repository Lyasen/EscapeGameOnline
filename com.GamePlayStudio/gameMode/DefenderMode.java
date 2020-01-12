package gameMode;

import HandlingException.NumberOfCluesException;
import HandlingException.StyleOfCluesException;
import domaine.properties.ConfigurationGame;
import player.PropositionPlayer;
import utils.GiveClues;
import utils.RandomNumber;

import java.util.Arrays;
import java.util.Scanner;

public class DefenderMode {
    /**
     * Setting up the defender mode
     */
    public void defender(Scanner scan, ConfigurationGame config) throws StyleOfCluesException, NumberOfCluesException {
        PropositionPlayer combinationPlayer = new PropositionPlayer();
        GiveClues clues = new GiveClues();
        combinationPlayer.propositionPlayer(scan,config);
        int counter = config.getMaxTries();

        RandomNumber combinationAi = new RandomNumber();
        System.out.println("\nThe AI's proposition is : " + Arrays.toString(combinationAi.randomSecret(config)));



//        do {
//            try {
                clues.giveClues(scan, config);
//                int[] dicho = DichotomousSearch.dichoSearch(turnComputer, clues, config.getMinValue(),
//                        config.getMaxValue());
//                counter--;
//                System.out.println("\nNew AI's proposition : " + Arrays.toString(dicho));
//                if (Arrays.equals(combinationPlayer, dicho)) {
//                    System.out.println("Ho ho ! AI has found the secret number ! Human is near extinction !");
//                    break;
//                } else if (counter == 0) {
//                    System.out.println("No more tries ! Computer has lost !");
//                    break;
//                } else {
//                    System.out.println("It stays " + counter + " tries for the AI");
//                }
//            } catch (NumberOfCluesException | StyleOfCluesException e) {
//                e.getMessage();
//            }
//        } while (true);
    }
}
