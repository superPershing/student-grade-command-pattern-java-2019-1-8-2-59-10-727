package com.tw;

public class AddStudent implements Service {
    @Override
    public boolean checkCommandFormat(String input) {
        return false;
    }

    @Override
    public String generatePrompt(boolean isRight) {
        return null;
    }

    @Override
    public void handleCommand(String command) {

    }
}
