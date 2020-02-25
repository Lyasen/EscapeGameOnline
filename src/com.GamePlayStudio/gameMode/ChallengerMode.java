package com.GamePlayStudio.gameMode;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.player.Player;
import com.GamePlayStudio.utils.IsWin;

import java.util.Arrays;

public class ChallengerMode extends Mode {
    public ChallengerMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * Player suggest combinations
     * IA give clues in order to help player finding the secret combination
     */
    @Override
    public void playWithTwoPlayers(Player attack, Player defense) {
        config.getMsgInfo().choiceGameChallenger();
        int counter = config.getMaxTries();
        int[] attackCombination;

        config.getMsgInfo().player1();
        config.getMsgInfo().secretCombinationPlayer();
        int[] defenseCombination = defense.initialiseCombination();
        if (config.isDevMode())
            config.getMsgCombination().devMode(defenseCombination);

        config.getMsgInfo().player2();
        attackCombination = attack.research(null);
        config.getMsgCombination().newAnswer(attackCombination);
        String[] clues = defense.clues(attackCombination);
        config.getMsgCombination().cluesAre(clues);
        if (IsWin.winIf(clues)) {
            config.getMsgInfo().isWin();
            return;
        }
        counter--;
        config.getMsgInfo().counterLess(counter);

        do {
            config.getMsgInfo().player2();
            attackCombination = attack.research(clues);
            config.getMsgCombination().newAnswer(attackCombination);
            clues = defense.clues(attackCombination);

            if (IsWin.winIf(clues)) {
                config.getMsgInfo().isWin();
                return;
            }

            if (counter > 1) {
                config.getMsgCombination().cluesAre(clues);
                counter--;
                config.getMsgInfo().counterLess(counter);
            } else if (counter == 1) {
                if(!Arrays.equals(defenseCombination, attackCombination)) {
                    config.getMsgInfo().attackerLoose();
                    config.getMsgCombination().finallyRevealSecretCombination(defenseCombination);
                }
                break;
            }
        } while (true);
    }
}