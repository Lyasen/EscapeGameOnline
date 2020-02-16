package gameMessage;

public class MessageError {
    public void errorLoadFile(){
        System.err.println("File not found in classpath");
    }

    public void tooManySymbols(){
        System.out.println("Hep hep hep ! Too many symbols in your clues ! Try again !");
    }

    public void realSymbols(){
        System.out.println("What was that ? You're afraid to loose or something ! Please enter only real symbols or leave !");
    }

    public void tooManyTypes(){
        System.out.println("Wow !! How many times you count typing on the keyboard");
    }

    public void onlyNumbers(){
        System.err.println("Wow ! What was that ? Please enter a  numbers combination, that's all dude !");
    }

    public void positiveNumbers(){
        System.err.println("OK ! you were so enthusiastic ! Please enter only positive numbers !");
    }

    public void noLetters(){
        System.err.println("do you really know the difference between numbers and letters ? Try again and stay focus !");
    }

    public void wasteMyTime(){
        System.out.println("Why don't you choose between these menus, you're wasting my time !");
    }
}
