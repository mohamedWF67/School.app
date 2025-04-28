package com.mycompany.School_app.AuthSystem;

import com.mycompany.School_app.*;
import com.mycompany.School_app.LibrarySystem.Librarian;
import com.mycompany.School_app.LibrarySystem.Library;
import com.mycompany.School_app.MainApp.MainApp;
import com.mycompany.School_app.StatusSystem.Status;
import com.mycompany.School_app.StatusSystem.StatusManager;
import com.mycompany.School_app.User.Student;
import com.mycompany.School_app.User.Teacher;
import com.mycompany.School_app.User.User;

import java.util.ArrayList;
import java.util.Date;

public class AuthManager {
    static School school;
    static Library library;
    static Status status;
    static AuthManagerUI authManagerUI;
    static StatusManager statusManager;

    public AuthManager(School school, Library library) {
        this.school = school;
        this.library = library;
        Start();
        this.statusManager = new StatusManager();
    }

    public static void Start() {
        authManagerUI = new AuthManagerUI();
    }

    protected static void Authenticate(String email, String password,int mode) {
        if (!CLI_interface.isValidEmail(email)){
            setStatus(1,"Invalid Email Address");
            return;
        }
        switch (mode) {
            case 0->{
                ArrayList<User> users = school.getUsers();
                User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);

                if (user == null) {
                    setStatus(1,"Invalid Email");
                    return;
                }

                if(Encryption.CheckCorrectness(password,user.getPassword())){
                    setStatus(-1,"User Authenticated");
                    new MainApp(user);
                    authManagerUI.dispose();
                }else{
                    setStatus(2,"Incorrect Password");
                }
            }
            case 1->{
                ArrayList<Librarian> users = library.getLibrarians();
                User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);

                if (user == null) {
                    setStatus(1,"Invalid Email");
                    return;
                }

                if(Encryption.CheckCorrectness(password,user.getPassword())){
                    setStatus(-1,"Librarian Authenticated");
                    new MainApp(user);
                    authManagerUI.dispose();
                }else{
                    setStatus(2,"Incorrect Password");
                }
            }
            default->{
                setStatus(3,"Invalid Mode");
            }
        }
    }

    protected static void setStatus(int errorCode,String statusMessage) {
        AuthManager.status = statusManager.addStatus(new Status(errorCode,statusMessage));
        System.out.println(statusMessage);
    }

    protected static Status getStatus() {
        return AuthManager.status;
    }

    protected static void Register(String username,String email, String password,int mode) {
        if (username.isEmpty()) {
            setStatus(1, "Username is Empty");
            return;
        }

        if (!CLI_interface.isValidEmail(email)) {
            setStatus(2, "Invalid Email Address");
            return;
        }

        if (school.emailExists(email)) {
            setStatus(2, "Email Already Exists");
            return;
        }

        if (password.isEmpty()) {
            setStatus(3, "Password is Empty");
            return;
        }

            switch (mode) {
                case 0 ->{
                    setStatus(-1,"Register a Student");
                    new StudentCreatorUI(username,email,password);
                    authManagerUI.dispose();
                }
                case 1 ->{
                    setStatus(-1,"Register a Teacher");
                    new TeacherCreatorUI(username,email,password);
                    authManagerUI.dispose();
                }
                case 2 ->{
                    setStatus(-1,"Register a Librarian");
                    new LibrarianCreatorUI(username,email,password);
                    authManagerUI.dispose();
                }
            }
    }

    protected static void RegisterStudent(String username, String email, String password, String parentname, Date dob,String gender,String mobileNo,String address,String section) {
        if (parentname.isEmpty()) {
            setStatus(1, "Parent Name is Empty");
            return;
        }

        if (dob == null) {
            setStatus(2, "Date of birth is Empty");
            return;
        }

        if (mobileNo.isEmpty()) {
            setStatus(4, "Mobile Number is Empty");
            return;
        }

        if (address.isEmpty()) {
            setStatus(5, "Address is Empty");
            return;
        }

        if (section.isEmpty()) {
            setStatus(6, "Section is Empty");
            return;
        }

        Student student = new Student(username,email,password,parentname,dob,gender,mobileNo,address,section.toUpperCase());
        school.addUser(student);

        setStatus(-1,"Student Registered");
        new MainApp(student);
    }

    protected static void RegisterTeacher(String username, String email, String password,String qualification,int salary,String mobileNo,String address) {

        if (qualification.isEmpty()) {
            setStatus(1, "Qualification is Empty");
            return;
        }

        if (salary < 0) {
            setStatus(2, "Salary is Empty");
            return;
        }

        if (mobileNo.isEmpty()) {
            setStatus(3, "Mobile Number is Empty");
            return;
        }

        if (address.isEmpty()) {
            setStatus(4, "Address is Empty");
            return;
        }
        Teacher teacher = new Teacher(username,email,password,qualification,salary,mobileNo,address);
        school.addUser(teacher);

        setStatus(-1,"Teacher Registered");
        new MainApp(teacher);
    }

    protected static void RegisterLibrarian(String username, String email, String password,String experience) {
        Librarian librarian = new Librarian(username,email,password,experience);
        school.getLibrary().addLibrarian(librarian);

        setStatus(-1,"Librarian Registered");
        new MainApp(librarian);
    }
}
