package com.mycompany.test2;

public class Enrollment {
    private static int count = 0;
    private int id;
    private String name;
    private Student student;
    private Module module;

    public Enrollment(String name, Student student, Module module) {
        this.id = ++count;
        this.name = name;
        this.student = student;
        this.module = module;
    }

    public Enrollment(int id, String name, Student student, Module module) {
        this.id = id;
        this.name = name;
        this.student = student;
        this.module = module;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Enrollment.count = count;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", student=" + student +
                ", module=" + module +
                '}';
    }
}
