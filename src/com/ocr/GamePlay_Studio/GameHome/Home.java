package com.ocr.GamePlay_Studio.GameHome;

import com.ocr.GamePlay_Studio.GameMode.ChallengerMode;
import com.ocr.GamePlay_Studio.GameMode.DefenderMode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    private static Scanner scan = new Scanner(System.in);

    /**
     * Home of the game
     * Setting up different game modes
     */
    public static void menu() {
        System.out.println("Welcome on EscapeGame Online" +
                "\nPlease choose your game mode : " +
                "\n1- Challenger" +
                "\n2- defender" +
                "\n3- Duel");
        int response = 0;
        do {
            try {
                response = scan.nextInt();

                switch (response) {
                    case 1:
                        System.out.println("You have choice the game mode : Challenger");
                        System.out.println("Find the secret number");
                        ChallengerMode.challenger();
                        break;
                    case 2:
                        System.out.println("You have choice the game mode : Defender");
                        System.out.println("Will the computer regain your secret combination ?");
                        DefenderMode.defender();
                        break;
                    case 3:
                        System.out.println("You have choice the game mode : Duel");
                        System.out.println("Challenge the computer");
//                    DuelMode.duel();
                        break;
                    default:
                        System.out.println("your choice does not correspond to a choice of the menu");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number's menu");
                break;
            }
        } while (!(1 <= response && response <= 3));
    }
}