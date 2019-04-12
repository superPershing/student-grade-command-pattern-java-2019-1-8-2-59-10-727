package com.tw;

import java.util.Arrays;

public class App implements Service {

    private AddStudent addStudentService = new AddStudent(this);
    private BuildGradeTable buildGradeTableService = new BuildGradeTable(this);

    public enum State {
        ADD_STUDENT_INFO("1"), SHOW_GRADE_TABLE("2"), EXIT("3");
        private String command;

        State(String command) {
            this.command = command;
        }

        static boolean isFormatCorrect(String code) {
            return Arrays.stream(State.values()).anyMatch(x -> x.command.equals(code));
        }

        static State getState(String code) {
            for (State value : State.values()) {
                if (value.command.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }

    @Override
    public boolean checkCommandFormat(String input) {
        if (State.isFormatCorrect(input)) {
            return true;
        }
        return false;
    }

    @Override
    public String generatePrompt(boolean isRight) {
        return "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出请输入你的选择（1～3）：\n";
    }

    @Override
    public void handleCommand(String command) {
        State state = State.getState(command);
        switch (state) {
            case ADD_STUDENT_INFO:
                addStudentService.main();
                break;
            case SHOW_GRADE_TABLE:
                buildGradeTableService.main();
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    public static void main(String[] args) {
        new App().main();
    }
}
