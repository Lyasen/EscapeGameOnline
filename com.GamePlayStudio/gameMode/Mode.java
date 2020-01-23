package gameMode;

import domaine.properties.ConfigurationGame;

import java.util.Scanner;

public abstract class Mode {
    protected ConfigurationGame config;
    protected Scanner scan;

    public Mode(ConfigurationGame config, Scanner scan) {
        this.config = config;
        this.scan = scan;
    }
}
