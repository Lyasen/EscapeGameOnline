package com.GamePlayStudio.gameMode;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.player.Player;

import java.util.Arrays;

public class DefenderMode extends Mode {
    public DefenderMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * defense suggest a secret combination
     * attack try to find the secret number
     */
    @Override
    public void playWithTwoPlayers(Player defense, Player attack) {
        config.getMsgInfo().choiceGameDefender();
        int counter = config.getMaxTries();
        config.getMsgInfo().counter(counter);
        int[] combinationDefender = defense.initialiseCombination();
        config.getMsgCombination().newAnswer(combinationDefender);

        int[] combinationAttacker = attack.research(null);
        config.getMsgCombination().propositionOpponent(combinationAttacker);

        do {
            String[] clew = defense.clues(combinationAttacker);
            config.getMsgCombination().cluesAre(clew);
            combinationAttacker = attack.research(clew);
            config.getMsgCombination().propositionOpponent(combinationAttacker);
            counter--;

            if (Arrays.equals(combinationAttacker, combinationDefender)) {
                config.getMsgInfo().attackerFindSecretNumber();
                break;
            } else if (counter == 0) {
                config.getMsgInfo().attackerLoose();
                break;
            } else {
                config.getMsgInfo().counterLess(counter);
            }
        } while (true);
    }
}
