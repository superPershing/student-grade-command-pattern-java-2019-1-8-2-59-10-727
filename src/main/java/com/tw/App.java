package com.tw;

public class App implements Service {

    private AddStudent addStudentService = new AddStudent();
    private BuildGradeTable buildGradeTableService = new BuildGradeTable();

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

    public static void main(String[] args) {
        new App().main();
    }
}
