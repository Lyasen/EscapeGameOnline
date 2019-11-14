package com.ocr.GamePlay_Studio;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Game mode "Défenseur"
 */
public class SecondMainBis {
    private static Scanner scan = new Scanner(System.in);
    private final static int NB_DIGITS_COMBINATION = 4;
    private static String[] digits = new String[NB_DIGITS_COMBINATION];
    private static String[] computerProposition = new String[NB_DIGITS_COMBINATION];

    public static void main(String[] args) {
        Home home = new Home();
        home.menu();

        System.out.print("\nPlayer, enter your proposition : ");
        numberPlayer();
        computerSearchCombination();

        if (Arrays.equals(digits, computerProposition))
            System.out.println("Well done ! The AI win !");
        else
            System.out.println("\nSorry, you have used your tries ! The secret number was : " + Arrays.toString(digits) + " ! Try next time !");
    }

    /**
     * generation of a secret number from the player
     */
    private static void numberPlayer() {
        boolean condition = false;

        while (!condition) {
            try {
                int proposition = scan.nextInt();
                digits = String.format("%0" + NB_DIGITS_COMBINATION + "d", proposition).split("");
                if (digits.length > NB_DIGITS_COMBINATION || proposition < 0) {
                    System.out.println("Please only write a combination with " + NB_DIGITS_COMBINATION + " positive numbers !");
                    scan.nextLine();
                } else {
                    System.out.print("\nYour proposition is : " + Arrays.toString(digits));
                    condition = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a combination with " + NB_DIGITS_COMBINATION + " numbers");
                scan.nextLine();
            }
        }
    }

    /**
     * Proposition from the AI
     */
    private static void computerSearchCombination() {
        int minValue = 0, maxValue = 9, counter = 5;

        System.out.printf("\nThe AI have %s tries\n", counter);
        Random hazard = new Random();
        scan.nextLine();
        do {
            for (int i = 0; i < NB_DIGITS_COMBINATION; i++) {
                computerProposition[i] = String.valueOf(minValue + hazard.nextInt(maxValue - minValue + 1));
            }
            System.out.println("AI proposition : " + Arrays.toString(computerProposition));

            if (counter > 1) {
                System.out.println("Give the clues for helping AI :  ");
                String clue = scan.nextLine();
                while (!(clue.contains("+")) && !(clue.contains("-"))) {
                    System.out.println("Please only write positive or negative operators, try again : ");
                    clue = scan.nextLine();
                }
                String[] clues = String.format("%" + NB_DIGITS_COMBINATION + "s", clue).split("");
                if (clues.length > NB_DIGITS_COMBINATION) {
                    System.out.println("I see you contribute to help the IA but it's a bit excessive, isn't it ?");
                    scan.nextLine();
                } else {
                    System.out.print("The clues are :  " + Arrays.toString(clues) + "\n");
                }
            }
            counter--;
            System.out.printf("\nThere are %s tries left\n\n", counter);
        } while (!Arrays.equals(digits, computerProposition) && counter > 0);
    }
}