package com.mycompany.test2;

import java.util.ArrayList;

public class School {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Module> modules;

    public School() {
        modules = new ArrayList<>();
        users = new ArrayList<>();
    }

    public School(String name) {
        this.name = name;
        modules = new ArrayList<>();
        users = new ArrayList<>();
    }
    public School(String name, ArrayList<User> users, ArrayList<Module> modules) {
        this.name = name;
        this.users = users;
        this.modules = modules;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public void addUser(User user) {
        if (users.contains(user)) {
            return;
        }
        users.add(user);
    }

    public void removeUser(int id) {
        User user = getUser(id);
        if (user != null) {
            users.remove(user);
        }
    }

    public void printStudents() {
        for (User user : users) {
            if (user instanceof Student) {
                System.out.println(user);
            }
        }
    }

    public void printStudent(int id) {
        User user = getUser(id);
        if (user != null) {
            System.out.println(user);
        }else{
            System.out.println("No such user");
        }
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public Module getModule(int id) {
        for (Module module : modules) {
            if (module.getId() == id) {
                return module;
            }
        }
        return null;
    }

    public String updateModule(int id,Module module) {
        Module oldModule = getModule(id);
        if (oldModule != null) {
            oldModule = module;
            return "Module found";
        }
        return "Module not found";
    }
    public String deleteModule(int id) {
        Module module = getModule(id);
        if (module != null) {
            modules.remove(module);
            return "Module deleted";
        }
        return "Module not found";
    }
    public void GenerateReport(int id) {
        Module module = getModule(id);
        if (module != null) {
            System.out.println("Number of Students in module '"+ module.getName() +"' : " + module.getTotalEnrolled()  + "/" + module.getMaxstudents());
        }else {
            System.out.println("Module not found");
        }
    }

    public void printModules() {
        for (Module module : modules) {
            System.out.println(module.toString());
        }
    }

    public boolean emailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void enrollStudent(int studentId,int moduleId) {
        User user = getUser(studentId);
        Module module = getModule(moduleId);
        if (modules.contains(module)) {
            if (user instanceof Student) {
                ((Student) user).enroll(module);
            }else{
                System.out.println("User not found");
            }
        }else{
            System.out.println("Module not found");
        }
    }

    public void cancelEnrollment(int studentId,int moduleId) {
        User user = getUser(studentId);
        Module module = getModule(moduleId);
        if (modules.contains(module) && ((Student) user).getEnrolledModules().contains(module) ) {
            if (user instanceof Student) {
                ((Student) user).cancelEnrollment(module);
            }else{
                System.out.println("User not found");
            }
        }else{
            System.out.println("Module not found");
        }
    }
}
