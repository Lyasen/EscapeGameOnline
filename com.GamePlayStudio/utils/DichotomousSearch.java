package utils;

public class DichotomousSearch {
    /**
     * A dichotomous searching for helping the AI to find the good secret number
     * @return : A new combination to play
     */
    public int[] AiSearch(int[] combinationAi, String[] clues, int[] min, int[] max) {

        for (int i = 0, len = combinationAi.length; i < len; i++) {
            switch (clues[i]) {
                case "+":
                    min[i] = combinationAi[i] + 1;
                    break;
                case "-":
                    max[i] = combinationAi[i] - 1;
                    break;
                case "=":
                    min[i] = combinationAi[i];
                    max[i] = combinationAi[i];
                    break;
            }
            combinationAi[i] = (min[i] + max[i]) / 2;
        }
        return combinationAi;
    }
}
