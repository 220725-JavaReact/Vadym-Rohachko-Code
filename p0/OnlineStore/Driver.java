import BLL.BLLManagerImpl;
import UI.*;

import java.util.HashMap;
import java.util.Scanner;

import Util.*;
import Models.*;

public class Driver {

    public static void main(String[] args) {
        //Menu.manageMenu();
        HashMap<Integer, String> stores = new HashMap<Integer, String>();
        stores.put(11, "Fort Worth");
        stores.put(13, "Dallas");
        stores.put(12, "McKinney");
        stores.put(14, "Allen");
        stores.put(15, "Prosper");



        Logger logger = Logger.getInstance();
        logger.log(Logger.LogLevel.info, "Program started");

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String[] welcomeScreenOptions = {"Login", "Register", "Exit App\n"};
        String[] mainMenuShopOptions = {"List of Stores", "Cart", "Account", "History", "Exit Store\n"};
        boolean exit = false;

        while (!exit) {
            Message.welcomeToStore();
            MenuHelper.displayMenu(welcomeScreenOptions);
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    //login
                    Login login = new MenuLogin().getLogin();
                    if (login != null) {
                        //IF LOGIN WAS SUCCESSFUL
                        String email = login.getLogin();
                        String pass = login.getPass();
                        boolean isLoginSuccess = new BLLManagerImpl().processLogin(email, pass);
                        if (isLoginSuccess) {
                            System.out.println("Login and Pass are ok! Entering Main Menu...\n");
                            //MAIN MENU
                            while (!exit) {
                                Message.mainMenu();
                                MenuHelper.displayMenu(mainMenuShopOptions);
                                userInput = scanner.nextLine();
                                switch (userInput) {
                                    //List of stores
                                    case "1":
                                        //Temporary get list of stores in HashMap format
                                        String choiceOfStore = MenuListOfStores.manageMenuOfStores(stores);
                                        if (!choiceOfStore.equals("q")) {
                                            Store.displayStore(Integer.parseInt(choiceOfStore));
                                        }
                                        break;
                                    case "2":
                                        //get single product by id
                                        try {
                                            int productId = 1;
                                            System.out.println(new BLLManagerImpl().processProductById(productId).getProductName());
                                        } catch (NullPointerException e) {
                                            System.out.println("Failed to get product");
                                        }
                                        //Get all available products by category
                                        try {
                                            int categoryId = 1;
                                            System.out.println(new BLLManagerImpl().processProductsByCategory(categoryId).size());
                                        } catch (NullPointerException e) {
                                            System.out.println("Failed to get products");
                                        }
                                        //Get all available products by store
                                        try {
                                            int storeId = 1;
                                            System.out.println(new BLLManagerImpl().processProductsByStore(storeId).size());
                                        } catch (NullPointerException e) {
                                            System.out.println("Failed to get products");
                                        }
                                        System.out.println("Go to Cart\n");
                                        break;
                                    case "3":
                                        System.out.println("Go to Account\n");
                                        break;
                                    case "4":
                                        //Get all records from Archive ordered by order for the current user
                                        try {
                                            int userId = 1;
                                            System.out.println(new BLLManagerImpl().processArchivesByUserId(userId).size());
                                        } catch (NullPointerException e) {
                                            System.out.println("Failed to get records from Archive");
                                        }
                                        break;
                                    case "q":
                                        exit = true;
                                        break;
                                    default:
                                        Message.wrongInput();
                                }
                            }
                            exit = false;
                        } else {
                            System.out.println("Wrong login and/or password! Enter again, please...\n");
                        }
                    }

                    break;
                case "2":
                    //Register
                    Register register = new MenuRegister().register();
                    if (register != null) {
                        boolean isRegistered = new BLLManagerImpl().processRegister(register.getLogin(), register.getPass(), register.getName(), register.getSurname(), register.getCardNumber());
                        if (isRegistered) {
                            Message.registerSuccess();
                        } else {
                            Message.registerFailed();
                        }
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

