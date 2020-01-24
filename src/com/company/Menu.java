package com.company;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class Menu implements Serializable {

    private Scanner input = new Scanner(System.in);
    private String[] choices = {"1", "2", "3", "4"};
    private boolean running = true;
    private LibrarySystem newLibrarySystem = new LibrarySystem();
    ManageUserInformation userInformation = new ManageUserInformation();


    public Menu() {
        mainMenu();
    }

    private void mainMenu(){

        String choice = "";

        while(running) {
            System.out.println(
                    "MAIN MENU" + "\n" +
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
                        case "1":
                            System.out.println("Admin");
                            adminMenu();
                            break;

                        case "2":
                            System.out.println("Library member");
                            //userInformation.userLogin();
                            if(userInformation.userLogin()){
                                libraryMemberMenu();
                            }
                            break;

                        case "3":
                            System.out.println("Create a new user");
                            userInformation.createNewLibraryMember();
                            break;

                        case "4":
                            System.out.println("Have a nice day! ");
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
                "2. Exit program" + "\n");

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
                        System.out.println("Library member");
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

    private void libraryMemberMenu(){
        String choice = "";
        System.out.println("Make one of the following choices: " + "\n" +
                "1. See all books in the library" + "\n" +
                "2. Available books to rent" + "\n");

        try {
            // User choice
            choice = input.nextLine();

            // Check if array above contains the choice made by user
            if (Arrays.asList(choices).contains(choice)) {
                switch (choice) {
                    case "1":
                        System.out.println("See all books in the library");
                        newLibrarySystem.allLibraryBooks();
                        break;

                    case "2":
                        System.out.println("Available books");
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
