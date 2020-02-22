package com.GamePlayStudio.gameMode;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.player.IAPlayer;
import com.GamePlayStudio.player.Player;
import com.GamePlayStudio.utils.IsWin;

import java.util.Arrays;

public class BonusMode extends Mode {
    public BonusMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * A special mode where player and IA try to find the secret combination of the computer
     */
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        int[] combinationPlayer;
        int counter = config.getMaxTries();
        config.getMsgInfo().counter(counter);

        config.getMsgInfo().choiceGameBonus();
        //  initialize a random number that both players will try to find
        IAPlayer ia = new IAPlayer(config);
        int[] defenseCombination = ia.initialiseCombination();
        if (config.isDevMode())
            config.getMsgCombination().devMode(defenseCombination);
        config.getMsgInfo().computer();

        //  Proposition player1
        config.getMsgInfo().player1();
        combinationPlayer = player1.research(null);
        config.getMsgCombination().newAnswer(combinationPlayer);
        String[] clues = ia.clues(combinationPlayer);
        if (IsWin.winIf(clues)) {
            config.getMsgInfo().isWin();
            return;
        } else if (counter > 1) {
            config.getMsgCombination().cluesAre(clues);
        }

        //  Proposition player2
        config.getMsgInfo().player2();
        combinationPlayer = player1.research(clues);
        config.getMsgCombination().newAnswer(combinationPlayer);
        clues = ia.clues(combinationPlayer);
        if (IsWin.winIf(clues)) {
            config.getMsgInfo().isWin();
            return;
        } else if (counter > 1) {
            config.getMsgCombination().cluesAre(clues);
        }

        counter--;
        config.getMsgInfo().counterLess(counter);

        do {
            //  Proposition player1
            config.getMsgInfo().player1();
            combinationPlayer = player1.research(clues);
            config.getMsgCombination().newAnswer(combinationPlayer);
            clues = ia.clues(combinationPlayer);
            if (IsWin.winIf(clues)) {
                config.getMsgInfo().isWin();
                return;
            } else if (counter > 1) {
                config.getMsgCombination().cluesAre(clues);
            } else if (counter == 1) {
                if (!Arrays.equals(combinationPlayer, defenseCombination))
                    config.getMsgInfo().notGood();
            }

            //  Proposition player2
            config.getMsgInfo().player2();
            combinationPlayer = player1.research(clues);
            config.getMsgCombination().newAnswer(combinationPlayer);
            clues = ia.clues(combinationPlayer);
            if (IsWin.winIf(clues)) {
                config.getMsgInfo().isWin();
                return;
            } else if (counter > 1) {
                config.getMsgCombination().cluesAre(clues);
            } else if (counter == 1) {
                if (!Arrays.equals(combinationPlayer, defenseCombination))
                    config.getMsgInfo().notGood();
            }

            counter--;
            if (counter >= 1)
                config.getMsgInfo().counterLess(counter);
            else {
                config.getMsgInfo().endGameDuel();
                break;
            }
        } while (true);
    }
}