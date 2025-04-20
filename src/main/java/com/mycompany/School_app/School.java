package com.mycompany.School_app;

import com.mycompany.School_app.LibrarySystem.Library;
import com.mycompany.School_app.User.Student;
import com.mycompany.School_app.User.Teacher;
import com.mycompany.School_app.User.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

public class School {
    //Attributes and collections
    private String name;
    private ArrayList<User> users;
    private ArrayList<Module> modules;
    private ArrayList<Enrollment> enrollments;
    private ArrayList<Grade> grades;
    private Library library;

    //Empty Constructor
    public School() {
        modules = new ArrayList<>();
        users = new ArrayList<>();
        enrollments = new ArrayList<>();
        grades = new ArrayList<>();
        library = new Library();
    }

    //Constructor with name
    public School(String name) {
        this.name = name;
        modules = new ArrayList<>();
        users = new ArrayList<>();
        enrollments = new ArrayList<>();
        grades = new ArrayList<>();
        library = new Library();
    }

    //Constructor with all data
    public School(String name, ArrayList<User> users, ArrayList<Module> modules, ArrayList<Enrollment> enrollments, ArrayList<Grade> grades, Library library) {
        this.name = name;
        this.users = users;
        this.modules = modules;
        this.enrollments = enrollments;
        this.grades = grades;
        this.library = library;
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

    //Getter for Students as an ArrayList
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Student) {
                students.add((Student) user);
            }
        }
        return students;
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

    //Setter for enrollments
    public void setEnrollments(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    //Getter for enrollments
    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    //Getter for Grades
    public ArrayList<Grade> getGrades() {
        return grades;
    }

    //Setter for Grades
    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    //Getter for Library
    public Library getLibrary() {
        return library;
    }

    //Setter for library
    public void setLibrary(Library library) {
        this.library = library;
    }

    //Adds user to users
    public void addUser(User user) {
        (!users.contains(user)?users:null).add(user);
    }

    //Getter for a User by it's id
    public User getUser(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    //Removes user from users by it's id
    public void removeUser(int id) {
        User user = getUser(id);
        if (user == null) {
            return;
        }

        if (user instanceof Student) {
            Student student = (Student) user;
            cancelEnrollments(student);
        }
        users.remove(user);
    }

    //Print Admins
    public void printAdmins() {
        users.stream()
                .filter(user -> user instanceof Admin)
                .forEach(user -> System.out.println(user));
    }

    //Prints users
    public void printStudents() {
        users.stream()
                .filter(user -> user instanceof Student)
                .forEach(user -> System.out.println(user));
    }

    //Print teachers
    public void printTeachers() {
        users.stream()
                .filter(user -> user instanceof Teacher)
                .forEach(user -> System.out.println(user));
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
        return modules.stream().filter((m) -> m.getId() == id).findFirst().orElse(null);
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
        if (module == null) {
            return "Module not found";
        }

        modules.remove(module);
        deleteModuleFromEnrollments(module);
        return "Module '" + module.getName() + "' deleted successfully";
    }


    //Generate report for module by it's id
    public void generateReport(int id) {
        Module module = getModule(id);

        if (module == null) {
            System.err.println("Module not found");
            return;
        }

        System.out.printf(
                "Report for Module '%s': %d / %d students enrolled%n",
                module.getName(),
                module.getTotalEnrolled(),
                module.getMaxstudents()
        );
    }


    //Prints modules
    public void printModules() {
        modules.forEach(module -> System.out.println(module));
    }

    //Checks if this Email already exists
    public boolean emailExists(String email) {
        return (users.stream()
                .anyMatch(user -> user.getEmail().equals(email))|| library.emailExists(email));
    }


    //Add enrollment to enrollments
    public boolean addEnrollment(Student student, Module module) {
        if (module.isFull()) {
            System.err.println("Module " + module.getName() + " is full.");
            return false;
        }

        if (!module.checkCompatibility(student)) {
            System.err.println("Module " + module.getName() + " is not compatible with student " + student.getName());
            return false;
        }

        enrollments.add(new Enrollment(student, module));
        return true;
    }


    //Getter for enrollment by student's id
    public Enrollment getEnrollmentByStudentId(int id) {
        if (enrollments == null) return null;

        return enrollments.stream()
                .filter(enrollment -> enrollment.getStudent().getId() == id)
                .findFirst()
                .orElse(null);
    }


    // Checks if the student is enrolled in the given module
    public boolean isModuleInEnrollments(Student student, Module module) {
        if (student == null || module == null) return false;

        Enrollment enrollment = getEnrollmentByStudentId(student.getId());
        return enrollment != null && enrollment.moduleExists(module);
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

    // Enrolls a student in a module
    public void enrollStudent(int studentId, int moduleId) {
        User user = getUser(studentId);
        if (!(user instanceof Student student)) {
            System.err.println("User not found or not a student");
            return;
        }

        Module module = getModule(moduleId);
        if (module == null || !modules.contains(module)) {
            System.err.println("Module not found");
            return;
        }

        Enrollment enrollment = getEnrollmentByStudentId(studentId);

        if (enrollment != null) {
            if (enrollment.addModule(module)) {
                System.out.println("Added module " + module.getName() + " for Student " + student.getName());
            } else {
                System.err.println("Student is already enrolled in " + module.getName());
            }
        } else {
            if (addEnrollment(student, module)) {
                System.out.println("Added a new enrollment for module " + module.getName() + " for Student " + student.getName());
            } else {
                System.err.println("Failed to create new enrollment");
            }
        }
    }


    public boolean enrollStudent(Student student, int moduleindex) {
        Module module = getCompatibleModules(student).get(moduleindex);
        if (module != null) {
            enrollStudent(student.getId(), module.getId());
            return true;
        }
        return false;
    }

    //Cancels Enrollment of student from a module
    public void cancelEnrollment(int studentId, int moduleId) {
        User user = getUser(studentId);

        if (!(user instanceof Student student)) {
            System.err.println("User not found or not a student");
            return;
        }

        Module module = getModule(moduleId);
        if (module == null) {
            System.err.println("Module not found");
            return;
        }

        Enrollment enrollment = getEnrollmentByStudentId(studentId);
        if (enrollment == null || enrollment.isEmpty()) {
            System.err.println("Enrollment not found");
            return;
        }


        if (!modules.contains(module)) {
            System.err.println("module not found");
            return;
        }

        if (enrollment.removeModule(module)) {
            System.out.println("Removed module " + module.getName() + " from Student " + student.getName());
        } else {
            System.err.println("Failed to remove module");
        }
    }

    public void cancelEnrollments(Student student) {
        Enrollment enrollment = getEnrollmentByStudentId(student.getId());
        if (enrollment == null) {
            return;
        }

        ArrayList<Module> enrolledModules = enrollment.getModuleList();

        enrolledModules.stream().forEach((module) -> {cancelEnrollment(student.getId(), module.getId());});

        System.out.println("All enrolled modules for " + student.getName() + " are deleted");
    }

    public boolean cancelEnrollment(Student student, String moduleName) {
        Module module = getEnrollmentByStudentId(student.getId())
                .getModuleList()
                .stream()
                .filter(m -> m.getName().equals(moduleName))
                .findFirst()
                .orElse(null);
        cancelEnrollment(student.getId(), module.getId());
        if (module != null) return true;
        return false;
    }

    //Swaps an enrolled module with a new one
    public void swapEnrollments(int studentId, int moduleId1, int moduleId2) {
        User user = getUser(studentId);

        if (!(user instanceof Student student)) {
            System.err.println("Student not found");
            return;
        }

        Module module1 = getModule(moduleId1);
        Module module2 = getModule(moduleId2);

        if (module1 == null || module2 == null) {
            System.err.println("One or both modules not found");
            return;
        }

        Enrollment enrollment = getEnrollmentByStudentId(studentId);
        if (enrollment == null || enrollment.isEmpty()) {
            System.err.println("Enrollment not found");
            return;
        }

        if (!enrollment.moduleExists(module1)) {
            System.err.println("Module swap failed: Student " + user.getName() + " is not enrolled in " + module1.getName());
            return;
        }

        if (moduleId1 == moduleId2) {
            System.err.println("Module swap failed: Both module IDs are the same");
            return;
        }

        if (!module2.checkCompatibility(student)) {
            System.err.println("Module swap failed: " + module2.getName() + " is not compatible with " + student.getName());
            return;
        }

        if (enrollment.moduleExists(module2)){
            System.err.println("Module swap failed: " + student.getName() + " is already enrolled in " + module2.getName());
            return;
        }

        enrollment.removeModule(module1);
        enrollment.addModule(module2);
        System.out.println("Successfully swapped " + module1.getName() + " with " + module2.getName() + " for Student " + student.getName());
    }


    //Delete module from all enrollments
    public void deleteModuleFromEnrollments(Module module) {
        if (enrollments == null || enrollments.isEmpty()) {
            System.err.println("No enrollments");
            return;
        }

        enrollments.stream()
                .filter(enrollment -> enrollment.moduleExists(module))
                .forEach(enrollment -> enrollment.removeModule(module));
    }


    //Getter for all the modules that the student can enroll in
    public ArrayList<Module> getCompatibleModules(User user) {
        if (user instanceof Student student) {
            Enrollment enrollment = getEnrollmentByStudentId(student.getId());

            return (ArrayList<Module>) modules.stream()
                    .filter(module -> module.checkCompatibility(student) &&
                            (enrollment == null || !enrollment.moduleExists(module)))
                    .collect(Collectors.toList());
        }
        return null;
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

    //getter all the Users Enrolled modules
    public HashSet<Module> getStudentModules(User user) {
        if (user instanceof Student student) {
            Enrollment enrollment = getEnrollmentByStudentId(student.getId());

            if (enrollment != null) {
                return getEnrollmentByStudentId(student.getId()).getModules();
            }
        }
        return null;
    }

    //Prints all the Users Enrolled modules
    public void printEnrolledModules(User user) {
        if (user instanceof Student) {
            Enrollment enrollment = getEnrollmentByStudentId(user.getId());
            if (enrollment != null) {
                enrollment.printModules();
            }
        }
    }

    // Retrieves the grade for a given student in a specific module
    public Grade getGrade(Student student, Module module) {
        return grades.stream()
                .filter(grade -> grade.getStudent().equals(student) && grade.getModule().equals(module))
                .findFirst()
                .orElse(null);
    }

    // Checks if a grade exists for the student in the given module
    public boolean gradeExists(Student student, Module module) {
        return getGrade(student, module) != null;
    }

    // Clamps the grade within the range [0, 100] and returns its value
    public int checkGrade(int grade) {
        return Math.max(0, Math.min(100, grade));
    }

    //Adds a grade for a student in a certain module
    public void addGradetoStudent(int studentId, int moduleId,int grade) {
        int mark = checkGrade(grade);
        User user = getUser(studentId);

        if (!(user instanceof Student student)) {
            System.err.println("Student not found");
            return;
        }

        Module module = getModule(moduleId);
        if (module == null) {
            System.err.println("Module not found");
            return;
        }

        Enrollment enrollment = getEnrollmentByStudentId(studentId);
        if (enrollment == null || enrollment.isEmpty()) {
            System.err.println("Enrollment not found");
            return;
        }

        if (!gradeExists(student, module)) {
            grades.add(new Grade(student, module, mark));
            System.out.println("Added grade " + mark + " in " + module.getName() + " for Student " + student.getName());
        } else {
            editGrade(studentId, moduleId, grade);
        }
    }

    //Removes a grade for a student in a certain module
    public void removeGradeFromStudent(int studentId, int moduleId) {
        User user = getUser(studentId);

        if (!(user instanceof Student student)) {
            System.err.println("Student not found");
            return;
        }

        Module module = getModule(moduleId);
        if (module == null) {
            System.err.println("Module not found");
            return;
        }

        Enrollment enrollment = getEnrollmentByStudentId(studentId);
        if (enrollment == null || enrollment.isEmpty()) {
            System.err.println("Enrollment not found");
            return;
        }

        Grade grade = getGrade(student,module);
        if (grade != null) {
            grades.remove(grade);
            System.out.println("Removed grade from " + module.getName() + " for Student " + user.getName());
        } else {
            System.err.println("Grade not found");
        }
    }


    //Edits a grade for a student in a certain module
    public void editGrade(int studentId, int moduleId, int mark) {
        int newgrade = checkGrade(mark);
        User user = getUser(studentId);

        if (!(user instanceof Student student)) {
            System.err.println("Student not found");
            return;
        }

        Module module = getModule(moduleId);
        if (module == null) {
            System.err.println("Module not found");
            return;
        }

        Enrollment enrollment = getEnrollmentByStudentId(studentId);
        if (enrollment == null || enrollment.isEmpty()) {
            System.err.println("Enrollment not found");
            return;
        }

        Grade grade = getGrade(student,module);
        if (grade != null) {
            int oldgrade = grade.getGrade();
            grade.setGrade(newgrade);
            System.out.println("Grade updated from " + oldgrade + " to "+ newgrade + " in " + module.getName() + " for Student " + user.getName());
        }else {
            System.err.println("Grade not found");
        }
    }

    public void PrintFGrade(Grade grade) {
        System.out.printf("%-10d %-30s %-10d %-10C\n", grade.getModule().getId(),grade.getModule().getName() , grade.getGrade(), grade.getGradeChar());
    }

    public ArrayList<Grade> getStudentGrades(int id){
        Student student = (Student) getUser(id);
        if (student != null) {
          ArrayList<Grade> studentGrades = new ArrayList<>();
          grades.stream().filter(grade -> grade.getStudent().equals(student)).forEach(grade -> {studentGrades.add(grade);});
          return studentGrades;
        }
        return null;
    }

    // View student grades using their ID
    public void viewGrades(int studentId) {
        User user = getUser(studentId);

        if (!(user instanceof Student student)) {
            System.err.println("Student not Found");
            return;
        }

        if (grades.isEmpty()) {
            System.err.println("No grades available");
            return;
        }

        boolean foundAGrade = false;
        for (Grade grade : grades) {
            if (grade.getStudent().equals(student)) {
                if (!foundAGrade) {
                    System.out.println("Grades for Student " + student.getName() + ":");
                    System.out.println("---------------------------------------------");
                    System.out.printf("%-10s %-30s %-10s %-10s\n", "Code", "Module Name", "Mark", "Grade");
                    foundAGrade = true;
                }
                PrintFGrade(grade);
            }
        }

        if (!foundAGrade) {
            System.out.println("No grades found for student " + student.getName());
        }
    }
}
