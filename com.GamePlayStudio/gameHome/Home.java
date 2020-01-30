package gameHome;

import domaine.properties.ConfigurationGame;
import gameMode.*;
import player.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Home {
    protected int response;
    protected ConfigurationGame config;

    public Home(ConfigurationGame config) {
        this.config = config;
    }

    public int getResponse() {
        return response;
    }

    /**
     * Display the different game modes
     *
     * @param scan : the answer of the user
     */
    public void menu(Scanner scan, Player player_1, Player player_2) {
        System.out.println("\nPlease choose your game mode :\n1- Challenger\n2- defender\n3- Duel\n4- Bonus mode");
        do {
            try {
                response = scan.nextInt();
                switch (getResponse()) {
                    case 1:
                        new ChallengerMode(config, scan).playWithTwoPlayers(player_1, player_2);
                        break;
                    case 2:
                        new DefenderMode(config, scan).playWithTwoPlayers(player_1, player_2);
                        break;
                    case 3:
                        new DuelMode(config, scan).playWithTwoPlayers(player_1, player_2);
                        break;
                    case 4:
                        new BonusMode(config, scan).playWithTwoPlayers(player_1, player_2);
                        break;
                    default:
                        System.out.println("Please have a choice between this four menus : ");
                }
            } catch (InputMismatchException e) {
                System.err.println("You have won the relaunch of the game ! I find this very funny");
            }
        } while (!(1 <= getResponse() && getResponse() <= 3));
    }

    public void relaunchGame(Scanner scan, Player player_1, Player player_2) {
        ChallengerMode challenger = new ChallengerMode(config, scan);
        DefenderMode defender = new DefenderMode(config, scan);
        DuelMode duel = new DuelMode(config, scan);
        BonusMode bonus = new BonusMode(config, scan);

        boolean sameGame = false;

        System.out.println("You want to play more ! Good luck !");
    }
}