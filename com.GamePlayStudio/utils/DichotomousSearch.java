package utils;

public class DichotomousSearch {
    /**
     * A dichotomous searching for helping the AI to find the good secret number
     * @return : A new combination to play
     */
    public int[] IASearch(int[] IACombination, String[] clues, int[] min, int[] max) {

        for (int i = 0, len = IACombination.length; i < len; i++) {
            switch (clues[i]) {
                case "+":
                    min[i] = IACombination[i] + 1;
                    break;
                case "-":
                    max[i] = IACombination[i] - 1;
                    break;
                case "=":
                    min[i] = IACombination[i];
                    max[i] = IACombination[i];
                    break;
            }
            IACombination[i] = (min[i] + max[i]) / 2;
        }
        return IACombination;
    }
}
