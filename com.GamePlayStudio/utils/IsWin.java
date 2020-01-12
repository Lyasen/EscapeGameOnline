package utils;

public class IsWin {
    /**
     * If all symbols = are found, you win
     *
     * @param comparison : The comparison between the symbols
     * @return : If true, you win else it's not won yet
     */
    public static boolean winIf(String[] comparison) {
        for (String operator : comparison) {
            if (!operator.equals("="))
                return false;
        }
        return true;
    }
}
