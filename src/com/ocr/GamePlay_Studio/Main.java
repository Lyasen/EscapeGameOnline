package com.ocr.GamePlay_Studio;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    /**
     * Setting up the challenger mode
     *
     * @param args
     */
    public static void main(String[] args) {
        Home home = new Home();
        home.menu();

        final int NB_DIGITS_COMBINATION = 4;

        /*
         * Secret number randomized
         */
        int[] secret = new int[NB_DIGITS_COMBINATION];
        Random hazard = new Random();
        int minValue = 0, maxValue = 9, counter = 3;
        for (int i = 0; i <= NB_DIGITS_COMBINATION - 1; i++) {
            secret[i] = minValue + hazard.nextInt(maxValue - minValue + 1);
        }
        for (int i = 0; i <= NB_DIGITS_COMBINATION - 1; i++) {
            secret[i] = Integer.parseInt(String.valueOf(secret[i]));
        }
        //System.out.println(Arrays.toString(secret));  =>  Display for test

        /*
         * Player's proposition until he find the secret number or lose
         */
        int[] answer = new int[NB_DIGITS_COMBINATION];
        boolean condition = false;  //  Condition in case of Exceptions
        System.out.printf("Are you ready !! You have %s tries\n", counter);

        while (!condition) {
            do {
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

                    for (int i = 0; i <= NB_DIGITS_COMBINATION - 1; i++) {
                        answer[i] = Integer.parseInt(digits[i]);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a 4 number combination");
                    scan.nextLine();    //  dump the variable otherwise infinite loop
                } catch (NumberFormatException n) {
                    System.out.println("Please enter only positive numbers");
                    scan.nextLine();    //  dump the variable otherwise infinite loop
                }
                /*
                 * Compare the board and the second board
                 * Find the combination in 10 times
                 */
                char[] symbol = new char[NB_DIGITS_COMBINATION];
                if (!Arrays.equals(answer, secret)) {
                    for (int s = 0, len = secret.length; s < len; s++) {
                        if (secret[s] > answer[s])
                            symbol[s] = '+';
                        else if (secret[s] < answer[s])
                            symbol[s] = '-';
                        else if (secret[s] == answer[s])
                            symbol[s] = '=';
                    }
                    if (counter > 1)
                        System.out.println("\rThe clues are  : " + Arrays.toString(symbol));
                    counter--;

                    if (counter == 0) {
                        System.out.println("Sorry, you have used your tries ! The secret number was : " + Arrays.toString(secret) + " ! Try next time !");
                        break;
                    } else {
                        System.out.printf("%s tries left", counter);
                    }

                } else {
                    System.out.println("Well done ! You are the MasterMind !");
                    break;
                }
            } while (true);
            condition = true;
        }
    }
}
