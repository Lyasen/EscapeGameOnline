package com.ocr.GamePlay_Studio.OtherNumbersMethod;

import java.util.Arrays;

public class Configuration {
    public static final int digitsCombination = 4;
    public static final int maxTries = 10;
    public static int[] minValue = new int[digitsCombination];
    public static int[] maxValue = new int[digitsCombination];

    public static int setMinValue(){
        Arrays.fill(minValue, 0);
        return 0;
    }

    public static int setMaxValue(){
        Arrays.fill(maxValue, 9);
        return 9;
    }
}
