package player;

import domaine.properties.ConfigurationGame;

public abstract class Player {
    protected ConfigurationGame config;

    public Player(ConfigurationGame config) {
        this.config = config;
    }

    public abstract int[] research(String[] clues);

    public abstract String[] clues(int[] combination);
}
