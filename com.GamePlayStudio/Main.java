import domaine.properties.ConfigurationGame;
import gameMode.ChallengerMode;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Escape Game Online");
        ConfigurationGame properties = new ConfigurationGame();
        System.out.println("Here are the rules to play ! Good Luck !");
        properties.configGame();

        ChallengerMode challenge = new ChallengerMode();
        challenge.challenge();
    }
}
