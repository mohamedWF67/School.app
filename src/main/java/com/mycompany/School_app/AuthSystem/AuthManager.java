package com.mycompany.School_app.AuthSystem;

import com.mycompany.School_app.Encryption;
import com.mycompany.School_app.LibrarySystem.Librarian;
import com.mycompany.School_app.LibrarySystem.Library;
import com.mycompany.School_app.School;
import com.mycompany.School_app.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

public class AuthManager {
    static School school;
    static Library library;
    static String status;

    public AuthManager(School school, Library library) {
        this.school = school;
        this.library = library;
        new AuthManagerUI();
    }

    protected static void Authenticate(String email, String password,int mode) {
        if (mode == 0) {
            ArrayList<User> users = school.getUsers();
            User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
            if (user != null) {
                if(Encryption.CheckCorrectness(password,user.getPassword())){
                    setStatus("User Authenticated");
                }else{
                    setStatus("Incorrect Password");
                }
            }else {
                setStatus("Invalid Email");
            }
        } else if (mode == 1) {
            ArrayList<Librarian> users = library.getLibrarians();
            User user = users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
            if (user != null) {
                if(Encryption.CheckCorrectness(password,user.getPassword())){
                    setStatus("Librarian Authenticated");
                }else{
                    setStatus("Incorrect Password");
                }
            }else {
                setStatus("Invalid Email");
            }
        }
    }

    protected static void setStatus(String status) {
        AuthManager.status = status;
        System.out.println(status);
    }

    protected static String getStatus() {
        return AuthManager.status;
    }

    protected static void Register(String username,String email, String password,int mode) {
        if (!school.emailExists(email)) {
            switch (mode) {
                case 0 ->{setStatus("Register a Student");}
                case 1 ->{setStatus("Register a Teacher");}
                case 2 ->{setStatus("Register a Librarian");}
            }
        }
    }

    protected static void RegisterStudent(String username,String email, String password) {

    }
}
