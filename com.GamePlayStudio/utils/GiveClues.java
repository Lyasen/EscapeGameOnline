package utils;

import domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.Scanner;

public class GiveClues {
    /**
     * Clues to help finding the good secret number
     *
     * @return clues : The array of clues
     */
    public String[] giveClues(Scanner scan, ConfigurationGame config) {
        System.out.println("Please, give the clues for the computer : ");
        String clue;
        String[] clues;
        do {
            clue = scan.nextLine();
            clues = String.format("%" + config.getDigitsCombination() + "s", clue).split("");

            if (clues.length > config.getDigitsCombination())
                System.out.println("Hep hep hep ! Too many symbols in your clues ! Try again !");
            else if (!(clue.contains("=")) && !(clue.contains("-")) && !(clue.contains("+")))
                System.out.println("What sort of clues is that ? Please enter only the real symbols !");
        } while (clues.length > config.getDigitsCombination() || !(clue.contains("=")) && !(clue.contains("-")) && !(clue.contains("+")));

        System.out.println("My clues are : " + Arrays.toString(clues));
        return clues;
    }
}