package HandlingException;

public class NumberOfCluesException extends Exception {
    public NumberOfCluesException() {
        System.out.println("Hep hep hep ! Too many symbols in your clues ! Try again !");
    }
}
