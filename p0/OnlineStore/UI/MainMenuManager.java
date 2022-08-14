package UI;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

import DAL.CartDaoImpl;
import DAL.StoreDaoImpl;
import Util.*;
import Models.*;
import BLL.*;
import Enum.*;


public class MainMenuManager {
    private static Scanner scanner = new Scanner(System.in);

    private static void processCart(int userId) {
        System.out.println("You ar inside the Cart!");
        //print list from Cart using current user id only
        //menu to delete all products
        //to add any number of the distinct product
        //to substract any number of the distinct product
        //while substracting, check if it not negative
        //if it equals 0, then remove from the cart
        //return to shopping
        //return to main menu?
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
        if (idOfChosenProduct.equals("q") || idOfChosenProduct.equals("0") || idOfChosenProduct.equals("-1"))
            return idOfChosenProduct;
        //check if there is id of store in current meu that is displayed on screen
        int sizeOfList =
                listOfProducts.stream()
                        .filter(product -> String.valueOf(product.getProductId()).equals(idOfChosenProduct))
                        .collect(Collectors.toList()).size();
        //if no id in current list, than return "-1" error, else return user's choice
        return sizeOfList == 0 ? "-1" : idOfChosenProduct;
    }

    //dealing with stores and their contents
    private static void processShopping(int userId) {
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
                    }
                    //get quantity from user
                    System.out.println("Enter quantity of product:");
                    String quantityOfProduct = Helper.validateUserInput(scanner.nextLine());
                    //return to Stores menu if "q"
                    if (quantityOfProduct.equals("q")) {
                        break;
                    }
                    //return to Stores menu if "q"
                    if (idOfChosenProduct.equals("-1") || idOfChosenProduct.equals("0")) {
                        Message.wrongInputTryAgain();
                    } else if (quantityOfProduct.equals("-1") || quantityOfProduct.equals("0")) {
                        Message.wrongInputTryAgain();
                    } else {
                        try {
                            //check if available in DB
                            Product product =
                                    new BLLManagerImpl()
                                            .processProductById(Integer.valueOf(idOfChosenStore),
                                                    Integer.valueOf(idOfChosenProduct));
                            if (product == null) {
                                System.out.println("Failed to add to Shopping Cart due to internal error");
                            } else {
                                if (product.getQuantity() == 0) {
                                    System.out.println("We do not have the product at this store.");
                                    System.out.println("Please, Order from another location.");
                                    System.out.println("Sorry for inconvenience...");
                                } else if (Integer.valueOf(quantityOfProduct) >= product.getQuantity()) {
                                    System.out.println("Failed to add to Shopping Cart");
                                    System.out.println("You requested " + quantityOfProduct + ",items we have only " + product.getQuantity());
                                    System.out.println("Request less quantity or order the product from another location.");
                                    System.out.println("Sorry for inconvenience...");
                                } else {
                                    //collect cart for sql request to check if there is such a record in the Cart table
                                    Cart cart = new Cart(userId, product.getStoreId(), product.getProductId(), Integer.valueOf(quantityOfProduct));

                                    Cart cartTemp = new BLLManagerImpl().processSingleRecordFromCart(cart, CommandWord.SELECT);
                                    if (cartTemp != null) {
                                        new BLLManagerImpl().processSingleRecordFromCart(cart, CommandWord.UPDATE);
                                        System.out.println("Product updated in Cart!");
                                    } else {
                                        new BLLManagerImpl().processSingleRecordFromCart(cart, CommandWord.INSER);
                                        System.out.println("Product added to Cart!");
                                    }
                                    //menu to go to Cart

                                    //processCart();


                                }

                            }
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
                    processShopping(userId);
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
                    processCart(userId);
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
