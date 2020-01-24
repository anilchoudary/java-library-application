package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem implements Serializable {

    private ArrayList<Book> availableBooks = new ArrayList<>();
    private ArrayList<Book> addedBooks = new ArrayList<>();
    private ArrayList<Book> borrowedBooks = new ArrayList<>();
    private String outputFile = "output.ser";
    transient private Scanner input = new Scanner(System.in);


    public LibrarySystem() {
        FileUtility.loadObject(outputFile);
    }

    public void addNewBook(){
        System.out.println("Please add the book title: ");
        String bookTitle = input.nextLine();
        System.out.println("Please add the author: ");
        String author = input.nextLine();
        System.out.println("Please add information about the book: ");
        String aboutThisBook = input.nextLine();

        Book newBook = new Book(bookTitle, author, aboutThisBook);
        addedBooks.add(newBook);
        saveFile(outputFile, addedBooks, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public void allLibraryBooks(){
        List<Book>books = (List<Book>)FileUtility.loadObject(outputFile);
        for(Book b: books){
            System.out.println("NEXT! + " + b.toString());
        }
    }



    public void searchBookTitle(){
        System.out.println("Search title: ");
        String searchTitle = input.nextLine();

        List<Book> searchBooks = (List<Book>)FileUtility.loadObject(outputFile);
        for(Book book: searchBooks){
            if(searchTitle.equals(book.getBookTitle())){
                System.out.print("You searched for this book and we found it! " + "\n" + book.toString() + "\n");
                System.out.println("Would you like to rent this book? [Y]/[N]");
                String choice = input.nextLine();

                switch (choice){
                    case "Y":
                        System.out.println("You will now borrow the book: " + book.getBookTitle() + "\n");
                        addedBooks.remove(book);
                        saveFile(outputFile, addedBooks, StandardOpenOption.APPEND);
                        System.out.println("ENDING");
                        //borrowBook(book);
                        break;

                    case "N":
                        System.out.println("NOT RENTING");
                        break;

                    default:
                        break;
                }

                break;
            }
            else if(!searchTitle.equals(book.getBookTitle())){
                System.out.println("Sorry we could not find the book you were looking for. ");
                break;
            }
        }
    }

    public void searchAuthor(){
        System.out.println("Search author: ");
        String searchTitle = input.nextLine();

        List<Book> searchBooks = (List<Book>)FileUtility.loadObject(outputFile);
        for(Book book: searchBooks){
            if(searchTitle.equals(book.getBookTitle())){
                System.out.print("You searched for this author and we found it! " + "\n" + book.toString() + "\n");
                System.out.println("Would you like to rent this book? [Y]/[N]");
                String choice = input.nextLine();

                switch (choice){
                    case "Y":
                        System.out.println("You will now borrow the book: " + book.getBookTitle() + "\n");
                        addedBooks.remove(book);
                        borrowedBooks.add(book);
                        saveFile(outputFile, addedBooks, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        System.out.println("ENDING");
                        //borrowBook(book);
                        break;

                    case "N":
                        System.out.println("NOT RENTING");
                        break;

                    default:
                        break;
                }

                break;
            }
            else if(!searchTitle.equals(book.getBookTitle())){
                System.out.println("Sorry we could not find the book you were looking for. ");
                break;
            }
        }

    }

    public void borrowedBook(){
        for(Book b: borrowedBooks){
            System.out.println("My borrowed books: " + b);
        }
        }



    public void saveFile(String filename, Object o, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path, option));
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved filed successfully! " + o.toString());
    }

    public void loadFile(Object obj){
        Path path = Paths.get(outputFile);
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
            try {
                obj = ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(obj.toString() + "\n");
    }

}
