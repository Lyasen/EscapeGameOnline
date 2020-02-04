package player;

import domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.Random;

public class IAPlayer extends Player {
    protected int[] combinationPlayer = new int[config.getDigitsCombination()];
    private int[] combination;
    private String[] symbols;
    private int[] min = new int[config.getDigitsCombination()];
    private int[] max = new int[config.getDigitsCombination()];

    public IAPlayer(ConfigurationGame config, int[] combination, String[] symbols) {
        super(config);
        this.combination = combination;
        this.symbols = symbols;
    }

    /**
     * A random combination played by the IA
     *
     * @return the secret number
     */
    public int[] random() {
        int[] secret = new int[config.getDigitsCombination()];
        Random hazard = new Random();
        for (int i = 0; i < config.getDigitsCombination(); i++) {
            secret[i] = config.getMinValue() + hazard.nextInt(config.getMaxValue() - config.getMinValue() + 1);
        }
        System.out.println(Arrays.toString(secret)); //Display for test
        return secret;
    }

    /**
     * A dichotomous searching for helping IA to find the good secret number
     * @return : A new combination to play
     */
    @Override
    public int[] research(String[] clues) {
        for (int i = 0, len = combination.length; i < len; i++) {
            switch (clues[i]) {
                case "+":
                    min[i] = combination[i] + 1;
                    break;
                case "-":
                    max[i] = combination[i] - 1;
                    break;
                case "=":
                    min[i] = combination[i];
                    max[i] = combination[i];
                    break;
            }
            combination[i] = (min[i] + max[i]) / 2;
        }
        return combination;
    }

    /**
     * Compare results between two combinations in order to display clues for the player
     */
    @Override
    public String[] clues(int[] combination) {
        System.out.print("Now let's see ! ");


        for (int i = 0, len = combination.length; i < len; i++) {
            if (combinationPlayer[i] > combination[i])
                symbols[i] = "+";
            else if (combinationPlayer[i] < combination[i])
                symbols[i] = "-";
            else if (combinationPlayer[i] == combination[i])
                symbols[i] = "=";
        }
        return symbols;
    }
}
