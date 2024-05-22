/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package resgistration.and.login;

import javax.swing.*;

public class RegistrationAndLogin {

    public static void main(String[] args) {
        // Capture user input using JOptionPane
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        String firstname = JOptionPane.showInputDialog("Enter first name:");
        String lastname = JOptionPane.showInputDialog("Enter last name:");

        // Check username and password
        boolean isUsernameValid = checkUsername(username);
        boolean isPasswordValid = checkPassword(password);

        // Display appropriate output messages using JOptionPane
        if (isUsernameValid && isPasswordValid) {
            JOptionPane.showMessageDialog(null, "Account created successfully!");

            // Proceed to EasyKanban functionality
            easyKanban();
        } else {
            JOptionPane.showMessageDialog(null, "Account creation failed. Please check the input fields.");
        }
    }

    // Check if username contains an underscore and is no more than 5 characters in length
    public static boolean checkUsername(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted");
            return false;
        }
    }

    // Check password complexity rules
    public static boolean checkPassword(String password) {
        if (password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*\\d.*") &&
                password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password does not meet complexity requirements");
            return false;
        }
    }

    // EasyKanban functionality
    private static void easyKanban() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
        int choice;
        do {
            choice = Integer.parseInt(JOptionPane.showInputDialog(
                    "Select an option:\n" +
                            "1) Add tasks\n" +
                            "2) Show report\n" +
                            "3) Quit"
            ));

            switch (choice) {
                case 1:
                    addTasks();
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Coming Soon");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Exiting...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));
        int totalHours = 0;
        for (int i = 0; i < numTasks; i++) {
            Task task = new Task();
            String name = JOptionPane.showInputDialog("Enter Task Name:");
            String description = JOptionPane.showInputDialog("Enter Task Description:");
            String developer = JOptionPane.showInputDialog("Enter Developer Details:");
            int duration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours):"));
            String status = JOptionPane.showInputDialog("Enter Task Status (To Do/Done/Doing):");
            task.setTask(name, description, developer, duration, status);
            totalHours += duration;
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }
        JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + totalHours);
    }
}

class Task {
    private String name;
    private String description;
    private String developer;
    private int duration;
    private String status;
    private static int taskNumber = 0;

    public void setTask(String name, String description, String developer, int duration, String status) {
        this.name = name;
        this.description = description;
        this.developer = developer;
        this.duration = duration;
        this.status = status;
        taskNumber++;
    }

    public String printTaskDetails() {
        return "Task Status: " + status + "\n" +
                "Developer Details: " + developer + "\n" +
                "Task Number: " + taskNumber + "\n" +
                "Task Name: " + name + "\n" +
                "Task Description: " + description + "\n" +
                "Task ID: " + createTaskID() + "\n" +
                "Task Duration: " + duration + "hrs";
    }

    public int getDuration() {
        return duration;
    }

    public static int returnTotalHours() {
        return taskNumber;
    }

    String createTaskID() {
        String taskID = name.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" +
                developer.substring(developer.length() - 3).toUpperCase();
        return taskID;
    }

    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }
}
