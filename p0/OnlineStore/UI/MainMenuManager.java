package UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import DAL.StoreDaoImpl;
import Util.*;
import Models.*;
import BLL.*;

public class MainMenuManager {
    private static Scanner scanner = new Scanner(System.in);

    private static void processCart() {
        System.out.println("You ar inside the Cart!");
    }

    //get all available stores from inventory
    private static String chooseStoreFromMenu() {
        //get all available stores from inventory
        ArrayList listOfStoresAvailableInInventory = new BLLManagerImpl().processListOfStores();
        //get id of the chosen store
        return MenuListOfStores.manageMenuOfStores(listOfStoresAvailableInInventory);
    }

    //display all available products from the current store
    private static String chooseProductFromMenu(String storeId) {
        int id = Integer.valueOf(storeId);
        //list of products from the chosen store
        ArrayList<Product> listOfProducts =
                new BLLManagerImpl().processProductsByStore(id);
        //display the products on the screen
        MenuHelper.displayProducts(listOfProducts, id);
        //validated user choice
        String idOfChosenProduct = Helper.validateUserInput(scanner.nextLine());
        //check if there is id of store in current meu that is displayed on screen
        int sizeOfList =
                listOfProducts.stream()
                        .filter(product -> String.valueOf(product.getProductId()).equals(idOfChosenProduct))
                        .collect(Collectors.toList()).size();
        //if no id in current list, than return "-1" error, else return user's choice
        return sizeOfList < 1 ? "-1" : idOfChosenProduct;
    }

    //dealing with stores and their contents
    private static void processShopping() {
        boolean exitToMainMenu = false;
        while (!exitToMainMenu) {
            //get validated id of the chosen store
            String idOfChosenStore = chooseStoreFromMenu();
            boolean exitToListOfStores = false;
            if (idOfChosenStore.equals("q")) {
                //move to the previous menu
                break;
            } else if (idOfChosenStore.equals("-1") || idOfChosenStore.equals("0")) {
                Message.wrongInputTryAgain();
                exitToListOfStores = true;
            }
            while (!exitToListOfStores) {
                //Get all available products from the chosen store
                try {
                    //get validate user's choice of choice
                    String idOfChosenProduct = chooseProductFromMenu(idOfChosenStore);
                    //return to Stores menu if "q"
                    if (idOfChosenProduct.equals("q")) {
                        break;
                    } else if (idOfChosenProduct.equals("-1") || idOfChosenProduct.equals("0")) {
                        Message.wrongInputTryAgain();
                    } else {
                        try {
                            System.out.println("Checking if a store with ID #" + idOfChosenProduct + " is in the DB...");
                            //work with DB...
                            //add to local list
                            //continue shopping...
                        } catch (Exception e) {
                            Message.wrongInputTryAgain();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Failed to get products");
                }
            }
        }
    }

    public static void processMainMenu(int userId) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String[] mainMenuShopOptions = {"List of Stores", "Cart", "Account", "History", "Exit Store\n"};
        String[] menuSortingOptions = {"Order", "Store", "Category", "Time", "Back to prev menu\n"};
        boolean exit = false;

        while (!exit) {
            MenuHelper.displayMenu(mainMenuShopOptions, "Main menu:");
            userInput = scanner.nextLine();
            switch (userInput) {
                //List of stores
                case "1":
                    //Move to the store for shopping
                    processShopping();
////Get single product by id
//                    try {
//                        int productId = 1;
//                        System.out.println(new BLLManagerImpl().processProductById(productId).getProductName());
//                    } catch (NullPointerException e) {
//                        System.out.println("Failed to get product");
//                    }
//                    //Get all available products by category
//                    try {
//                        int categoryId = 1;
//                        System.out.println(new BLLManagerImpl().processProductsByCategory(categoryId).size());
//                        for (Product product : new BLLManagerImpl().processProductsByCategory(categoryId)) {
//                            System.out.println(product.getProductName() + " " + product.getCategory());
//                        }
//                    } catch (NullPointerException e) {
//                        System.out.println("Failed to get products");
//                    }
//                    //Get all available products by store
//                    try {
//                        int storeId = 1;
//                        System.out.println(new BLLManagerImpl().processProductsByStore(storeId).size());
//                    } catch (NullPointerException e) {
//                        System.out.println("Failed to get products");
//                    }
//                    //Get all available products
//                    try {
//                        System.out.println(new BLLManagerImpl().processAllProductsFromInventory().size());
//                    } catch (NullPointerException e) {
//                        System.out.println("Failed to get products");
//                    }
                    break;
                case "2":
                    processCart();
                    break;
                case "3":
                    System.out.println("Menu 'Account' is closed for reconstruction...\n");
                    break;
                case "4":
                    exit = Helper.archiveSortAndDisplay(exit, userId, menuSortingOptions);
                    break;
                case "q":
                    exit = true;
                    break;
                default:
                    Message.wrongInput();
            }
        }
    }
}
