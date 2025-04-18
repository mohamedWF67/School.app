package com.mycompany.School_app;

import com.mycompany.School_app.User.Student;

import java.io.Serializable;
import java.util.Objects;

public class Grade implements Serializable {
    //Attributes and collections
    private Student student;
    private Module module;
    private int grade;

    //Constructor
    public Grade(Student student, Module module, int grade) {
        this.student = student;
        this.module = module;
        this.grade = grade;
    }

    //Getter for student
    public Student getStudent() {
        return student;
    }

    //Setter for student
    public void setStudent(Student student) {
        this.student = student;
    }

    //Getter for module
    public Module getModule() {
        return module;
    }

    //Setter for module
    public void setModule(Module module) {
        this.module = module;
    }

    //Getter for grade
    public int getGrade() {
        return grade;
    }

    //Setter for grade
    public void setGrade(int grade) {
        this.grade = grade;
    }

    //Method to convert number to Char
    public char getGradeChar() {
        if (grade >= 70) return 'A';
        if (grade >= 60) return 'B';
        if (grade >= 50) return 'C';
        if (grade >= 40) return 'D';
        return 'F';
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
}
