package gameMessage;

import java.util.Arrays;

public interface MsgCombination {
    default void reminderPlayer(int[] propositionPlayer, String[] answerPlayer){
        System.out.println("I remember you have chosen the following last combination : " + Arrays.toString(propositionPlayer) +
                "\nand the last clues was : " + Arrays.toString(answerPlayer));
    }

    default void seeRandom(int[] secret){
        System.out.println(Arrays.toString(secret));
    }

    default void cluesAre(String[] clues){
        System.out.println("Clues are : " + Arrays.toString(clues));
    }

    default void devMode(int[] secretCombination){
        System.out.println("DevMode: " + Arrays.toString(secretCombination));
    }

    default void propositionPlayer(int[] proposition){
        System.out.println("Your Proposition : " + Arrays.toString(proposition));
    }

    default void propositionOpponent(int[] proposition){
        System.out.println("\nProposal of your opponent : " + Arrays.toString(proposition));
    }

    default void newAnswer(int[] propositionPlayer){
        System.out.println("Your answer is " + Arrays.toString(propositionPlayer));
    }

    default void finallyRevealSecretCombination(int[] defenseCombination){
        System.out.println("What a pity ! It's lost ! The secret number was : "
                + Arrays.toString(defenseCombination) + " ! Try next time !");
    }
}
