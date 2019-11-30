package com.ocr.GamePlay_Studio.OtherNumbersMethod;

public class CompareResult {
    public static String[] compare(int[] combinationPlayer, int[] combination) {
        String[] symbol = new String[Configuration.digitsCombination];

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
