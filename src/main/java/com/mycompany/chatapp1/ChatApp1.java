/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatapp1;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class ChatApp1 {
    
    static int totalMessages = 0;

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
        
        
        // QuickChat<<part2
        
        if (loginSuccess) {
            System.out.println("\nWelcome to Quick Chat!");

            System.out.print("How many messages would you like to send today?");
            int numMessages = myScan.nextInt();
            myScan.nextLine(); 

            int messagesSent = 0;

            while (true) {
                System.out.println("\nPlease choose an option:");
                System.out.println("1. Send Message");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");
                System.out.println("4. Run unit tests");
                

                int option = myScan.nextInt();
                myScan.nextLine();

                if (option == 1 && messagesSent < numMessages) {
                    createAndSendMessage(myScan);
                    messagesSent++;
                    totalMessages++;
                } else if (option == 2) {
                    JOptionPane.showMessageDialog(null, "Coming soon...");
                } else if (option == 3) {
                    System.out.println("You have sent a total of: " + totalMessages + " messages.");
                    break;
                } else {
                    System.out.println("Invalid option or message limit reached.");
                }
            }
        }

        myScan.close();
    }

    // Message ID Validation
    public static boolean checkMessageID(String id) {
        return id.matches("\\d{10}");
    }

    // Recipient Cell Number Validation
    public static boolean checkRecipientCell(String number) {
        return number.startsWith("+") && number.length() <= 10;
    }

    // Generate Message Hash 
    public static String generateHash(String message) {
        return String.valueOf(message.hashCode());
    }
    
    // Send Message
    public static void createAndSendMessage(Scanner myScan) {
        String messageID;
        do {
            System.out.print("Enter a 10-digit message ID: ");
            messageID = myScan.nextLine();
            if (!checkMessageID(messageID)) {
                System.out.println("Invalid ID. Must be exactly 10 digits.");
            }
        } while (!checkMessageID(messageID));

        String recipient;
        do {
            System.out.print("Enter recipient cell number (start with +, max 10 characters): ");
            recipient = myScan.nextLine();
            if (!checkRecipientCell(recipient)) {
                System.out.println("Invalid number. Must start with + and be 10 characters or fewer.");
            }
        } while (!checkRecipientCell(recipient));

        String messageText;
        do {
            System.out.print("Please enter a messagenof less than 250 characters (max 250 characters): ");
            messageText = myScan.nextLine();
            if (messageText.length() > 250) {
                System.out.println("Please enter a message shorter than 8 characters.");
            }
        } while (messageText.length() > 250);

        String hash = generateHash(messageText);

        // Ask user whether to send
        System.out.print("Send message? (yes/no): ");
        String choice = myScan.nextLine().toLowerCase();

        if (choice.equals("yes")) {
            System.out.println("Message sent.");
            storeInJson(messageID, hash, recipient, messageText);
        } else {
            System.out.println("Message not sent.");
        }
        
    }

    //Store message in JSON file
    public static void storeInJson(String id, String hash, String recipient, String text) {
        String json = "{\n"
                    + "  \"MessageID\": \"" + id + "\",\n"
                    + "  \"MessageHash\": \"" + hash + "\",\n"
                    + "  \"Recipient\": \"" + recipient + "\",\n"
                    + "  \"Message\": \"" + text + "\"\n"
                    + "}\n";

        try (FileWriter writer = new FileWriter("messages.json", true)) {
            writer.write(json);
            writer.write(",\n");
            System.out.println("Message saved to JSON file.");
        } catch (IOException e) {
            System.out.println("Error saving message: " + e.getMessage());
        }
        
        
        }
    //Unit Testing
    public static void runUnitTests(){
        System.out.println("Running Unit Tests...");
        
        String validMessage ="Hi Mike, can you please join us for dinner tonight?";
        if (validMessage.length() <= 250) {
            System.out.println("Message length test passed: Ready to send");
        } else {
            System.out.println("Message too long: Exceeds 250 characters");
        }

        // Test 2: Message length (fail)
        String longMessage = "a".repeat(251);
        if (longMessage.length() <= 250) {
            System.out.println("Message too long test failed: Should not allow");
        } else {
            System.out.println("Message too long test passed: Exceeds 250 characters");
        }

        // Test 3: Phone number formatting
        String phone = "+27718693002";
        if (phone.matches("\\+27\\d{9}")) {
            System.out.println("Phone number successfully captured");
        } else {
            System.out.println("Phone number format incorrect");
        }

        
    }
}
                
        



        
    
    

//Code referred from ChatGpt.Bonolo
