package com.ocr.GamePlay_Studio;

import java.util.InputMismatchException;
import java.util.Scanner;

class Home {
    private static Scanner scan = new Scanner(System.in);

    /**
     * Home of the game and setting up different game modes
     */
    void menu() {
        System.out.println("Welcome on EscapeGame Online" +
                "\nPlease choose your game mode : " +
                "\n1- Challenger" +
                "\n2- defender" +
                "\n3- Duel");

        int response = 0;
        boolean condition = false;  //  Condition in case of Exceptions

        /*
         * Choice of different game modes
         * As long as the answer isn't between 1 & 3, re-ask the question
         */
        while (!condition) {
            try {
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
                            System.out.print("Your choice doesn't correspond to the choices offered, please try again : ");
                    }
                } while (!(1 <= response && response <= 3));
                condition = true;
            } catch (InputMismatchException e) {
                System.out.print("Your choice must match a menu choice" +
                        "\n1- Challenger" +
                        "\n2- defender" +
                        "\n3- Duel" +
                        "\nPlease try again : ");
                scan.nextLine();    //  dump the variable otherwise infinite loop
            }
        }
    }
}
