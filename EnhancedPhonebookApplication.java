import java.util.*;
import java.io.*;
/*
 * MIT License
 * Copyright (c) 2024 Purohit1999
 */

public class EnhancedPhonebookApplication {

    private Map<String, String> phonebook;

    // Constructor initializes the phonebook as a HashMap
    public EnhancedPhonebookApplication() {
        phonebook = new HashMap<>();
    }

    // Adds a contact to the phonebook
    public void addContact(String name, String phoneNumber) {
        if (phonebook.containsKey(name)) {
            System.out.println("Contact already exists. Use update option to modify.");
        } else {
            phonebook.put(name, phoneNumber);
            System.out.println("Contact added: " + name);
        }
    }

    // Updates an existing contact's phone number
    public void updateContact(String name, String newPhoneNumber) {
        if (phonebook.containsKey(name)) {
            phonebook.put(name, newPhoneNumber);
            System.out.println("Contact updated: " + name);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }

    // Searches for a contact by name
    public void searchContact(String name) {
        if (phonebook.containsKey(name)) {
            String phoneNumber = phonebook.get(name);
            System.out.println("Name: " + name + ", Phone Number: " + phoneNumber);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }

    // Searches for contacts containing a partial name
    public void searchByPartialName(String partialName) {
        boolean found = false;
        for (Map.Entry<String, String> entry : phonebook.entrySet()) {
            if (entry.getKey().toLowerCase().contains(partialName.toLowerCase())) {
                System.out.println("Name: " + entry.getKey() + ", Phone Number: " + entry.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found with the name: " + partialName);
        }
    }

    // Deletes a contact from the phonebook
    public void deleteContact(String name) {
        if (phonebook.containsKey(name)) {
            phonebook.remove(name);
            System.out.println("Contact deleted: " + name);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }

    // Displays all contacts in the phonebook
    public void displayAllContacts() {
        if (phonebook.isEmpty()) {
            System.out.println("Phonebook is empty.");
        } else {
            System.out.println("Phonebook Contacts:");
            for (Map.Entry<String, String> entry : phonebook.entrySet()) {
                System.out.println("Name: " + entry.getKey() + ", Phone Number: " + entry.getValue());
            }
        }
    }

    // Saves the phonebook to a file
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : phonebook.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            System.out.println("Phonebook saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving phonebook: " + e.getMessage());
        }
    }

    // Loads the phonebook from a file
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    phonebook.put(parts[0], parts[1]);
                }
            }
            System.out.println("Phonebook loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading phonebook: " + e.getMessage());
        }
    }

    // Main method to interact with the phonebook application
    public static void main(String[] args) {
        EnhancedPhonebookApplication phonebookApp = new EnhancedPhonebookApplication();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu
            System.out.println("\nPhonebook Application");
            System.out.println("1. Add Contact");
            System.out.println("2. Update Contact");
            System.out.println("3. Search Contact");
            System.out.println("4. Search by Partial Name");
            System.out.println("5. Delete Contact");
            System.out.println("6. Display All Contacts");
            System.out.println("7. Save Phonebook to File");
            System.out.println("8. Load Phonebook from File");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the phone number: ");
                    String phoneNumber = scanner.nextLine();
                    phonebookApp.addContact(name, phoneNumber);
                    break;

                case 2:
                    System.out.print("Enter the name to update: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter the new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    phonebookApp.updateContact(updateName, newPhoneNumber);
                    break;

                case 3:
                    System.out.print("Enter the name to search: ");
                    String searchName = scanner.nextLine();
                    phonebookApp.searchContact(searchName);
                    break;

                case 4:
                    System.out.print("Enter part of the name to search: ");
                    String partialName = scanner.nextLine();
                    phonebookApp.searchByPartialName(partialName);
                    break;

                case 5:
                    System.out.print("Enter the name to delete: ");
                    String deleteName = scanner.nextLine();
                    phonebookApp.deleteContact(deleteName);
                    break;

                case 6:
                    phonebookApp.displayAllContacts();
                    break;

                case 7:
                    System.out.print("Enter the filename to save to: ");
                    String saveFile = scanner.nextLine();
                    phonebookApp.saveToFile(saveFile);
                    break;

                case 8:
                    System.out.print("Enter the filename to load from: ");
                    String loadFile = scanner.nextLine();
                    phonebookApp.loadFromFile(loadFile);
                    break;

                case 9:
                    System.out.println("Exiting Phonebook Application...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

