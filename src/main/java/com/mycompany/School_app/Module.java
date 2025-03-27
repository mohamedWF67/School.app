package com.mycompany.School_app;

import java.util.Objects;

public class Module {
    private static int count = 0;
    private int id;
    private String name;
    private String section;
    private int maxstudents;
    private int numstudents;

    public Module(int id, String name, String section, int maxstudents) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.maxstudents = maxstudents;
    }

    public Module(String name, String section, int maxstudents) {
        id = ++count;
        this.name = name;
        this.section = section;
        if (maxstudents <= 0) {
            maxstudents = 1;
        }
        this.maxstudents = maxstudents;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getMaxstudents() {
        return maxstudents;
    }

    public void setMaxstudents(int maxstudents) {
        if (maxstudents <= 0) {
            maxstudents = 1;
        }
        this.maxstudents = maxstudents;
    }

    public void setNumstudents(int numstudents) {
        this.numstudents = numstudents;
    }

    public int getTotalEnrolled() {
        return numstudents;
    }

    public boolean isFull() {
        return numstudents >= maxstudents;
    }

    public void addEnrolledModule() {
        numstudents++;
    }

    public void removeEnrolledModule() {
        numstudents--;
    }

    public boolean checkCompatability(Student student) {
        if (student.getSection().equals(section)) {
            //System.out.println("Student " + student.getName() + " is compatible with this module");
            return true;
        }
        //System.err.println("Student " + student.getName() + " isn't compatible with this module");
        return false;
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
