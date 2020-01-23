package player;

import domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(ConfigurationGame config, Scanner scan){
        super(config, scan);
    }

    /**
     * Display a combination suggest by the player
     *
     * @return : proposition of the player
     */
    @Override
    public int[] propositionPlayer() {
        do {
            System.out.println("\tDo your proposition : ");
            try {
                int proposition = scan.nextInt();
                String[] digits = String.format("%0" + config.getDigitsCombination() + "d", proposition).split("");
                if (digits.length > config.getDigitsCombination()) {
                    System.out.println("Wow !! How many times you count typing on the keyboard");
                } else {
                    System.out.println("Your answer is : " + Arrays.toString(digits));
                    int[] answer = new int[config.getDigitsCombination()];
                    for (int i = 0; i < config.getDigitsCombination(); i++) {
                        answer[i] = Integer.parseInt(String.valueOf(digits[i]));
                    }
                    return answer;
                }
            } catch (InputMismatchException e) {
                System.err.println("Wow ! What was that ? Please enter a  numbers combination, that's all dude !");
                scan.nextLine(); // dump the variable otherwise infinite loop
            } catch (NumberFormatException n) {
                System.err.println("OK ! you were so enthusiastic ! Please enter only positive numbers !");
                scan.nextLine(); // dump the variable otherwise infinite loop
            }
        } while (true);
    }

    /**
     * Clues to help finding the good secret number
     *
     * @return clues : The array of clues
     */
    @Override
    public String[] clues() {
        System.out.println("Please, give the clues for the computer : ");
        String clue;
        String[] clues;
        boolean b;
        do {
            clue = scan.nextLine();

            clues = String.format("%" + config.getDigitsCombination() + "s", clue).split("");
            b = clue.matches("[+-=]+");


            if (clues.length > config.getDigitsCombination())
                System.out.println("Hep hep hep ! Too many symbols in your clues ! Try again !");
            else if (!b) {
                System.out.println("What was that ? You're afraid to loose or something ! Please enter only real symbols or leave !");
            } else
                break;
        } while (clues.length > config.getDigitsCombination() || !b);

        System.out.println("My clues are : " + Arrays.toString(clues));
        return clues;
    }

    @Override
    public String[] compare_result(int[] combinationPlayer, int[] combinationAi) {
        return null;
    }

    @Override
    public int[] dichotomousResearch(int[] IACombination, String[] clues, int[] min, int[] max) {
        return null;
    }
}
