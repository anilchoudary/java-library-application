package com.company;

import java.io.Serializable;

public class Book implements Serializable {

    private String bookTitle;
    private String author;
    private String aboutThisBook;

    // Added to resolve invalid class exception
    private static final long serialVersionUID = 4095936714703586143L;

    public Book(String bookTitle, String author, String aboutThisBook) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.aboutThisBook = aboutThisBook;
    }

    public boolean isAvailable(Boolean available){
        return available;
    }

    @Override
    public String toString() {
        return "[Book] " + "\n" +
                "TITLE: '" + bookTitle + '\''  + "\n" +
                "AUTHOR: '" + author + '\''  + "\n" +
                "ABOUT: '" + aboutThisBook + '\''  + "\n";
    }
}
