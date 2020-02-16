package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MessageCombination;
import gameMessage.MessageInfo;
import player.IAPlayer;
import player.Player;
import utils.IsWin;

public class BonusMode extends Mode {
    private MessageInfo mi = new MessageInfo();
    private MessageCombination mc = new MessageCombination();

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

        mi.choiceGameBonus(counter);
        //  initialize a random number that both players will try to find
        IAPlayer ia = new IAPlayer(config);
        int[] defenseCombination = ia.initialiseCombination();
        if (config.isDevMode())
            mc.devMode(defenseCombination);


        //  Proposition player1
        mi.player1();
        int[] combinationPlayer1 = player1.initialiseCombination();
        mc.newAnswer(combinationPlayer1);
        String[] clues = ia.clues(combinationPlayer1);
        if (IsWin.winIf(clues)) {
            mi.isWin();
        }
        mc.cluesAre(clues);

        //  TODO: Problème de tours au niveau du compteur => Player1 joue 2 fois

        do {
            //  Proposition player2
            mi.player2();
            combinationPlayer2 = player2.research(clues);
            mc.newAnswer(combinationPlayer2);
            clues = ia.clues(combinationPlayer2);
            if (IsWin.winIf(clues)) {
                mi.isWin();
                break;
            }
            mc.cluesAre(clues);

            //  Proposition player1
            mi.player1();
            combinationPlayer1 = player1.research(clues);
            mc.newAnswer(combinationPlayer1);
            clues = ia.clues(combinationPlayer1);

            if (IsWin.winIf(clues)) {
                mi.isWin();
                break;
            } else if (counter > 1) {
                mc.cluesAre(clues);
            } else if (counter == 1) {
                mi.lastTimeToFindCombination();
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