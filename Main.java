/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Main {

    // 1️ Arrays for messages
    static ArrayList<String> sentMessages = new ArrayList<>();
    static ArrayList<String> disregardedMessages = new ArrayList<>();
    static ArrayList<String> storedMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<Integer> messageIDs = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        // 2️ Sample data (simulate reading from JSON)
        storedMessages.add("Where are you? You are late!");
        storedMessages.add("Yohoooo, I am at your gate.");
        storedMessages.add("It is dinner time!");
        storedMessages.add("Ok, I am leaving without you.");

        // Generate IDs and hashes
        for (int i = 0; i < storedMessages.size(); i++) {
            messageIDs.add(i + 1);
            messageHashes.add(generateHash(storedMessages.get(i)));
        }

        // 3️ Main menu
        do {
            System.out.println("\n=== MESSAGE MENU ===");
            System.out.println("1. Sent Messages");
            System.out.println("2. Disregarded Messages");
            System.out.println("3. Exit");
            System.out.println("4. Stored Messages");
            System.out.print("Enter choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 4:
                    storedMessagesMenu();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Feature under development.");
            }
        } while (choice != 3);
    }

    // 4 Stored Messages submenu
    public static void storedMessagesMenu() {
        Scanner input = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("1. Display sender and recipient");
            System.out.println("2. Display longest stored message");
            System.out.println("3. Search by message ID");
            System.out.println("4. Delete message using hash");
            System.out.println("5. Display full report");
            System.out.println("6. Return to main menu");
            System.out.print("Enter option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    displaySenderRecipient();
                    break;
                case 2:
                    displayLongestMessage();
                    break;
                case 3:
                    searchByID();
                    break;
                case 4:
                    deleteByHash();
                    break;
                case 5:
                    displayFullReport();
                    break;
            }
        } while (option != 6);
    }

    // 5️ Display sender and recipient
    public static void displaySenderRecipient() {
        System.out.println("\nSender: +27838884567");
        for (String msg : storedMessages) {
            System.out.println("Recipient: +27834484567 | Message: " + msg);
        }
    }

    // 6️ Display longest message
    public static void displayLongestMessage() {
        String longest = Collections.max(storedMessages, Comparator.comparingInt(String::length));
        System.out.println("\nLongest Message: " + longest);
    }

    // 7️ Search by message ID
    public static void searchByID() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter message ID: ");
        int id = input.nextInt();

        if (id > 0 && id <= storedMessages.size()) {
            System.out.println("Message found: " + storedMessages.get(id - 1));
        } else {
            System.out.println("Message ID not found.");
        }
    }

    // 8️ Delete message using hash
    public static void deleteByHash() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter hash to delete: ");
        String hash = input.nextLine();

        int index = messageHashes.indexOf(hash);
        if (index != -1) {
            storedMessages.remove(index);
            messageHashes.remove(index);
            messageIDs.remove(index);
            System.out.println("Message deleted successfully.");
        } else {
            System.out.println("Hash not found.");
        }
    }

    // 9️ Display full report
    public static void displayFullReport() {
        System.out.println("\n--- FULL REPORT ---");
        for (int i = 0; i < storedMessages.size(); i++) {
            System.out.println("ID: " + messageIDs.get(i)
                    + " | Hash: " + messageHashes.get(i)
                    + " | Message: " + storedMessages.get(i));
        }
    }

    //  10 Hash generator
    public static String generateHash(String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(message.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            return "Error generating hash";
        }
    }
} 