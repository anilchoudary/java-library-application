package com.company;

import java.io.Serializable;

public class Book implements Serializable {

    private String bookTitle;
    private String author;
    private String aboutThisBook;

    public Book(String bookTitle, String author, String aboutThisBook) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.aboutThisBook = aboutThisBook;
    }

    @Override
    public String toString() {
        return "[Book] " + "\n" +
                "TITLE: '" + bookTitle + '\''  + "\n" +
                "AUTHOR: '" + author + '\''  + "\n" +
                "ABOUT: '" + aboutThisBook + '\''  + "\n";
    }
}
