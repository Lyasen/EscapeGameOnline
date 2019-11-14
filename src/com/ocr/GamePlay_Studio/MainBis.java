package com.ocr.GamePlay_Studio;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainBis {
    private static Scanner scan = new Scanner(System.in);
    private final static int NB_DIGITS_COMBINATION = 4;
    private static int[] secret = new int[NB_DIGITS_COMBINATION];
    private static int[] answer = new int[NB_DIGITS_COMBINATION];
    private static char[] symbol = new char[NB_DIGITS_COMBINATION];

    /**
     * Setting up the challenger mode
     *
     * @param args
     */
    public static void main(String[] args) {
        int counter = 5;

        Home home = new Home();
        home.menu();
        randomNumber();

        System.out.printf("Are you ready !! You have %s tries\n", counter);

        boolean condition = false;
        while (!condition) {
            do {
                propositionPlayer();
                compareResult();

                if (counter > 1)
                    System.out.println("\rThe clues are  : " + Arrays.toString(symbol));
                counter--;

                if (counter == 0) {
                    System.out.println("Sorry, you have used your tries ! The secret number was : " + Arrays.toString(secret) + " ! Try next time !");
                    break;
                } else {
                    System.out.printf("There are %s tries left", counter);
                }
            } while (true);
            condition = true;
        }
    }

    /**
     * Secret number randomized
     */
    private static void randomNumber() {
        Random hazard = new Random();
        int minValue = 0, maxValue = 9;

        for (int i = 0; i < NB_DIGITS_COMBINATION; i++) {
            secret[i] = minValue + hazard.nextInt(maxValue - minValue + 1);
        }
        /* System.out.println(Arrays.toString(secret)); => Display the result */
    }

    /**
     * Player's proposition
     */
    private static void propositionPlayer() {
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
    }

    /*
     * Compare the board and the second board
     * Find the combination in 10 times
     */
    private static void compareResult() {
        if (!Arrays.equals(answer, secret)) {
            for (int s = 0, len = secret.length; s < len; s++) {
                if (secret[s] > answer[s])
                    symbol[s] = '+';
                else if (secret[s] < answer[s])
                    symbol[s] = '-';
                else if (secret[s] == answer[s])
                    symbol[s] = '=';
            }
        } else {
            System.out.println("Well done ! You are the MasterMind !");
            System.exit(0);
        }
    }
}