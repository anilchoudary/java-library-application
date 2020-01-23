package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    private Scanner input = new Scanner(System.in);
    private String[] choices = {"1", "2", "3", "4"};
    private boolean running = true;
    
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
                    "[3] Create a new user" + "\n" +
                    "[4] Exit program");

            try {

                // User choice
                choice = input.nextLine();

                // Check if array above contains the choice made by user
                if (Arrays.asList(choices).contains(choice)) {
                    switch (choice) {
                        case "1":
                            System.out.println("Admin");
                            break;

                        case "2":
                            System.out.println("Library member");
                            break;

                        case "3":
                            System.out.println("Create a new user");
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

    }

    private void libraryMemberMenu(){

    }
}
