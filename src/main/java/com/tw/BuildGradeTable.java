package com.tw;

import java.util.Arrays;

public class BuildGradeTable implements Service {
    private App app = null;
    private int[] idArray;

    public BuildGradeTable(App app) {
        this.app = app;
    }

    @Override
    public boolean checkCommandFormat(String input) {
        try {
            String[] ids = input.replaceAll("\\s+", "").split(",");
            idArray = Arrays.stream(ids).mapToInt(x -> Integer.parseInt(x)).toArray();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String generatePrompt(boolean isRight) {
        return isRight ? "请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交："
                : "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：";
    }

    @Override
    public void handleCommand() {
        System.out.println("成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================");

        for (int i : idArray) {
            if (app.getDb().getStudentHashSet().containsKey(i)) {
                Student tempStudent = app.getDb().getStudentHashSet().get(i);
                Grade tempGrade = tempStudent.getGrade();
                System.out.printf("%s|%f|%f|%f|%f|%f|%f\n", tempStudent.getName(), tempGrade.getMathematics(), tempGrade.getChinese(), tempGrade.getEnglish(), tempGrade.getProgramming(), tempGrade.averageScore(), tempGrade.totalScore());
            }
        }
        System.out.println("========================");

        System.out.printf("全班总分平均数：%f\n", app.getDb().getStudentHashSet().values().stream().mapToDouble(i -> i.getGrade().totalScore()).average().getAsDouble());
        double[] sortedGrades = app.getDb().getStudentHashSet().values().stream().mapToDouble(i -> i.getGrade().totalScore()).sorted().toArray();
        double middleGrade;
        if (sortedGrades.length % 2 == 0) {
            middleGrade = (sortedGrades[sortedGrades.length / 2 - 1] + sortedGrades[sortedGrades.length / 2]) / 2;
        } else {
            middleGrade = sortedGrades[(sortedGrades.length - 1) / 2];
        }
        System.out.printf("全班总分中位数：%f\n", middleGrade);

        this.app.main();
    }
}
