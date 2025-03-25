package com.mycompany.test2;

import java.util.HashSet;

public class Enrollment {
    private static int count = 0;
    private int id;
    private Student student;
    private HashSet<Module> modules;

    public Enrollment(Student student, Module module) {
        this.id = ++count;
        this.student = student;
        this.modules = new HashSet<>();
        addModule(module);
    }

    public Enrollment(int id, Student student, HashSet<Module> modules) {
        this.id = id;
        this.student = student;
        this.modules = modules;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public HashSet<Module> getModules() {
        return modules;
    }

    public void setModules(HashSet<Module> modules) {
        this.modules = modules;
    }

    public boolean isEmpty() {
        return modules.isEmpty();
    }

    public boolean findModule(Module module) {
        return modules.contains(module);
    }

    public boolean addModule(Module module) {
        if (!module.isFull()){
            if (!modules.contains(module)){
                modules.add(module);
                module.addEnrolledModule();
                return true;
            }else{
                System.err.println("Module " + module.getName() + " is already enrolled");
                return false;
            }
        }else {
            System.err.println("Module is Full");
            return false;
        }
    }

    public boolean removeModule(Module module) {
        try {
            if (modules.contains(module)) {
                modules.remove(module);
                module.removeEnrolledModule();
                return true;
            }else {
                System.err.println("No Enrollment for this module");
                return false;
            }
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void printModules() {
        if(modules.size() > 0) {
            System.out.println(modules);
        }else{
            System.err.println("No modules");
        }
    }

    /*@Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", student=" + student +
                ", module=" + module +
                '}';
    }*/
}
