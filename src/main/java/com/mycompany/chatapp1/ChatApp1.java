/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp1;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class ChatApp1 {

    public static void main(String[] args) {
        
        
        Scanner myScan = new Scanner(System.in);
        Login1 login = new Login1();

        System.out.println("User Registration");
        
        System.out.print("Enter your first name: ");
        String firstName = myScan.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = myScan.nextLine();

        System.out.print("Enter a username (must contain '_' and maximum 5 characters): ");
        String userName = myScan.nextLine();

        System.out.print("Enter a password (8+ characters, capital letter, number, special character): ");
        String passWord = myScan.nextLine();

        System.out.print("Enter your cell phone number (e.g. +27821234567): ");
        String cellPhone = myScan.nextLine();

        String registrationMessage = login.registerUser(userName, passWord, cellPhone, firstName, lastName);
        System.out.println(registrationMessage);

        if (!registrationMessage.equals("User successfully registered.")) {
            System.out.println("Registration failed.");
            return;
        }

        System.out.println("\n Login");
        System.out.print("Enter your username: ");
        String inputUsername = myScan.nextLine();

        System.out.print("Enter your password: ");
        String inputPassword = myScan.nextLine();

        boolean loginSuccess = login.loginUser(inputUsername, inputPassword);
        System.out.println(login.returnLoginStatus(loginSuccess));

        
    }
}
//Code referred from ChatGpt.Bonolo
