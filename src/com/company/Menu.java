package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;

public class Menu implements Serializable {

    private Scanner input = new Scanner(System.in);
    private String[] choices = {"0", "1", "2", "3", "4"};
    private boolean running = true;
    static LibrarySystem newLibrarySystem = new LibrarySystem();
    static ManageUserInformation userInformation = new ManageUserInformation();
    static String memberList = "member.ser";


    public Menu() {
        //loadAndStartProgram(memberList, userInformation);
        mainMenu();
    }


    private void mainMenu(){

        //FileUtility.loadObject(memberList);

        String choice = "";

        while (running) {
            System.out.println(
                    "MAIN MENU" + "\n" +
                            "[0] Load and start program" + "\n" +
                            "[1] Log in as admin" + "\n" +
                            "[2] Log in as library member" + "\n" +
                            "[3] Create a library member account" + "\n" +
                            "[4] Exit program");

            try {

                // User choice
                choice = input.nextLine();

                // Check if array above contains the choice made by user
                if (Arrays.asList(choices).contains(choice)) {
                    switch (choice) {

                        case "0":
                            loadAndStartProgram();
                            break;

                        case "1":
                            System.out.println("Admin");
                            adminMenu();
                            break;

                        case "2":
                            System.out.println("Library member");
                            if(userInformation.userLogin()){
                                libraryMemberMenu();
                            }
                            break;

                        case "3":
                            System.out.println("Create a new user");
                            createNewLibraryMember();
                            break;

                        case "4":
                            System.out.println("Have a nice day! ");
                            saveAndExit(memberList, userInformation);
                            //FileUtility.saveObject(memberList, userInformation, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
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

    private void adminMenu(){
        String choice = "";
        System.out.println("Make one of the following choices: " + "\n" +
                "1. Add new book" + "\n" +
                "2. List of all users" + "\n");

        try {
            // User choice
            choice = input.nextLine();

            // Check if array above contains the choice made by user
            if (Arrays.asList(choices).contains(choice)) {
                switch (choice) {
                    case "1":
                        System.out.println("Adding new book");
                        newLibrarySystem.addNewBook();
                        break;

                    case "2":
                        System.out.println("See list of all users");
                        userInformation.seeAllUsers();
                        break;

                    default:
                        System.out.println("Default! ");
                        break;
                }

            } else {
                System.out.println("You can only make a choice between 1 and 2. Please try again. " + "\n");
            }


        } catch (Exception e) {
            System.out.println("Oops! Something went wrong. ");
            System.out.println(e.getMessage());
        }

    }

    private void libraryMemberMenu(){
        String choice = "";

        while(running) {
            System.out.println(
                    "Make one of the following choices: " + "\n" +
                    "1. See all books in the library " + "\n" +
                    "2. Search a book by title " + "\n" +
                    "3. Search book by author " + "\n" +
                    "4. See available books to rent ");

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
                            newLibrarySystem.searchBookTitle();
                            break;

                        case "3":
                            System.out.println("Search author");
                            newLibrarySystem.searchAuthor();
                            break;

                        case "4":
                            System.out.println("My borrowed books: ");
                            newLibrarySystem.myBorrowedBooks();
                            break;

                        default:
                            System.out.println("Default! ");
                            break;
                    }

                } else {
                    System.out.println("You can only make a choice between 1 and 2. Please try again. " + "\n");
                }


            } catch (Exception e) {
                System.out.println("Oops! Something went wrong. ");
            }
        }

    }

    public void createNewLibraryMember() {
        System.out.println("Please enter a user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter a password: ");
        String password = input.nextLine();
        System.out.println("username and password added! ");
        User newMember = new User(userName, password);
        userInformation.addUserToArray(newMember);
    }

    private void saveAndExit(String fileName, ManageUserInformation user){

        FileOutputStream fos = null;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
            oos.writeObject(user);
            fos.close();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAndStartProgram()  {
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



}
