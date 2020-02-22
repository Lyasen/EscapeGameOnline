package player;

import domaine.properties.ConfigurationGame;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scan;

    public HumanPlayer(ConfigurationGame config, Scanner scan){
        super(config);
        this.scan = scan;
    }

    /**
     * Clues to help finding the good secret number
     *
     * @return clues : The array of clues
     */
    @Override
    public String[] clues(int[] combination) {
        String clue;
        String[] clues;
        boolean b;
        config.getMsgInfo().giveClues();

        do {
            clue = scan.nextLine();
            clues = clue.split("");
            b = clue.matches("[+\\-=]+");

            if (clues.length > config.getDigitsCombination()) {
                config.getMsgError().tooManySymbols();
            } else if (!b) {
                config.getMsgError().realSymbols();
            } else
                break;
        } while (true);

        return clues;
    }

    /**
     * Display a combination suggest by player
     *
     * @return : proposition of the player
     */
    @Override
    public int[] research(String[] clues) {
        do {
            config.getMsgInfo().doProposition();
            try {
                int proposition = scan.nextInt();
                scan.nextLine();
                String[] digits = String.format("%0" + config.getDigitsCombination() + "d", proposition).split("");
                if (digits.length > config.getDigitsCombination()) {
                    config.getMsgError().tooManyTypes();
                } else {
                    int[] combination = new int[config.getDigitsCombination()];
                    for (int i = 0; i < config.getDigitsCombination(); i++) {
                        combination[i] = Integer.parseInt(String.valueOf(digits[i]));
                    }
                    return combination;
                }
            } catch (InputMismatchException e) {
                config.getMsgError().onlyNumbers();
                scan.nextLine(); // dump the variable otherwise infinite loop
            } catch (NumberFormatException n) {
                config.getMsgError().positiveNumbers();
                scan.nextLine();
            }
        } while (true);
    }

    @Override
    public int[] initialiseCombination() {
        return research(null);
    }
}