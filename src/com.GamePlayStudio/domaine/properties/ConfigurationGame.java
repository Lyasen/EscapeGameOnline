package com.GamePlayStudio.domaine.properties;

import com.GamePlayStudio.gameMessage.Messages;
import com.GamePlayStudio.gameMessage.MsgCombination;
import com.GamePlayStudio.gameMessage.MsgError;
import com.GamePlayStudio.gameMessage.MsgInfo;

import java.util.Properties;

public class ConfigurationGame {
    private final int digitsCombination, maxTries;
    private int minValue, maxValue;
    private final boolean devMode;

    private final MsgInfo msgInfo = new Messages();
    private final MsgCombination msgCombination = new Messages();
    private final MsgError msgError = new Messages();


    public ConfigurationGame(Properties properties) {
        digitsCombination = Integer.parseInt(properties.getProperty("digitsCombination", "4"));
        maxTries = Integer.parseInt(properties.getProperty("maxTries", "10"));
        minValue = Integer.parseInt(properties.getProperty("minValue", "0"));
        maxValue = Integer.parseInt(properties.getProperty("maxValue", "9"));
        devMode = Boolean.parseBoolean(properties.getProperty("devMode", "false"));
    }

    public int getDigitsCombination() {
        return digitsCombination;
    }

    public int getMaxTries() {
        return maxTries;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public boolean isDevMode() {
        return devMode;
    }

    /**
     * Display after the welcome message to establish the rules in order to play
      */
    public void configGame() {
        //  get the property value and print it out
        getMsgInfo().rules(this);
    }

    public MsgInfo getMsgInfo() {
        return msgInfo;
    }

    public MsgCombination getMsgCombination() {
        return msgCombination;
    }

    public MsgError getMsgError() {
        return msgError;
    }
}