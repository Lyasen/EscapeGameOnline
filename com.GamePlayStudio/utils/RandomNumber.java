package utils;

import domaine.properties.ConfigurationGame;

import java.util.Random;

public class RandomNumber {
    /**
     * A random combination played by the AI
     *
     * @return the secret number
     */
    public int[] randomSecret(ConfigurationGame config) {
        int[] secret = new int[config.getDigitsCombination()];
        Random hazard = new Random();

        for (int i = 0; i < config.getDigitsCombination(); i++) {
            secret[i] = config.getMinValue() + hazard.nextInt(config.getMaxValue() - config.getMinValue() + 1);
        }
//        System.out.println(Arrays.toString(secret)); Display for test
        return secret;
    }
}
