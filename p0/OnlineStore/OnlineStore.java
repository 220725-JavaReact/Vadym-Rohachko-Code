package OnlineStore;

import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.getenv;

public class OnlineStore {

    public static void main(String[] args) {
        Helper.displayMessWelcomeToStore();
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String userChoice = "";
        String[] welcomeScreenOptions = {"[1] - Login ", "[2] - Register", "[0] - Exit\n"};
        boolean exit = false;

        while (!exit) {
            Helper.displayMenu(welcomeScreenOptions);
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    //login
                    MenuLogin menuLogin = new MenuLogin();
                    //menuLogin.getLogin() returns the login object (email, pass, boolean/status)
                   if( menuLogin.getLogin() != null){
                       //check here against BD?
                       System.out.println("Login and Pass are ok! Entering Main Menu...");
                   }
                    break;
                case "2":
                    userChoice = "Register";
                    break;
                case "0":
                    Helper.displayMessGoodBy();
                    System.exit(0);
                default:
                    Helper.displayMessWrongInput();
            }
        }// end login register menu


    }// end main
}
