package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MsgCombination;
import gameMessage.MsgInfo;
import player.Player;

import java.util.Arrays;

public class DefenderMode extends Mode implements MsgCombination, MsgInfo {
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
        choiceGameDefender();
        int counter = config.getMaxTries();
        counter(counter);
        int[] combinationDefender = defense.research(clues);
        newAnswer(combinationDefender);

        int[] combinationAttacker = attack.initialiseCombination();
        propositionOpponent(combinationAttacker);

        do {
            String[] clew = defense.clues(combinationAttacker);
            combinationAttacker = attack.research(clew);
            propositionOpponent(combinationAttacker);
            counter--;

            if (Arrays.equals(combinationAttacker, combinationDefender)) {
                attackerFindSecretNumber();
                break;
            } else if (counter == 0) {
               attackerLoose();
                break;
            } else {
                counterLess(counter);
            }
        } while (true);
    }
}