import UI.*;

import java.util.HashMap;
import java.util.Scanner;

import DAL.DaoPostgresql;
import Util.*;
import Models.*;

public class Driver {
    public static void main(String[] args) {
        //Menu.manageMenu();
        HashMap<Integer, String> stores = new HashMap<Integer, String>();
        stores.put(11,"Fort Worth");
        stores.put(13,"Dallas");
        stores.put(12,"McKinney");
        stores.put(14,"Allen");
        stores.put(15,"Prosper");

        Logger logger = Logger.getInstance();
        logger.log(Logger.LogLevel.info, "Program started");

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
                                    String choiceOfStore = MenuListOfStores.manageMenuOfStores(stores);
                                    if (!choiceOfStore.equals("q")) {
                                        Store.displayStore(Integer.parseInt(choiceOfStore));
                                    }
                                    break;
                                case "2":
                                    System.out.println("Go to Cart\n");
                                    break;
                                case "3":
                                    System.out.println("Go to Account\n");
                                    break;
                                case "4":
                                    System.out.println("Go to History\n");
                                    DaoPostgresql daoPostgresql = new DaoPostgresql();
                                    daoPostgresql.addInstance(new BLLManager());
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
                    //MenuRegister menuRegister = new MenuRegister();
                    if (MenuRegister.register() != null) {
                        //check here against BD? !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        //arrange actions in case of failure and success!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                        System.out.println("User has been registered! Returning to Main Menu...");
                        Message.registerSuccess();
                    }
                    break;
                case "q":
                    Message.goodBy();
                    logger.log(Logger.LogLevel.info, "Program stopped");
                    System.exit(0);
                default:
                    Message.wrongInput();
            }
        }// end login register menu
    }
}

