package gameHome;

import domaine.properties.ConfigurationGame;
import gameMode.BonusMode;
import gameMode.ChallengerMode;
import gameMode.DefenderMode;
import gameMode.DuelMode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayAgain {
    private int response;
    private ConfigurationGame config;
    private Object player_1;
    private Object player_2;

    public PlayAgain(ConfigurationGame config) {
        this.config = config;
    }

    public int getResponse() {
        return response;
    }

    /**
     * Display the possibility to replay or not
     * @param scan : the answer of the user
     */
    public void playOneMore(Scanner scan){
        System.out.println("\nThe game is now finished !\n1 - You may play again \n2 - You can come back to the menu \n3 - You can go swimming");

        do {
            try {
                response = scan.nextInt();
                switch (getResponse()) {
                    case 1:
                        System.out.println("You want to play one more ! Good luck !");
                        new ChallengerMode(config, scan).challenge();
                        new DefenderMode(config, scan).defender();
                        new DuelMode(config, scan).duel();
                        new BonusMode(config,scan).bonus();
                    case 2:
                        System.out.println("Return to the menu !");
                        new Home(config).menu(scan);
                        break;
                    case 3:
                        System.out.println("Thank you to play with us ! See you soon !");
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
