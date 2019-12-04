package com.ocr.GamePlay_Studio.OtherNumbersMethod;

public class DichotomousSearch {
    public static int[] dichoSearch(int[] turnComputer, String[] clues, int[] minValue, int[] maxValue) {
        for (int i = 0, len = turnComputer.length; i < len; i++) {
            switch (clues[i]) {
                case "+":
                    minValue[i] = turnComputer[i] + 1;
                    break;
                case "-":
                    maxValue[i] = turnComputer[i] - 1;
                    break;
                case "=":
                    minValue[i] = turnComputer[i];
                    maxValue[i] = turnComputer[i];
                    break;
            }
            turnComputer[i] = (minValue[i] + maxValue[i]) / 2;
        }
        return turnComputer;
    }
}
