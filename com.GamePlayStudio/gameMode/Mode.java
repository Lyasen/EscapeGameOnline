package gameMode;

import domaine.properties.ConfigurationGame;
import player.Player;

public abstract class Mode {
    protected ConfigurationGame config;

    public Mode(ConfigurationGame config) {
        this.config = config;
    }

    public abstract void playWithTwoPlayers(Player attack, Player defense);
}
