/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.School_app;

/**
 *
 * @author mohamed waleed
 */

public abstract class User {
    //Attributes and collections
    private static int counter = 0;//Counter for number of objects from user
    private int id;
    private String name;
    private String email;
    private String password;

    //Empty Constructor with Counter increment
    public User() {
        this.id = counter++;
    }

    //Constructor
    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    //Constructor Auto id with password encryption
    public User(String name, String email, String password) {
        id = ++counter;
        this.name = name;
        this.email = email;
        try {
            this.password = Encryption.Encrypt(password);
        }catch (Exception e) {
            System.err.println(e);
        }
    }

    //Getter for Counter
    public static int getCounter() {
        return counter;
    }

    //Setter for Counter
    public static void setCounter(int counter) {
        User.counter = counter;
    }

    //Getter for id
    public int getId() {
        return id;
    }

    //Setter for Id
    public void setId(int id) {
        this.id = id;
    }

    //Getter for name
    public String getName() {
        return name;
    }

    //Setter for name
    public void setName(String name) {
        this.name = name;
    }

    //Getter for Email
    public String getEmail() {
        return email;
    }

    //Setter for Email
    public void setEmail(String email) {
        this.email = email;
    }

    //Getter for password
    public String getPassword() {
        return password;
    }

    //Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    //Setter for password with Hashing
    public void setHashedPassword(String password) {
        try {
            this.password = Encryption.Encrypt(password);
        }catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public String toString() {
        return "User{" + "ID=" + id + ", Name='" + name + '\'' + ", Email='" + email + '\'' + ", Password='" + password + '\'' + '}';
    }
}
