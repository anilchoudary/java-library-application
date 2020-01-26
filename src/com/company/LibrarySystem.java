package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem implements Serializable {

    private List<Book> addedBooks; // All books in the library
    private List<Book> borrowedBooks; // All borrowed books
    private List<Book> availableBooks; // All available books to rent

    // Added to resolve invalid class exception
    private static final long serialVersionUID = 4220048072494770351L;

    public LibrarySystem() {
        addedBooks = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
        availableBooks = new ArrayList<>();
    }

    // Adding both to all books (addedBooks) and availaleBooks list
    public void addBookToArray(Book book){
        addedBooks.add(book);
        availableBooks.add(book);
        System.out.println("Successfully added " + book.getBookTitle() + " to the system. ");
    }

    // Removing books both from all books in the library as well as the available books list
    public void removeBookFromArray(String removeBook){
        for(Book book: addedBooks){
            if(removeBook.equals(book.getBookTitle())){
                addedBooks.remove(book);
                System.out.println("Successfully removed the book " + book.getBookTitle() + " from the system. ");
            }
            for(Book aBook: availableBooks){
                if(removeBook.equals(aBook.getBookTitle())){
                    availableBooks.remove(aBook);
                    System.out.println("Removed available! ");
                }
            }
        }
        System.out.println("Could not remove the book from the system. ");
    }


    // All library books in the system (both available and non available)
    public void allLibraryBooks(String user){
        for(Book book: addedBooks){
            if(user.equals("admin")){
                System.out.println(book.toString());
            }
            else{
                System.out.println(book.toStringMembers());
            }
        }
    }

    // All borrowed books, admin view only
    public void allBorrowedBooks(){
        for(Book book: borrowedBooks){
            System.out.println(book.toString());
        }
    }

    // Users borrowed books
    public void myBorrowedBooks(String userName){
        for(Book book: borrowedBooks){
            if(userName.equals(book.getLibraryUser())){
                if(!book.isAvailable()){
                    System.out.println(book.toString());
                }
            }
            else{
                System.out.println("You have no borrowed books. " + "\n");
            }
        }
    }

    // See all available books to rent
    public void allAvailableBooksToRent(String user){
        for(Book book: availableBooks){
            if(user.equals("admin")){
                System.out.println(book.toString());
            }
            else{
                System.out.println(book.toStringMembers());
            }
        }
    }

    // Search book by title
    public boolean findTitle(String title, String userName){
        for(Book book: addedBooks){
            if(book.getBookTitle().equals(title)){
                System.out.println(book.toStringMembers());
                if(book.isAvailable()){
                    borrowBook(book, userName);
                    return true;
                }
                else if(!book.isAvailable()){
                    System.out.println("Sorry this book is not available. ");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findAuthor(String author, String userName){
        for(Book book: addedBooks){
            if(book.getAuthor().equals(author)){
                System.out.println(book.toStringMembers());
                if(book.isAvailable()){
                    borrowBook(book, userName);
                    return true;
                }
                else if(!book.isAvailable()){
                    System.out.println("Sorry this book is not available. ");
                    return true;
                }
            }
        }
        return false;
    }

    // For library member: borrow book method
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

    // Return book and add and remove from the different lists
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
}


