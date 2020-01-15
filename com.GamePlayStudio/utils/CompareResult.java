package utils;

import domaine.properties.ConfigurationGame;

public class CompareResult {
    public String[] compareDigits(int[] combinationPlayer, int[] combinationAi, ConfigurationGame config){
        System.out.print("Now let's see ! ");
        String[] symbol = new String[config.getDigitsCombination()];

        for (int i = 0, len = combinationAi.length; i < len; i++) {
            if (combinationAi[i] > combinationPlayer[i])
                symbol[i] = "+";
            else if (combinationAi[i] < combinationPlayer[i])
                symbol[i] = "-";
            else if (combinationAi[i] == combinationPlayer[i])
                symbol[i] = "=";
        }
        return symbol;
    }
}
