package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManageUserInformation implements Serializable {

    private List<User> users; // All library members
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

        }
        System.out.println("Could not remove the user from the system. ");
    }

    public boolean loginAsLibraryMember(String userName, String password){
        for (User user : users) {
                if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                    return true;
                }
        }
        return false;
    }

}

