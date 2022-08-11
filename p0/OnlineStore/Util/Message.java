package Util;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Message {

    public static void internalErrorTryAgain() {
        System.out.println("Internal error! Try again...");
    }
    public static void chooseOption() {
        System.out.printf("Choose your option: ");
    }
    public static void listOfStores() {
        System.out.println("\nList of Stores:");
        System.out.println(" ID\t\tLocation");
    }
    public static void chooseStore() {
        System.out.println("Choose Store:");
    }
    public static void mainMenu() {
        System.out.println("Main Menu:");
    }

    public static void loginSuccess() {
        System.out.println("Registration successful! Please login.\n");
    }
    public static void registerSuccess() {
        System.out.println("Registration successful! Please login.\n");
    }
    public static void registerFailed() {
        System.out.println("Failed to register! Please, try again...\n");
    }

    public static void  wrongInputTryAgain() {
        System.out.println("Wrong input! Try again...\n");
    }
    public static void wrongEmailFormat() {
        System.out.println("Wrong Email format!\n");
    }

    public static void enterAddress() {
        System.out.printf("Enter your address: ");
    }

    public static void enterSurname() {
        System.out.printf("Enter your surname: ");
    }

    public static void enterName() {
        System.out.printf("Enter your name: ");
    }

    public static void enterBuilding() {
        System.out.printf("Enter building: ");
    }

    public static void enterStreet() {
        System.out.printf("Enter street: ");
    }

    public static void enterApartment() {
        System.out.printf("Enter apartment: ");
    }

    public static void enterState() {
        System.out.printf("Enter surname: ");
    }

    public static void enterZip() {
        System.out.printf("Enter zip: ");
    }

    public static void moveToPrevMenu() {
        System.out.println("[q] to move to previous menu");
    }

    public static void enterLogin() {
        System.out.printf("Enter Login: ");
    }

    public static void enterPass() {
        System.out.printf("Enter Pass: ");
    }

    public static void goodBy() {
        System.out.println("We will be happy to see you again!");
    }

    public static void wrongInput() {
        System.out.println("Wrong input!\n");
    }

    public static void emptyInput() {
        System.out.println("Empty input!\n");
    }

    public static void welcomeToStore() {
        System.out.println("Welcome to our store!");
    }
}

