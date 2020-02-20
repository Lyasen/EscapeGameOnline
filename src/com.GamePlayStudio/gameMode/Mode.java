package com.GamePlayStudio.gameMode;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.player.Player;

public abstract class Mode {
    protected ConfigurationGame config;

    public Mode(ConfigurationGame config) {
        this.config = config;
    }

    public abstract void playWithTwoPlayers(Player player1, Player player2);
}
