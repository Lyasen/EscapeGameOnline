package com.GamePlayStudio.player;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.gameMessage.MsgCombination;
import com.GamePlayStudio.gameMessage.MsgError;
import com.GamePlayStudio.gameMessage.MsgInfo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player implements MsgCombination, MsgError, MsgInfo {
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
        giveClues();

        do {
            clue = scan.nextLine();
            clues = clue.split("");
            b = clue.matches("[+\\-=]+");

            if (clues.length > config.getDigitsCombination()) {
                tooManySymbols();
            } else if (!b) {
                realSymbols();
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
            doProposition();
            try {
                int proposition = scan.nextInt();
                scan.nextLine();
                String[] digits = String.format("%0" + config.getDigitsCombination() + "d", proposition).split("");
                if (digits.length > config.getDigitsCombination()) {
                    tooManyTypes();
                } else {
                    int[] combination = new int[config.getDigitsCombination()];
                    for (int i = 0; i < config.getDigitsCombination(); i++) {
                        combination[i] = Integer.parseInt(String.valueOf(digits[i]));
                    }
                    return combination;
                }
            } catch (InputMismatchException e) {
                onlyNumbers();
                scan.nextLine(); // dump the variable otherwise infinite loop
            } catch (NumberFormatException n) {
                positiveNumbers();
                scan.nextLine();
            }
        } while (true);
    }

    @Override
    public int[] initialiseCombination() {
        return research(null);
    }

    @Override
    public void initialiseCombination(int[] combination) {

    }
}
