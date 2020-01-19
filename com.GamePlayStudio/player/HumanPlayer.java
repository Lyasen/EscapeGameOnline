package player;

import domaine.properties.ConfigurationGame;
import utils.GivingClues;
import utils.PlayerProposition;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player implements PlayerProposition, GivingClues {
    public HumanPlayer(ConfigurationGame config, Scanner scan){
        this.config = config;
        this.scan = scan;
    }

    /**
     * Display a combination suggest by the player
     *
     * @return : proposition of the player
     */
    @Override
    public int[] research() {
        do {
            System.out.println("\nDo your proposition : ");
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
        //  TODO: a regex to have a strictly combination with only +, -, =
        do {
            clue = scan.nextLine();
            clues = String.format("%" + config.getDigitsCombination() + "s", clue).split("");

            if (clues.length > config.getDigitsCombination())
                System.out.println("Hep hep hep ! Too many symbols in your clues ! Try again !");
            else if(!(clue.contains("=")) && !(clue.contains("-")) && !(clue.contains("+")))
                System.out.println("What was that ? You're afraid to loose or something ! Please enter only real symbols or leave !");
            else
                break;
        } while (clues.length > config.getDigitsCombination() || !(clue.contains("=")) && !(clue.contains("-")) && !(clue.contains("+")));

        System.out.println("My clues are : " + Arrays.toString(clues));
        return clues;
    }
}
