package com.tw;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddStudent implements Service {
    private static String pattern = "^([0-9A-Za-z\\u4e00-\\u9fa5]*)(,|[\\uff0c])(\\d+)(,|[\\uff0c])([\\u4e00-\\u9fa5]*)(:|[\\uff1a])(\\d+)(,|[\\uff0c])([\\u4e00-\\u9fa5]+)(:|[\\uff1a])(\\d+)(,|[\\uff0c])([\\u4e00-\\u9fa5]+)(:|[\\uff1a])(\\d+)(,|[\\uff0c])([\\u4e00-\\u9fa5]+)(:|[\\uff1a])(\\d+)$";
    private static Pattern r = Pattern.compile(pattern);
    private App app = null;

    private String tempName;
    private int tempId;
    private HashMap<String, Double> gradeHashMap;

    public AddStudent(App app) {
        this.app = app;
    }

    @Override
    public boolean checkCommandFormat(String input) {
        String cleanedInput = input.replaceAll("\\s+", "");
        Matcher m = r.matcher(cleanedInput);
        if (m.find()) {
            tempName = m.group(1);
            tempId = Integer.parseInt(m.group(3));

            gradeHashMap = new HashMap<>();
            gradeHashMap.put(m.group(5), Double.parseDouble(m.group(7)));
            gradeHashMap.put(m.group(9), Double.parseDouble(m.group(11)));
            gradeHashMap.put(m.group(13), Double.parseDouble(m.group(15)));
            gradeHashMap.put(m.group(17), Double.parseDouble(m.group(19)));
            return true;
        }
        return false;
    }

    @Override
    public String generatePrompt(boolean isRight) {
        return isRight ? "请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交："
                : "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：";
    }

    @Override
    public void handleCommand() {

        Grade g = new Grade();
        g.setChinese(gradeHashMap.get("语文"));
        g.setMathematics(gradeHashMap.get("数学"));
        g.setEnglish(gradeHashMap.get("英语"));
        g.setProgramming(gradeHashMap.get("编程"));

        Student s = new Student(tempId, tempName, g);

        System.out.printf("学生%d的成绩被添加\n", tempId);
        this.app.getDb().addStudent(s);
        this.app.main();
    }
}
