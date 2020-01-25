package com.company;

import java.io.Serializable;

public class Book implements Serializable {

    private String bookTitle;
    private String author;
    private String aboutThisBook;
    private boolean available;

    // Added to resolve invalid class exception
    private static final long serialVersionUID = 4095936714703586143L;



    public Book(String bookTitle, String author, String aboutThisBook, boolean available) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.aboutThisBook = aboutThisBook;
        this.available = available;

    }


    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "[Book] " + "\n" +
                "TITLE: '" + bookTitle + '\''  + "\n" +
                "AUTHOR: '" + author + '\''  + "\n" +
                "ABOUT: '" + aboutThisBook + '\''  + "\n" +
                "AVAILABLE: '" + available + '\''  + "\n";
    }
}
