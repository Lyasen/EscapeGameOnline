package player;

import domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PropositionPlayer {
    /**
     * Display a combination suggest by the player
     * @param scan : input numbers for a combination
     * @param config : settings to play
     * @return : proposition of the player
     */
    public int[] propositionPlayer(Scanner scan, ConfigurationGame config) {
        do {
            System.out.println("Do your proposition : ");
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
}
