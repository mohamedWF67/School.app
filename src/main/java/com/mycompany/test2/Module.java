package com.mycompany.test2;

import java.util.Objects;

public class Module {
    private static int count = 0;
    private int id;
    private String name;
    private char section;
    private int maxstudents;
    private int numstudents;

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
        if (maxstudents <= 0) {
            maxstudents = 1;
        }
        this.maxstudents = maxstudents;
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

    /*public void addStudent(Student student) {
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
*/
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
