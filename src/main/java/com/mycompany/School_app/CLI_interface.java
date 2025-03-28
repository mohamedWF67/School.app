package com.mycompany.School_app;

import com.mycompany.School_app.LibrarySystem.Book;
import com.mycompany.School_app.LibrarySystem.Librarian;
import com.mycompany.School_app.LibrarySystem.Library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CLI_interface {

    //Formatter to convert Date to string and vise-versa
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //Method for Adding a new Admin object to the Arraylist in School
    public static void addAdmin(School school, String name, String Email, String password) {
        if (!school.emailExists(Email)) {
            school.addUser(new Admin(name, Email, password));

            System.out.println("Admin added successfully");
        }else{
            System.err.println("This Email already exists");
        }
    }

    //Method for Modifying an admin using id
    public static void modifyAdmin(School school,int id, String name, String Email, String password) {
        User user = school.getUser(id);
        if (!school.emailExists(Email) &&  user instanceof Admin || user.getEmail().equals(Email) && user instanceof Admin) {
            user.setName(name);
            user.setEmail(Email);
            if (password != null) {
                user.setHashedPassword(password);
            }

            System.out.println("Admin modified successfully");
        }else{
            System.err.println("Failed to modify admin");
        }
    }

    //Method for Deleting an admin using id
    public static void deleteAdmin(School school, int id) {
        User user = school.getUser(id);
        if (user instanceof Admin) {
            school.removeUser(id);

            System.out.println("Admin removed successfully");
        }else{
            System.err.println("Failed to remove admin");
        }
    }

    //Method for Adding a new Student object to the Arraylist in School
    public static void addStudent(School school,String name, String Email, String password, String parentName, Date DOB, String gender, String mobileNo,String address, String section) {
        if (!school.emailExists(Email)) {
            school.addUser(new Student(name, Email, password, parentName, DOB, gender, mobileNo,address, section));

            System.out.println("Added student successfully");
        }else {
            System.err.println("This Email already exists");
        }
    }

    //Method for Modifying Student using id
    public static void modifyStudent(School school,int id,String name, String Email, String password, String parentName, Date DOB, String gender, String mobileNo,String address, String section){
        User user = school.getUser(id);
        if (!school.emailExists(Email) && user instanceof Student || user.getEmail().equals(Email) && user instanceof Student) {
            Student student = (Student) user;
            student.setName(name);
            student.setEmail(Email);
            if (password != null) {
                student.setHashedPassword(password);
            }
            student.setParentName(parentName);
            student.setDOB(DOB);
            student.setGender(gender);
            student.setMobileNo(mobileNo);
            student.setAddress(address);
            student.setSection(section);

            System.out.println("Modified student successfully");
        }else {
            System.err.println("Failed to modify student");
        }
    }

    //Method for Deleting Student using id
    public static void deleteStudent(School school, int id) {
        User user = school.getUser(id);
        if (user instanceof Student) {
            school.removeUser(id);

            System.out.println("Removed student successfully");
        }else{
            System.err.println("Failed to remove student");
        }
    }

    //Method for Adding a new Teacher object to the Arraylist in School
    public static void addTeacher(School school,String name, String Email, String password, String qualification, int salary, String mobileNo, String address) {
        if (!school.emailExists(Email)) {
            school.addUser(new Teacher(name, Email, password, qualification, salary, mobileNo, address));

            System.out.println("Added teacher successfully");
        }else {
            System.err.println("This Email already exists");
        }
    }

    //Method for Modifying Teacher using id
    public static void modifyTeacher(School school,int id,String name, String Email, String password, String qualification, int salary, String mobileNo, String address){
        User user = school.getUser(id);
        if (!school.emailExists(Email) && user instanceof Teacher || user.getEmail().equals(Email) && user instanceof Teacher) {
            try {
                Teacher teacher = (Teacher) user;
            teacher.setName(name);
            teacher.setEmail(Email);
            if (password != null) {
                teacher.setHashedPassword(password);
            }
            teacher.setQualification(qualification);
            if (salary != 0) {
                teacher.setSalary(salary);
            }
            teacher.setMobileNo(mobileNo);
            teacher.setAddress(address);

            System.out.println("Modified teacher successfully");
            }catch (ClassCastException e) {
                System.err.println("Failed to modify teacher");
            }
        }else{
            System.err.println("Failed to modify teacher");
        }
    }

    //Method for Deleting Teacher using id
    public static void deleteTeacher(School school, int id) {
        User user = school.getUser(id);
        if (user instanceof Teacher) {
            school.removeUser(id);

            System.out.println("Removed teacher successfully");
        }else{
            System.err.println("Failed to remove teacher");
        }
    }

    //Method for Adding a new Module object to the Arraylist in School
    public static void addModule(School school,String name, String section, int maxstudents){
        school.addModule(new Module(name, section, maxstudents));
        System.out.println("Added Module successfully");
    }

    //Method for Modifying Module using id
    public static void modifyModule(School school,int id,String name, String section, int maxstudents){
        Module module = school.getModule(id);
        if(module != null){
            module.setName(name);
            module.setSection(section);
            module.setMaxstudents(maxstudents);

            System.out.println("Modified Module successfully");
        }else{
            System.err.println("Failed to modify Module");
        }
    }

    //Method for Deleting Module using id
    public static void deleteModule(School school,int id){
        Module module = school.getModule(id);
        if(module != null){
            school.deleteModule(id);
            System.out.println("Removed Module successfully");
        }else{
            System.err.println("Failed to delete Module");
        }
    }

    //Method for Adding a new Librarian object to the Arraylist in Library
    public static void addLibrarian(School school,String name,String Email,String password,String experience){
        if (!school.emailExists(Email) && !school.getLibrary().emailExists(Email)) {
            school.getLibrary().addLibrarian(new Librarian(name, Email, password, experience));

            System.out.println("Added Librarian successfully");
        }else {
            System.err.println("This Email already exists");
        }
    }

    //interface for Admin
    private static void Cli_Admin(School school) {
        int choice = 0;
        while (choice != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Add Admin");
            System.out.println("2. Modify Admin");
            System.out.println("3. Delete Admin");
            System.out.println("4. List Admins");
            System.out.println("5. Admin Report");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            in.nextLine();
            int id;
            String name;
            String Email;
            String password;
            switch (choice) {
                case 1:
                    System.out.println("Enter Admin Name:");
                    name = in.nextLine();
                    if (name.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Admin Email:");
                    Email = in.nextLine();
                    if (Email.isEmpty() || !(isValidEmail(Email))) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Admin Password:");
                    password = in.nextLine();
                    if (password.isEmpty()) {System.err.println("Invalid");continue;}

                    addAdmin(school,name,Email,password);
                    break;
                case 2:
                    System.out.println("Enter Admin id to modify");
                    id = in.nextInt();
                    in.nextLine();
                    User user = school.getUser(id);
                    if (user instanceof Admin) {
                        String checklist;
                        System.out.println("type the numbers of what you want to modify");
                        System.out.println("1. Name");
                        System.out.println("2. Email");
                        System.out.println("3. Password");
                        System.out.println("0. Cancel");
                        checklist = in.nextLine();
                        if(checklist.contains("0")){
                            System.out.println("Exiting..");
                            break;
                        }
                        if (checklist.contains("1")){
                            System.out.println("Enter New Name:");
                            name = in.nextLine();
                            if (name.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            name = user.getName();
                        }
                        if (checklist.contains("2")){
                            System.out.println("Enter New Email:");
                            Email = in.nextLine();
                            if (Email.isEmpty() || !(isValidEmail(Email))) {System.err.println("Invalid");continue;}
                        }else{
                            Email = user.getEmail();
                        }
                        if (checklist.contains("3")){
                            System.out.println("Enter New Password:");
                            password = in.nextLine();
                            if (password.isEmpty()) {System.err.println("Invalid");continue;}
                        }else{
                            password = null;
                        }
                        modifyAdmin(school,id,name,Email,password);
                    }else{
                        System.err.println("Admin not found");
                    }
                    break;
                case 3:
                    System.out.println("Enter Admin id to delete");
                    id = in.nextInt();
                    in.nextLine();
                    deleteAdmin(school,id);
                    break;
                case 4:
                    school.printAdmins();
                    break;
                case 5:
                    System.out.println("Enter Admin id");
                    id = in.nextInt();
                    in.nextLine();
                    school.printAdmin(id);
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }

    //interface for Student Class
    private static void Cli_student(School school){
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
            String Email;
            String password;
            String parentName;
            String dateOfBirth;
            String gender;
            String mobileNo;
            String address;
            String section;
            switch (choice) {
                case 1:
                    System.out.println("Enter Student name:");
                    name = in.nextLine();
                    if (name.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Student Email:");
                    Email = in.nextLine();
                    if (Email.isEmpty() || !(isValidEmail(Email))) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Student password:");
                    password = in.nextLine();
                    if (password.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Student Parent's Name:");
                    parentName = in.nextLine();
                    if (parentName.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Student Date of Birth (dd/MM/yyyy):");
                    dateOfBirth = in.nextLine();
                    if (dateOfBirth.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Student Gender (M or F):");
                    gender = in.nextLine();
                    if (gender.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Student Mobile No:");
                    mobileNo = in.nextLine();
                    if (mobileNo.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Student Address:");
                    address = in.nextLine();
                    if (address.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Student Section:");
                    section = in.nextLine();
                    if (section.isEmpty()) {System.err.println("Invalid");continue;}

                    try {
                        addStudent(school, name, Email, password, parentName, formatter.parse(dateOfBirth), gender, mobileNo,address, section);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter Student id to modify:");
                    id = in.nextInt();
                    in.nextLine();
                    User user = school.getUser(id);
                    if (user instanceof Student student ) {
                        String checklist;
                        System.out.println("type the numbers of what you want to modify:");
                        System.out.println("1. Name");
                        System.out.println("2. Email");
                        System.out.println("3. Password");
                        System.out.println("4. Parent's Name");
                        System.out.println("5. Date of Birth (dd/MM/yyyy)");
                        System.out.println("6. Gender (F or M)");
                        System.out.println("7. Mobile No");
                        System.out.println("8. Address");
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
                            if (name.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            name = user.getName();
                        }
                        if (checklist.contains("2")) {
                            System.out.println("Enter Student Email:");
                            Email = in.nextLine();
                            if (Email.isEmpty() || !(isValidEmail(Email))) {System.err.println("Invalid");continue;}
                        }else {
                            Email = user.getEmail();
                        }
                        if (checklist.contains("3")) {
                            System.out.println("Enter Student password:");
                            password = in.nextLine();
                            if (password.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            password = null;
                        }
                        if (checklist.contains("4")) {
                            System.out.println("Enter Student Parent's Name:");
                            parentName = in.nextLine();
                            if (parentName.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            parentName = student.getParentName();
                        }
                        if (checklist.contains("5")) {
                            System.out.println("Enter Student Date of Birth (dd/MM/yyyy):");
                            dateOfBirth = in.nextLine();
                            if (dateOfBirth.isEmpty()) {System.err.println("Invalid");continue;}
                        }else{
                            dateOfBirth = formatter.format(student.getDOB());
                        }
                        if (checklist.contains("6")) {
                            System.out.println("Enter Student Gender (M or F):");
                            gender = in.nextLine();
                            if (gender.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            gender = student.getGender();
                        }
                        if (checklist.contains("7")) {
                            System.out.println("Enter Student Mobile No:");
                            mobileNo = in.nextLine();
                            if (mobileNo.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            mobileNo = student.getMobileNo();
                        }
                        if (checklist.contains("8")) {
                            System.out.println("Enter Student Address:");
                            address = in.nextLine();
                            if (address.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            address = student.getAddress();
                        }
                        if (checklist.contains("9")) {
                            System.out.println("Enter Student Section:");
                            section = in.nextLine();
                            if (section.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            section = student.getSection();
                        }
                        try {
                            modifyStudent(school, id, name, Email, password, parentName, formatter.parse(dateOfBirth), gender, mobileNo, address, section);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.err.println("No student found for id " + id);
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
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }

    //interface for Teacher Class
    private static void Cli_Teacher(School school) {
        int choice = 0;
        while (choice != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Add Teacher");
            System.out.println("2. Modify Teacher");
            System.out.println("3. Delete Teacher");
            System.out.println("4. List Teachers");
            System.out.println("5. List Teacher Details");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            in.nextLine();
            int id;
            String name;
            String Email;
            String password;
            String qualification;
            String salary;
            String mobileNo;
            String address;
            switch (choice) {
                case 1:
                    System.out.println("Enter Teacher's name:");
                    name = in.nextLine();
                    if (name.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Teacher's Email:");
                    Email = in.nextLine();
                    if (Email.isEmpty() || !(isValidEmail(Email))) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Teacher's password:");
                    password = in.nextLine();
                    if (password.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Teacher's Qualification:");
                    qualification = in.nextLine();
                    if (qualification.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Teacher's Salary:");
                    salary = in.nextLine();
                    if (salary.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Teacher's Mobile No:");
                    mobileNo = in.nextLine();
                    if (mobileNo.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Teacher's Address:");
                    address = in.nextLine();
                    if (address.isEmpty()) {System.err.println("Invalid");continue;}

                    addTeacher(school, name, Email, password, qualification, removeString(salary), mobileNo, address);
                    break;
                case 2:
                    System.out.println("Enter Teacher id to modify:");
                    id = in.nextInt();
                    in.nextLine();
                    User user = school.getUser(id);
                    if (user instanceof Teacher teacher) {
                        String checklist;
                        System.out.println("type the numbers of what you want to modify:");
                        System.out.println("1. Name");
                        System.out.println("2. Email");
                        System.out.println("3. Password");
                        System.out.println("4. Qualification");
                        System.out.println("5. Salary");
                        System.out.println("6. Mobile No");
                        System.out.println("7. Address");
                        System.out.println("0. Cancel");
                        checklist = in.nextLine();
                        if (checklist.contains("0")) {
                            System.out.println("Exiting..");
                            break;
                        }
                        if (checklist.contains("1")) {
                            System.out.println("Enter Teacher name:");
                            name = in.nextLine();
                            if (name.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            name = user.getName();
                        }
                        if (checklist.contains("2")) {
                            System.out.println("Enter Teacher Email:");
                            Email = in.nextLine();
                            if (Email.isEmpty() || !(isValidEmail(Email))) {System.err.println("Invalid");continue;}
                        }else {
                            Email = user.getEmail();
                        }
                        if (checklist.contains("3")) {
                            System.out.println("Enter Teacher password:");
                            password = in.nextLine();
                            if (password.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            password = null;
                        }
                        if (checklist.contains("4")) {
                            System.out.println("Enter Teacher Qualification:");
                            qualification = in.nextLine();
                            if (qualification.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            qualification = teacher.getQualification();
                        }
                        if (checklist.contains("5")) {
                            System.out.println("Enter Teacher Salary:");
                            salary = in.nextLine();
                            if (salary.isEmpty()) {System.err.println("Invalid");continue;}
                        }else{
                            salary = 0 + "";
                        }
                        if (checklist.contains("6")) {
                            System.out.println("Enter Teacher Mobile No:");
                            mobileNo = in.nextLine();
                            if (mobileNo.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            mobileNo = teacher.getMobileNo();
                        }
                        if (checklist.contains("7")) {
                            System.out.println("Enter Teacher Address:");
                            address = in.nextLine();
                            if (address.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            address = teacher.getMobileNo();
                        }
                        modifyTeacher(school,id, name, Email, password, qualification, removeString(salary), mobileNo, address);
                    }else {
                        System.err.println("No teacher found for id " + id);
                    }
                    break;
                case 3:
                    System.out.println("Enter Teacher's id to delete:");
                    id = in.nextInt();
                    in.nextLine();
                    deleteTeacher(school, id);
                    break;
                case 4:
                    school.printTeachers();
                    break;
                case 5:
                    System.out.println("Enter Teacher's id:");
                    id = in.nextInt();
                    in.nextLine();
                    school.printTeacher(id);
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }

    //interface for Module Class
    private static void Cli_module(School school){
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
            String section;
            int maxstudents;
            int id;
            switch (choice) {
                case 1:
                    System.out.println("Enter Module Name:");
                    name = in.nextLine();
                    if (name.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Module section as char:");
                    section = in.nextLine();
                    if (section.isEmpty()) {System.err.println("Invalid");continue;}

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
                    if (module != null) {
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
                            if (name.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            name = module.getName();
                        }
                        if (checklist.contains("2")){
                            System.out.println("Enter New section as char:");
                            section = in.nextLine();
                            if (section.isEmpty()) {System.err.println("Invalid");continue;}
                        }else{
                            section = module.getSection();
                        }
                        if (checklist.contains("3")){
                            System.out.println("Enter New student limit:");
                            maxstudents = in.nextInt();
                            in.nextLine();
                        }else{
                            maxstudents = module.getMaxstudents();
                        }
                        modifyModule(school, id, name, section, maxstudents);
                    }else{
                        System.err.println("Module not found");
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
                    school.generateReport(id);
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }

    //interface for Enrollment Class
    private static void Cli_Enrollment(School school) {
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
                    System.out.println("Enter Student ID:");
                    studentId = in.nextInt();
                    in.nextLine();

                    if (school.getUser(studentId) instanceof Student student) {
                        school.printCompatibleModules(student);

                        System.out.println("Enter Module id:");
                        moduleId = in.nextInt();
                        in.nextLine();

                        school.enrollStudent(studentId, moduleId);
                    }else{
                        System.err.println("User not found");
                    }
                }
                case 2 -> {
                    System.out.println("Enter Student ID: ");
                    studentId = in.nextInt();
                    in.nextLine();

                    User user = school.getUser(studentId);
                    if (!(user instanceof Student student)) {
                        System.err.println("User not found or not a student");
                        return;
                    }

                    Enrollment enrollment = school.getEnrollmentByStudentId(studentId);
                    if (enrollment == null) {
                        System.err.println("Enrollment not found");
                        return;
                    }

                    school.printEnrolledModules(student);

                    System.out.println("Enter Module ID to cancel enrollment: ");
                    moduleId = in.nextInt();
                    in.nextLine();

                    school.cancelEnrollment(studentId, moduleId);
                }

                case 3 -> {
                    System.out.println("Enter Student ID: ");
                    studentId = in.nextInt();
                    in.nextLine();

                    User user = school.getUser(studentId);
                    if (!(user instanceof Student student)) {
                        System.err.println("User not found or not a student");
                        return;
                    }

                    Enrollment enrollment = school.getEnrollmentByStudentId(studentId);
                    if (enrollment == null || enrollment.isEmpty()) {
                        System.err.println("No enrollment found");
                        return;
                    }

                    school.printEnrolledModules(student);

                    System.out.println("Enter Module ID to swap from: ");
                    int oldModuleId = in.nextInt();
                    in.nextLine();
                    Module oldModule = school.getModule(oldModuleId);

                    if (oldModule == null || !enrollment.moduleExists(oldModule)) {
                        System.err.println("Module not found or not enrolled in this module");
                        return;
                    }

                    school.printCompatibleModules(student);

                    System.out.println("Enter New Module ID to swap into: ");
                    int newModuleId = in.nextInt();
                    in.nextLine();
                    Module newModule = school.getModule(newModuleId);

                    if (newModule == null) {
                        System.err.println("New module not found");
                        return;
                    }

                    school.swapEnrollments(studentId, oldModuleId, newModuleId);
                }
                case 4 -> {
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();

                    if (school.getUser(studentId) instanceof Student) {
                        school.viewStudentEnrollments(studentId);
                    }else{
                        System.err.println("User not found");
                    }
                }
                case -1 -> {}
                default -> System.err.println("Invalid choice");
            }
        }
    }

    //interface for Grade Class
    private static void Cli_Grade(School school) {
        int choice = 0;
        while(choice != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Enter Grade for Student");
            System.out.println("2. Edit Grade for Student");
            System.out.println("3. Remove Grade from Student");
            System.out.println("4. View Student Grades");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            int studentId;
            int moduleId;
            int grade;
            switch (choice){
                case 1 -> {
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();

                    if (school.getUser(studentId) instanceof Student student) {
                        school.printEnrolledModules(student);
                        System.out.println("Enter Module id:");
                        moduleId = in.nextInt();
                        in.nextLine();

                        if(school.isModuleInEnrollments(student,school.getModule(moduleId))){
                            System.out.println("Enter Grade:");
                            grade = in.nextInt();
                            in.nextLine();
                            school.addGradetoStudent(studentId, moduleId, grade);
                        }else {
                            System.err.println("Module not found");
                        }
                    }else{
                        System.err.println("User not found");
                    }
                }
                case 2 ->{
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();

                    if (school.getUser(studentId) instanceof Student student) {
                        school.viewGrades(studentId);
                        System.out.println("Enter Module id:");
                        moduleId = in.nextInt();
                        in.nextLine();

                        if(school.isModuleInEnrollments(student,school.getModule(moduleId))){
                            System.out.println("Enter Grade:");
                            grade = in.nextInt();
                            in.nextLine();

                            school.editGrade(studentId, moduleId, grade);
                        }else {
                            System.err.println("Module not found");
                        }
                    }else{
                        System.err.println("User not found");
                    }
                }
                case 3 ->{
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();

                    if ((school.getUser(studentId) instanceof Student student)) {
                        school.viewGrades(studentId);
                        System.out.println("Enter Module id:");
                        moduleId = in.nextInt();
                        in.nextLine();

                        if(school.isModuleInEnrollments(student,school.getModule(moduleId))){
                            school.removeGradeFromStudent(studentId, moduleId);
                        }else {
                            System.err.println("Module not found");
                        }
                    }else{
                        System.err.println("User not found");
                    }
                }
                case 4 -> {
                    System.out.println("Enter Student id:");
                    studentId = in.nextInt();
                    in.nextLine();

                    if (school.getUser(studentId) instanceof Student) {
                        school.viewGrades(studentId);
                    }else{
                        System.err.println("User not found");
                    }
                }
                case -1 -> {}
                default -> System.err.println("Invalid choice");
            }
        }
    }

    private static void Cli_Librarian(School school){
        int choice = 0;
        while (choice != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Add Librarian");
            System.out.println("2. Modify Librarian");
            System.out.println("3. Delete Librarian");
            System.out.println("4. List Librarians");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = in.nextInt();
            in.nextLine();
            int id;
            String name;
            String Email;
            String password;
            String experience;
            switch (choice) {
                case 1:
                    System.out.println("Enter Librarian Name:");
                    name = in.nextLine();
                    if (name.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Librarian Email:");
                    Email = in.nextLine();
                    if (Email.isEmpty() || !(isValidEmail(Email))) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Librarian Password:");
                    password = in.nextLine();
                    if (password.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter Librarian Experience:");
                    experience = in.nextLine();
                    if (experience.isEmpty()) {System.err.println("Invalid");continue;}

                    addLibrarian(school,name,Email,password,experience);
                    break;
                case 2:
                    System.out.println("Enter Librarian id to modify");
                    id = in.nextInt();
                    in.nextLine();
                    Librarian librarian = school.getLibrary().getLibrarian(id);
                    if (librarian != null) {
                        String checklist;
                        System.out.println("type the numbers of what you want to modify");
                        System.out.println("1. Name");
                        System.out.println("2. Email");
                        System.out.println("3. Password");
                        System.out.println("4. Experience");
                        System.out.println("0. Cancel");
                        checklist = in.nextLine();
                        if(checklist.contains("0")){
                            System.out.println("Exiting..");
                            break;
                        }
                        if (checklist.contains("1")){
                            System.out.println("Enter New Name:");
                            name = in.nextLine();
                            if (name.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            name = librarian.getName();
                        }
                        if (checklist.contains("2")){
                            System.out.println("Enter New Email:");
                            Email = in.nextLine();
                            if (Email.isEmpty() || !(isValidEmail(Email))) {System.err.println("Invalid");continue;}
                        }else{
                            Email = librarian.getEmail();
                        }
                        if (checklist.contains("3")){
                            System.out.println("Enter New Password:");
                            password = in.nextLine();
                            if (password.isEmpty()) {System.err.println("Invalid");continue;}
                        }else{
                            password = null;
                        }
                        if (checklist.contains("4")){
                            System.out.println("Enter New Experience:");
                            experience = in.nextLine();
                            if (experience.isEmpty()) {System.err.println("Invalid");continue;}
                        }else {
                            experience = librarian.getExperience();
                        }

                        school.getLibrary().editLibrarian(id, name, Email, password, experience);
                    }else{
                        System.err.println("Librarian not found");
                    }
                    break;
                case 3:
                    System.out.println("Enter Librarian id to delete");
                    id = in.nextInt();
                    in.nextLine();

                    if (school.getLibrary().removeLibrarian(id)){
                        System.out.println("Librarian deleted successfully");
                    }else{
                        System.err.println("Librarian not found");
                    }
                    break;
                case 4:
                    school.getLibrary().getLibrarians().stream().forEach(l -> System.out.println(l));
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        }
    }

    //Interface for Library
    private static void Cli_Library(School school) {
        int choice = 0;
        String[] choices = {"Add Book","Remove Book","Lend Book","Return Book","View Books"};
        while(choice != -1) {
            System.out.println("Choose your choice");
            for (int i = 0; i < choices.length; i++) {
                System.out.println( (i+1) + ". " + choices[i]);
            }
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            String ISBN;
            String name;
            String author;
            switch (choice) {
                case 1:
                    System.out.println("Enter ISBN :");
                    ISBN = in.nextLine();
                    if (ISBN.isEmpty()) {System.err.println("Invalid");continue;}
                    if (school.getLibrary().getBook(CleanString(ISBN)) != null) {System.err.println("Book Already in Library");continue;}

                    System.out.println("Enter the Book's name :");
                    name = in.nextLine();
                    if (name.isEmpty()) {System.err.println("Invalid");continue;}

                    System.out.println("Enter the Author's name :");
                    author = in.nextLine();
                    if (author.isEmpty()) {System.err.println("Invalid");continue;}

                    if (school.getLibrary().addBook(new Book(CleanString(ISBN), name, author))) {
                        System.out.println("Book Added");
                    }else {
                        System.err.println("Book insertion failed");
                    }
                    break;
                case 2:
                    System.out.println("Enter ISBN :");
                    ISBN = in.nextLine();
                    if (ISBN.isEmpty()) {System.err.println("Invalid");continue;}
                    if(school.getLibrary().removeBook(CleanString(ISBN))){
                        System.out.println("Book Removed");
                    }else {
                        System.err.println("Book Cannot be removed");
                    }
                    break;
                case 3:
                    System.out.println("Enter ISBN :");
                    ISBN = in.nextLine();
                    if (ISBN.isEmpty()) {System.err.println("Invalid");continue;}
                    if(school.getLibrary().lendBook(CleanString(ISBN))){
                        System.out.println("Book Lent");
                    }else {
                        System.err.println("Book Cannot be Lent");
                    }
                    break;
                case 4:
                    System.out.println("Enter ISBN :");
                    ISBN = in.nextLine();
                    if (ISBN.isEmpty()) {System.err.println("Invalid");continue;}
                    if(school.getLibrary().returnBook(CleanString(ISBN))){
                        System.out.println("Book Returned");
                    }else {
                        System.err.println("Book Taken");
                    }
                    break;
                case 5:
                    school.getLibrary().getBooks().stream().forEach(book -> System.out.println(book));
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
                    break;
            }
        }
    }

    //Selector if the user is Admin
    public static void CLI_Admin_select(School school) {
        int choice = 0;
        String[] choices = {"Manage Admin", "Manage Student", "Manage Teacher", "Manage Module","Manage Enrollment","Manage Grade","Manage librarian","Manage Library"};
        while(choice != -1) {
            System.out.println("Choose your choice");
            for (int i = 0; i < choices.length; i++) {
                System.out.println( (i+1) + ". " + choices[i]);
            }
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    Cli_Admin(school);
                    break;
                case 2:
                    Cli_student(school);
                    break;
                case 3:
                    Cli_Teacher(school);
                    break;
                case 4:
                    Cli_module(school);
                    break;
                case 5:
                    Cli_Enrollment(school);
                    break;
                case 6:
                    Cli_Grade(school);
                    break;
                case 7:
                    Cli_Librarian(school);
                    break;
                case 8:
                    Cli_Library(school);
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
                    break;
            }
        }
    }

    //Selector if the user is Student
    public static void CLI_Student_select(School school) {
        int choice = 0;
        String[] choices = {"Manage Student","Enrollment"};
        while(choice != -1) {
            System.out.println("Choose your choice");
            for (int i = 0; i < choices.length; i++) {
                System.out.println( (i+1) + ". " + choices[i]);
            }
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    Cli_student(school);
                    break;
                case 2:
                    Cli_Enrollment(school);
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
                    break;
            }
        }
    }

    //Selector if the user is Teacher
    public static void CLI_Teacher_select(School school) {
        int choice = 0;
        String[] choices = {"Teacher","Grade"};
        while(choice != -1) {
            System.out.println("Choose your choice");
            for (int i = 0; i < choices.length; i++) {
                System.out.println( (i+1) + ". " + choices[i]);
            }
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    Cli_Teacher(school);
                    break;
                case 2:
                    Cli_Grade(school);
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
                    break;
            }
        }
    }

    private static void CLI_Librarian_select(School school) {
        int choice = 0;
        String[] choices = {"Library"};
        while(choice != -1) {
            System.out.println("Choose your choice");
            for (int i = 0; i < choices.length; i++) {
                System.out.println( (i+1) + ". " + choices[i]);
            }
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    Cli_Library(school);
                    break;
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
                    break;
            }
        }
    }

    //Main Selector for every Class
    public static void CLI_selection(School school) {
        System.out.println("Welcome to " + school.getName());
        int choice = 0;
        String[] choices = {"Admin", "Student", "Teacher","Librarian"};
        while(choice != -1) {
            System.out.println("Choose your choice");
            for (int i = 0; i < choices.length; i++) {
                System.out.println( (i+1) + ". " + choices[i]);
            }
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            choice = Integer.parseInt(in.nextLine());
            switch (choice) {
                case 1:
                    CLI_Admin_select(school);
                    break;
                case 2:
                    CLI_Student_select(school);
                    break;
                case 3:
                    CLI_Teacher_select(school);
                    break;
                case 4:
                    CLI_Librarian_select(school);
                case -1:
                    break;
                default:
                    System.err.println("Invalid choice");
                    break;
            }
        }
    }

    //Function to remove characters from a String with numbers
    public static int removeString(String str) {
        String CleanString = "";
        if (str.isEmpty()) {
            return 0;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                CleanString += ch;
            }
        }
        return Integer.parseInt(CleanString);
    }

    //Return as string
    public static String CleanString(String str) {
        return removeString(str)+"";
    }

    //Checks the validity of the Email
    public static boolean isValidEmail(String Email) {
        if (Email == null || Email.isBlank()) return false;

        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Email.matches(regex);
    }

}
