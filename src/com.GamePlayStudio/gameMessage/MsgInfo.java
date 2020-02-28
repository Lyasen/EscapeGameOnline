package com.GamePlayStudio.gameMessage;

import com.GamePlayStudio.domaine.properties.ConfigurationGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface MsgInfo {
    Logger log = LogManager.getLogger(MsgInfo.class);

    default void welcomeInGame(){
        log.info("Welcome to Escape Game Online");
    }

    default void rules(ConfigurationGame config){
        log.info("Here are the rules to play ! Good Luck !\nNumber of digits in combination : " + config.getDigitsCombination() +
                "\nEach number in combination is between " + config.getMinValue() + " and " + config.getMaxValue() +
                "\nNumber of tries for a game : " + config.getMaxTries() +
                "\nDeveloper mode active : " + config.isDevMode());
    }

    default void chooseGameMode(){
        log.info("\nPlease choose your game mode :\n1- Challenger\n2- defender\n3- Duel\n4- Bonus mode");
    }

    default void chooseEndGame(){
        log.info("\n\nThe game is now finished !\n1 - You may play again \n2 - You can come back to the menu \n3 - You can go swimming");
    }

    default void choiceGameDuel(){
        log.info("You have choice the game mode : Duel\nWho will be faster to find each other’s secret combination ?");
    }

    default void choiceGameDefender(){
        log.info("You have choice the game mode : Defender\nAttacker try to find the secret combination !");
    }

    default void choiceGameChallenger(){
        log.info("You have choice the game mode : Challenger\nTry to find the secret number !");
    }

    default void choiceGameBonus(){
        log.info("You have choice the game mode : Bonus mode\nChallenge the computer\nNow, deliver a real fight !");
    }

    default void playSameGame(){ log.info("You want to play more ! Good luck !"); }

    default void backMenu(){
        log.info("Back to the menu !");
    }

    default void stopGame(){
        log.info("Thank you to play with us ! See you soon !");
    }

    default void player1(){
        log.info("\nPlayer1 ! ");
    }

    default void player2(){
        log.info("\nPlayer2 ! ");
    }

    default void computer() {
        log.info("\nHoooo Great computer, initialize your secret combination");
    }

    default void counter(int counter){
        log.info("Now, let's fight ! You have " + counter + " tries");
    }

    default void counterLess(int counter){
        log.info("\nIt stays {} tries\n", counter);
    }

    default void giveClues(){
        log.info("Please, give clues : ");
    }

    default void doProposition(){
        log.info("Do your proposition : ");
    }

    default void secretCombinationPlayer(){
        log.info("Choose your secret combination");
    }

    default void playerWin(){
        log.info("Well done ! You WIN !");
    }

    default void notGood(){
        log.info("Sorry ! Seems not good !");
    }

    default void endGameDuel(){
        log.info("\n\nSorry, no more tries ! Both players don't found any combination !\nyou are really not good !");
    }

    default void attackerFindSecretNumber(){
        log.info("Attacker has found the secret number ! Defender is mortify !");
    }

    default void attackerLoose(){
        log.info("No more tries ! Attacker has lost ! \nNot even able to find a simple combination of digits ! What misery !");
    }

    default void isWin(){
        log.info("Well done ! You're the very best Mastermind i've never seen in my life... No, i'm kidding !");
    }
}
