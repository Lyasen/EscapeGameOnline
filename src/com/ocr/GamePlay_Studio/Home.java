/**
 * Game Menu
 */
package com.ocr.GamePlay_Studio;

import java.util.Scanner;

public class Home {
    static Scanner scan = new Scanner(System.in);

    void menu() {
        System.out.println("Welcome on EscapeGame Online" +
                "\nPlease choose your game mode : " +
                "\n1- Challenger" +
                "\n2- defender" +
                "\n3- Duel");

        int response;

        /**
         * Choice of different game modes
         * As long as the answer isn't between 1 & 3, reask the question
         */
        do {
            response = scan.nextInt();
            switch (response) {
                case 1:
                    System.out.println("You have choice the game mode : Challenger");
                    System.out.println("Find the secret number");
                    break;
                case 2:
                    System.out.println("You have choice the game mode : Defender");
                    System.out.println("Will the computer regain your secret combination ?");
                    break;
                case 3:
                    System.out.println("You have choice the game mode : Duel");
                    System.out.println("Challenge the computer");
                    break;
                default:
                    System.out.println("Your choice doesn't correspond to the choices offered");
            }
        } while (!(1 <= response && response <= 3));
    }
}
