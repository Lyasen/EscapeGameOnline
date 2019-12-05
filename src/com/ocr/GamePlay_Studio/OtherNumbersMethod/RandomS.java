package com.ocr.GamePlay_Studio.OtherNumbersMethod;

import java.util.Random;

import static com.ocr.GamePlay_Studio.OtherNumbersMethod.Configuration.*;

public class RandomS {

    /**
     * A random combination played by the AI
     * @return : A random combination
     */
    public static int[] randomS() {
        int[] secret = new int[digitsCombination];
        Random hazard = new Random();
        Configuration.setMinValue();
        Configuration.setMaxValue();

        for (int i = 0; i < digitsCombination; i++) {
            secret[i] = setMinValue() + hazard.nextInt(setMaxValue() - setMinValue() + 1);
        }
        //System.out.println(Arrays.toString(secret)); => Display the result
        return secret;
    }
}
