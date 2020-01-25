package com.company;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Menu implements Serializable {

    private transient Scanner input = new Scanner(System.in);
    private String[] choices = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    private boolean running = true;

    static LibrarySystem newLibrarySystem = new LibrarySystem();
    static ManageUserInformation userInformation = new ManageUserInformation();

    static String memberList = "member.ser"; // All members in the system
    static String outputFile = "output.ser"; // All books in the system
    static String borrowedBooksFile = "borrow.ser"; // Borrowed books


    public Menu() {
        mainMenu();
    }


    private void mainMenu(){

        String choice = "";

        while (running) {
            System.out.println(
                    "MAIN MENU" + "\n" +
                            "[0] Load and start program" + "\n" +
                            "[1] Log in as admin" + "\n" +
                            "[2] Log in as library member" + "\n" +
                            "[3] Create a new library member account" + "\n" +
                            "[4] Exit and save program");

            try {

                // User choice
                choice = input.nextLine();

                // Check if array above contains the choice made by user
                if (Arrays.asList(choices).contains(choice)) {
                    switch (choice) {

                        case "0":
                            loadBookInformationAndStartProgram(outputFile);
                            loadBookInformationAndStartProgram(borrowedBooksFile);
                            loadUserInformationAndStartProgram();
                            break;

                        case "1":
                            System.out.println("Admin login");
                            adminLogin();
                            break;

                        case "2":
                            System.out.println("Library members login");

                            /*if(userInformation.loginAsLibraryMember()){
                                System.out.println("Please confirm your username: ");
                                libraryMemberMenu("Welcome! ");
                            }
                            else{
                                System.out.println("Could not find the user. ");
                            }*/

                            //userInformation.seeAllUsers();
                            String login[] = userLogin();
                            if(userInformation.userLogin(login[0], login[1])){
                                libraryMemberMenu(login[0]);
                            }
                            else{
                                System.out.println("Could not find the user. ");
                            }
                            break;

                        case "3":
                            System.out.println("Create a new user");
                            createNewLibraryMember();
                            break;

                        case "4":
                            System.out.println("Have a nice day! ");
                            saveBookInformationAndExit(outputFile, newLibrarySystem);
                            saveBookInformationAndExit(borrowedBooksFile, newLibrarySystem);
                            saveUserInformationAndExit();
                            System.exit(0);
                            running = false;
                            break;

                        default:
                            System.out.println("Default! ");
                            break;
                    }

                } else {
                    System.out.println("You can only make a choice between 1 and 4. Please try again. " + "\n");
                }


            } catch (Exception e) {
                System.out.println("Oops! Something went wrong. ");
            }
        }
    }

    private void adminLogin(){
        String admin = "admin";
        System.out.println("Please enter your user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter your password: ");
        String password = input.nextLine();

        if(userName.equals(admin) && password.equals(admin)){
            adminMenu();
        }
        else{
            System.out.println("Wrong user information. Please try again. ");
            mainMenu();
        }

    }

    private void adminMenu(){
        String choice = "";
        boolean run = true;

        while(run) {
            System.out.println("Make one of the following choices: " + "\n" +
                    "1. Add new book" + "\n" +
                    "2. Remove book " + "\n" +
                    "3. Borrowed books " + "\n" +
                    "4. See all books in the library " + "\n" +
                    "5. See all available books" + "\n" +
                    "6. List of all users" + "\n" +
                    "7. Remove user" + "\n" +
                    "8. Back to main menu" + "\n");

            try {
                // User choice
                choice = input.nextLine();

                // Check if array above contains the choice made by user
                if (Arrays.asList(choices).contains(choice)) {
                    switch (choice) {
                        case "1":
                            System.out.println("Adding new book");
                            addNewBook();
                            break;

                        case "2":
                            System.out.println("Remove book");
                            newLibrarySystem.removeBookFromArray(searchBook());
                            break;

                        case "3":
                            System.out.println("Borrowed books");
                            newLibrarySystem.allBorrowedBooks();
                            break;

                        case "4":
                            System.out.println("All books in the library. ");
                            newLibrarySystem.allLibraryBooks();
                            break;

                        case "5":
                            System.out.println("Available books: ");
                            newLibrarySystem.allAvailableBooksToRent();
                            break;

                        case "6":
                            System.out.println("See list of all users");
                            userInformation.seeAllUsers();
                            break;

                        case "7":
                            System.out.println("Remove user ");
                            userInformation.removeUserFromArray(searchUser());
                            break;

                        case "8":
                            System.out.println("Go back to main menu. ");
                            run = false;
                            break;

                        default:
                            System.out.println("Default! ");
                            break;
                    }

                } else {
                    System.out.println("You can only make a choice between 1 and 8. Please try again. " + "\n");
                }


            } catch (Exception e) {
                System.out.println("Oops! Something went wrong. ");
            }
        }
    }

    private void libraryMemberMenu(String userName){
        System.out.println("Welcome " + userName);
        String choice = "";
        boolean run = true;

        while(run) {
            System.out.println(
                    "Make one of the following choices: " + "\n" +
                    "1. See all books in the library " + "\n" +
                    "2. Search a book by title " + "\n" +
                    "3. Search book by author " + "\n" +
                    "4. My borrowed books " + "\n" +
                    "5. Exit to main menu");

            try {
                // User choice
                choice = input.nextLine();

                // Check if array above contains the choice made by user
                if (Arrays.asList(choices).contains(choice)) {
                    switch (choice) {
                        case "1":
                            System.out.println("All books in the library: ");
                            newLibrarySystem.allLibraryBooks();
                            break;

                        case "2":
                            System.out.println("Search book title");
                            newLibrarySystem.searchTitle(searchBook());
                            break;

                        case "3":
                            System.out.println("Search author");
                            break;

                        case "4":
                            System.out.println("My borrowed books: ");
                            newLibrarySystem.myBorrowedBooks(userName);
                            break;

                        case "5":
                            System.out.println("Back to main menu. ");
                            run = false;
                            break;

                        default:
                            System.out.println("Default! ");
                            break;
                    }

                } else {
                    System.out.println("You can only make a choice between 1 and 5. Please try again. " + "\n");
                }


            } catch (Exception e) {
                System.out.println("Oops! Something went wrong. ");
            }
        }

    }

    private String searchBook(){
        System.out.println("Search title: ");
        return input.nextLine();
    }

    private String searchUser(){
        System.out.println("Search user: ");
        return input.nextLine();
    }

    private void createNewLibraryMember() {
        System.out.println("Please enter a user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter a password: ");
        String password = input.nextLine();
        System.out.println("Username and password added! ");
        User newMember = new User(userName, password);
        userInformation.addUserToArray(newMember);
    }

    public void addNewBook(){
        System.out.println("Please add the book title: ");
        String bookTitle = input.nextLine();
        System.out.println("Please add the author: ");
        String author = input.nextLine();
        System.out.println("Please add information about the book: ");
        String aboutThisBook = input.nextLine();
        boolean available = true;
        String user = "None";
        Book newBook = new Book(bookTitle, author, aboutThisBook, true, user);
        newLibrarySystem.addBookToArray(newBook);
    }

    private String[] userLogin() {
        System.out.println("Please enter you user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter your password: ");
        String password = input.nextLine();

        return new String[] {userName, password};
    }

    private void saveUserInformationAndExit(){

        FileOutputStream fos = null;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(memberList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(userInformation);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserInformationAndStartProgram()  {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        File file = new File(memberList);

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            userInformation = (ManageUserInformation) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void saveBookInformationAndExit(String fileName, LibrarySystem library){

        FileOutputStream fos = null;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(library);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBookInformationAndStartProgram(String filename)  {
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        File file = new File(filename);

        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            newLibrarySystem = (LibrarySystem) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
