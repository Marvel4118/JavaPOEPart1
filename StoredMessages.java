/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javapoe.part1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Module: PROG5121 (Java Programming 1A) - POE Part 3
 * Class: StoredMessages
 * Purpose: Manages message arrays (sent, disregarded, stored, message hashes,
 *          and message IDs), reads/stores JSON data, searches records, deletes
 *          by message hash, and generates message reports.
 *
 * Attribution: JSON reading/storage logic and structure referenced from
 *              standard Java/JSON documentation and AI-assisted guidance.
 */
public class StoredMessages {

    // Arrays used to store message information
    String[] messageID = new String[100];
    String[] messageHash = new String[100];
    String[] messageText = new String[100];
    String[] messageRecipient = new String[100];
    String[] messageSender = new String[100];

    // Keeps track of how many messages are stored
     int messageCount = 0;

    // Constructor
    public StoredMessages() {
        messageID = new String[100];
        messageHash = new String[100];
        messageText = new String[100];
        messageSender = new String[100];
        messageRecipient = new String[100];
        
        // Sample data for your video demo
        messageID[0] = "1";
        messageHash[0] = "1:1:Hello";
        messageText[0] = "Hello world";
        messageSender[0] = "SenderA";
        messageRecipient[0] = "RecipientB";
        messageCount = 1;
        // Messages can be added using addStoredMessage()
    }

    // Add a message to the stored-message arrays
    public void addStoredMessage(
            String id,
            String hash,
            String text,
            String sender,
            String recipient) {

        if (messageCount < messageID.length) {
            messageID[messageCount] = id;
            messageHash[messageCount] = hash;
            messageText[messageCount] = text;
            messageSender[messageCount] = sender;
            messageRecipient[messageCount] = recipient;

            messageCount++;

            System.out.println("Message stored successfully.");
        } else {
            System.out.println("Storage is full. Cannot add more messages.");
        }
    }

   // Display the seven-option menu
    public void showStoredMessagesMenu(Scanner input) {

        int option = 0;

        while (option != 7) {

            System.out.println("\n===== STORED MESSAGES MENU =====");
            System.out.println("1. Display all stored messages");
            System.out.println("2. Display the longest stored message");
            System.out.println("3. Search for a message by ID");
            System.out.println("4. Search for messages by recipient");
            System.out.println("5. Delete a message using its hash");
            System.out.println("6. Display the stored messages report");
            System.out.println("7. Return to the main menu");
            System.out.print("Choose an option (1-7): ");

            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine();

                validateMenuOption(option, input);

            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
            }
        }
    }

    // Validate and handle the selected menu option
    private void validateMenuOption(int option, Scanner input) {

        switch (option) {

            case 1:
                displayStoredSendersAndRecipients();
                break;

            case 2:
                getLongestStoredMessage();
                break;

            case 3:
                searchByMessageID(input);
                break;

            case 4:
                searchByRecipient(input);
                break;

            case 5:
                deleteByMessageHash(input);
                break;

            case 6:
                generateReport();
                break;

            case 7:
                System.out.println("Returning to the main menu...");
                break;

            default:
                System.out.println(
                    "Invalid option. Please choose a number from 1 to 7."
                );
        }
    }

    // OPTION 1: Display stored messages
    private void displayStoredSendersAndRecipients() {

        for (int i = 0; i < messageCount; i++) {
            System.out.println("\nMessage ID: " + messageID[i]);
            System.out.println("Sender: " + messageSender[i]);
            System.out.println("Recipient: " + messageRecipient[i]);
            System.out.println("Message: " + messageText[i]);
            System.out.println("Hash: " + messageHash[i]);
        }
    }

    // OPTION 2: Display the longest stored message
    private void getLongestStoredMessage() {

        if (messageCount == 0) {
            System.out.println("There are no stored messages.");
            return;
        }

        int longestIndex = 0;

        for (int i = 1; i < messageCount; i++) {
            if (messageText[i].length()
                    > messageText[longestIndex].length()) {
                longestIndex = i;
            }
        }

        System.out.println("\nLongest stored message:");
        System.out.println("Message ID: " + messageID[longestIndex]);
        System.out.println("Message: " + messageText[longestIndex]);
        System.out.println(
            "Length: " + messageText[longestIndex].length() + " characters"
        );
    }

    // OPTION 3: Search for a message using its ID
    private void searchByMessageID(Scanner input) {

        System.out.print("Enter the message ID: ");
        String searchID = input.nextLine();

        boolean found = false;

        for (int i = 0; i < messageCount; i++) {

            if (messageID[i].equalsIgnoreCase(searchID)) {
                System.out.println("Message found!");
                System.out.println("Sender: " + messageSender[i]);
                System.out.println("Recipient: " + messageRecipient[i]);
                System.out.println("Message: " + messageText[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No message found with that ID.");
        }
    }

    // OPTION 4: Search for messages using a recipient
    private void searchByRecipient(Scanner input) {

        System.out.print("Enter the recipient: ");
        String searchRecipient = input.nextLine();

        boolean found = false;

        for (int i = 0; i < messageCount; i++) {

            if (messageRecipient[i].equalsIgnoreCase(searchRecipient)) {
                System.out.println("\nMessage found!");
                System.out.println("Message ID: " + messageID[i]);
                System.out.println("Sender: " + messageSender[i]);
                System.out.println("Recipient: " + messageRecipient[i]);
                System.out.println("Message: " + messageText[i]);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No messages found for that recipient.");
        }
    }

    // OPTION 5: Delete a message using its hash
    void deleteByMessageHash(Scanner input) {

        System.out.print("Enter the message hash to delete: ");
        String searchHash = input.nextLine();

        int foundIndex = -1;

        for (int i = 0; i < messageCount; i++) {
            if (messageHash[i].equals(searchHash)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {
            System.out.println("No message found with that hash.");
            return;
        }

        // Move later messages one position to the left
        for (int i = foundIndex; i < messageCount - 1; i++) {
            messageID[i] = messageID[i + 1];
            messageHash[i] = messageHash[i + 1];
            messageText[i] = messageText[i + 1];
            messageSender[i] = messageSender[i + 1];
            messageRecipient[i] = messageRecipient[i + 1];
        }

        // Clear the last occupied position
        messageCount--;

        messageID[messageCount] = null;
        messageHash[messageCount] = null;
        messageText[messageCount] = null;
        messageSender[messageCount] = null;
        messageRecipient[messageCount] = null;

        System.out.println("Message deleted successfully.");
    }

    // OPTION 6: Display a report of stored messages
    void generateReport() {

        System.out.println("\n===== STORED MESSAGES REPORT =====");
        System.out.println("Total stored messages: " + messageCount);

        if (messageCount == 0) {
            System.out.println("No stored messages to report.");
            return;
        }

        for (int i = 0; i < messageCount; i++) {
            System.out.println("\nMessage " + (i + 1));
            System.out.println("ID: " + messageID[i]);
            System.out.println("Hash: " + messageHash[i]);
            System.out.println("Sender: " + messageSender[i]);
            System.out.println("Recipient: " + messageRecipient[i]);
            System.out.println("Message: " + messageText[i]);
        }
    }
}
