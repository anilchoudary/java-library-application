package com.company;

import java.io.Serializable;

public class Book implements Serializable {

    private String bookTitle;
    private String author;
    private String aboutThisBook;
    private boolean available;
    private String libraryUser;

    // Added to resolve invalid class exception
    private static final long serialVersionUID = 4095936714703586143L;



    public Book(String bookTitle, String author, String aboutThisBook, boolean available, String libraryUser) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.aboutThisBook = aboutThisBook;
        this.available = available;
        this.libraryUser = libraryUser;
    }


    public String getBookTitle() {
        return bookTitle;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(String libraryUser) {
        this.libraryUser = libraryUser;
    }

    // For admin only, should be able to see library users
    public String toString() {
        return "[Book] " + "\n" +
                "TITLE: '" + bookTitle + '\''  + "\n" +
                "AUTHOR: '" + author + '\''  + "\n" +
                "ABOUT: '" + aboutThisBook + '\''  + "\n" +
                "AVAILABLE: '" + available + '\''  + "\n" +
                "RENTED BY: '" + libraryUser + '\''  + "\n";
    }

    // Library members should not the library users who has rented the book
    public String toStringMembers() {
        return "[Book] " + "\n" +
                "TITLE: '" + bookTitle + '\''  + "\n" +
                "AUTHOR: '" + author + '\''  + "\n" +
                "ABOUT: '" + aboutThisBook + '\''  + "\n" +
                "AVAILABLE: '" + available + '\''  + "\n";
    }


}
