package com.mycompany.School_app.LibrarySystem;

import java.io.Serializable;

public class Book implements Serializable {
    String ISBN;
    String name;
    String author;
    Boolean isAvailable;

    //Constructor
    public Book(String ISBN, String name, String author) {
        this.ISBN = ISBN;
        this.name = name;
        this.author = author;
        this.isAvailable = true;
    }

    //Getter for ISBN
    public String getISBN() {
        return ISBN;
    }

    //Setter for ISBN
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    //Getter for name
    public String getName() {
        return name;
    }

    //Setter for name
    public void setName(String name) {
        this.name = name;
    }

    //Getter for author
    public String getAuthor() {
        return author;
    }

    //Setter for author
    public void setAuthor(String author) {
        this.author = author;
    }

    //Getter for isAvailable
    public Boolean getAvailable() {
        return isAvailable;
    }

    //Setter for isAvailable
    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
