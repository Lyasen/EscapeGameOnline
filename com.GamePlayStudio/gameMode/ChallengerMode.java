package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MessageCombination;
import gameMessage.MessageInfo;
import player.Player;
import utils.IsWin;

public class ChallengerMode extends Mode {
    private MessageInfo mi = new MessageInfo();
    private MessageCombination mc = new MessageCombination();

    public ChallengerMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * Player suggest combinations
     * IA give clues in order to help player finding the secret combination
     */
    @Override
    public void playWithTwoPlayers(Player attack, Player defense) {
        mi.choiceGameChallenger();
        int counter = config.getMaxTries();
        int[] attackCombination;
        int[] defenseCombination = defense.initialiseCombination();
        if (config.isDevMode())
            mc.devMode(defenseCombination);

        do {
            attackCombination = attack.initialiseCombination();
            mc.newAnswer(attackCombination);
            String[] clues = defense.clues(attackCombination);

            if (counter > 1) {
                mc.cluesAre(clues);
                counter--;
            } else if (counter == 1) {
                mi.lastTimeToFindCombination();
                counter--;
            }

            if (IsWin.winIf(clues)) {
                mi.isWin();
                return;
            } else if (counter == 0) {
                mc.finallyRevealSecretCombination(defenseCombination);
                return;
            } else {
                mi.counterLess(counter);
            }
        } while (true);
    }
}