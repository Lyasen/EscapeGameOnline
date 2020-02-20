package com.GamePlayStudio.gameMode;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import com.GamePlayStudio.gameMessage.MsgCombination;
import com.GamePlayStudio.gameMessage.MsgInfo;
import com.GamePlayStudio.player.IAPlayer;
import com.GamePlayStudio.player.Player;
import com.GamePlayStudio.utils.IsWin;

public class BonusMode extends Mode implements MsgCombination, MsgInfo {
    public BonusMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * A special mode where player and IA try to find the secret combination of the computer
     */
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        int[] combinationPlayer2;
        int counter = config.getMaxTries();
        counter(counter);

        choiceGameBonus();
        //  initialize a random number that both players will try to find
        IAPlayer ia = new IAPlayer(config);
        int[] defenseCombination = ia.initialiseCombination();
        if (config.isDevMode())
            devMode(defenseCombination);


        //  Proposition player1
        player1();
        int[] combinationPlayer1 = player1.initialiseCombination();
        newAnswer(combinationPlayer1);
        String[] clues = ia.clues(combinationPlayer1);
        if (IsWin.winIf(clues)) {
            isWin();
        }
        cluesAre(clues);

        //  TODO: Problème de tours au niveau du compteur => Player1 joue 2 fois

        do {
            //  Proposition player2
            player2();
            combinationPlayer2 = player2.research(clues);
            newAnswer(combinationPlayer2);
            clues = ia.clues(combinationPlayer2);
            if (IsWin.winIf(clues)) {
                isWin();
                break;
            }
            cluesAre(clues);

            //  Proposition player1
            player1();
            combinationPlayer1 = player1.research(clues);
            newAnswer(combinationPlayer1);
            clues = ia.clues(combinationPlayer1);

            if (IsWin.winIf(clues)) {
                isWin();
                break;
            } else if (counter > 1) {
                cluesAre(clues);
            } else if (counter == 1) {
                lastTimeToFindCombination();
            }

            counter--;
            counterLess(counter);

            if (counter == 0) {
                endGameDuel();
                break;
            }
        } while (true);
    }
}
