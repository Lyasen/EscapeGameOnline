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
        int[] defenseCombination = defense.initialiseCombination();
        if (config.isDevMode())
            devMode(defenseCombination);

        do {
            attackCombination = attack.initialiseCombination();
            newAnswer(attackCombination);
            String[] clues = defense.clues(attackCombination);

            if (counter > 1) {
                cluesAre(clues);
                counter--;
            } else if (counter == 1) {
                lastTimeToFindCombination();
                counter--;
            }

            if (IsWin.winIf(clues)) {
                isWin();
                return;
            } else if (counter == 0) {
                finallyRevealSecretCombination(defenseCombination);
                return;
            } else {
                counterLess(counter);
            }
        } while (true);
    }
}