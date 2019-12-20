package com.ocr.GamePlay_Studio.OtherNumbersMethod;

import com.ocr.GamePlay_Studio.Domain.Config;
import com.ocr.GamePlay_Studio.HandlingException.NumberOfCluesException;
import com.ocr.GamePlay_Studio.HandlingException.StyleOfCluesException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ActionPlayer {
    private static Scanner scan = new Scanner(System.in);

    /**
     * Proposition made by the player
     * @return : Answer of the player
     */
    public static int[] propositionPlayer() {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("\nDo your proposition : ");
            try {
                int proposition = scan.nextInt();
                String[] digits = String.format("%0" + Config.getDigitsCombination() + "d", proposition).split("");
                if (digits.length > Config.getDigitsCombination()) {
                    System.out.println("Wow !! How many times you count typing on the keyboard");
                } else {
                    System.out.println("Your answer is : " + Arrays.toString(digits));
                    int[] answer = new int[Config.getDigitsCombination()];
                    for (int i = 0; i < Config.getDigitsCombination(); i++) {
                        answer[i] = Integer.parseInt(String.valueOf(digits[i]));
                    }
                    return answer;
                }
            } catch (InputMismatchException e) {
                System.err.println("Wow ! What was that ? Please enter a  numbers combination, that's all dude !");
                scan.nextLine();    //  dump the variable otherwise infinite loop
            } catch (NumberFormatException n) {
                System.err.println("OK ! you were so enthusiastic ! Please enter only positive numbers !");
                scan.nextLine();    //  dump the variable otherwise infinite loop
            }
        } while (true);
    }

    /**
     * Clues to help finding the good secret number
     * @return : clues
     */
    public static String[] giveClues() throws NumberOfCluesException, StyleOfCluesException {
        System.out.println("Please, give the clues for the computer : ");
        String clue = scan.nextLine();
        String[] clues = String.format("%" + Config.getDigitsCombination() + "s", clue).split("");

        if (clues.length > Config.getDigitsCombination())
            throw new NumberOfCluesException();
        else if (!clue.contains("=") && (!clue.contains("-"))&& (!clue.contains("+")))
            throw new StyleOfCluesException();
        else
            System.out.println("My clues are : " + Arrays.toString(clues));

        return clues;
    }
}
