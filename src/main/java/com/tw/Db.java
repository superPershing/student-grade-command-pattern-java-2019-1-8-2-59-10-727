package com.tw;

import java.util.HashMap;

public class Db {
    private HashMap<Integer, Student> studentHashSet = new HashMap();

    public void addStudent(Student student) {
        studentHashSet.put(student.getId(), student);
    }

    public HashMap<Integer, Student> getStudentHashSet() {
        return studentHashSet;
    }

}
