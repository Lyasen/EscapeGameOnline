package com.ocr.GamePlay_Studio.GameMode;

import com.ocr.GamePlay_Studio.Domain.Config;
import com.ocr.GamePlay_Studio.GameHome.PlayAgain;
import com.ocr.GamePlay_Studio.HandlingException.NumberOfCluesException;
import com.ocr.GamePlay_Studio.HandlingException.StyleOfCluesException;
import com.ocr.GamePlay_Studio.OtherNumbersMethod.ActionPlayer;
import com.ocr.GamePlay_Studio.OtherNumbersMethod.DichotomousSearch;

import java.util.Arrays;

import static com.ocr.GamePlay_Studio.OtherNumbersMethod.ActionPlayer.propositionPlayer;
import static com.ocr.GamePlay_Studio.OtherNumbersMethod.RandomS.randomS;

public class DefenderMode {
    /**
     * Setting up the defender mode
     */
    public static void defender() {
        int counter = Config.getMaxTries();
        int[] proposition = propositionPlayer();
        int[] turnComputer = randomS();
        System.out.println("\nThe AI's proposition is : " + Arrays.toString(turnComputer));
        do {
            try {
                String[] clues = ActionPlayer.giveClues();
                int[] dicho = DichotomousSearch.dichoSearch(turnComputer, clues, Config.getMinValue(), Config.getMaxValue());
                counter--;
                System.out.println("\nNew AI's proposition : " + Arrays.toString(dicho));
                if (Arrays.equals(proposition, dicho)) {
                    System.out.println("Ho ho ! AI has found the secret number ! Human is near extinction !");
                    break;
                } else if (counter == 0) {
                    System.out.println("No more tries ! Computer has lost !");
                    break;
                } else {
                    System.out.println("It stays " + counter + " tries for the AI");
                }
            } catch (NumberOfCluesException | StyleOfCluesException e) {
                e.getMessage();
            }
        } while (true);

        PlayAgain.Play();
    }
}