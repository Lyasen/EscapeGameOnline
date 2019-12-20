package com.ocr.GamePlay_Studio.GameMode;

import com.ocr.GamePlay_Studio.Domain.Config;
import com.ocr.GamePlay_Studio.GameHome.PlayAgain;
import com.ocr.GamePlay_Studio.OtherNumbersMethod.CompareResult;
import com.ocr.GamePlay_Studio.OtherNumbersMethod.IsWin;

import java.util.Arrays;

import static com.ocr.GamePlay_Studio.OtherNumbersMethod.ActionPlayer.propositionPlayer;
import static com.ocr.GamePlay_Studio.OtherNumbersMethod.DichotomousSearch.dichoSearch;
import static com.ocr.GamePlay_Studio.OtherNumbersMethod.RandomS.randomS;

public class DuelMode {
    /**
     * Setting up the duel mode
     */
    public static void duel() {
        int counter = Config.getMaxTries();

        int[] combination = randomS();
        System.out.println("Now, computer and human deliver a real fight !" +
                "\nYou'll have " + Config.getMaxTries() + " tries");

        do {
            /*
             * Player's proposition
             */
            int[] combinationPlayer = propositionPlayer();

            String[] comparePlayer = CompareResult.compare(combinationPlayer, combination);
            if (counter > 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(comparePlayer));

            if (IsWin.winIf(comparePlayer)){
                System.out.println("\nWell done ! HUMAN WIN !");
                break;
            }

            /*
             *  AI's proposition
             */
            int[] dicho = dichoSearch(combinationPlayer, comparePlayer, Config.getMinValue(), Config.getMaxValue());
            System.out.println("\nNew AI's proposition : " + Arrays.toString(dicho));
            String[] compareAi = CompareResult.compare(dicho, combination);
            if (counter > 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(compareAi));

            if (IsWin.winIf(compareAi)){
                System.out.println("\nWell done ! AI win !");
                break;
            }

            counter -= 2;

            if (counter == 0) {
                System.out.println("Sorry, no more tries ! The secret number was : " + Arrays.toString(combination) + " ! Try next time !");
                break;
            } else {
                System.out.printf("\nWell ! You'll have played one time each other, it stays %d tries", counter);
            }
        } while (true);

        PlayAgain.Play();
    }
}
