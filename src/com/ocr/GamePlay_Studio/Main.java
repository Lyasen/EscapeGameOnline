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
        final int secret[] = new int[NB_DIGITS_COMBINATION];
        String board = "";
        int minValue = 1000, maxValue = 9999, i;

        Random hazard = new Random();
        for (i = 0; i < NB_DIGITS_COMBINATION; i++){
            secret[i] = minValue + hazard.nextInt(maxValue - minValue);
            board = new String(String.valueOf(secret[i]));
        }
        System.out.println("Secret number : " + board);

        /**
         * Enter a combination of 4 digits by the player
         */
        final int MAX_TRY = 2;
        int answer[] = new int[NB_DIGITS_COMBINATION];
        String secondBoard = "";
        int counter = 1;
        char symbole[] = new char[' '];

        System.out.print("\rPlease enter your proposition : ");
        do {
            int proposition = scan.nextInt();
            for (int j = 0; j < NB_DIGITS_COMBINATION; j++){
                answer[j] = proposition;
                secondBoard = new String(String.valueOf(answer[j]));
            }
            System.out.println("Your proposition is : " + secondBoard);
            /**
             * Compare the board and the second board
             * Find the combination and win
             * or try 10 times and lose
             */
            for (int a = 0; a < answer.length; a++){
                for (int s = 0; s < secret.length; s++){
                    if (answer[a] > secret[s]){

                    } else if (answer[a] < secret[s]){

                    } else {

                    }
                }
            }
            counter++;
        } while (board != secondBoard && counter <= MAX_TRY);
    }
}
