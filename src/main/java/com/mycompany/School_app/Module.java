package com.mycompany.School_app;

import com.mycompany.School_app.User.Student;

import java.io.Serializable;
import java.util.Objects;

public class Module implements Serializable {

    //Attributes and collections
    private static int count = 0;
    private int id;
    private String name;
    private String section;
    private int maxstudents;
    private int numstudents;

    //Constructor with id as an input
    public Module(int id, String name, String section, int maxstudents) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.maxstudents = maxstudents;
    }

    //Constructor with id automatic
    public Module(String name, String section, int maxstudents) {
        id = ++count;
        this.name = name;
        this.section = section;
        if (maxstudents <= 0) {
            maxstudents = 1;
        }
        this.maxstudents = maxstudents;
    }

    //Getter for count
    public static int getCount() {
        return count;
    }

    //Setter for count
    public static void setCount(int count) {
        Module.count = count;
    }

    //Getter for ID
    public int getId() {
        return id;
    }

    //Setter for ID
    public void setId(int id) {
        this.id = id;
    }

    //Getter for name
    public String getName() {
        return name;
    }

    //Setter for name
    public void setName(String name) {
        this.name = name;
    }

    //Getter for section
    public String getSection() {
        return section;
    }

    //Setter for section
    public void setSection(String section) {
        this.section = section;
    }

    //Getter for student limit
    public int getMaxstudents() {
        return maxstudents;
    }

    //Setter for student limit
    public void setMaxstudents(int maxstudents) {
        if (maxstudents > numstudents) {
            if (maxstudents <= 0) {
                this.maxstudents = 1;
            }
            this.maxstudents = maxstudents;
        }else{
            this.maxstudents = numstudents;
        }
    }

    //Getter for number of enrolled students
    public int getTotalEnrolled() {
        return numstudents;
    }

    //Setter for number of enrolled students
    public void setNumstudents(int numstudents) {
        this.numstudents = numstudents;
    }

    //Checks if the module is full
    public boolean isFull() {
        return numstudents >= maxstudents;
    }

    //Increment number of enrolled students
    public void addEnrolledModule() {
        numstudents++;
    }

    //Decrement number of enrolled students
    public void removeEnrolledModule() {
        numstudents--;
    }

    //Check if the student and the module are from the same section
    public boolean checkCompatibility(Student student) {
        return student.getSection().equals(section);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Module module)) return false;
        return id == module.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Module{" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Section=" + section +
                ", Enrolled Students=" + getTotalEnrolled() +
                ", Student Limit=" + maxstudents +
                '}';
    }
}
