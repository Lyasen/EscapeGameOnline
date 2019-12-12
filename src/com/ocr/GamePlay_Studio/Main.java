package com.ocr.GamePlay_Studio;

import com.ocr.GamePlay_Studio.GameHome.Home;
import com.ocr.GamePlay_Studio.Domain.Config;

import java.io.IOException;

public class Main {

    /**
     * Used the main to launch the game mode
     * @param args : A welcome menu
     */
    public static void main(String[] args) {
        try {
            Config.configGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Home.menu();
    }
}