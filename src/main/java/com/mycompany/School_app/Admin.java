package com.mycompany.School_app;

public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    @Override
    public String toString() {
        return "Admin{" + "ID=" + super.getId() + ", Name='" + super.getName() + '\'' + ", Email='" + super.getEmail() + '\'' + ", Password='" + super.getPassword() + '\'' + '}';
    }
}
