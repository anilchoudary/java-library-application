package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManageUserInformation implements Serializable {

    private transient Scanner input = new Scanner(System.in);
    private List<User> users;
    private static final long serialVersionUID = -4263653752514191475L;


    public ManageUserInformation() {
        users = new ArrayList<>();
    }

    public void addUserToArray(User user) {
        users.add(user);
        System.out.println("New user successfully added! Welcome " + user.getUserName());
    }

    public void seeAllUsers() {
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public void removeUserFromArray(String removeUser){
        for(User user: users){
            if(removeUser.equals(user.getUserName())){
                users.remove(user);
                System.out.println("Successfully removed the user " + user.getUserName() + " from the system. ");
            }
            else{
                System.out.println("Could not remove the user from the system. ");
            }
        }
    }

    public boolean loginAsLibraryMember(String userName, String password){
        /*Scanner in = new Scanner(System.in);
        System.out.println("Please enter you user name: ");
        String userName = in.nextLine();
        System.out.println("Please enter your password: ");
        String password = in.nextLine();*/

        for (User user : users) {
            System.out.println(user.toString());

                if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                    System.out.println("Found the user! ");
                    return true;
                }
        }
        return false;
    }




    public boolean userLogin(String userName, String password) {

        for (User user : users) {
            //String getUserName = user.getUserName();
            //String getUserPassword = user.getPassword();
            System.out.println(user.toString());
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            } else {
                System.out.println("Sorry could not find this user. ");
                return false;
            }
        }
        return false;
    }
}

