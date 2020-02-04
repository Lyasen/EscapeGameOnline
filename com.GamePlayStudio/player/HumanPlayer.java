package player;

import domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner scan;
    private String[] symbols;
    private int[] returnedCombination;

    public HumanPlayer(ConfigurationGame config, Scanner scan, String[] symbols, int[] returnedCombination){
        super(config);
        this.scan = scan;
        this.symbols = symbols;
        this.returnedCombination = returnedCombination;
    }

    /**
     * Clues to help finding the good secret number
     *
     * @return clues : The array of clues
     */
    @Override
    public String[] clues(int[] combination) {
        String clue;
        boolean b;
        System.out.println("Please, give the clues for the computer : ");

        do {
            clue = scan.nextLine();
            symbols = clue.split("");
            b = clue.matches("[+\\-=]+");

            if (symbols.length > config.getDigitsCombination())
                System.out.println("Hep hep hep ! Too many symbols in your clues ! Try again !");
            else if (!b)
                System.out.println("What was that ? You're afraid to loose or something ! Please enter only real symbols or leave !");
            else
                break;
        } while (true);

        System.out.println("My clues are : " + Arrays.toString(symbols));
        return symbols;
    }

    /**
     * Display a combination suggest by player
     *
     * @return : proposition of the player
     */
    @Override
    public int[] research(String[] clues) {
        do {
            System.out.println("Do your proposition : ");
            try {
                int proposition = scan.nextInt();
                String[] digits = String.format("%0" + config.getDigitsCombination() + "d", proposition).split("");
                if (digits.length > config.getDigitsCombination()) {
                    System.out.println("Wow !! How many times you count typing on the keyboard");
                } else {
                    System.out.println("Your answer is : " + Arrays.toString(digits));
                    returnedCombination = new int[config.getDigitsCombination()];
                    for (int i = 0; i < config.getDigitsCombination(); i++) {
                        returnedCombination[i] = Integer.parseInt(String.valueOf(digits[i]));
                    }
                    return returnedCombination;
                }
            } catch (InputMismatchException e) {
                System.err.println("Wow ! What was that ? Please enter a  numbers combination, that's all dude !");
                scan.nextLine(); // dump the variable otherwise infinite loop
            } catch (NumberFormatException n) {
                System.err.println("OK ! you were so enthusiastic ! Please enter only positive numbers !");
                scan.nextLine();
            }
        } while (true);
    }
}