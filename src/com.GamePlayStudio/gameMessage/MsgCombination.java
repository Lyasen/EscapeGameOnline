package com.GamePlayStudio.gameMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public interface MsgCombination {
    Logger log = LogManager.getLogger(MsgCombination.class);

    default void reminderPlayer(int[] propositionPlayer, String[] answerPlayer){
        log.info("I remember you have chosen the following last combination : " + Arrays.toString(propositionPlayer) +
                "\nand the last clues was : " + Arrays.toString(answerPlayer));
    }

    default void cluesAre(String[] clues){
        log.info("Clues are : " + Arrays.toString(clues));
    }

    default void devMode(int[] secretCombination){
        log.info("DevMode: " + Arrays.toString(secretCombination));
    }

    default void propositionPlayer(int[] proposition){
        log.info("Your Proposition : " + Arrays.toString(proposition));
    }

    default void newAnswer(int[] propositionPlayer){
        log.info("Your answer is " + Arrays.toString(propositionPlayer));
    }

    default void finallyRevealSecretCombination(int[] defenseCombination){
        log.info("The secret number was : " + Arrays.toString(defenseCombination) + " ! Try next time !");
    }
}
