package com.GamePlayStudio.player;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;

import java.util.Arrays;
import java.util.Random;

public class IAPlayer extends Player {
    private int[] min = new int[config.getDigitsCombination()];
    private int[] max = new int[config.getDigitsCombination()];
    private int[] combination;
    private int[] proposition;

    public IAPlayer(ConfigurationGame config) {
        super(config);
        this.combination = random();
        initialize();
    }

    public void initialize(){
        this.proposition = random();
        //  Configure value minimum and maximum in order to make dichotomous search work
        Arrays.fill(min, config.getMinValue());
        Arrays.fill(max, config.getMaxValue());
    }

    /**
     * A random combination played by the IA
     *
     * @return random: a random combination
     */
    public int[] random() {
        int[] secret = new int[config.getDigitsCombination()];
        Random hazard = new Random();
        for (int i = 0; i < config.getDigitsCombination(); i++) {
            secret[i] = config.getMinValue() + hazard.nextInt(config.getMaxValue() - config.getMinValue() + 1);
        }
        return secret;
    }

    /**
     * A dichotomous searching for helping IA to find the good secret number
     * @return : A new combination to play
     */
    @Override
    public int[] research(String[] clues) {
        if (clues == null) {
            initialize();
            return proposition;
        }

        for (int i = 0, len = proposition.length; i < len; i++) {
            switch (clues[i]) {
                case "+":
                    min[i] = proposition[i] + 1;
                    break;
                case "-":
                    max[i] = proposition[i] - 1;
                    break;
                case "=":
                    min[i] = proposition[i];
                    max[i] = proposition[i];
                    break;
            }
            proposition[i] = (min[i] + max[i]) / 2;
        }
        return proposition;
    }

    /**
     * Compare results between two combinations in order to display clues for the player
     */
    @Override
    public String[] clues(int[] combinationPlayer) {
        String[] symbols = new String[config.getDigitsCombination()];
        for (int i = 0, len = combination.length; i < len; i++) {
            if (combination[i] > combinationPlayer[i])
                symbols[i] = "+";
            else if (combination[i] < combinationPlayer[i])
                symbols[i] = "-";
            else if (combination[i] == combinationPlayer[i])
                symbols[i] = "=";
        }
        return symbols;
    }

    @Override
    public int[] initialiseCombination() {
        this.combination = random();
        return this.combination;
    }
}