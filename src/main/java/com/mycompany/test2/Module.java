package com.mycompany.test2;

import java.util.ArrayList;

public class Module {
    private static int count = 0;
    private int id;
    private String name;
    private char section;
    private int maxstudents;

    public Module(int id, String name, char section, int maxstudents) {
        this.id = id;
        this.name = name;
        this.section = section;
        this.maxstudents = maxstudents;
    }

    public Module(String name, char section, int maxstudents) {
        id = ++count;
        this.name = name;
        this.section = section;
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

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", section=" + section +
                ", maxstudents=" + maxstudents +
                '}';
    }
}
