package OnlineStore;

import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    public static void manageMenu() {

        HashMap<Integer, String> stores = new HashMap<Integer, String>();
        stores.put(11, "Fort Worth");
        stores.put(13, "Dallas");
        stores.put(12, "McKinney");
        stores.put(14, "Allen");
        stores.put(15, "Prosper");

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String[] welcomeScreenOptions = {"Login", "Register", "Exit App\n"};
        String[] mainMenuShopOptions = {"List of Stores", "Cart",
                "Account", "History", "Exit Store\n"};
        boolean exit = false;

        while (!exit) {
            Message.welcomeToStore();
            MenuHelper.displayMenu(welcomeScreenOptions);
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
                        //Main menu
                        while (!exit) {
                            Message.mainMenu();
                            MenuHelper.displayMenu(mainMenuShopOptions);
                            userInput = scanner.nextLine();
                            switch (userInput) {
                                //List of stores
                                case "1":
                                    //get list of stores in HashMap format
                                    int choiceOfStore = MenuListOfStores.manageMenuOfStores(stores);
                                    switch (choiceOfStore) {
                                        case 0:
                                            exit = true;
                                            break;
                                        case -1:
                                            Message.internalErrorTryAgain();
                                            break;
                                        default:
                                            Store.displayStore(choiceOfStore);
                                    }
                                    exit = false;
                                    break;
                                case "2":
                                    System.out.println("Go to Cart\n");
                                    break;
                                case "3":
                                    System.out.println("Go to Account\n");
                                    break;
                                case "4":
                                    System.out.println("Go to History\n");
                                    break;
                                case "q":
                                    exit = true;
                                    break;
                                default:
                                    Message.wrongInput();
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
                        Message.loginSuccess();

                        Message.registerSuccess();
                        Message.loginSuccess();

                    }
                    break;
                case "q":
                    Message.goodBy();
                    System.exit(0);
                default:
                    Message.wrongInput();
            }
        }// end login register menu
    }
}
