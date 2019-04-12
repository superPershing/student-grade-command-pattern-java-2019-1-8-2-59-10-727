package com.tw;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

public class Db {
    private String dataFile = "student.dat";

    public Db() {
    }

    public Db(String dataFile) {
        this.dataFile = dataFile;
    }

    private HashMap<Integer, Student> studentHashSet = new HashMap<>();

    public void addStudent(Student student) {
        try (PrintWriter out = new PrintWriter(dataFile, "UTF-8")) {
            writeStudent(out, student);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        studentHashSet.put(student.getId(), student);
    }

    public static void writeStudent(PrintWriter out, Student student) {
        out.println(student.getId() + "|" + student.getName() + "|" + student.getGrade().getChinese() + "|" + student.getGrade().getMathematics() + "|" + student.getGrade().getEnglish() + "|" + student.getGrade().getProgramming());
    }

    public HashMap<Integer, Student> getStudentHashSet() {
        try (Scanner in = new Scanner(new FileInputStream(dataFile), "UTF-8")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] token = line.split("\\|");
                int id = Integer.parseInt(token[0]);
                String name = token[1];
                Grade g = new Grade(Double.parseDouble(token[3]), Double.parseDouble(token[2]), Double.parseDouble(token[4]), Double.parseDouble(token[5]));
                Student s = new Student(id, name, g);
                studentHashSet.put(id, s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return studentHashSet;
    }
}
