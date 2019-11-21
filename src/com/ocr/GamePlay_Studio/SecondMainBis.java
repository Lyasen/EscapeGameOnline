package com.ocr.GamePlay_Studio;

import java.util.Arrays;
import java.util.Scanner;

public class SecondMainBis {
    private static Scanner scan = new Scanner(System.in);
    private final static int NB_DIGITS_COMBINATION = 4;

    public static void main(String[] args) {
        int counter = 5;

        Home home = new Home();
        home.menu();
        int[] turnPlayer = propositionPlayer();

        do {
            int[] turnComputer = propositionComputer();
            giveClues();
            binarySearch(turnComputer, cle);
            counter--;
            if (counter >= 1) {
                System.out.println("It stays " + counter + " tries for the AI");
            } else if(counter == 0) {
                System.out.println("No more tries ! Computer has lost !");
                break;
            } else {
                System.out.println("Well done !");
            }
        } while (!(turnPlayer == turnComputer));
    }

    private static int[] propositionPlayer() {
        int[] answerPlayer = new int[NB_DIGITS_COMBINATION];
        System.out.println("Player ! Write your secret number : ");
        int proposition = scan.nextInt();
        String[] numberPlayer = String.format("%0" + NB_DIGITS_COMBINATION + "d", proposition).split("");
        System.out.println("Your secret number is " + Arrays.toString(numberPlayer));
        scan.nextLine();    // emptied the scan
        return answerPlayer;
    }

    private static int[] propositionComputer() {
        int[] proposition = new int[NB_DIGITS_COMBINATION];
        int minValue = 0, maxValue = 9;
        int mid = (minValue + maxValue) / 2;

        for (int i = 0; i < NB_DIGITS_COMBINATION; i++) {
            proposition[i] = mid;
        }
        System.out.println("\nThe AI's proposition is : " + Arrays.toString(proposition));
        return proposition;
    }

    private static void giveClues(){
        System.out.println("Please, give the clues for the computer : ");
        String clue = scan.nextLine();
        String[] clues = String.format("%" + NB_DIGITS_COMBINATION + "s", clue).split("");
        System.out.println("My clues are : " + Arrays.toString(clues));
    }

    private static int binarySearch(int[] turnComputer, int cle) {
        int debut = 0;
        int fin = turnComputer.length - 1;
        while (debut <= fin) {
            int milieu = (debut + fin)/2;
            if (turnComputer[milieu] < cle)
                debut = milieu + 1;
            else if (turnComputer[milieu] > cle)
                fin = milieu - 1;
            else
                return milieu; // trouvé
        }
        return - (debut + 1); // pas trouvé.
    }
}