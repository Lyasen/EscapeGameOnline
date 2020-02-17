package gameMessage;

public interface MsgInfo {
    static void welcomeInGame(){
        System.out.println("Welcome to Escape Game Online");
    }

    default void chooseGameMode(){
        System.out.println("\nPlease choose your game mode :\n1- Challenger\n2- defender\n3- Duel\n4- Bonus mode");
    }

    default void chooseEndGame(){
        System.out.println("\nThe game is now finished !\n1 - You may play again \n2 - You can come back to the menu \n3 - You can go swimming");
    }

    default void choiceGameDuel(){
        System.out.println("You have choice the game mode : Duel\nWho will be faster to find each other’s secret combination ?");
    }

    default void choiceGameDefender(){
        System.out.println("You have choice the game mode : Defender\nAttacker try to find the secret combination !");
    }

    default void choiceGameChallenger(){
        System.out.println("You have choice the game mode : Challenger\nTry to find the secret number !");
    }

    default void choiceGameBonus(){
        System.out.println("You have choice the game mode : Bonus mode\nChallenge the computer\nNow, computer and human deliver a real fight !");
    }

    default void playSameGame(){ System.out.println("You want to play more ! Good luck !"); }

    default void backMenu(){
        System.out.println("Back to the menu !");
    }

    default void stopGame(){
        System.out.println("Thank you to play with us ! See you soon !");
    }

    default void player1(){
        System.out.println("\nPlayer1 ! ");
    }

    default void player2(){
        System.out.println("\nPlayer2 ! ");
    }

    default void counter(int counter){
        System.out.println("Now, let's fight ! You have " + counter + " tries");
    }

    default void counterLess(int counter){
        System.out.printf("\nIt stays %d tries\n", counter);
    }

    default void seeClues(){ System.out.print("Now let's see ! "); }

    default void giveClues(){
        System.out.println("Please, give clues : ");
    }

    default void doProposition(){
        System.out.println("Do your proposition : ");
    }

    default void secretCombinationPlayer(){
        System.out.println("Choose your secret combination");
    }

    default void playerWin(){
        System.out.println("\nWell done ! You WIN !");
    }

    default void notGood(){
        System.out.println("Sorry ! Seems not good !");
    }

    default void endGameDuel(){
        System.out.println("\nSorry, no more tries ! Both players don't found any combination !\nyou are really not good ! Try next time !");
    }

    default void attackerFindSecretNumber(){
        System.out.println("Attacker has found the secret number ! Defender is mortify !");
    }

    default void attackerLoose(){
        System.out.println("No more tries ! Attacker has lost ! \nNot even able to find a simple combination of digits ! What misery !");
    }

    default void lastTimeToFindCombination(){
        System.out.print("It was the last time to find the secret number, Soooo...");
    }

    default void isWin(){
        System.out.println("Well done ! You're the very best Mastermind i've never seen in my life... No, i'm kidding !");
    }
}
