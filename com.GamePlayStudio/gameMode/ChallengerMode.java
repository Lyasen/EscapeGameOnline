package gameMode;

import utils.RandomNumber;

public class ChallengerMode {
    public void challenge(){
        System.out.println("\nHere's the Ai's secret number");
        RandomNumber random = new RandomNumber();
        random.randomSecret();
    }
}
