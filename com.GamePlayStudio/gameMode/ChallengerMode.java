package gameMode;

import domaine.properties.ConfigurationGame;
import player.PropositionPlayer;
import utils.CompareResult;
import utils.IsWin;
import utils.RandomNumber;

import java.util.Arrays;
import java.util.Scanner;

public class ChallengerMode {
    public void challenge(Scanner scan, ConfigurationGame config){
        RandomNumber random = new RandomNumber();
        PropositionPlayer play = new PropositionPlayer();
        CompareResult compare = new CompareResult();

        int counter = config.getMaxTries();
        int[] combinationAi = random.randomSecret(config);
        int[] combinationPlayer;
        do {
            combinationPlayer = play.propositionPlayer(scan, config);
            String[] clew =  compare.compareDigits(combinationPlayer, combinationAi, config);

            if (counter > 1) {
                System.out.println("\rThe clues are  : " + Arrays.toString(clew));
                counter--;
            } else if (counter == 1){
                System.out.print("It was the last time to find the secret number, Soooo...");
                counter--;
            }

            if (IsWin.winIf(clew)) {
                System.out.println("Weeell done ! You're theeee Mastermind !");
                break;
            } else if (counter == 0) {
                System.out.println("What a pity ! It's lost ! The secret number was : "
                        + Arrays.toString(combinationAi) + " ! Try next time !");
            } else {
                System.out.printf("There are %s tries left", counter);
            }
        } while(counter > 0 || combinationAi == combinationPlayer);
    }
}
