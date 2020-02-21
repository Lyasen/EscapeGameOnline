package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MsgCombination;
import gameMessage.MsgInfo;
import player.Player;
import utils.IsWin;

public class ChallengerMode extends Mode implements MsgCombination, MsgInfo {
    public ChallengerMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * Player suggest combinations
     * IA give clues in order to help player finding the secret combination
     */
    @Override
    public void playWithTwoPlayers(Player attack, Player defense) {
        choiceGameChallenger();
        int counter = config.getMaxTries();
        int[] attackCombination;
        player1();
        secretCombinationPlayer();
        int[] defenseCombination = defense.initialiseCombination();
        if (config.isDevMode())
            devMode(defenseCombination);

        player2();
        attackCombination = attack.research(null);
        newAnswer(attackCombination);
        String[] clues = defense.clues(attackCombination);
        cluesAre(clues);
        if (IsWin.winIf(clues)) {
            isWin();
            return;
        }
        counter--;
        counterLess(counter);

        do {
            player2();
            attackCombination = attack.research(clues);
            newAnswer(attackCombination);
            clues = defense.clues(attackCombination);

            if (IsWin.winIf(clues)) {
                isWin();
                return;
            }

            if (counter > 1) {
                cluesAre(clues);
                counter--;
                counterLess(counter);
            } else if (counter == 1) {
                lastTimeToFindCombination();
                counter--;
            } else if (counter == 0) {
                finallyRevealSecretCombination(defenseCombination);
                return;
            }

        } while (true);
    }
}