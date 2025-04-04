package com.mycompany.School_app;

import com.mycompany.School_app.User.User;

public class Admin extends User {

    //Empty Constructor
    public Admin() {
        super();
    }

    //Constructor with id as an input
    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    //Constructor with id automatic
    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public String toString() {
        return "Admin{" + "ID=" + super.getId() + ", Name='" + super.getName() + '\'' + ", Email='" + super.getEmail() + '\'' + ", Password='" + super.getPassword() + '\'' + '}';
    }
}
