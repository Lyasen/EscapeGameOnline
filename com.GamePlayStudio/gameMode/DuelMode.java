package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MessageCombination;
import gameMessage.MessageInfo;
import player.Player;
import utils.IsWin;

public class DuelMode extends Mode {
    private MessageInfo mi = new MessageInfo();
    private MessageCombination mc = new MessageCombination();

    public DuelMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * Both players have a secret number and try to find the each other's secret combination
     */
    @Override
    public void playWithTwoPlayers(Player player1, Player player2) {
        mi.choiceGameDuel();
        int counter = config.getMaxTries();
        mi.counter(counter);

        //  Secrets combinations
        mi.player1();
        mi.secretCombinationPlayer();
        int[] secretCombinationPlayer1 = player1.initialiseCombination();
        if (config.isDevMode())
            mc.devMode(secretCombinationPlayer1);

        mi.player2();
        mi.secretCombinationPlayer();
        int[] secretCombinationPlayer2 = player2.initialiseCombination();
        if (config.isDevMode())
            mc.devMode(secretCombinationPlayer2);

        //  propositions players
        mi.player2();
        int[] propositionPlayer2 = player2.initialiseCombination();
        mc.propositionPlayer(propositionPlayer2);
        String[] answerPlayer1 = player1.clues(propositionPlayer2);
        if (IsWin.winIf(answerPlayer1)) {
            mi.playerWin();
            return;
        }

        mi.player1();
        int[] propositionPlayer1 = player1.initialiseCombination();
        mc.propositionPlayer(propositionPlayer1);
        String[] answerPlayer2 = player2.clues(propositionPlayer1);
        mc.cluesAre(answerPlayer2);
        if (IsWin.winIf(answerPlayer2)) {
            mi.playerWin();
            return;
        }

        counter--;
        mi.counterLess(counter);

        do {
            /*
             * Player2's proposition
             */
            mi.player2();
            mc.reminderPlayer(propositionPlayer2, answerPlayer1);
            propositionPlayer2 = player2.research(answerPlayer1);
            mc.newAnswer(propositionPlayer2);
            answerPlayer1 = player1.clues(propositionPlayer2);

            if (counter == 1) {
                if (!IsWin.winIf(answerPlayer1))
                    mi.notGood();
            }

             if (IsWin.winIf(answerPlayer1)) {
                mi.playerWin();
                break;
            }

            /*
             * Player1's proposition
             */
            mi.player1();
            mc.reminderPlayer(propositionPlayer1, answerPlayer2);
            propositionPlayer1 = player1.research(answerPlayer2);
            mc.newAnswer(propositionPlayer1);
            answerPlayer2 = player2.clues(propositionPlayer1);
            mc.cluesAre(answerPlayer2);
            if (counter == 1) {
                if (!IsWin.winIf(answerPlayer2))
                    mi.notGood();
            }

            if (IsWin.winIf(answerPlayer2)) {
                mi.playerWin();
                break;
            }

            counter--;
            mi.counterLess(counter);

            if (counter == 0) {
                mi.endGameDuel();
                break;
            }
        } while (true);
    }
}
