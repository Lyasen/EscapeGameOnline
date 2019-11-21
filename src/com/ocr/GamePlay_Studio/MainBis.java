package com.ocr.GamePlay_Studio;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainBis {
    private static Scanner scan = new Scanner(System.in);
    private final static int NB_DIGITS_COMBINATION = 4;

    /**
     * Setting up the challenger mode
     *
     * @param args
     */
    public static void main(String[] args) {
        int counter = 5;
        int[] combination = randomSecret();

        Home home = new Home();
        home.menu();

        System.out.printf("Are you ready !! You have %s tries\n", counter);

        do {
            int[] combiPlayer = propositionPlayer();
            String[] comparison = compareResult(combiPlayer, combination);

            if (counter > 1)
                System.out.println("\rThe clues are  : " + Arrays.toString(comparison));
            counter--;

            if (isWin(comparison)) {
                System.out.println("Well done ! You're the mastermind !");
                break;
            } else if (counter == 0) {
                System.out.println("Sorry, you have used your tries ! The secret number was : " + Arrays.toString(combination) + " ! Try next time !");
                break;
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
     * Secret number randomized
     *
     * @return secret : return a secret combination
     */
    private static int[] randomSecret() {
        int[] secret = new int[NB_DIGITS_COMBINATION];
        Random hazard = new Random();
        int minValue = 0, maxValue = 9;

        for (int i = 0; i < NB_DIGITS_COMBINATION; i++) {
            secret[i] = minValue + hazard.nextInt(maxValue - minValue + 1);
        }
        /* System.out.println(Arrays.toString(secret)); => Display the result */
        return secret;
    }

    /**
     * Player's proposition
     */
    private static int[] propositionPlayer() {
        int[] answer = new int[NB_DIGITS_COMBINATION];
        System.out.println("\nDo your proposition : ");
        try {
            int proposition = scan.nextInt();

            String[] digits = String.format("%0" + NB_DIGITS_COMBINATION + "d", proposition).split("");
            if (digits.length > NB_DIGITS_COMBINATION) {
                System.out.println("What a pity ! You have lost one try ! Please respect the number of digits in the combination !");
                scan.nextLine();
            } else {
                System.out.println("\nYour answer is : " + Arrays.toString(digits));
            }

            for (int i = 0; i < NB_DIGITS_COMBINATION; i++) {
                answer[i] = Integer.parseInt(String.valueOf(digits[i]));
            }
        } catch (InputMismatchException e) {
            System.out.println("Wow ! What was that ? Please enter a 4 number combination, that's all dude !");
            scan.nextLine();    //  dump the variable otherwise infinite loop
        } catch (NumberFormatException n) {
            System.out.println("OK ! you were so enthusiastic that you loose one try  ! Please enter only positive numbers !");
            scan.nextLine();    //  dump the variable otherwise infinite loop
        }
        return answer;
    }

    /*
     * Compare the board and the second board
     * Find the combination in 10 times
     */
    private static String[] compareResult(int[] combiPlayer, int[] combination) {
        String[] symbol = new String[NB_DIGITS_COMBINATION];

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