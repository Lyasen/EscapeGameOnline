package gameMode;

import domaine.properties.ConfigurationGame;
import gameMessage.MsgCombination;
import gameMessage.MsgInfo;
import player.Player;

import java.util.Arrays;

public class DefenderMode extends Mode implements MsgCombination, MsgInfo {
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

        player1();
        int[] combinationDefender = defense.initialiseCombination();
        newAnswer(combinationDefender);

        player2();
        int[] combinationAttacker = attack.research(null);
        propositionPlayer(combinationAttacker);
        String[] clues = defense.clues(combinationAttacker);
        cluesAre(clues);
        counter--;
        counterLess(counter);

        do {
            player2();
            combinationAttacker = attack.research(clues);
            propositionPlayer(combinationAttacker);


            if (Arrays.equals(combinationAttacker, combinationDefender)) {
                attackerFindSecretNumber();
                return;
            }

            if (counter > 1) {
                clues = defense.clues(combinationAttacker);
                cluesAre(clues);
                counter--;
                counterLess(counter);
            } else if (counter == 1) {
                lastTimeToFindCombination();
                counter--;
            } else if (counter == 0) {
                attackerLoose();
                return;
            }
        } while (true);
    }
}