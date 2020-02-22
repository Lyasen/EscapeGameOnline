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

        config.getMsgInfo().player1();
        int[] combinationDefender = defense.initialiseCombination();
        config.getMsgCombination().newAnswer(combinationDefender);

        config.getMsgInfo().player2();
        int[] combinationAttacker = attack.research(null);
        config.getMsgCombination().propositionPlayer(combinationAttacker);
        String[] clues = defense.clues(combinationAttacker);
        config.getMsgCombination().cluesAre(clues);
        counter--;
        config.getMsgInfo().counterLess(counter);

        do {
            config.getMsgInfo().player2();
            combinationAttacker = attack.research(clues);
            config.getMsgCombination().propositionPlayer(combinationAttacker);


            if (Arrays.equals(combinationAttacker, combinationDefender)) {
                config.getMsgInfo().attackerFindSecretNumber();
                return;
            }

            if (counter > 1) {
                clues = defense.clues(combinationAttacker);
                config.getMsgCombination().cluesAre(clues);
                counter--;
                config.getMsgInfo().counterLess(counter);
            } else if (counter == 1) {
                config.getMsgInfo().lastTimeToFindCombination();
                counter--;
            } else if (counter == 0) {
                config.getMsgInfo().attackerLoose();
                return;
            }
        } while (true);
    }
}