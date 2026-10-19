/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javapoe.part1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sendmessage {

    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();

    private static int nextMessageNumber = 1;

    public static void main(String[] args) {
    
        Scanner input = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       WELCOME TO QUICKCHAT");
        System.out.println("================================");
        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.println("\nWelcome, " + name + "!");

        boolean running = true;

        while (running) {

            System.out.println("\n========== QUICKCHAT ==========");
            System.out.println("1. Send Messages");
            System.out.println("2. Show Recently Sent Messages");
            System.out.println("3. Quit");
            System.out.print("Choose an option: ");

            String option = input.nextLine();

            switch (option) {

                case "1":
                    sendMessages(input);
                    break;

                case "2":
                    displayMessages();
                    break;

                case "3":
                    running = false;
                    System.out.println("Thank you for using QuickChat.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }

        input.close();
    }

    public static void sendMessages(Scanner input) {

        System.out.print("How many messages would you like to enter? ");

        int numberOfMessages;

        try {
            numberOfMessages = Integer.parseInt(input.nextLine());

            if (numberOfMessages <= 0) {
                System.out.println("Enter a number greater than zero.");
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid whole number.");
            return;
        }

        for (int i = 0; i < numberOfMessages; i++) {

            System.out.println("\nMessage " + (i + 1) + " of "
                    + numberOfMessages);

            System.out.print("Enter recipient cellphone number: ");
            String recipient = input.nextLine();

            System.out.print("Enter your message: ");
            String messageText = input.nextLine();

            if (messageText.trim().isEmpty()) {
                System.out.println("The message cannot be empty.");
                i--;
                continue;
            }

            if (messageText.length() > 250) {
                System.out.println("The message cannot exceed 250 characters.");
                i--;
                continue;
            }

            String messageID = createMessageID(nextMessageNumber);
            String messageHash = createMessageHash(
                    messageID, nextMessageNumber, messageText);

            Message newMessage = new Message(
                    messageID,
                    nextMessageNumber,
                    recipient,
                    messageText,
                    messageHash,
                    "Sent"
            );

            System.out.println("\nChoose what to do with this message:");
            System.out.println("1. Send and save message");
            System.out.println("2. Disregard message");
            System.out.println("3. Return message to menu without saving");
            System.out.print("Choose an option: ");

            String action = input.nextLine();

            if (action.equals("1")) {

                sentMessages.add(newMessage);
                saveMessagesToJSON();

                System.out.println("Message sent and saved.");
                displayMessage(newMessage);

                nextMessageNumber++;

            } else if (action.equals("2")) {

                disregardedMessages.add(newMessage);
                System.out.println("Message disregarded.");

            } else if (action.equals("3")) {

                System.out.println("Message was not saved.");

            } else {

                System.out.println("Invalid choice. Message was not saved.");
            }
        }
    }

    public static String createMessageID(int messageNumber) {

        String number = String.valueOf(messageNumber);

        // Keep the ID at a maximum of 10 characters.
        if (number.length() > 10) {
            number = number.substring(0, 10);
        }

        return number;
    }
    
  public boolean checkRecipientCell(String Recipient) {

    /*
     * Uses the South African international
     * number structure.
     *
     * Example: +27718693002
     */

    return Recipient != null
            && Recipient.matches("^\\+27[6-8] [0-9] {8}$");
}
  
// CREATE MESSAGE HASH
    public static String createMessageHash(
            String messageID, int messageNumber, String messageText) {

        String trimmedMessage = messageText.trim();
        String[] words = trimmedMessage.split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String firstTwoIDCharacters = messageID.substring(
                0, Math.min(2, messageID.length()));

        return (firstTwoIDCharacters + ":"
                + messageNumber + ":"
                + firstWord + lastWord).toUpperCase();
    }
    //==================================
    //DISPLAY MESSAGES
    //==================================
    public static void displayMessages() {

        if (sentMessages.isEmpty()) {
            System.out.println("No messages have been sent yet.");
            return;
        }

        System.out.println("\n======= SENT MESSAGES =======");

        for (Message message : sentMessages) {
            displayMessage(message);
        }
    }
   
    public static void displayMessage(Message message) {

        System.out.println("\n------------------------------");
        System.out.println("Message ID: " + message.messageID);
        System.out.println("Message Number: " + message.messageNumber);
        System.out.println("Recipient: " + message.recipient);
        System.out.println("Message: " + message.messageText);
        System.out.println("Message Hash: " + message.messageHash);
        System.out.println("Status: " + message.messageStatus);
        System.out.println("------------------------------");
    }

    public static void saveMessagesToJSON() {

        try (FileWriter writer = new FileWriter("messages.json")) {

            writer.write("[\n");

            for (int i = 0; i < sentMessages.size(); i++) {

                Message message = sentMessages.get(i);

                writer.write("  {\n");
                writer.write("    \"messageID\": \""
                        + escapeJSON(message.messageID) + "\",\n");
                writer.write("    \"messageNumber\": "
                        + message.messageNumber + ",\n");
                writer.write("    \"recipient\": \""
                        + escapeJSON(message.recipient) + "\",\n");
                writer.write("    \"messageText\": \""
                        + escapeJSON(message.messageText) + "\",\n");
                writer.write("    \"messageHash\": \""
                        + escapeJSON(message.messageHash) + "\",\n");
                writer.write("    \"messageStatus\": \""
                        + escapeJSON(message.messageStatus) + "\"\n");
                writer.write("  }");

                if (i < sentMessages.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]\n");

            System.out.println("Messages saved to messages.json.");

        } catch (IOException e) {
            System.out.println("Could not save messages: " + e.getMessage());
        }
    }

    public static String escapeJSON(String text) {

        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }

    // Simple class used to store each message.
    public static class Message {

        private String messageID;
        private int messageNumber;
        private String recipient;
        private String messageText;
        private String messageHash;
        private String messageStatus;
    
        public Message(
                String messageID,
                int messageNumber,
                String recipient,
                String messageText,
                String messageHash,
                String messageStatus) {

            this.messageID = messageID;
            this.messageNumber = messageNumber;
            this.recipient = recipient;
            this.messageText = messageText;
            this.messageHash = messageHash;
            this.messageStatus = messageStatus;
        }
    }
    
}

