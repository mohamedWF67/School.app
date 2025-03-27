package com.mycompany.School_app;

import java.util.Objects;

public class Grade {
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
    private char getGradeChar() {
        if (grade >= 70) {
            return 'A';
        }else if (grade >= 60) {
            return 'B';
        }
        else if (grade >= 50) {
            return 'C';
        }
        else if (grade >= 40) {
            return 'D';
        }
        else{
            return 'F';
        }
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

    //Returns the module name and grade as a String
    public String getGradeString() {
        return  "ID: " + module.getId()+" "+module.getName() +"\t"+
                ", Grade:" + grade + " " + getGradeChar();
    }
}
