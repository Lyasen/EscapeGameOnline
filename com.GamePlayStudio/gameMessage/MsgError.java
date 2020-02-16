package gameMessage;

public interface MsgError {
    static void errorLoadFile(){ System.err.println("File not found in classpath"); }

    default void tooManySymbols(){
        System.out.println("Hep hep hep ! Too many symbols in your clues ! Try again !");
    }

    default void realSymbols(){
        System.out.println("What was that ? You're afraid to loose or something ! Please enter only real symbols or leave !");
    }

    default void tooManyTypes(){
        System.out.println("Wow !! How many times you count typing on the keyboard");
    }

    default void onlyNumbers(){
        System.err.println("Wow ! What was that ? Please enter a  numbers combination, that's all dude !");
    }

    default void positiveNumbers(){
        System.err.println("OK ! you were so enthusiastic ! Please enter only positive numbers !");
    }

    default void noLetters(){
        System.err.println("do you really know the difference between numbers and letters ? Try again and stay focus !");
    }

    default void wasteMyTime(){
        System.out.println("Why don't you choose between these menus, you're wasting my time !");
    }
}
