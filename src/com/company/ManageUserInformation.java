package com.company;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageUserInformation implements Serializable {

    private Scanner input = new Scanner(System.in);
    private String memberList = "member.ser";
    private ArrayList<User> users = new ArrayList<>();


    public ManageUserInformation() {
    }


    public void createNewLibraryMember() {
        System.out.println("Please enter a user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter a password: ");
        String password = input.nextLine();
        User newMember = new User(userName, password);
        users.add(newMember);
        saveFile(memberList, users, StandardOpenOption.CREATE);
        System.out.printf("New user s% successfully saved!  " + userName + "\n");
    }

    public boolean userLogin(){
        System.out.println("Please enter you user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter your password: ");
        String password = input.nextLine();

        List<User> members = (ArrayList<User>)FileUtility.loadObject(memberList);
        for(User user: members){
            if(userName.equals(user.getUserName()) && password.equals(user.getPassword())){
                System.out.println("Welcome " + user.getUserName() + "\n");
                return true;
            }
            else{
                System.out.println("Error. You have not entered the correct login information. " + "\n" +
                        "Please try again or create a new library member account. " + "\n"
                );
                return false;
            }
        }

        return false;
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







}

