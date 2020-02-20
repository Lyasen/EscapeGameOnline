package com.GamePlayStudio.player;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;

public abstract class Player {
    protected ConfigurationGame config;

    public Player(ConfigurationGame config) {
        this.config = config;
    }

    public abstract int[] research(String[] clues);

    public abstract String[] clues(int[] combination);

    public abstract int[] initialiseCombination();

    public abstract void initialiseCombination(int[] combination);
}
