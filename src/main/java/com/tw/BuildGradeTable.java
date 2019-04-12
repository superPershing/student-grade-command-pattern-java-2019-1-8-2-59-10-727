package com.tw;

public class BuildGradeTable implements Service {
    private App app = null;

    public BuildGradeTable(App app) {
        this.app = app;
    }

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
