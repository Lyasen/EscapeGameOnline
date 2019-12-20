package com.ocr.GamePlay_Studio.GameMode;

import com.ocr.GamePlay_Studio.Domain.Config;
import com.ocr.GamePlay_Studio.GameHome.PlayAgain;
import com.ocr.GamePlay_Studio.OtherNumbersMethod.*;

import java.util.Arrays;

import static com.ocr.GamePlay_Studio.OtherNumbersMethod.ActionPlayer.propositionPlayer;
import static com.ocr.GamePlay_Studio.OtherNumbersMethod.RandomS.randomS;

public class ChallengerMode {
    /**
     * Setting up the challenger mode
     */
    public static void challenger() {
        int counter = Config.getMaxTries();
        int[] combination = randomS();
        System.out.printf("Now ! You have %s tries\n", counter);

        do {
            int[] combinationPlayer = propositionPlayer();
            String[] comparison = CompareResult.compare(combinationPlayer, combination);

            if (counter > 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(comparison));
            counter--;

            if (IsWin.winIf(comparison)) {
                System.out.println("Well done ! You're the mastermind !");
                break;
            } else if (counter == 0) {
                System.out.println("Sorry, you have used your tries ! The secret number was : " + Arrays.toString(combination) + " ! Try next time !");
                break;
            } else {
                System.out.printf("There are %s tries left", counter);
            }
        } while (true);

        PlayAgain.Play();
    }
}
