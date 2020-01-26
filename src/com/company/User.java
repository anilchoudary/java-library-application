package com.company;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;

    // // Added to resolve invalid class exception
    private static final long serialVersionUID = 5841402549658530467L;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
