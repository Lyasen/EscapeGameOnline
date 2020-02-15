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
        mi.secretCombinationP1();
        int[] secretCombinationPlayer1 = player1.initialiseCombination();
        if (config.isDevMode())
            mc.devMode(secretCombinationPlayer1);

        mi.secretCombinationP2();
        int[] secretCombinationPlayer2 = player2.initialiseCombination();
        if (config.isDevMode())
            mc.devMode(secretCombinationPlayer2);

        //  propositions players
        int[] propositionPlayer2 = player2.initialiseCombination();
        mc.propositionPlayer2(propositionPlayer2);
        String[] answerPlayer1 = player1.clues(propositionPlayer2);
        mc.cluesPlayer1ToPlayer2(answerPlayer1);
        if (IsWin.winIf(answerPlayer1)) {
            mi.player2Win();
            return;
        }

        int[] propositionPlayer1 = player1.initialiseCombination();
        mc.propositionPlayer1(propositionPlayer1);
        String[] answerPlayer2 = player2.clues(propositionPlayer1);
        mc.cluesPlayer2ToPlayer1(answerPlayer2);
        if (IsWin.winIf(answerPlayer2)) {
            mi.player1Win();
            return;
        }

        counter--;
        mi.counterLess(counter);

        do {
            /*
             * Player2's proposition
             */
            mc.reminderP2(propositionPlayer2, answerPlayer1);
            propositionPlayer2 = player2.research(answerPlayer1);
            mc.newAnswer(propositionPlayer2);
            answerPlayer1 = player1.clues(propositionPlayer2);

            if (counter > 1)
                mc.cluesAre(answerPlayer1);
            else if (counter == 1) {
                if (!IsWin.winIf(answerPlayer1))
                    mi.notGood();
            }

             if (IsWin.winIf(answerPlayer1)) {
                mi.player2Win();
                break;
            }

            /*
             * Player1's proposition
             */
            mc.reminderP1(propositionPlayer1, answerPlayer2);
            propositionPlayer1 = player1.research(answerPlayer2);
            mc.newAnswer(propositionPlayer1);
            answerPlayer2 = player2.clues(propositionPlayer1);
            if (counter > 1)
                mc.cluesAre(answerPlayer2);
            else if (counter == 1) {
                if (!IsWin.winIf(answerPlayer2))
                    mi.notGood();
            }

            if (IsWin.winIf(answerPlayer2)) {
                mi.player1Win();
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
