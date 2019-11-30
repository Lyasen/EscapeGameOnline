package com.ocr.GamePlay_Studio.OtherNumbersMethod;

import java.util.Random;

import static com.ocr.GamePlay_Studio.OtherNumbersMethod.Configuration.digitsCombination;

public class RandomS {

    public static int[] randomS() {

        int[] secret = new int[digitsCombination];
        Random hazard = new Random();
        int minValue = 0, maxValue = 9;

        for (int i = 0; i < digitsCombination; i++) {
            secret[i] = minValue + hazard.nextInt(maxValue - minValue + 1);
        }
        //System.out.println(Arrays.toString(secret)); => Display the result
        return secret;
    }
}
