package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MsgCombination;
import gameMessage.MsgInfo;
import player.Player;
import utils.IsWin;

import java.util.Arrays;

public class DuelMode extends Mode implements MsgCombination, MsgInfo {
    public DuelMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * Both players have a secret number and try to find the each other's secret combination
     */
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        choiceGameDuel();
        int counter = config.getMaxTries();
        counter(counter);

        //  Secret combination Player1
        player1();
        secretCombinationPlayer();
        int[] secretCombinationPlayer1 = player1.initialiseCombination();
        if (config.isDevMode())
            devMode(secretCombinationPlayer1);

        //  Proposition Player2
        player2();
        int[] propositionPlayer2 = player2.research(null);
        propositionPlayer(propositionPlayer2);
        String[] answerPlayer1 = player1.clues(propositionPlayer2);
        cluesAre(answerPlayer1);
        if (IsWin.winIf(answerPlayer1)) {
            playerWin();
            return;
        }

        //  Secret combination Player2
        player2();
        secretCombinationPlayer();
        int[] secretCombinationPlayer2 = player2.initialiseCombination();
        if (config.isDevMode())
            devMode(secretCombinationPlayer2);

        //  proposition player1
        player1();
        int[] propositionPlayer1 = player1.research(null);
        propositionPlayer(propositionPlayer1);
        String[] answerPlayer2 = player2.clues(propositionPlayer1);
        cluesAre(answerPlayer2);
        if (IsWin.winIf(answerPlayer2)) {
            playerWin();
            return;
        }

        counter--;
        counterLess(counter);

        do {
            //  Player2's proposition
            player2();
            reminderPlayer(propositionPlayer2, answerPlayer1);
            propositionPlayer2 = player2.research(answerPlayer1);
            newAnswer(propositionPlayer2);

            if (Arrays.equals(propositionPlayer2, secretCombinationPlayer1)) {
                playerWin();
                return;
            }

            if (counter > 1) {
                answerPlayer1 = player1.clues(propositionPlayer2);
                cluesAre(answerPlayer1);
            } else if (counter == 1) {
                if (!Arrays.equals(propositionPlayer2, secretCombinationPlayer1))
                    notGood();
            }

            //  Player1's proposition
            player1();
            reminderPlayer(propositionPlayer1, answerPlayer2);
            propositionPlayer1 = player1.research(answerPlayer2);
            newAnswer(propositionPlayer1);

            if (Arrays.equals(propositionPlayer1, secretCombinationPlayer2)) {
                playerWin();
                return;
            }

            if (counter > 1) {
                answerPlayer2 = player2.clues(propositionPlayer1);
                cluesAre(answerPlayer2);
            } else if (counter == 1) {
                if (!Arrays.equals(propositionPlayer1, secretCombinationPlayer2))
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
