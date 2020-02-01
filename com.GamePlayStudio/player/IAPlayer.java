package player;

import domaine.properties.ConfigurationGame;

import java.util.Random;

public class IAPlayer extends Player {
    public static int[] random;

    public IAPlayer(ConfigurationGame config) {
        super(config);
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
//        System.out.println(Arrays.toString(secret)); Display for test
        return secret;
    }

    /**
     * A dichotomous searching for helping the IA to find the good secret number
     * @return : A new combination to play
     */
    @Override
    public int[] research(String[] clues) {
        int[] min = new int[config.getDigitsCombination()];
        int[] max = new int[config.getDigitsCombination()];
        int[] combination = new int[config.getDigitsCombination()];

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
        int[] combinationPlayer = new int[config.getDigitsCombination()];
        System.out.print("Now let's see ! ");
        String[] symbol = new String[config.getDigitsCombination()];

        for (int i = 0, len = combination.length; i < len; i++) {
            if (combinationPlayer[i] > combination[i])
                symbol[i] = "+";
            else if (combinationPlayer[i] < combination[i])
                symbol[i] = "-";
            else if (combinationPlayer[i] == combination[i])
                symbol[i] = "=";
        }
        return symbol;
    }
}
