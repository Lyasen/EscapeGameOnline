package gameMessage;

public class MessageInfo {
    public void welcomeInGame(){
        System.out.println("Welcome to Escape Game Online");
    }

    public void chooseGameMode(){
        System.out.println("\nPlease choose your game mode :\n1- Challenger\n2- defender\n3- Duel\n4- Bonus mode");
    }

    public void chooseEndGame(){
        System.out.println("\nThe game is now finished !\n1 - You may play again \n2 - You can come back to the menu \n3 - You can go swimming");
    }

    public void choiceGameDuel(){
        System.out.println("You have choice the game mode : Duel\nWho will be faster to find each other’s secret combination ?");
    }

    public void choiceGameDefender(){
        System.out.println("You have choice the game mode : Defender\nAttacker try to find the secret combination !");
    }

    public void choiceGameChallenger(){
        System.out.println("You have choice the game mode : Challenger\nTry to find the secret number !");
    }

    public void choiceGameBonus(int counter){
        System.out.println("You have choice the game mode : Bonus mode\nChallenge the computer\nNow, computer and human deliver a real fight !\nYou'll have " +
                counter + " tries\n");
    }

    public void playSameGame(){
        System.out.println("You want to play more ! Good luck !");
    }

    public void backMenu(){
        System.out.println("Back to the menu !");
    }

    public void stopGame(){
        System.out.println("Thank you to play with us ! See you soon !");
    }

    public void player1(){
        System.out.println("\nPlayer1 ! ");
    }

    public void player2(){
        System.out.println("\nPlayer2 ! ");
    }

    public void counter(int counter){
        System.out.println("Now, let's fight ! You have " + counter + " tries");
    }

    public void counterLess(int counter){
        System.out.printf("\nIt stays %d tries\n", counter);
    }

    public void seeClues(){ System.out.print("Now let's see ! "); }

    public void giveClues(){
        System.out.println("Please, give clues : ");
    }

    public void doProposition(){
        System.out.println("Do your proposition : ");
    }

    public void secretCombinationPlayer(){
        System.out.println("Choose your secret combination");
    }

    public void playerWin(){
        System.out.println("\nWell done ! You WIN !");
    }

    public void notGood(){
        System.out.println("Sorry ! Seems not good !");
    }

    public void endGameDuel(){
        System.out.println("\nSorry, no more tries ! Both players don't found any combination !\nyou are really not good ! Try next time !");
    }

    public void attackerFindSecretNumber(){
        System.out.println("Attacker has found the secret number ! Defender is mortify !");
    }

    public void attackerLoose(){
        System.out.println("No more tries ! Attacker has lost ! \nNot even able to find a simple combination of digits ! What misery !");
    }

    public void lastTimeToFindCombination(){
        System.out.print("It was the last time to find the secret number, Soooo...");
    }

    public void isWin(){
        System.out.println("Well done ! You're the very best Mastermind i've never seen in my life... No, i'm kidding !");
    }
}
