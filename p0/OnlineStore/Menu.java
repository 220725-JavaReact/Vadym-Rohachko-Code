package OnlineStore;

import java.util.Scanner;

public class Menu {

    public static void manageMenu() {
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
                    //Register
                    MenuRegister menuRegister = new MenuRegister();
                    if( menuRegister.register() != null){
                        //check here against BD?
                        System.out.println("User has been registered! Returning to Main Menu...");
                    }
                    break;
                case "0":
                    Helper.displayMessGoodBy();
                    System.exit(0);
                default:
                    Helper.displayMessWrongInput();
            }
        }// end login register menu
    }
}
