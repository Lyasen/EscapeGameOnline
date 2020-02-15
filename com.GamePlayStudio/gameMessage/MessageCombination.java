package gameMessage;

import java.util.Arrays;

public class MessageCombination {
    public void reminderP2(int[] propositionPlayer, String[] answerPlayer){
        System.out.println("\nPlayer2's turn !\nI remember you have chosen the following last combination : " + Arrays.toString(propositionPlayer) +
                "\nand the last clues was : " + Arrays.toString(answerPlayer));
    }

    public void reminderP1(int[] propositionPlayer, String[] answerPlayer){
        System.out.println("\nPlayer1's turn !\nI remember you have chosen the following last combination : " + Arrays.toString(propositionPlayer) +
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

    public void propositionPlayer2(int[] proposition){
        System.out.println("Proposition from player2 : " + Arrays.toString(proposition));
    }

    public void propositionPlayer1(int[] proposition){
        System.out.println("Proposition from player1 : " + Arrays.toString(proposition));
    }

    public void propositionAttacker(int[] combinationAttacker){
        System.out.println("\nAttacker's proposition : " + Arrays.toString(combinationAttacker));
    }

    public void cluesPlayer1ToPlayer2(String[] answerPlayer1){
        System.out.println("Clues from player1 to player2 : " + Arrays.toString(answerPlayer1));
    }

    public void cluesPlayer2ToPlayer1(String[] answerPlayer2){
        System.out.println("Clues from player2 to player1 : " + Arrays.toString(answerPlayer2));
    }

    public void newAnswer(int[] propositionPlayer){
        System.out.println("Your answer is " + Arrays.toString(propositionPlayer));
    }

    public void finallyRevealSecretCombination(int[] defenseCombination){
        System.out.println("What a pity ! It's lost ! The secret number was : "
                + Arrays.toString(defenseCombination) + " ! Try next time !");
    }
}
