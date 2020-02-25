package com.GamePlayStudio.player;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;

public abstract class Player {
    protected ConfigurationGame config;

    public Player(ConfigurationGame config) {
        this.config = config;
    }

    //  search for a combination based on indices
    public abstract int[] research(String[] clues);

    //  gives clues in comparison to a combination to find and a proposition made
    public abstract String[] clues(int[] combination);

    //  initialization of a combination at the start of the game
    public abstract int[] initialiseCombination();
}
