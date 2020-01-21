package gameMode;

import player.HumanPlayer;
import player.IAPlayer;
import player.Player;
import utils.IsWin;

import java.util.Arrays;

public class ChallengerMode extends Mode {

    /**
     * Player suggest combinations
     * IA give clues in order to help player finding the secret combination
     */
    public void challenge() {
        IAPlayer IAPlay = new IAPlayer(config);
        Player humanPlay = new HumanPlayer(config, scan);

        int counter = config.getMaxTries();
        int[] combinationAi = IAPlay.random();
        int[] combinationPlayer;
        do {
            combinationPlayer = humanPlay.propositionPlayer();
            String[] clew = IAPlay.compare_result(combinationPlayer, combinationAi);

            if (counter > 1) {
                System.out.println("\rThe clues are  : " + Arrays.toString(clew));
                counter--;
            } else if (counter == 1) {
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
        } while (counter > 0 || combinationAi == combinationPlayer);
    }
}
