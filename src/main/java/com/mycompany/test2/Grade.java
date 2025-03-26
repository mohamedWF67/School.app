package com.mycompany.test2;

import java.util.Objects;

public class Grade {
    private Student student;
    private Module module;
    private int grade;

    public Grade(Student student, Module module, int grade) {
        this.student = student;
        this.module = module;
        this.grade = grade;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Grade grade)) return false;
        return Objects.equals(student, grade.student) && Objects.equals(module, grade.module);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, module);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "student=" + student +
                ", module=" + module +
                ", grade=" + grade +
                '}';
    }

    public String getGradeString() {
        return  module.getName() +
                ", Grade=" + grade;
    }
}
