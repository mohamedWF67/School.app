package com.mycompany.test2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CLI_interface {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    //Method for Adding a new Admin object to the Arraylist in School
    public static void addAdmin(School school, String name, String email, String password) {
        if (!school.emailExists(email)) {
            school.addUser(new Admin(name, email, password));
        }
    }

    //Method for Modifying an admin using id
    public static void modifyAdmin(School school,int id, String name, String email, String password) {
        User user = school.getUser(id);
        if (!school.emailExists(email) &&  user instanceof Admin) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
        }
    }

    //Method for Deleting an admin using id
    public static void deleteAdmin(School school, int id) {
        User user = school.getUser(id);
        if (user instanceof Admin) {
            school.removeUser(id);
        }
    }

    //Method for Adding a new Student object to the Arraylist in School
    public static void addStudent(School school,String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo, int classId, String section) {
        if (school.emailExists(email)) {
            System.out.println("Email already exists");
        }else {
            school.addUser(new Student(name, email, password, parentName, DOB, gender, mobileNo, classId, section));
            System.out.println("Added student");
        }
    }

    //Method for Modifying Student using id
    public static void modifyStudent(School school,int id,String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo, int classId, String section){
        User user = school.getUser(id);
        if (user.getEmail().equals(email) && user instanceof Student || !school.emailExists(email) && user instanceof Student) {
            Student student = (Student) user;
            student.setName(name);
            student.setEmail(email);
            if (password != null) {
                student.setPassword(password);
            }
            student.setParentName(parentName);
            student.setDOB(DOB);
            student.setGender(gender);
            student.setMobileNo(mobileNo);
            student.setClassId(classId);
            student.setSection(section);
            System.out.println("Modified student");
        }else {
            System.out.println("Failed to modify student");
        }
    }

    //Method for Deleting Student using id
    public static void deleteStudent(School school, int id) {
        User user = school.getUser(id);
        if (user instanceof Student) {
            school.removeUser(id);
            System.out.println("Removed student");
        }else{
            System.out.println("No such student was found");
        }
    }

    //Method for Adding a new Teacher object to the Arraylist in School
    public static void addTeacher(School school,String name, String email, String password, String qualification, int salary, String mobileNo, String address) {
        if (!school.emailExists(email)) {
            school.addUser(new Teacher(name, email, password, qualification, salary, mobileNo, address));
            System.out.println("Added teacher");
        }
    }

    //Method for Modifying Teacher using id
    public static void modifyTeacher(School school,int id,String name, String email, String password, String qualification, int salary, String mobileNo, String address){
        User user = school.getUser(id);
        if (!school.emailExists(email) && user instanceof Student) {
            try {
                Teacher teacher = (Teacher) user;
            teacher.setName(name);
            teacher.setEmail(email);
            teacher.setPassword(password);
            teacher.setQualification(qualification);
            teacher.setSalary(salary);
            teacher.setMobileNo(mobileNo);
            teacher.setAddress(address);
            System.out.println("Modified teacher");
            }catch (ClassCastException e) {
                System.out.println("Failed to modify teacher");
            }
        }
    }

    //Method for Deleting Teacher using id
    public static void deleteTeacher(School school, int id) {
        User user = school.getUser(id);
        if (user instanceof Teacher) {
            school.removeUser(id);
            System.out.println("Removed teacher");
        }
    }

    //Method for Adding a new Module object to the Arraylist in School
    public static void addModule(School school,String name, char section, int maxstudents){
        school.addModule(new Module(name, section, maxstudents));
        System.out.println("Added Module");
    }

    //Method for Modifying Module using id
    public static void modifyModule(School school,int id,String name, char section, int maxstudents){
        Module module = school.getModule(id);
        if(module != null){
            module.setName(name);
            module.setSection(section);
            module.setMaxstudents(maxstudents);
            System.out.println("Modified Module");
        }
    }
    //Method for Deleting Module using id
    public static void deleteModule(School school,int id){
        school.deleteModule(id);
    }

    //interface for Module Class
    public static void Cli_module(School school){
        int choice = 0;
        while (choice != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Add Module");
            System.out.println("2. Modify Module");
            System.out.println("3. Delete Module");
            System.out.println("4. List Modules");
            System.out.println("5. Module Report");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            in.nextLine();
            String name;
            char section;
            int maxstudents;
            int id;
            switch (choice) {
                case 1:
                    System.out.println("Enter Module Name:");
                    name = in.nextLine();
                    System.out.println("Enter Module section as char:");
                    section = in.next().charAt(0);
                    System.out.println("Enter Module Student limit:");
                    maxstudents = in.nextInt();
                    in.nextLine();
                    addModule(school, name, section, maxstudents);
                    break;
                case 2:
                    System.out.println("Enter Module id to modify");
                    id = in.nextInt();
                    in.nextLine();
                    Module module = school.getModule(id);
                    if (module instanceof Module) {
                        String checklist;
                        System.out.println("type the numbers of what you want to modify");
                        System.out.println("1. Name");
                        System.out.println("2. Section");
                        System.out.println("3. Student Limit");
                        System.out.println("0. Cancel");
                        checklist = in.nextLine();
                        if(checklist.contains("0")){
                            System.out.println("Exiting..");
                            break;
                        }
                        if (checklist.contains("1")){
                            System.out.println("Enter New Name:");
                            name = in.nextLine();
                        }else {
                            name = school.getModule(id).getName();
                        }
                        if (checklist.contains("2")){
                            System.out.println("Enter New section as char:");
                            section = in.next().charAt(0);
                        }else{
                            section = school.getModule(id).getSection();
                        }
                        if (checklist.contains("3")){
                            System.out.println("Enter New student limit:");
                            maxstudents = in.nextInt();
                        }else{
                            maxstudents = school.getModule(id).getMaxstudents();
                        }
                        modifyModule(school, id, name, section, maxstudents);
                    }else{
                        System.out.println("Module not found");
                    }
                    break;
                case 3:
                    System.out.println("Enter Module id to delete");
                    id = in.nextInt();
                    in.nextLine();
                    deleteModule(school, id);
                    break;
                case 4:
                    school.printModules();
                    break;
                case 5:
                    System.out.println("Enter Module id");
                    id = in.nextInt();
                    in.nextLine();
                    school.GenerateReport(id);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    //interface for Student Class
    public static void Cli_student(School school){
        int choice = 0;
        while (choice != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Add Student");
            System.out.println("2. Modify Student");
            System.out.println("3. Delete Student");
            System.out.println("4. List Students");
            System.out.println("5. List Student Details");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            in.nextLine();
            int id;
            String name;
            String email;
            String password;
            String parentName;
            String dateOfBirth;
            String gender;
            String mobileNo;
            int classId;
            String section;
            switch (choice) {
                case 1:
                    System.out.println("Enter Student name:");
                    name = in.nextLine();
                    System.out.println("Enter Student Email:");
                    email = in.nextLine();
                    System.out.println("Enter Student password:");
                    password = in.nextLine();
                    System.out.println("Enter Student Parent's Name:");
                    parentName = in.nextLine();
                    System.out.println("Enter Student Date of Birth (dd-MM-yyyy):");
                    dateOfBirth = in.nextLine();
                    System.out.println("Enter Student Gender (M or F):");
                    gender = in.nextLine();
                    System.out.println("Enter Student Mobile No:");
                    mobileNo = in.nextLine();
                    System.out.println("Enter Student Class ID:");
                    classId = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter Student Section:");
                    section = in.nextLine();
                    try {
                        addStudent(school, name, email, password, parentName, formatter.parse(dateOfBirth), gender, mobileNo, classId, section);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter Student id to modify:");
                    id = in.nextInt();
                    in.nextLine();
                    User user = school.getUser(id);
                    if (user instanceof Student ) {
                        String checklist;
                        System.out.println("type the numbers of what you want to modify:");
                        System.out.println("1. Name");
                        System.out.println("2. Email");
                        System.out.println("3. Password");
                        System.out.println("4. Parent's Name");
                        System.out.println("5. Date of Birth (dd-MM-yyyy)");
                        System.out.println("6. Gender (F or M)");
                        System.out.println("7. Mobile No");
                        System.out.println("8. Class ID");
                        System.out.println("9. Section");
                        System.out.println("0. Cancel");
                        checklist = in.nextLine();
                        if (checklist.contains("0")) {
                            System.out.println("Exiting..");
                            break;
                        }
                        if (checklist.contains("1")) {
                            System.out.println("Enter Student name:");
                            name = in.nextLine();
                        }else {
                            name = user.getName();
                        }
                        if (checklist.contains("2")) {
                            System.out.println("Enter Student Email:");
                            email = in.nextLine();
                        }else {
                            email = user.getEmail();
                        }
                        if (checklist.contains("3")) {
                            System.out.println("Enter Student password:");
                            password = in.nextLine();
                        }else {
                            password = null;
                        }
                        if (checklist.contains("4")) {
                            System.out.println("Enter Student Parent's Name:");
                            parentName = in.nextLine();
                        }else {
                            parentName = ((Student) user).getParentName();
                        }
                        if (checklist.contains("5")) {
                            System.out.println("Enter Student Date of Birth:");
                            dateOfBirth = in.nextLine();
                        }else{
                            dateOfBirth = formatter.format(((Student) user).getDOB());
                        }
                        if (checklist.contains("6")) {
                            System.out.println("Enter Student Gender:");
                            gender = in.nextLine();
                        }else {
                            gender = ((Student) user).getGender();
                        }
                        if (checklist.contains("7")) {
                            System.out.println("Enter Student Mobile No:");
                            mobileNo = in.nextLine();
                        }else {
                            mobileNo = ((Student) user).getMobileNo();
                        }
                        if (checklist.contains("8")) {
                            System.out.println("Enter Student Class ID:");
                            classId = in.nextInt();
                            in.nextLine();
                        }else {
                            classId = ((Student) user).getClassId();
                        }
                        if (checklist.contains("9")) {
                            System.out.println("Enter Student Section:");
                            section = in.nextLine();
                        }else {
                            section = ((Student) user).getSection();
                        }
                        try {
                            modifyStudent(school, id, name, email, password, parentName, formatter.parse(dateOfBirth), gender, mobileNo, classId, section);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println("No student found for id " + id);
                    }
                    break;
                case 3:
                    System.out.println("Enter Student id to delete:");
                    id = in.nextInt();
                    in.nextLine();
                    deleteStudent(school, id);
                    break;
                case 4:
                    school.printStudents();
                    break;
                case 5:
                    System.out.println("Enter Student id:");
                    id = in.nextInt();
                    in.nextLine();
                    school.printStudent(id);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    public static void Cli_Enrollment(School school) {
        int choice = 0;
        while(choice != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Enroll Student");
            System.out.println("2. Cancel Enrollment");
            System.out.println("3. Switch Modules");
            System.out.println("4. View Enrolled Courses");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            int studentId;
            int moduleId;
            switch (choice){
                case 1 -> {
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();
                    User user = school.getUser(studentId);
                    if (user instanceof Student) {
                        System.out.println("Enter Module id:");
                        moduleId = in.nextInt();
                        in.nextLine();
                        school.enrollStudent(studentId, moduleId);
                    }else{
                        System.out.println("User not found");
                    }
                }
                case 2 ->{
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();
                    User user = school.getUser(studentId);
                    if (user instanceof Student) {
                        System.out.println("Enter Module id:");
                        moduleId = in.nextInt();
                        in.nextLine();
                        school.cancelEnrollment(studentId, moduleId);
                    }else{
                        System.out.println("User not found");
                    }
                }
                case 3 ->{
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();
                    User user = school.getUser(studentId);
                    if (user instanceof Student) {
                        System.out.println("Enter Module id:");
                        moduleId = in.nextInt();
                        in.nextLine();
                        Module module = school.getModule(moduleId);
                        if (module != null && ((Student) user).getEnrolledModules().contains(module)) {
                            System.out.println("Enter New Module id:");
                            int new_moduleId = in.nextInt();
                            in.nextLine();
                            Module new_module = school.getModule(new_moduleId);
                            if (new_module != null) {
                                ((Student) user).cancelEnrollment(module);
                                ((Student) user).enroll(new_module);
                            }
                        }else{
                            System.out.println("Module not found");
                        }
                    }
                }
                case 4 -> {
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();
                    User user = school.getUser(studentId);
                    if (user instanceof Student) {
                        ((Student) user).viewCourses();
                    }
                }
            }
        }
    }

    //Main Selector for every Class
    public static void CLI_selection(School school) {
        int choice = 0;
        while(choice != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Module");
            System.out.println("2. Student");
            System.out.println("3. Enrollment");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    Cli_module(school);
                    break;
                case 2:
                    Cli_student(school);
                    break;
                case 3:
                    Cli_Enrollment(school);
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
