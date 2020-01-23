package gameHome;

import domaine.properties.ConfigurationGame;
import gameMode.BonusMode;
import gameMode.ChallengerMode;
import gameMode.DefenderMode;
import gameMode.DuelMode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    private int response;
    private ConfigurationGame config;

    public Home(ConfigurationGame config) {
        this.config = config;
    }

    public int getResponse() {
        return response;
    }

    /**
     * Display the different game modes
     * @param scan : the answer of the user
     */
    public void menu(Scanner scan) {
        System.out.println("\nPlease choose your game mode :\n1- Challenger\n2- defender\n3- Duel\n4- Bonus mode");
        do {
            try {
                response = scan.nextInt();
                switch (getResponse()) {
                    case 1:
                        System.out.println("You have choice the game mode : Challenger\nTry to find the secret number !");
                        new ChallengerMode(config, scan).challenge();
                        break;
                    case 2:
                        System.out.println("You have choice the game mode : Defender\nWill the computer regain your secret combination ?");
                        new DefenderMode(config, scan).defender();
                        break;
                    case 3:
                        System.out.println("You have choice the game mode : Duel\nWho will be faster to find each other’s secret combination ?");
                        new DuelMode(config, scan).duel();
                        break;
                    case 4:
                        System.out.println("You have choice the game mode : Bonus mode\nChallenge the computer");
                        new BonusMode(config, scan).bonus();
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