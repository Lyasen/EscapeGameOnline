package player;

import domaine.properties.ConfigurationGame;

public abstract class Player {
    protected static ConfigurationGame config;

    public Player(ConfigurationGame config) {
        Player.config = config;
    }

    public abstract int[] research(int[] combination, String[] clues);

    public abstract String[] clues(int[] combinationPlayer_1, int[] combinationPlayer_2);
}
