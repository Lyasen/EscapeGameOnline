package com.ocr.GamePlay_Studio.GameMode;

import com.ocr.GamePlay_Studio.OtherNumbersMethod.*;
import com.ocr.GamePlay_Studio.exceptions.TooManyNumbers;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ChallengerMode extends RandomS {
    private static Scanner scan = new Scanner(System.in);
    private static DigitsCombination digitsCombination = DigitsCombination.NB_DIGITS_COMBINATION;

    /**
     * Setting up the challenger mode
     */
    public static void challenger() throws TooManyNumbers {
        int counter = 5;
        new RandomS();
        int[] combination = randomS();
        System.out.printf("Now ! You have %s tries\n", counter);

        do {
            int[] combiPlayer = propositionPlayer();
            String[] comparison = compareResult(combiPlayer, combination);

            if (counter > 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(comparison));
            counter--;

            if (isWin(comparison)) {
                System.out.println("Well done ! You're the mastermind !");
                System.exit(0);
            } else if (counter == 0) {
                System.out.println("Sorry, you have used your tries ! The secret number was : " + Arrays.toString(combination) + " ! Try next time !");
                System.exit(0);
            } else {
                System.out.printf("There are %s tries left", counter);
            }
        } while (true);
    }

    private static boolean isWin(String[] comparison) {
        for (String operator : comparison) {
            if (!operator.equals("="))
                return false;
        }
        return true;
    }

    /**
     * Player's proposition
     */
    private static int[] propositionPlayer() throws TooManyNumbers {
        int[] answer = new int[digitsCombination.getDigits()];
        System.out.println("\nDo your proposition : ");
        try {
            int proposition = scan.nextInt();

            String[] digits = String.format("%0" + digitsCombination.getDigits() + "d", proposition).split("");
            if (digits.length > digitsCombination.getDigits()) {
                throw new TooManyNumbers();
            } else {
                System.out.println("\nYour answer is : " + Arrays.toString(digits));
            }

            for (int i = 0; i < digitsCombination.getDigits(); i++) {
                answer[i] = Integer.parseInt(String.valueOf(digits[i]));
            }
        } catch (InputMismatchException e) {
            System.out.println("Wow ! What was that ? Please enter a  numbers combination, that's all dude !");
            scan.nextLine();    //  dump the variable otherwise infinite loop
        } catch (NumberFormatException n) {
            System.out.println("OK ! you were so enthusiastic that you loose one try  ! Please enter only positive numbers !");
            scan.nextLine();    //  dump the variable otherwise infinite loop
        }
        return answer;
    }

    /**
     * Compare the board and the second board
     * Find the combination in 10 times
     */
    private static String[] compareResult(int[] combiPlayer, int[] combination) {
        String[] symbol = new String[digitsCombination.getDigits()];

        for (int s = 0, len = combination.length; s < len; s++) {
            if (combination[s] > combiPlayer[s])
                symbol[s] = "+";
            else if (combination[s] < combiPlayer[s])
                symbol[s] = "-";
            else if (combination[s] == combiPlayer[s])
                symbol[s] = "=";
        }
        return symbol;
    }
}
