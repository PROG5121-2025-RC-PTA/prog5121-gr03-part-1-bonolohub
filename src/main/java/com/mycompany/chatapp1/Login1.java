/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp1;
import java.util.Scanner;
import java.util.regex.*;
/**
 *
 * @author RC_Student_lab
 */
public class Login1 {
    
    
    public String userName;
    public String passWord;
    public String cellPhone;
    public String firstName;
    public String lastName;

    //Username check
    public boolean checkUsername(String userName) {
        System.out.println("Checking username: " + userName);
        return userName.contains("_") && userName.length() <= 5;
    }

    //Password complexity check
    public boolean checkPasswordComplexity(String passWord) {
        System.out.println("Checking password complexity: " + passWord);
        String pattern = "(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return passWord.matches(pattern);
    }

    //Cell phone number check
    public boolean checkCellPhoneNumber(String cellPhone) {
        System.out.println("Checking cell phone: " + cellPhone);
        String pattern = "\\+\\d{1,3}\\d{10}$";
        return cellPhone.matches(pattern);
    }

    //Register user
    public String registerUser(String userName, String passWord, String cellPhone, String firstName, String lastName) {
        if (!checkUsername(userName)) {
            System.out.println("Registering user");
            return "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!checkPasswordComplexity(passWord)) {
            return "Password is not correctly formatted. Please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        this.userName = userName;
        this.passWord = passWord;
        this.cellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;

        return "User successfully registered.";
    }

    //Login user
    public boolean loginUser(String inputUserName, String inputPassWord) {
        System.out.println("Logging in.");
        return inputUserName.equals(this.userName) && inputPassWord.equals(this.passWord);
    }

    //Return login status
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + this.firstName + " " + this.lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect. Please try again.";
        }
    }
    
}
//Code referred from chatGpt.