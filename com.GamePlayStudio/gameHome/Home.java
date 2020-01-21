package gameHome;

import gameMode.BonusMode;
import gameMode.ChallengerMode;
import gameMode.DefenderMode;
import gameMode.DuelMode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    private int response;

    public int getResponse() {
        return response;
    }

    /**
     * Display the different games mode
     * @param scan : the answer of the users
     */
    public void menu(Scanner scan) {
        System.out.println("\nPlease choose your game mode : " + "\n1- Challenger" + "\n2- defender" + "\n3- Duel" + "\n4- Bonus mode");
        do {
            try {
                response = scan.nextInt();
                switch (getResponse()) {
                    case 1:
                        System.out.println("You have choice the game mode : Challenger" +
                                "\nTry to find the secret number !");
                        new ChallengerMode().challenge();
                        break;
                    case 2:
                        System.out.println("You have choice the game mode : Defender");
                        System.out.println("Will the computer regain your secret combination ?");
                        new DefenderMode().defender();
                        break;
                    case 3:
                        System.out.println("You have choice the game mode : Duel");
                        System.out.println("who will be faster to find each other’s secret combination ?");
                        new DuelMode().duel();
                        break;
                    case 4:
                        System.out.println("You have choice the game mode : Bonus mode");
                        System.out.println("Challenge the computer");
                        new BonusMode().bonus();
                        break;
                    default:
                        System.out.println("Please have a choice between this four menus : ");
                }
            } catch (InputMismatchException e) {
                System.err.println("You have won the relaunch of the game ! I find this very funny");
                break;
            }
        } while (!(1 <= getResponse() && getResponse() <= 3));
    }
}