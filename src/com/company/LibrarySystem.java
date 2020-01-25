package com.company;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaNamespaceSupport;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem implements Serializable {

    private List<Book> addedBooks;
    private List<Book> borrowedBooks;
    private List<Book> availableBooks;
    transient private Scanner input = new Scanner(System.in);
    private static final long serialVersionUID = 4220048072494770351L;



    public LibrarySystem() {
        addedBooks = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
        availableBooks = new ArrayList<>();
    }

    public void addBookToArray(Book book){
        addedBooks.add(book);
        availableBooks.add(book);
        System.out.println("Successfully added " + book.getBookTitle() + " to the system. ");
    }

    public void removeBookFromArray(String removeBook){
        for(Book book: addedBooks){
            if(removeBook.equals(book.getBookTitle())){
                addedBooks.remove(book);
                System.out.println("Successfully removed the book " + book.getBookTitle() + " from the system. ");
            }
            else{
                System.out.println("Could not remove the book from the system. ");
            }
        }

        for(Book aBook: availableBooks){
            if(removeBook.equals(aBook.getBookTitle())){
                availableBooks.remove(aBook);
            }

        }


    }

    public void allLibraryBooks(){
        for(Book book: addedBooks){
            System.out.println(book.toString());
        }
    }

    public void allBorrowedBooks(){
        for(Book book: borrowedBooks){
            System.out.println(book.toString());
        }
    }

    public void myBorrowedBooks(String userName){
        for(Book book: borrowedBooks){
            if(userName.equals(book.getLibraryUser())){
                if(!book.isAvailable()){
                    System.out.println(book.toString());
                }
            }
            else{
                System.out.println("You have no borrowed books. ");
            }
        }
    }

    public void allAvailableBooksToRent(){
        for(Book book: availableBooks){
            System.out.println(book.toString());
        }
    }

    public boolean findTitle(String title, String userName){
        for(Book book: addedBooks){
            if(book.getBookTitle().equals(title)){
                if(book.isAvailable()){
                    borrowBook(book, userName);
                }
                else{
                    System.out.println("Sorry this book is not available. ");
                    break;
                }
            }
        }
        return false;
    }

    private void borrowBook(Book book, String userName){
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to borrow the book " + book.getBookTitle() +"?" + "\n"
        + "[Y]/[N]");
        String userInput = in.nextLine();

        switch (userInput){
            case "Y":
                book.setAvailable(false);
                book.setLibraryUser(userName);
                borrowedBooks.add(book);

                for(Book aBook: availableBooks){
                    if(book.equals(aBook)){
                        availableBooks.remove(book);
                    }
                }

                System.out.println("You will borrow the book: " + book.getBookTitle() + "\n");
                break;

            case "N":
                break;

            default:
                System.out.println("Default. ");
                break;
        }
    }




    /*public void searchAuthor(){
        System.out.println("Search author: ");
        String searchAuthor = input.nextLine();

        List<Book> searchBooks = (List<Book>)FileUtility.loadObject(outputFile);
        for(Book book: searchBooks){
            if(searchAuthor.equals(book.getAuthor())){
                System.out.print("You searched for this author and we found it! " + "\n" + book.toString() + "\n");
                System.out.println("Would you like to rent this book? [Y]/[N]");
                String choice = input.nextLine();

                switch (choice){
                    case "Y":
                        System.out.println("You will now borrow the book: " + book.getBookTitle() + "\n");
                        borrowBook(book);
                        System.out.println("ENDING");
                        break;

                    case "N":
                        System.out.println("NOT RENTING");
                        break;

                    default:
                        break;
                }

                break;
            }
            else if(!searchAuthor.equals(book.getAuthor())){
                System.out.println("Sorry we could not find the book you were looking for. ");
                break;
            }
        }

    }

    public void borrowBook(Book b){
        System.out.println("Inside borrow book! ");

        addedBooks.remove(b);
        borrowedBooks.add(b);

        saveFile(borrowedBooksFile, addedBooks, StandardOpenOption.CREATE, StandardOpenOption.CREATE_NEW, StandardOpenOption.APPEND);

        List<Book> borrowed = (List<Book>)FileUtility.loadObject(borrowedBooksFile);
        for(Book bk: borrowed){
            System.out.println("Borrowed books: " + bk);
        }

        saveFile(outputFile, addedBooks, StandardOpenOption.CREATE, StandardOpenOption.CREATE_NEW, StandardOpenOption.APPEND);

        List<Book> newAddedBooks = (List<Book>)FileUtility.loadObject(outputFile);
        for(Book bkk: newAddedBooks){
            System.out.println("Available books: " + bkk);
        }
        }

        public void myBorrowedBooks(){
        List<Book> userBooks = (List<Book>)FileUtility.loadObject(borrowedBooksFile);
        for(Book b: userBooks){
            System.out.println("The borrowed books: " + b.toString());
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



    public Object loadFile(String filename, ArrayList<Book> bookFiles){
        Path path = Paths.get(filename);
        try {
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
            try {
                bookFiles = (ArrayList<Book>) ois.readObject();
                for(Book book: bookFiles){
                    System.out.println("Object: " + "\n" + book);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookFiles;
    }

    public void saveAndExitBooks(){
        saveFile(outputFile, addedBooks, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        saveFile(borrowedBooksFile, borrowedBooks, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    }

    public void loginAndStartBooks(){
        //loadFile(outputFile, addedBooks);
        //loadFile(borrowedBooksFile, borrowedBooks);
        FileUtility.loadObject(outputFile);
        FileUtility.loadObject(borrowedBooksFile);
    }*/
}


