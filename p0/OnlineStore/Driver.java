import BLL.BLLManagerImpl;
import Interfaces.IArchiveDao;
import UI.*;

import java.util.ArrayList;
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
        String[] menuSortingOptions = {"Order", "Store", "Category", "Time", "Back to prev menu\n"};
        boolean exit = false;

        while (!exit) {
            //Message.welcomeToStore();
            MenuHelper.displayMenu(welcomeScreenOptions, "Welcome to the Store!");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    //login
                    Login login = new MenuLogin().getLogin();
                    if (login != null) {
                        //IF LOGIN WAS SUCCESSFUL
                        String email = login.getLogin();
                        String pass = login.getPass();
                        int userId = new BLLManagerImpl().processLogin(email, pass);
                        if (userId > 0) {
                            System.out.println("Login and Pass are ok! Entering Main Menu...\n");
                            //MAIN MENU
                            while (!exit) {
                                MenuHelper.displayMenu(mainMenuShopOptions, "Main menu:");
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
                                        //Get single product by id
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
                                            for (Product product : new BLLManagerImpl().processProductsByCategory(categoryId)) {
                                                System.out.println(product.getProductName() + " " + product.getCategory());
                                            }
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
                                        //Get all available products
                                        try {
                                            System.out.println(new BLLManagerImpl().processAllProductsFromInventory().size());
                                        } catch (NullPointerException e) {
                                            System.out.println("Failed to get products");
                                        }
                                        System.out.println("Go to Cart\n");
                                        break;
                                    case "3":
                                        System.out.println("Go to Account\n");
                                        break;
                                    case "4":
                                        while (!exit) {
                                            MenuHelper.displayMenu(menuSortingOptions, "Sort Archive by:");
                                            userInput = scanner.nextLine();
                                            switch (userInput) {
                                                case "1":
                                                    System.out.println("Sorting by Order...");
                                                    Helper.displayArchive(userId, IArchiveDao.SortingType.order);
                                                    break;
                                                case "2":
                                                    System.out.println("Sorting by Store...");
                                                    Helper.displayArchive(userId, IArchiveDao.SortingType.store);
                                                    break;
                                                case "3":
                                                    System.out.println("Sorting by Category...");
                                                    Helper.displayArchive(userId, IArchiveDao.SortingType.category);
                                                    break;
                                                case "4":
                                                    System.out.println("Sorting by Time...");
                                                    Helper.displayArchive(userId, IArchiveDao.SortingType.time);
                                                    break;
                                                case "q":
                                                    exit = true;
                                                default:
                                                    Message.wrongInput();
                                                    break;
                                            }
                                        }
                                        exit = false;
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
                        boolean isRegistered =
                                new BLLManagerImpl().processRegister(register.getLogin(),
                                        register.getPass(), register.getName(),
                                        register.getSurname(), register.getCardNumber());
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

