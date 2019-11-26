package com.ocr.GamePlay_Studio.OtherNumbersMethod;

public enum DigitsCombination {
    NB_DIGITS_COMBINATION(4);

    private int digits;

    DigitsCombination(int digits) {
        this.digits = digits;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }
}
