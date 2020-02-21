package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MsgCombination;
import gameMessage.MsgInfo;
import player.IAPlayer;
import player.Player;
import utils.IsWin;

import java.util.Arrays;

public class BonusMode extends Mode implements MsgCombination, MsgInfo {
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
        counter(counter);

        choiceGameBonus();
        //  initialize a random number that both players will try to find
        IAPlayer ia = new IAPlayer(config);
        int[] defenseCombination = ia.initialiseCombination();
        if (config.isDevMode())
            devMode(defenseCombination);
        computer();

        //  Proposition player1
        player1();
        combinationPlayer = player1.research(null);
        newAnswer(combinationPlayer);
        String[] clues = ia.clues(combinationPlayer);
        if (IsWin.winIf(clues)) {
            isWin();
            return;
        } else if (counter > 1) {
            cluesAre(clues);
        }

        //  Proposition player2
        player2();
        combinationPlayer = player1.research(clues);
        newAnswer(combinationPlayer);
        clues = ia.clues(combinationPlayer);
        if (IsWin.winIf(clues)) {
            isWin();
            return;
        } else if (counter > 1) {
            cluesAre(clues);
        }

        counter--;
        counterLess(counter);

        do {
            //  Proposition player1
            player1();
            combinationPlayer = player1.research(clues);
            newAnswer(combinationPlayer);
            clues = ia.clues(combinationPlayer);
            if (IsWin.winIf(clues)) {
                isWin();
                return;
            } else if (counter > 1) {
                cluesAre(clues);
            } else if (counter == 1) {
                if (!Arrays.equals(combinationPlayer, defenseCombination))
                    notGood();
            }

            //  Proposition player2
            player2();
            combinationPlayer = player1.research(clues);
            newAnswer(combinationPlayer);
            clues = ia.clues(combinationPlayer);
            if (IsWin.winIf(clues)) {
                isWin();
                return;
            } else if (counter > 1) {
                cluesAre(clues);
            } else if (counter == 1) {
                if (!Arrays.equals(combinationPlayer, defenseCombination))
                    notGood();
            }

            counter--;
            if (counter >= 1)
                counterLess(counter);
            else {
                endGameDuel();
                break;
            }
        } while (true);
    }
}