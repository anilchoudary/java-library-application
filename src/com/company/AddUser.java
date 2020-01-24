package com.company;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddUser {

    private Scanner input = new Scanner(System.in);
    private String fileName = "member.ser";
    //private String allMembersFile = "all-members.ser";
    private List<User> users;


    public AddUser() {
        createNewLibraryMember();
        List<User> something = (List<User>)readObject("test.ser");


    }


    private void createNewLibraryMember() {
        System.out.println("Please enter a user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter a password: ");
        String password = input.nextLine();
        User newMember = new User(userName, password);
        users = new ArrayList<>();
        users.add(newMember);
        writeObject(users);
        System.out.println("SAVED USER! ");
    }

    public static void writeObject(Object object){

        ObjectOutputStream objectOutputStream = null;

        FileOutputStream fileOutputStream = null;

        try{

            fileOutputStream = new FileOutputStream("test.ser", false);

            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(object);

            objectOutputStream.close();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }


    public static Object readObject(String fileName){

        ObjectInputStream objectinputstream = null;

        Object object = null;

        try {

            FileInputStream streamIn = new FileInputStream("test.ser");

            objectinputstream = new ObjectInputStream(streamIn);

            object = objectinputstream.readObject();

            objectinputstream .close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return object;

    }


}

