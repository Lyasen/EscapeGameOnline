package com.ocr.GamePlay_Studio;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Home home = new Home();
        home.menu();

        final int NB_DIGITS_COMBINATION = 4;
        int[] secret = new int[NB_DIGITS_COMBINATION];
        Random hazard = new Random();
        int minValue = 0, maxValue = 9, counter = 10;

        for (int i = 0; i <= NB_DIGITS_COMBINATION - 1; i++) {
            secret[i] = minValue + hazard.nextInt(maxValue - minValue + 1);
        }
        System.out.println(Arrays.toString(secret));

        int[] answer = new int[NB_DIGITS_COMBINATION];
        System.out.printf("Are you ready !! You have " + counter + " trials\n");
        do {
            System.out.println("\nDo your proposition : ");
            int proposition = scan.nextInt();
            String[] digits = String.valueOf(proposition).split("");

            System.out.println("\nYour answer is : " + Arrays.toString(digits));

            for (int i = 0; i <= NB_DIGITS_COMBINATION - 1; i++) {
                answer[i] = Integer.parseInt(digits[i]);
            }

            /**
             * Compare the board and the second board
             * Find the combination and win
             * or try 10 times and lose
             */
            System.out.print("\rThe clues are  : ");
            char[] symbol = new char[' '];
            if (!Arrays.equals(answer, secret)) {
                for (int s = 0, len = secret.length; s < len; s++) {
                    if (secret[s] > answer[s])
                        symbol[s] = '+';
                    else if (secret[s] < answer[s])
                        symbol[s] = '-';
                    else if (secret[s] == answer[s])
                        symbol[s] = '=';
                }
                System.out.println(Arrays.toString(symbol));
                counter--;
                System.out.println("One trial less ! Be careful, you have " + counter  + " attempts left");
            } else {
                System.out.println("Welldone ! You are the MasterMind !");
                break;
            }

            if (counter == 0)
                System.out.println("You have not enough trials ! Try next time !");
        } while (answer != secret && counter > 0);
    }
}
