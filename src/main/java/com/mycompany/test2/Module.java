package com.mycompany.test2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Module {
    private static int count = 0;
    private int id;
    private String name;
    private char section;
    private int maxstudents;
    private Set<Student> enrolledStudents;

    public Module(int id, String name, char section, int maxstudents, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.maxstudents = maxstudents;
        this.enrolledStudents = students;
    }

    public Module(String name, char section, int maxstudents) {
        id = ++count;
        this.name = name;
        this.section = section;
        this.maxstudents = maxstudents;
        this.enrolledStudents = new HashSet<Student>();
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Module.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSection() {
        return section;
    }

    public void setSection(char section) {
        this.section = section;
    }

    public int getMaxstudents() {
        return maxstudents;
    }

    public void setMaxstudents(int maxstudents) {
        this.maxstudents = maxstudents;
    }

    public int getTotalEnrolled() {
        return enrolledStudents.size();
    }

    public boolean isFull() {
        return enrolledStudents.size() >= maxstudents;
    }

    public void addStudent(Student student) {
        if (!isFull()) {
            try{
                enrolledStudents.add(student);
                System.out.println("Enrolled student " + student.getName() + " in module " + getName());
            }catch(Exception e){
                System.out.println(e);
            }
        }else {
            System.out.println("Module is at full capacity");
        }
    }

    public void removeStudent(Student student) {
        try {
            enrolledStudents.remove(student);
            System.out.println("Removed student " + student.getName() + " from module " + getName());
        }catch(Exception e){
            System.out.println(e);
        }

    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", section=" + section +
                ", EnrolledStudents=" + getTotalEnrolled() +
                ", maxstudents=" + maxstudents +
                '}';
    }
}
