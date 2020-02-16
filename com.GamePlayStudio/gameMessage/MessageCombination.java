package gameMessage;

import java.util.Arrays;

public class MessageCombination {
    public void reminderPlayer(int[] propositionPlayer, String[] answerPlayer){
        System.out.println("I remember you have chosen the following last combination : " + Arrays.toString(propositionPlayer) +
                "\nand the last clues was : " + Arrays.toString(answerPlayer));
    }

    public void seeRandom(int[] secret){
        System.out.println(Arrays.toString(secret));
    }

    public void cluesAre(String[] clues){
        System.out.println("Clues are : " + Arrays.toString(clues));
    }

    public void devMode(int[] secretCombination){
        System.out.println("DevMode: " + Arrays.toString(secretCombination));
    }

    public void propositionPlayer(int[] proposition){
        System.out.println("Your Proposition : " + Arrays.toString(proposition));
    }

    public void propositionOpponent(int[] proposition){
        System.out.println("\nProposal of your opponent : " + Arrays.toString(proposition));
    }

    public void newAnswer(int[] propositionPlayer){
        System.out.println("Your answer is " + Arrays.toString(propositionPlayer));
    }

    public void finallyRevealSecretCombination(int[] defenseCombination){
        System.out.println("What a pity ! It's lost ! The secret number was : "
                + Arrays.toString(defenseCombination) + " ! Try next time !");
    }
}
