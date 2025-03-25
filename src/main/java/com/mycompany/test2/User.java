/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test2;

/**
 *
 * @author mohamed waleed
 */
public abstract class User {
    private static int counter = 0;
    private int id;
    private String name;
    private String email;
    private String password;

    public User() {
        this.id = counter++;
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

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

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        User.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHahedPassword(String password) {
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
