package com.ocr.GamePlay_Studio.OtherNumbersMethod;

public class DichotomousSearch {
    /**
     * A dichotomous searching for helping the AI to find the good secret number
     * @param turnComputer : Array used for the AI
     * @param clues : The clues initiate by the player in order to help AI
     * @param minValue : The floor value
     * @param maxValue : The ceiling value
     * @return : A new combination to play
     */
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
