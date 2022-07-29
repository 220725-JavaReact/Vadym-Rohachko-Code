package OnlineStore;

import java.util.Scanner;

public class Menu {

    public static void manageMenu() {
        Helper.displayMessWelcomeToStore();
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String userChoice = "";
        String[] welcomeScreenOptions = {"[1] - Login ", "[2] - Register", "[0] - Exit App\n"};
        String[] mainMenuShopOptions = {"[1] - List of Stores ", "[2] - Cart",
                "[3] - Account", "[4] - History", "[0] - Exit Store\n"};
        boolean exit = false;

        while (!exit) {
            Helper.displayMenu(welcomeScreenOptions);
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    //login
                    MenuLogin menuLogin = new MenuLogin();
                    //menuLogin.getLogin() returns the login object (email, pass, boolean/status)
                    if (menuLogin.getLogin() != null) {
                        //check here against BD? !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        System.out.println("Login and Pass are ok! Entering Main Menu...\n");
                        //IF LOGIN WAS SUCCESSFUL
                        while (!exit) {
                            Helper.displayMessMainMenu();
                            Helper.displayMenu(mainMenuShopOptions);
                            userInput = scanner.nextLine();
                            switch (userInput) {
                                case "1":
                                    System.out.println("Go to List of Stores\n");
                                    break;
                                case "2":
                                    System.out.println("Cart\n");
                                    break;
                                case "3":
                                    System.out.println("Account\n");
                                    break;
                                case "4":
                                    System.out.println("History\n");
                                    break;
                                case "0":
                                    Helper.displayMessWelcomeToStore();
                                    exit = true;
                                    break;
                                default:
                                    Helper.displayMessWrongInput();
                            }
                        }
                        exit = false;
                    }
                    break;
                case "2":
                    //Register
                    MenuRegister menuRegister = new MenuRegister();
                    if (menuRegister.register() != null) {
                        //check here against BD? !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        //arrange actions in case of failure and success!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        System.out.println("User has been registered! Returning to Main Menu...");
                        Helper.displayMessLogin();
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
