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
            for(Book aBook: availableBooks){
                if(removeBook.equals(aBook.getBookTitle())){
                    availableBooks.remove(aBook);
                    System.out.println("Removed available! ");
                }
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

    public boolean returnBook(String returnTitle){
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to return the book " + returnTitle +"?" + "\n"
                + "[Y]/[N]");

        String userInput = in.nextLine();

        if(userInput.equals("Y")){
            for(Book book: borrowedBooks){
                if(book.getBookTitle().equals(returnTitle)){
                    if(!book.isAvailable()){
                        book.setAvailable(true);
                        book.setLibraryUser("None");
                        borrowedBooks.remove(book);
                        availableBooks.add(book);
                    }

                }
            }
        }
        return false;
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

    }*/


}


