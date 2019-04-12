package com.tw;

import java.util.Scanner;

public interface Service {
    boolean checkCommandFormat(String input);

    String generatePrompt(boolean isRight);

    void handleCommand(String command);

    default String readPromptFromConsole() {
        String command = null;
        Scanner s = new Scanner(System.in);
        if (s.hasNextLine()) {
            command = s.nextLine();
        }
//        s.close();
        return command;
    }

    default void main() {
        System.out.println(generatePrompt(true));
        String command = readPromptFromConsole();
        if (!checkCommandFormat(command)) {
            do {
                System.out.println(generatePrompt(false));
                command = readPromptFromConsole();
            } while (!checkCommandFormat(command));
        }
        handleCommand(command);
    }
}
