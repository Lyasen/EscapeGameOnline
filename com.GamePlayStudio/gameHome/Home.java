package gameHome;

import HandlingException.NumberOfCluesException;
import HandlingException.StyleOfCluesException;
import domaine.properties.ConfigurationGame;
import gameMode.ChallengerMode;
import gameMode.DefenderMode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    private int response;

    public Home() {}

    public int getResponse() {
        return response;
    }

    public void menu(Scanner scan, ConfigurationGame config) {
        System.out.println("\nPlease choose your game mode : " + "\n1- Challenger" + "\n2- defender" + "\n3- Duel");
        do {
            try {
                response = scan.nextInt();
                switch (getResponse()) {
                    case 1:
                        System.out.println("You have choice the game mode : Challenger" +
                                "\nTry to find the secret number !");
                        new ChallengerMode().challenge(scan, config);
                        break;
                    case 2:
                        System.out.println("You have choice the game mode : Defender");
                        System.out.println("Will the computer regain your secret combination ?");
                        new DefenderMode().defender(scan, config);
                        break;
                    case 3:
                        System.out.println("You have choice the game mode : Duel");
                        System.out.println("Challenge the computer");
                        // new DuelMode();
                        break;
                    default:
                        System.out.println("Please have a choice between this three menus : ");
                }
            } catch (InputMismatchException e) {
                System.out.println("You have won the relaunch of the game ! I find this very funny");
                break;
            } catch (NumberOfCluesException | StyleOfCluesException e) {
                e.getMessage();
            }
        } while (!(1 <= getResponse() && getResponse() <= 3));
    }
}