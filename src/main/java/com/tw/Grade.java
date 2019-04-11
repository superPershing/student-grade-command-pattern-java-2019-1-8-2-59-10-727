package com.tw;

public class Grade {
    private double Mathematics;
    private double Chinese;
    private double English;
    private double Programming;

    public Grade(double mathematics, double chinese, double english, double programming) {
        Mathematics = mathematics;
        Chinese = chinese;
        English = english;
        Programming = programming;
    }

    public double getMathematics() {
        return Mathematics;
    }

    public double getChinese() {
        return Chinese;
    }

    public double getEnglish() {
        return English;
    }

    public double getProgramming() {
        return Programming;
    }

    public void setMathematics(double mathematics) {
        Mathematics = mathematics;
    }

    public void setChinese(double chinese) {
        Chinese = chinese;
    }

    public void setEnglish(double english) {
        English = english;
    }

    public void setProgramming(double programming) {
        Programming = programming;
    }

    public double averageScore() {
        return totalScore() / 4;
    }

    public double totalScore() {
        return Mathematics + Chinese + English + Programming;
    }
}
