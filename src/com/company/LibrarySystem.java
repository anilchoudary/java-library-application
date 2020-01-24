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
    private String bookFile = "books.ser";

    public LibrarySystem() {
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
        saveFile(outputFile, addedBooks, StandardOpenOption.CREATE);
    }

    public void allLibraryBooks(){
        addedBooks = (ArrayList<Book>)FileUtility.loadObject(outputFile);
        for(Book book: addedBooks){
            System.out.println(book.toString());
        }
    }

    private void borrowBook(){

        }



    public void saveFile(String filename, Object o, StandardOpenOption... option) {
        Path path = Paths.get(filename);
        //FileOutputStream fos = null;
        try {
            //fos = new FileOutputStream(outputFile);
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path, option));
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved filed successfully! " + o.toString());
    }

    public void loadFile(Object obj){
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(outputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        obj = null;
        try {
            obj = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(obj.toString() + "\n");
    }

}
