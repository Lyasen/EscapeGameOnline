package com.GamePlayStudio.utils;

public class IsWin {
    /**
     * If all symbols '=' are found, you win
     *
     * @param comparison : The comparison between symbols
     * @return : If true, you win
     */
    public static boolean winIf(String[] comparison) {
        for (String operator : comparison) {
            if (!operator.equals("="))
                return false;
        }
        return true;
    }
}
