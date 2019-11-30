package com.ocr.GamePlay_Studio.OtherNumbersMethod;

public class IsWin {
    public static boolean winIf(String[] comparison) {
        for (String operator : comparison) {
            if (!operator.equals("="))
                return false;
        }
        return true;
    }
}
