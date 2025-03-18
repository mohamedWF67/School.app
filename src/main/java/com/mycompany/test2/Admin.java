package com.mycompany.test2;

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
        return "Admin{" + "id=" + super.getId() + ", name='" + super.getName() + '\'' + ", email='" + super.getEmail() + '\'' + ", password='" + super.getPassword() + '\'' + '}';
    }
}
