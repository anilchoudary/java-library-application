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

    private transient Scanner input = new Scanner(System.in);
    //private String memberList = "member.ser";
    //private ArrayList<User> users = new ArrayList<>();
    private List<User> users;
    private static final long serialVersionUID = -4263653752514191475L;



    public ManageUserInformation() {
        users = new ArrayList<>();
    }

    public void addUserToArray(User user){
        users.add(user);
        System.out.println("New user successfully added! Welcome " + user.getUserName());
    }

    public void seeAllUsers(){
        for(User user: users){
            System.out.println(user.toString());
        }
    }

    public boolean userLogin(String userName, String password) {
        for (User user : users) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                System.out.println("Welcome! " + userName);
                return true;
            } else {
                System.out.println("Could not find the user. ");
                return false;
            }
        }
        return false;
    }
}

