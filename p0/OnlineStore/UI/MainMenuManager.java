package UI;

import java.util.HashMap;
import java.util.Scanner;

import Util.*;
import Models.*;
import BLL.*;

public class MainMenuManager {

    private static void processCart(){
        System.out.println("You ar inside the Cart!");
    }

    private static void processShopping(){
        System.out.println("You ar inside the Store!");
    }

    public static void processMainMenu(int userId) {
        HashMap<Integer, String> stores = new HashMap<Integer, String>();
        stores.put(11, "Fort Worth");
        stores.put(13, "Dallas");
        stores.put(12, "McKinney");
        stores.put(14, "Allen");
        stores.put(15, "Prosper");

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
                    //Temporary get list of stores in HashMap format
                    String choiceOfStore = MenuListOfStores.manageMenuOfStores(stores);
                    if (!choiceOfStore.equals("q")) {
                        Store.displayStore(Integer.parseInt(choiceOfStore));
                    }
                    //Get id of the Store
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
