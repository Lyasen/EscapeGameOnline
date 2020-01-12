package utils;

import domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.Random;

public class RandomNumber {
    /**
     * A random combination played by the AI
     *
     * @return the secret number
     */
    public int[] randomSecret(ConfigurationGame config) {
        System.out.println("\nHere's the Ai's secret number : " +
                "\nDo you really thought i was going to display the secret number Mouahaha !!");
        int[] secret = new int[config.getDigitsCombination()];
        Random hazard = new Random();

        for (int i = 0; i < config.getDigitsCombination(); i++) {
            secret[i] = config.getMinValue() + hazard.nextInt(config.getMaxValue() - config.getMinValue() + 1);
        }
        System.out.println(Arrays.toString(secret)); // Display the result
        return secret;
    }
}
