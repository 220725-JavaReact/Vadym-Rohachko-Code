package OnlineStore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static void displayMenu(String[] menu) {
        for (String item : menu) {
            System.out.println(item);
        }
        System.out.printf("Choose your option: ");
    }

    public static boolean validateEmail(String email) {
//        Pattern pattern =
//                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(email);
//        boolean result = matcher.find();
//        return result;

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.compile(regex).matcher(email).matches();
    }

    public static void displayMessMainMenu() {
        System.out.println("Main Menu:");
    }

    public static void displayMessLogin() {
        System.out.println("Registration successful! Please login.\n");
    }

    public static void displayMessWrongEmailFormat() {
        System.out.println("Wrong Email format!\n");
    }

    public static void displayMessEnterAddress() {
        System.out.printf("Enter your address: ");
    }

    public static void displayMessEnterSurname() {
        System.out.printf("Enter your surname: ");
    }

    public static void displayMessEnterName() {
        System.out.printf("Enter your name: ");
    }

    public static void displayMessEnterBuilding() {
        System.out.printf("Enter building: ");
    }

    public static void displayMessEnterStreet() {
        System.out.printf("Enter street: ");
    }

    public static void displayMessEnterApartment() {
        System.out.printf("Enter apartment: ");
    }

    public static void displayMessEnterState() {
        System.out.printf("Enter surname: ");
    }

    public static void displayMessEnterZip() {
        System.out.printf("Enter zip: ");
    }

    public static void displayMessMoveToPrevMenu() {
        System.out.println("[q] to move to previous menu");
    }

    public static void displayMessEnterLogin() {
        System.out.printf("Enter Login: ");
    }

    public static void displayMessEnterPass() {
        System.out.printf("Enter Pass: ");
    }

    public static void displayMessGoodBy() {
        System.out.println("We will be happy to see you again!");
    }

    public static void displayMessWrongInput() {
        System.out.println("Wrong input!\n");
    }

    public static void displayMessEmptyInput() {
        System.out.println("Empty input!\n");
    }

    public static void displayMessWelcomeToStore() {
        System.out.println("Welcome to our store!\n");
    }
}
