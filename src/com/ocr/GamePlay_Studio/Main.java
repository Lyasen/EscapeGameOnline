/**
 * Console game
 */
package com.ocr.GamePlay_Studio;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Home home = new Home();
        home.menu();

        Scanner scan = new Scanner(System.in);

        /**
         * Generate a secret number with 4 digits
         */
        final int NB_DIGITS_COMBINATION = 4;
        final int[] secret = new int[NB_DIGITS_COMBINATION];
        String board = "";
        int minValue = 1000, maxValue = 9999, i;

        Random hazard = new Random();
        for (i = 0; i < NB_DIGITS_COMBINATION; i++) {
            secret[i] = minValue + hazard.nextInt(maxValue - minValue);
            board = new String(String.valueOf(secret[i]));
        }
        System.out.println("Secret number : " + board);

        /**
         * Enter a combination of 4 digits by the player
         */
        final int MAX_TRY = 3;
        int[] answer = new int[NB_DIGITS_COMBINATION];
        String secondBoard = "";
        int counter = 1;
        String[] symbole = new String[NB_DIGITS_COMBINATION];

        System.out.print("\rPlease enter your proposition : ");
        do {
            int proposition = scan.nextInt();
            for (int j = 0; j < NB_DIGITS_COMBINATION; j++) {
                answer[j] = proposition;
                secondBoard = new String(String.valueOf(answer[j]));
            }
            System.out.println("Your proposition is : " + secondBoard);

            /**
             * Compare the board and the second board
             * Find the combination and win
             * or try 10 times and lose
             */
            if (!board.equals(secondBoard)) {
                for (int a = 0, len = board.length(); a < len; a++) {
                    for (int b = 0, sLen = secondBoard.length(); b < sLen; b++) {
                        if (secret[a] > answer[b]) {
                            symbole[a] = "+";
                        } else if (secret[a] < answer[b])
                            symbole[a] = "-";
                        else if (secret[a] == answer[b])
                            symbole[a] = "=";
                    }
                }
                System.out.println(Arrays.toString(symbole));
                counter++;
            } else {
                System.out.println("Bravo ! Vous êtes le MasteMind !");
                break;
            }
        } while (board != secondBoard && counter <= MAX_TRY);
    }
}
