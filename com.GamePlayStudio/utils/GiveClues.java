package utils;

import HandlingException.NumberOfCluesException;
import HandlingException.StyleOfCluesException;
import domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.Scanner;

public class GiveClues {
    /**
     * Clues to help finding the good secret number
     *
     */
    public void giveClues(Scanner scan, ConfigurationGame config) throws NumberOfCluesException, StyleOfCluesException {
        System.out.println("Please, give the clues for the computer : ");
        scan.nextLine();    //  Empty the combination's number line
        String clue = scan.nextLine();
        String[] clues = String.format("%" + config.getDigitsCombination() + "s", clue).split("");

        if (clues.length > config.getDigitsCombination())
            throw new NumberOfCluesException();
        else if (!clue.contains("=") && (!clue.contains("-")) && (!clue.contains("+")))
            throw new StyleOfCluesException();
        else
            System.out.println("My clues are : " + Arrays.toString(clues));
    }
}
