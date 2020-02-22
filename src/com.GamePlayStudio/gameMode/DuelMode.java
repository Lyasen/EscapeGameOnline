package com.GamePlayStudio.gameMode;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.player.Player;
import com.GamePlayStudio.utils.IsWin;

import java.util.Arrays;

public class DuelMode extends Mode {
    public DuelMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * Both players have a secret number and try to find the each other's secret combination
     */
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        config.getMsgInfo().choiceGameDuel();
        int counter = config.getMaxTries();
        config.getMsgInfo().counter(counter);

        //  Secret combination Player1
        config.getMsgInfo().player1();
        config.getMsgInfo().secretCombinationPlayer();
        int[] secretCombinationPlayer1 = player1.initialiseCombination();
        if (config.isDevMode())
            config.getMsgCombination().devMode(secretCombinationPlayer1);

        //  Proposition Player2
        config.getMsgInfo().player2();
        int[] propositionPlayer2 = player2.research(null);
        config.getMsgCombination().propositionPlayer(propositionPlayer2);
        String[] answerPlayer1 = player1.clues(propositionPlayer2);
        config.getMsgCombination().cluesAre(answerPlayer1);
        if (IsWin.winIf(answerPlayer1)) {
            config.getMsgInfo().playerWin();
            return;
        }

        //  Secret combination Player2
        config.getMsgInfo().player2();
        config.getMsgInfo().secretCombinationPlayer();
        int[] secretCombinationPlayer2 = player2.initialiseCombination();
        if (config.isDevMode())
            config.getMsgCombination().devMode(secretCombinationPlayer2);

        //  proposition player1
        config.getMsgInfo().player1();
        int[] propositionPlayer1 = player1.research(null);
        config.getMsgCombination().propositionPlayer(propositionPlayer1);
        String[] answerPlayer2 = player2.clues(propositionPlayer1);
        config.getMsgCombination().cluesAre(answerPlayer2);
        if (IsWin.winIf(answerPlayer2)) {
            config.getMsgInfo().playerWin();
            return;
        }

        counter--;
        config.getMsgInfo().counterLess(counter);

        do {
            //  Player2's proposition
            config.getMsgInfo().player2();
            config.getMsgCombination().reminderPlayer(propositionPlayer2, answerPlayer1);
            propositionPlayer2 = player2.research(answerPlayer1);
            config.getMsgCombination().newAnswer(propositionPlayer2);

            if (Arrays.equals(propositionPlayer2, secretCombinationPlayer1)) {
                config.getMsgInfo().playerWin();
                return;
            }

            if (counter > 1) {
                answerPlayer1 = player1.clues(propositionPlayer2);
                config.getMsgCombination().cluesAre(answerPlayer1);
            } else if (counter == 1) {
                if (!Arrays.equals(propositionPlayer2, secretCombinationPlayer1))
                    config.getMsgInfo().notGood();
            }

            //  Player1's proposition
            config.getMsgInfo().player1();
            config.getMsgCombination().reminderPlayer(propositionPlayer1, answerPlayer2);
            propositionPlayer1 = player1.research(answerPlayer2);
            config.getMsgCombination().newAnswer(propositionPlayer1);

            if (Arrays.equals(propositionPlayer1, secretCombinationPlayer2)) {
                config.getMsgInfo().playerWin();
                return;
            }

            if (counter > 1) {
                answerPlayer2 = player2.clues(propositionPlayer1);
                config.getMsgCombination().cluesAre(answerPlayer2);
            } else if (counter == 1) {
                if (!Arrays.equals(propositionPlayer1, secretCombinationPlayer2))
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
