package gameHome;

import domaine.properties.ConfigurationGame;
import gameMode.*;
import player.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    private final Player player1, player2;
    protected int response, playAgain;
    protected ConfigurationGame config;
    private final Scanner scan;

    public Home(Player player1, Player player2, ConfigurationGame config, Scanner scan) {
        this.player1 = player1;
        this.player2 = player2;
        this.config = config;
        this.scan = scan;
    }

    /**
     * Display the different game modes
     */
    public void menu() {
        System.out.println("\nPlease choose your game mode :\n1- Challenger\n2- defender\n3- Duel\n4- Bonus mode");
        do {
            try {
                response = scan.nextInt();
                relaunchGame();
            } catch (InputMismatchException e) {
                System.err.println("You have won the relaunch of the game ! I find this very funny");
            }
        } while (!(1 <= response && response <= 3));

        playOneMore();
    }

    public void relaunchGame() {
        switch (response) {
            case 1:
                new ChallengerMode(config, scan).playWithTwoPlayers(player1, player2);
                break;
            case 2:
                new DefenderMode(config, scan).playWithTwoPlayers(player1, player2);
                break;
            case 3:
                new DuelMode(config, scan).playWithTwoPlayers(player1, player2);
                break;
            case 4:
                new BonusMode(config, scan).playWithTwoPlayers(player1, player2);
                break;
            default:
                System.out.println("Please have a choice between this four menus : ");
        }
    }

    public void playOneMore(){
        System.out.println("\nThe game is now finished !\n1 - You may play again \n2 - You can come back to the menu \n3 - You can go swimming");

        do {
            try {
                playAgain = scan.nextInt();
                switch (playAgain) {
                    case 1:
                        System.out.println("You want to play more ! Good luck !");
                        break;
                    case 2:
                        System.out.println("Back to the menu !");
                        new Home(player1, player2, config, scan).menu();
                        break;
                    case 3:
                        System.out.println("Thank you to play with us ! See you soon !");
                        break;
                    default:
                        System.out.println("Please have a choice between these menus : ");
                }
            } catch (InputMismatchException e) {
                System.err.println("You have won the relaunch of the game ! I find this very funny");
                break;
            }
        } while (!(1 <= playAgain && playAgain <= 3));
    }
}