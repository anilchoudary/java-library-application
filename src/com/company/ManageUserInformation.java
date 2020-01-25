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


    public void createNewLibraryMember() {
        System.out.println("Please enter a user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter a password: ");
        String password = input.nextLine();
        System.out.println("username and password added! ");
        User newMember = new User(userName, password);
        //FileUtility.saveObject(memberList, newMember, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        addUserToArray(newMember);
    }


    public void addUserToArray(User user){
        users.add(user);
        System.out.println("New user successfully added! Welcome " + user.getUserName());
        //FileUtility.saveObject(memberList, user, StandardOpenOption.APPEND);
    }

    public void seeAllUsers(){
        for(User user: users){
            System.out.println(user.toString());
        }
    }

    public boolean userLogin(){
        System.out.println("Please enter you user name: ");
        String userName = input.nextLine();
        System.out.println("Please enter your password: ");
        String password = input.nextLine();

        for(User user: users){
            if(userName.equals(user.getUserName()) && password.equals(user.getPassword())){
                System.out.println("Welcome! " + userName);
                return true;
            }

            else{
                System.out.println("Could not find the user. ");
                return false;
            }

        }

        //users  = (ArrayList<User>)FileUtility.loadObject(memberList);
        /*List<User> members = (ArrayList<User>)FileUtility.loadObject(memberList);
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
        }*/
        return false;
    }



    public void saveFile(String filename, Object o, StandardOpenOption... option) {
        Path path = Paths.get(filename);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path, option));
            oos.writeObject(o);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved filed successfully! " + o.toString());
    }










}

