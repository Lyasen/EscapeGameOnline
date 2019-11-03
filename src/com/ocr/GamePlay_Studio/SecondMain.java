/**
 * Game mode "Défenseur"
 */
package com.ocr.GamePlay_Studio;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class SecondMain {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Home home = new Home();
        home.menu();

        final int NB_DIGITS_COMBINATION = 4;
        String[] digits = new String[NB_DIGITS_COMBINATION];
        boolean condition = false;

        /*
         * generation of a secret number from the player
         */
        while (!condition) {
            try {
                System.out.print("\nPlayer, enter your proposition : ");
                int proposition = scan.nextInt();
                scan.nextLine();    //  Vidage de la ligne int proposition
                digits = String.format("%0" + NB_DIGITS_COMBINATION + "d", proposition).split("");
                System.out.print("\nYour proposition is : " + Arrays.toString(digits));
                condition = true;
            } catch (InputMismatchException e) {
                System.out.println("Please enter a combination with " + NB_DIGITS_COMBINATION + " numbers");
                scan.nextLine();
            }
        }

        /*
         * Proposition from the AI
         */
        String[] computerProposition = new String[NB_DIGITS_COMBINATION];
        int minValue = 0, maxValue = 9, counter = 3;

        System.out.printf("\n\nThe AI have %s tries\n", counter);

        do {
            Random hazard = new Random();
            for (int i = 0; i <= NB_DIGITS_COMBINATION - 1; i++) {
                computerProposition[i] = String.valueOf(minValue + hazard.nextInt(maxValue - minValue + 1));
            }
            System.out.println("AI proposition : " + Arrays.toString(computerProposition));
            if (counter > 1) {
                System.out.println("Give the clues for helping AI :  ");
                String clue = scan.nextLine();
                String[] clues = String.format("%" + NB_DIGITS_COMBINATION + "s", clue).split("");
                System.out.print("The clues are :  " + Arrays.toString(clues) + "\n");
            }

            counter--;
            System.out.printf("\n%s tries left\n\n", counter);

        } while (!Arrays.equals(digits, computerProposition) && counter > 0);

        if (Arrays.equals(digits, computerProposition))
            System.out.println("Well done ! The AI win !");
        else
            System.out.println("\nSorry, you have used your tries ! The secret number was : " + Arrays.toString(digits) + " ! Try next time !");
    }
}
