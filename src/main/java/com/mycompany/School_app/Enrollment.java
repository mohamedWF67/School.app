package com.mycompany.School_app;

import java.util.HashSet;
import java.util.Objects;

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
            if (!modules.contains(module)) {
                if (module.checkCompatability(student)) {
                    modules.add(module);
                    module.addEnrolledModule();
                    return true;
                }
            }else{
                System.err.println("Module " + module.getName() + " is already enrolled");
            }
        }else {
            System.err.println("Module is Full");
        }
        return false;
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

    public boolean printModules() {
        if(!modules.isEmpty()) {
            System.out.println(modules);
            return true;
        }else{
            System.err.println("No modules");
            return false;
        }
    }

    public String getModuleNames(){
        String moduleNames = "";
        for (Module module : modules) {
            moduleNames += module.getName()+",";
        }
        return moduleNames;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Enrollment that)) return false;
        return Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(student);
    }


    @Override
    public String toString() {
        if (!modules.isEmpty()){
            return "Enrollment{" +
                    "id=" + id +
                    ", student=" + student.getName() +
                    ", modules=["+ getModuleNames() +
                    "]}";
        }
        return "Not Enrolled in any modules";
    }
}
