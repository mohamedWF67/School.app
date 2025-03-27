package com.mycompany.School_app;

import java.util.ArrayList;
import java.util.HashSet;

public class School {
    //Attributes and collections
    private String name;
    private ArrayList<User> users;
    private ArrayList<Module> modules;
    private HashSet<Enrollment> enrollments;
    private HashSet<Grade> grades;

    //Empty Constructor
    public School() {
        modules = new ArrayList<>();
        users = new ArrayList<>();
        enrollments = new HashSet<>();
        grades = new HashSet<>();
    }

    //Constructor with name
    public School(String name) {
        this.name = name;
        modules = new ArrayList<>();
        users = new ArrayList<>();
        enrollments = new HashSet<>();
        grades = new HashSet<>();
    }

    //Constructor with all data
    public School(String name, ArrayList<User> users, ArrayList<Module> modules, HashSet<Enrollment> enrollments, HashSet<Grade> grades) {
        this.name = name;
        this.users = users;
        this.modules = modules;
        this.enrollments = enrollments;
        this.grades = grades;
    }

    //Getter for name
    public String getName() {
        return name;
    }

    //Setter for name
    public void setName(String name) {
        this.name = name;
    }

    //Getter for users as an ArrayList
    public ArrayList<User> getUsers() {
        return users;
    }

    //Getter for a User by it's id
    public User getUser(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    //Setter for users
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    //Getter for Modules as an ArrayList
    public ArrayList<Module> getModules() {
        return modules;
    }

    //Setter for modules
    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    //Adds user to users
    public void addUser(User user) {
        (!users.contains(user)?users:null).add(user);
    }

    //Removes user from users by it's id
    public void removeUser(int id) {
        User user = getUser(id);
        if (user != null) {
            users.remove(user);
        }
    }

    //Print Admins
    public void printAdmins() {
        for (User user : users) {
            if (user instanceof Admin) {
                System.out.println(user);
            }
        }
    }

    //Prints users
    public void printStudents() {
        for (User user : users) {
            if (user instanceof Student) {
                System.out.println(user);
            }
        }
    }

    //Print teachers
    public void printTeachers() {
        for (User user : users) {
            if (user instanceof Teacher) {
                System.out.println(user);
            }
        }
    }

    //Print admin using it's id
    public void printAdmin(int id) {
        User user = getUser(id);
        System.out.println(user instanceof Admin?user:"No such Admin");
    }

    //Print student using it's id
    public void printStudent(int id) {
        User user = getUser(id);
        System.out.println(user instanceof Student?user:"No such Student");
    }

    //Print teacher using it's id
    public void printTeacher(int id) {
        User user = getUser(id);
        System.out.println(user instanceof Teacher?user:"No such Teacher");
    }

    //add module to modules
    public void addModule(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    //Getter for a module by it's id
    public Module getModule(int id) {
        for (Module module : modules) {
            if (module.getId() == id) {
                return module;
            }
        }
        return null;
    }

    //Legacy Update module method
    /*
    public String updateModule(int id,Module module) {
        Module oldModule = getModule(id);
        if (oldModule != null) {
            oldModule = module;
            return "Module found";
        }
        return "Module not found";
    }*/

    //Remove module from modules by it's id
    public String deleteModule(int id) {
        Module module = getModule(id);
        if (module != null) {
            modules.remove(module);
            deleteModuleFromEnrollments(module);
            return "Module deleted";
        }
        return "Module not found";
    }

    //Generate report for module by it's id
    public void GenerateReport(int id) {
        Module module = getModule(id);
        if (module != null) {
            System.out.println("Number of Students in module '"+ module.getName() +"' : " + module.getTotalEnrolled()  + "/" + module.getMaxstudents());
        }else {
            System.err.println("Module not found");
        }
    }

    //Prints modules
    public void printModules() {
        for (Module module : modules) {
            System.out.println(module);
        }
    }

    //Checks if this Email already exists
    public boolean emailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    //Setter for enrollments
    public void setEnrollments(HashSet<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    //Getter for enrollments
    public HashSet<Enrollment> getEnrollments() {
        return enrollments;
    }

    //Add enrollment to enrollments
    public boolean addEnrollment(Student student,Module module) {
        if (!module.isFull()){
            if (module.checkCompatability(student)) {
                enrollments.add(new Enrollment(student,module));
                return true;
            }
        }else{
            System.err.println("Module is Full");
        }
        return false;
    }

    //Getter for enrollment by student's id
    public Enrollment getEnrollmentByStudentId(int id) {
        if (enrollments != null) {
            for (Enrollment enrollment : enrollments) {
                if (enrollment.getStudent().getId() == id) {
                    return enrollment;
                }
            }
        }
        return null;
    }

    //Checks if the Student is enrolled int this module
    public boolean isModuleInEnrollments(Student student, Module module) {
        Enrollment enrollment = getEnrollmentByStudentId(student.getId());
        if (enrollment != null && module!=null && enrollment.moduleExists(module)) {
                return true;
        }
        return false;
    }

    //Prints the enrollment for student by it's id
    public void viewStudentEnrollments(int id) {
        Enrollment enrollment = getEnrollmentByStudentId(id);
        if (enrollment != null) {
            System.out.println(enrollment);
        }else{
            System.err.println("No enrollment for this student");
        }
    }

    //Enrolls student in a module
    public void enrollStudent(int studentId, int moduleId) {
        User user = getUser(studentId);
        Module module = getModule(moduleId);
        if (user instanceof Student) {
            if (modules.contains(module)){
                Enrollment enrollment = getEnrollmentByStudentId(studentId);
                if (enrollment != null) {
                    if(enrollment.addModule(module)) {
                        System.out.println("Added module " + module.getName() + " for Student " + user.getName());
                    }else {
                        System.err.println("Module " + module.getName() + " is not from the same section");
                    }
                }else{
                    if(addEnrollment((Student) user,module)){
                        System.out.println("Added a new enrollment for module " + module.getName() + " for Student " + user.getName());
                    }else{
                        System.err.println("Module " + module.getName() + " is not from the same section");
                    }
                }
            }else{
                System.err.println("Module not found");
            }
        }else{
            System.err.println("User not found");
        }
    }

    //Cancels Enrollment of student from a module
    public void cancelEnrollment(int studentId,int moduleId) {
        User user = getUser(studentId);
        Module module = getModule(moduleId);
        if (user instanceof Student) {
            Enrollment enrollment = getEnrollmentByStudentId(studentId);
            if (enrollment != null && !enrollment.isEmpty()) {
                if (modules.contains(module)){
                    if(enrollment.removeModule(module)) {
                        System.out.println("Removed module " + module.getName() + " from Student " + user.getName());
                    }
                }else{
                    System.err.println("Module not found");
                }
            }else{
                System.err.println("Enrollment not found");
            }
        }else{
            System.err.println("User not found");
        }
    }

    //Swaps an enrolled module with a new one
    public void swapEnrollments(int studentId,int moduleId1,int moduleId2) {
        User user = getUser(studentId);
        if (user instanceof Student) {
            Module module1 = getModule(moduleId1);
            Module module2 = getModule(moduleId2);
            if (module1 != null && module2 != null) {
                Enrollment enrollment = getEnrollmentByStudentId(studentId);
                if (enrollment != null && !enrollment.isEmpty()) {
                    if (enrollment.moduleExists(module1) && moduleId1 != moduleId2 && module2.checkCompatability((Student) user)) {
                        enrollment.removeModule(module1);
                        enrollment.addModule(module2);
                        System.out.println("Swapped module " + module1.getName() + " with "+ module2.getName() + " for Student " + user.getName());
                    }else{
                        System.err.println("Module failed Swap");
                    }
                }else{
                    System.err.println("Enrollment not found");
                }
            }else{
                System.err.println("Module not found");
            }
        }else {
            System.err.println("User not found");
        }
    }

    //Delete module from all enrollments
    public void deleteModuleFromEnrollments(Module module) {
        if (enrollments != null) {
            for (Enrollment enrollment : enrollments) {
                if (enrollment.moduleExists(module)) {
                    enrollment.removeModule(module);
                }
            }
        }else{
            System.err.println("No enrollments");
        }
    }

    //Getter for all the modules that the student can enroll in
    public ArrayList<Module> getCompatibleModules(User user) {
        Student student = (Student) user;
        ArrayList<Module> compatibleModules = new ArrayList<>();
        if (!modules.isEmpty()) {
            for (Module module : modules) {
                boolean enrolled = false;
                Enrollment enrollment = getEnrollmentByStudentId(user.getId());
                if (enrollment != null) {
                    enrolled = enrollment.moduleExists(module);
                }
                if (module.getSection().equals(student.getSection()) && !enrolled) {
                    compatibleModules.add(module);
                }
            }
        }
        return compatibleModules;
    }

    //Prints all the modules that the student can enroll in
    public void printCompatibleModules(User user) {
        ArrayList<Module> compatibleModules = getCompatibleModules(user);
        if (compatibleModules != null) {
            for (Module module : compatibleModules) {
                System.out.println(module);
            }
        }
    }

    //Prints all the Users Enrolled modules
    public void printEnrolledModules(User user) {
        if (getUser(user.getId()) instanceof Student) {
            Student student = (Student) user;
            Enrollment enrollment = getEnrollmentByStudentId(student.getId());
            if (enrollment != null) {
                enrollment.printModules();
            }
        }
    }

    //Getter for a Grade
    public Grade getGrade(Student student, Module module) {
        for (Grade grade : grades) {
            if (grade.getStudent().equals(student) && grade.getModule().equals(module)) {
                return grade;
            }
        }
        return null;
    }

    //Checks if the Grade exists
    public boolean gradeExists(Student student, Module module) {
        return getGrade(student, module) != null;
    }

    //Clamps the Grade in a range from [0,100] and returns it's value
    public int checkGrade(int grade){
        if (grade >=100) {
            return 100;
        }else if (grade <=0) {
            return 0;
        }
        return grade;
    }

    //Adds a grade for a student in a certain module
    public void addGradetoStudent(int studentId, int moduleId,int grade) {
        int mark = checkGrade(grade);
        User user = getUser(studentId);
        Module module = getModule(moduleId);
        if (user instanceof Student) {
            Enrollment enrollment = getEnrollmentByStudentId(studentId);
            if (enrollment != null && !enrollment.isEmpty()) {
                if (module != null) {
                    if (!gradeExists((Student) user,module)){
                        grades.add(new Grade((Student) user,module,mark));
                        System.out.println("Added grade " + mark + " in " + module.getName() + " for Student " + user.getName());
                    }else{
                        System.err.println("Grade already exists");
                    }
                }else{
                    System.err.println("Module not found");
                }
            }else {
                System.err.println("Enrollment not found");
            }
        }else {
            System.err.println("User not found");
        }
    }

    //Removes a grade for a student in a certain module
    public void removeGradeFromStudent(int studentId, int moduleId) {
        User user = getUser(studentId);
        Module module = getModule(moduleId);
        if (user instanceof Student) {
            Enrollment enrollment = getEnrollmentByStudentId(studentId);
            if (enrollment != null && !enrollment.isEmpty()) {
                if (module != null) {
                    Grade grade =getGrade((Student) user,module);
                    if (grade != null) {
                        grades.remove(grade);
                        System.out.println("Removed grade from " + module.getName() + " for Student " + user.getName());
                    }else{
                        System.err.println("Grade not found");
                    }
                }else{
                    System.err.println("Module not found");
                }
            }else {
                System.err.println("Enrollment not found");
            }
        }else {
            System.err.println("User not found");
        }
    }

    //Edits a grade for a student in a certain module
    public void editGrade(int studentId, int moduleId, int mark) {
        int newgrade = checkGrade(mark);
        User user = getUser(studentId);
        Module module = getModule(moduleId);
        if (user instanceof Student) {
            Enrollment enrollment = getEnrollmentByStudentId(studentId);
            if (enrollment != null && !enrollment.isEmpty()) {
                if (module != null) {
                    Grade grade = getGrade((Student) user,module);
                    if (grade != null) {
                        int oldgrade = grade.getGrade();
                        grade.setGrade(newgrade);
                        System.out.println("Grade updated from " + oldgrade + " to "+ newgrade + " in " + module.getName() + " for Student " + user.getName());
                    }else{
                        System.err.println("Grade not found exists");
                    }
                }else{
                    System.err.println("Module not found");
                }
            }else {
                System.err.println("Enrollment not found");
            }
        }else {
            System.err.println("User not found");
        }
    }

    //View student grades using his id
    public void viewGrades(int studentId) {
        User user = getUser(studentId);
        boolean foundAGrade = false;
        if (user instanceof Student) {
            if (!grades.isEmpty()) {
                for (Grade grade : grades) {
                    if(!foundAGrade){
                        foundAGrade = grade.getStudent().equals((Student) user);
                        if (foundAGrade){
                            System.out.println("Grades for Student " + user.getName());
                        }
                    }
                    if (grade.getStudent().equals((Student) user)) {
                        System.out.println(grade.getGradeString());
                    }
                }
                if(!foundAGrade){
                    System.out.println("No grade found");
                }
            }else {
                System.err.println("no grades");
            }
        }else{
            System.err.println("User not found");
        }
    }
}
