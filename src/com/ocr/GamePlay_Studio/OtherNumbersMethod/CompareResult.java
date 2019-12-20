package com.ocr.GamePlay_Studio.OtherNumbersMethod;

import com.ocr.GamePlay_Studio.Domain.Config;

public class CompareResult {
    /**
     * Compare the result between the secret number and the combination played by the gamer
     * @param combinationPlayer : A combination with 4 digits played by the gamer
     * @param combination : A combination with the same digits as the player
     * @return : the result of comparing the two combinations
     */
    public static String[] compare(int[] combinationPlayer, int[] combination) {
        String[] symbol = new String[Config.getDigitsCombination()];

        for (int s = 0, len = combination.length; s < len; s++) {
            if (combination[s] > combinationPlayer[s])
                symbol[s] = "+";
            else if (combination[s] < combinationPlayer[s])
                symbol[s] = "-";
            else if (combination[s] == combinationPlayer[s])
                symbol[s] = "=";
        }
        return symbol;
    }
}
