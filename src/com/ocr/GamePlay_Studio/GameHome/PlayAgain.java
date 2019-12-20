package com.ocr.GamePlay_Studio.GameHome;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayAgain {
    public static void Play() {
        Scanner scan = new Scanner(System.in);
        char playOneMore;

        System.out.println("\nThis is the end of the game, what do you want to do ?" +
                "\n1 - Play again" +
                "\n2 - Back to the menu" +
                "\n2 - I go swim now, see you next time !");

        int choice = 0;

        do {
            try {
                choice = scan.nextInt();

                switch (choice) {
                    case 1:
                        playOneMore = scan.nextLine().charAt(0);
                        break;
                    case 2:
                        Home.menu();
                        break;
                    case 3:
                        System.out.println("Bye ! Bye ! See ya !");
                        System.exit(choice);
                        break;
                    default:
                        System.out.println("Finally, you want to play or not, choose please !");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number's menu");
                break;
            }
        } while (!(1 <= choice && choice <= 3));
    }
}
