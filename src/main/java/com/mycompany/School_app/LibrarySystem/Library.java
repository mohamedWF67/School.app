package com.mycompany.School_app.LibrarySystem;

import java.util.ArrayList;

public class Library {
    ArrayList<Librarian> librarians;
    ArrayList<Book> books;

    //Constructor
    public Library() {
        librarians = new ArrayList<>();
        books = new ArrayList<>();
    }

    //Getter for Librarians
    public ArrayList<Librarian> getLibrarians() {
        return librarians;
    }

    //Setter for Librarians
    public void setLibrarians(ArrayList<Librarian> librarians) {
        this.librarians = librarians;
    }

    //Getter for Books
    public ArrayList<Book> getBooks() {
        return books;
    }

    //Setter for Books
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    //Add Librarian to librarians
    public Boolean addLibrarian(Librarian librarian) {
        if (!librarians.contains(librarian)) {
            librarians.add(librarian);
            return true;
        }
        return false;
    }

    //Getter for a Librarian
    public Librarian getLibrarian(int id) {
        return librarians.stream().filter(librarian -> librarian.getId() == id).findFirst().orElse(null);
    }

    //Getter for a book
    public Book getBook(String ISBN) {
        return books.stream().filter(book -> book.getISBN().equals(ISBN)).findFirst().orElse(null);
    }

    //Edit a Librarian's info
    public Boolean editLibrarian(int id,String name,String email,String password,String experience) {
        Librarian librarian = getLibrarian(id);
        if (librarian != null) {
            librarian.setName(name);
            librarian.setEmail(email);
            if (password != null) {
                librarian.setHashedPassword(password);
            }
            librarian.setExperience(experience);
            return true;
        }
        return false;
    }

    //Remove a Librarian
    public Boolean removeLibrarian(int id) {
        Librarian librarian = getLibrarian(id);
        if (librarian != null) {
            librarians.remove(librarian);
            return true;
        }
        return false;
    }

    //Add a librarian
    public Boolean addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            return true;
        }
        return false;
    }

    //Remove a Book
    public Boolean removeBook(String ISBN) {
        Book book = getBook(ISBN);
        if (book != null && book.getAvailable()) {
            books.remove(book);
            return true;
        }
        return false;
    }

    //Lend a book
    public Boolean lendBook(String ISBN) {
        Book book = getBook(ISBN);
        if (book != null && book.getAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    //Returns a book
    public Boolean returnBook(String ISBN) {
        Book book = getBook(ISBN);
        if (book != null && !book.getAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    //Checks if an email exists in the Librarians ArrayList
    public boolean emailExists(String Email) {
        return librarians.stream().anyMatch(librarian -> librarian.getEmail().equals(Email));
    }

}