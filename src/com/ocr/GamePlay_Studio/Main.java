package com.ocr.GamePlay_Studio;

import com.ocr.GamePlay_Studio.GameHome.Home;
import com.ocr.GamePlay_Studio.GameMode.ChallengerMode;
import com.ocr.GamePlay_Studio.GameMode.DefenderMode;
import com.ocr.GamePlay_Studio.exceptions.TooManyNumbers;

import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws TooManyNumbers {
        Home home = new Home();
        home.menu();

        try {
            new ChallengerMode();
            ChallengerMode.challenger();
        } catch (TooManyNumbers ignored){

        } finally {
            new ChallengerMode();
            ChallengerMode.challenger();
        }


        DefenderMode defender = new DefenderMode();
    }
}