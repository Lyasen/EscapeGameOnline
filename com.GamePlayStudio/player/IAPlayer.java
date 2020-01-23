package player;

import domaine.properties.ConfigurationGame;

import java.util.Random;
import java.util.Scanner;

public class IAPlayer extends Player {
    public IAPlayer(ConfigurationGame config, Scanner scan) {
        super(config, scan);
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
    public int[] dichotomousResearch(int[] IACombination, String[] clues, int[] min, int[] max) {
        for (int i = config.getMinValue(), len = IACombination.length; i < len; i++) {
            switch (clues[i]) {
                case "+":
                    min[i] = IACombination[i] + 1;
                    break;
                case "-":
                    max[i] = IACombination[i] - 1;
                    break;
                case "=":
                    min[i] = IACombination[i];
                    max[i] = IACombination[i];
                    break;
            }
            IACombination[i] = (min[i] + max[i]) / 2;
        }
        return IACombination;
    }

    /**
     * Compare results between two combinations in order to display clues for the player
     */
    @Override
    public String[] compare_result(int[] combinationPlayer, int[] combinationAi) {
        System.out.print("Now let's see ! ");
        String[] symbol = new String[config.getDigitsCombination()];

        for (int i = 0, len = combinationAi.length; i < len; i++) {
            if (combinationAi[i] > combinationPlayer[i])
                symbol[i] = "+";
            else if (combinationAi[i] < combinationPlayer[i])
                symbol[i] = "-";
            else if (combinationAi[i] == combinationPlayer[i])
                symbol[i] = "=";
        }
        return symbol;
    }

    @Override
    public int[] propositionPlayer() {
        return null;
    }

    @Override
    public String[] clues() {
        return null;
    }
}
