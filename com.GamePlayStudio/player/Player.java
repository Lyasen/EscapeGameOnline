package player;

import domaine.properties.ConfigurationGame;

import java.util.Scanner;

public abstract class Player {
    protected ConfigurationGame config;
    protected Scanner scan;

    protected abstract int[] dichotomousResearch(int[] IACombination, String[] clues, int[] min, int[] max);

    protected abstract String[] clues();

    protected abstract String[] compare_result(int[] combinationPlayer, int[] combinationAi);

    public abstract int[] propositionPlayer();
}
