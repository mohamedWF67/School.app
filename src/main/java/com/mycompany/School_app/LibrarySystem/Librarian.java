package com.mycompany.School_app.LibrarySystem;

import com.mycompany.School_app.User.User;

public class Librarian extends User {

    String Experience;

    //Constructor for librarian with auto id
    public Librarian(String name, String email, String password, String experience) {
        super(name, email, password);
        Experience = experience;
    }

    //Constructor for librarian with id as an input
    public Librarian(int id, String name, String email, String password, String experience) {
        super(id, name, email, password);
        Experience = experience;
    }

    //Getter for Experience
    public String getExperience() {
        return Experience;
    }

    //Setter for Experience
    public void setExperience(String experience) {
        Experience = experience;
    }

    @Override
    public String toString() {
        return  super.toString()+"Librarian{" +
                "Experience='" + Experience + '\'' +
                "} ";
    }
}
