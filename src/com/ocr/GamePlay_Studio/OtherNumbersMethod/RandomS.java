package com.ocr.GamePlay_Studio.OtherNumbersMethod;

import java.util.Random;

public class RandomS {
    private static DigitsCombination digitsCombination = DigitsCombination.NB_DIGITS_COMBINATION;

    protected static int[] randomS() {

        int[] secret = new int[digitsCombination.getDigits()];
        Random hazard = new Random();
        int minValue = 0, maxValue = 9;

        for (int i = 0; i < digitsCombination.getDigits(); i++) {
            secret[i] = minValue + hazard.nextInt(maxValue - minValue + 1);
        }
        //System.out.println(Arrays.toString(secret)); => Display the result
        return secret;
    }
}
