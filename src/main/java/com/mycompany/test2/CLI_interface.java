package com.mycompany.test2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CLI_interface {

    public static void addAdmin(School school, String name, String email, String password) {
        if (school.emailExists(email)) {
            return;
        }else{
            school.addUser(new Admin(name, email, password));
        }
    }

    public static void modifyAdmin(School school,int id, String name, String email, String password) {
        User user = school.getUser(id);
        if (!school.emailExists(email) &&  user instanceof Admin) {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
        }else {
            return;
        }
    }

    public static void deleteAdmin(School school, int id) {
        User user = school.getUser(id);
        if (user != null && user instanceof Admin) {
            school.removeUser(id);
        }
    }

    public static void addStudent(School school,String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo, int classId, String section) {
        if (school.emailExists(email)) {
            System.out.println("Email already exists");
            return;
        }else {
            school.addUser(new Student(name, email, password, parentName, DOB, gender, mobileNo, classId, section));
            System.out.println("Added student");
        }
    }

    public static void modifyStudent(School school,int id,String name, String email, String password, String parentName, Date DOB, String gender, String mobileNo, int classId, String section){
        User user = school.getUser(id);
        if (!school.emailExists(email) && user instanceof Student) {
            Student student = (Student) user;
            student.setName(name);
            student.setEmail(email);
            student.setPassword(password);
            student.setParentName(parentName);
            student.setDOB(DOB);
            student.setGender(gender);
            student.setMobileNo(mobileNo);
            student.setClassId(classId);
            student.setSection(section);
            System.out.println("Modified student");
        }
    }

    public static void deleteStudent(School school, int id) {
        User user = school.getUser(id);
        if (user != null && user instanceof Student) {
            school.removeUser(id);
            System.out.println("Removed student");
        }
    }

    public static void addTeacher(School school,String name, String email, String password, String qualification, int salary, String mobileNo, String address) {
        if (school.emailExists(email)) {
            return;
        }else {
            school.addUser(new Teacher(name, email, password, qualification, salary, mobileNo, address));
            System.out.println("Added teacher");
        }
    }

    public static void modifyTeacher(School school,int id,String name, String email, String password, String qualification, int salary, String mobileNo, String address){
        User user = school.getUser(id);
        if (!school.emailExists(email) && user instanceof Student) {
            Teacher teacher = (Teacher) user;
            teacher.setName(name);
            teacher.setEmail(email);
            teacher.setPassword(password);
            teacher.setQualification(qualification);
            teacher.setSalary(salary);
            teacher.setMobileNo(mobileNo);
            teacher.setAddress(address);
            System.out.println("Modified teacher");
        }
    }

    public static void deleteTeacher(School school, int id) {
        User user = school.getUser(id);
        if (user != null && user instanceof Teacher) {
            school.removeUser(id);
            System.out.println("Removed teacher");
        }
    }

    public static void addModule(School school,String name, char section, int maxstudents){
        school.addModule(new Module(name, section, maxstudents));
        System.out.println("Added Module");
    }

    public static void modifyModule(School school,int id,String name, char section, int maxstudents){
        Module module = school.getModule(id);
        if(module != null){
            module.setName(name);
            module.setSection(section);
            module.setMaxstudents(maxstudents);
            System.out.println("Modified Module");
        }
    }

    public static void deleteModule(School school,int id){
        school.deleteModule(id);
    }

    public static void Cli_module(School school){
        int x = 0;
        while (x != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Add Module");
            System.out.println("2. Modify Module");
            System.out.println("3. Delete Module");
            System.out.println("4. List Modules");
            System.out.println("5. Module Report");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            x = in.nextInt();
            switch (x) {
                case 1:
                    System.out.println("Enter Module name");
                    String name = in.nextLine();
                    name = in.nextLine();
                    System.out.println("Enter Module section as char");
                    char section = in.next().charAt(0);
                    System.out.println("Enter Module maxstudents");
                    int maxstudents = in.nextInt();
                    addModule(school, name, section, maxstudents);
                    break;
                case 2:
                    System.out.println("Enter Module id to modify");
                    int id = in.nextInt();
                    System.out.println("Enter New Module name");
                    name = in.nextLine();
                    name = in.nextLine();
                    System.out.println("Enter New Module section as char");
                    section = in.next().charAt(0);
                    System.out.println("Enter New Module maxstudents");
                    maxstudents = in.nextInt();
                    modifyModule(school, id, name, section, maxstudents);
                    break;
                case 3:
                    System.out.println("Enter Module id to delete");
                    id = in.nextInt();
                    deleteModule(school, id);
                    break;
                case 4:
                    school.printModules();
                    break;
                case 5:
                    System.out.println("Enter Module id");
                    id = in.nextInt();
                    school.GenerateReport(id);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void Cli_student(School school){
        int x = 0;
        while (x != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Add Student");
            System.out.println("2. Modify Student");
            System.out.println("3. Delete Student");
            System.out.println("4. List Students");
            System.out.println("5. List Student Details");
            System.out.println("-1. Exit");
            Scanner in = new Scanner(System.in);
            x = in.nextInt();
            switch (x) {
                case 1:
                    System.out.println("Enter Student name");
                    String name = in.nextLine();
                    name = in.nextLine();
                    System.out.println("Enter Student Email");
                    String email = in.nextLine();
                    System.out.println("Enter Student password");
                    String password = in.nextLine();
                    System.out.println("Enter Student Parent's Name");
                    String parentName = in.nextLine();
                    System.out.println("Enter Student Date of Birth");
                    String dateOfBirth = in.nextLine();
                    System.out.println("Enter Student Gender");
                    String gender = in.nextLine();
                    System.out.println("Enter Student Mobile No");
                    String mobileNo = in.nextLine();
                    System.out.println("Enter Student Class ID");
                    int classId = in.nextInt();
                    System.out.println("Enter Student Section");
                    String section = in.nextLine();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        addStudent(school, name, email, password, parentName, formatter.parse(dateOfBirth), gender, mobileNo, classId, section);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("Enter Student id to modify");
                    int id = in.nextInt();
                    System.out.println("Enter Student name");
                    name = in.nextLine();
                    System.out.println("Enter Student Email");
                    email = in.nextLine();
                    System.out.println("Enter Student password");
                    password = in.nextLine();
                    System.out.println("Enter Student Parent's Name");
                    parentName = in.nextLine();
                    System.out.println("Enter Student Date of Birth");
                    dateOfBirth = in.nextLine();
                    System.out.println("Enter Student Gender");
                    gender = in.nextLine();
                    System.out.println("Enter Student Mobile No");
                    mobileNo = in.nextLine();
                    System.out.println("Enter Student Class ID");
                    classId = in.nextInt();
                    System.out.println("Enter Student Section");
                    section = in.nextLine();
                    formatter = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        modifyStudent(school, id, name, email, password, parentName, formatter.parse(dateOfBirth), gender, mobileNo, classId, section);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Enter Module id to delete");
                    id = in.nextInt();
                    deleteStudent(school, id);
                    break;
                case 4:
                    school.printStudents();
                    break;
                case 5:
                    System.out.println("Enter Student id");
                    id = in.nextInt();
                    school.printStudent(id);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void CLI_selection(School school) {
        int x = 0;
        while(x != -1) {
            System.out.println("Choose your choice");
            System.out.println("1. Module");
            System.out.println("2. Student");
            Scanner in = new Scanner(System.in);
            x = in.nextInt();
            switch (x) {
                case 1:
                    Cli_module(school);
                    break;
                case 2:
                    Cli_student(school);
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
