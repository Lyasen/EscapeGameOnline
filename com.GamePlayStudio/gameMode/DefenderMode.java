package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MessageCombination;
import gameMessage.MessageInfo;
import player.Player;

import java.util.Arrays;

public class DefenderMode extends Mode {
    private MessageInfo mi = new MessageInfo();
    private MessageCombination mc = new MessageCombination();
    private String[] clues = new String[config.getDigitsCombination()];

    public DefenderMode(ConfigurationGame config) {
        super(config);
    }

    /**
     * defense suggest a secret combination
     * attack try to find the secret number
     */
    @Override
    public void playWithTwoPlayers(Player defense, Player attack) {
        mi.choiceGameDefender();
        int counter = config.getMaxTries();
        mi.counter(counter);
        int[] combinationDefender = defense.research(clues);
        mc.newAnswer(combinationDefender);

        int[] combinationAttacker = attack.initialiseCombination();
        mc.propositionOpponent(combinationAttacker);

        do {
            String[] clew = defense.clues(combinationAttacker);
            combinationAttacker = attack.research(clew);
            mc.propositionOpponent(combinationAttacker);
            counter--;

            if (Arrays.equals(combinationAttacker, combinationDefender)) {
                mi.attackerFindSecretNumber();
                break;
            } else if (counter == 0) {
                mi.attackerLoose();
                break;
            } else {
                mi.counterLess(counter);
            }
        } while (true);
    }
}