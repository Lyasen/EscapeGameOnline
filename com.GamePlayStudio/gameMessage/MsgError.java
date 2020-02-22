package gameMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface MsgError {
    Logger log = LogManager.getLogger(MsgError.class);

    static void errorLoadFile(){ log.error("File not found in classpath"); }

    default void tooManySymbols(){
        log.error("Hep hep hep ! Too many symbols in your clues ! Try again !");
    }

    default void realSymbols(){
        log.error("What was that ? You're afraid to loose or something ! Please enter only real symbols or leave !");
    }

    default void tooManyTypes(){
        log.error("Wow !! How many times you count typing on the keyboard");
    }

    default void onlyNumbers(){
        log.error("Wow ! Please enter a  numbers combination, that's all dude !");
    }

    default void positiveNumbers(){
        log.error("OK ! you were so enthusiastic ! Please enter only positive numbers !");
    }

    default void noLetters(){
        log.error("do you really know the difference between numbers and letters ? Try again and stay focus !");
    }

    default void wasteMyTime(){
        log.error("Why don't you choose between these menus, you're wasting my time !");
    }
}
