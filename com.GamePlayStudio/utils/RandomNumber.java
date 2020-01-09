package utils;

import domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.Random;

public class RandomNumber {
    /**
     * A random combination played by the AI
     *
     */
    public void randomSecret() {
        ConfigurationGame config = new ConfigurationGame();
        config.configGame();

        int[] secret = new int[config.];
        Random hazard = new Random();

        for (int i = 0; i < config.; i++) {
            secret[i] = config.getMinValue() + hazard.nextInt(config.getMaxValue() - config.getMinValue() + 1);
        }
        System.out.println(Arrays.toString(secret)); // Display the result
    }
}
