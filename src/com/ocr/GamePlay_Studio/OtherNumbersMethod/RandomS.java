package com.ocr.GamePlay_Studio.OtherNumbersMethod;

import com.ocr.GamePlay_Studio.Domain.Config;

import java.util.Random;

public class RandomS {

    /**
     * A random combination played by the AI
     * @return : A random combination
     */
    public static int[] randomS() {
        int[] secret = new int[Config.getDigitsCombination()];
        Random hazard = new Random();
        int minValue = 0, maxValue = 9;

        for (int i = 0; i < Config.getDigitsCombination(); i++) {
            secret[i] = minValue + hazard.nextInt(maxValue - minValue + 1);
        }
        //System.out.println(Arrays.toString(secret)); // Display the result
        return secret;
    }
}
