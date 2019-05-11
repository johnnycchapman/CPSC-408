package com.company;

/**
 *   Bar Hub
 *   By: Johnny Chapman and Samir Kamnani
 *   Rene German
 *   CPSC 408
 *
 **/

import java.util.*;


public class Main
{
    public static String url = "jdbc:mysql://35.233.129.12:3306/barsdb";
    public static String user = "johnnychapman";
    public static String pass = "Turkeyboy!";

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        String[] availableDatabases = {"bar_phone", "bar_email", "bar_info", "bar_features", "bar_ratings"};
        String[] availableCommands = {"display", "contact", "update", "filter", "create", "delete", "search",
                                        "export", "average","overview"};
        String databaseName = "";
        String command = "";
        boolean exitFlag = false;

        displayMenu();

        while (!exitFlag) {
            databaseName = "";
            command = "";
            System.out.println("\nCommand: ");
            String userInput = s.nextLine();

            userInput = userInput.toLowerCase().trim();

            if (userInput.equals("exit")) {
                exitFlag = true;
            }
            else if (userInput.equals("menu")) {
                displayMenu();
            }
            else if (userInput.equals("bigoh")) {
                System.out.println("For two functions f(n) and  g(n), f(n) = O(g(n)) when there exist \n" +
                        "constants c > 0 and n0 > 0 such that f(n) ≤ c * g(n), for all n ≥ n0");
            }
            else if (userInput.equals("rene?")) {
                System.out.println("It's on the syllabus!");
            }

            else {
                for (int i = 0; i < userInput.length(); i++) {
                    if (userInput.charAt(i) == ',') {
                        databaseName = userInput.substring(0, i).trim();
                        command = userInput.substring(i + 1, userInput.length()).trim();
                    }
                }

                if (Arrays.asList(availableDatabases).contains(databaseName) && Arrays.asList(availableCommands).contains(command)) {
                    switch (databaseName) {
                        case "bar_phone":
                            Phone.executeCommand(command);
                            break;
                        case "bar_email":
                            Email.executeCommand(command);
                            break;
                        case "bar_info":
                            Info.executeCommand(command);
                            break;
                        case "bar_features":
                            Features.executeCommand(command);
                            break;
                        case "bar_ratings":
                            Ratings.executeCommand(command);
                            break;
                    }
                }
                else {
                    System.out.println("\nInvalid Input\n");
                }
            }
        }

    }


    public static void displayMenu() {

        System.out.println("\n\n\n\n\n\n=================================================================================\n");
        System.out.println("                      -----      WELCOME TO BAR HUB     -----      ");
        System.out.println("\n=================================================================================");
        System.out.println("* Please navigate database with (database_name,command)");
        System.out.println("* Enter 'menu' to bring this back up");
        System.out.println("* Enter 'exit' to bring this back up");
        System.out.println("* Enter 'bigoh' to bring up definition of bigOh");
        System.out.println("* [bar_info] (display, create, update, delete, contact, search, export, overview)\n" +
                "* [bar_ratings] (display, filter, average, export)\n" +
                "* [bar_phone] (display, update)\n" +
                "* [bar_email] (display)\n" +
                "* [bar_features] (display)");
        System.out.println("=================================================================================");


    }
}

