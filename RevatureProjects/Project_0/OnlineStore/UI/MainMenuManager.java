package UI;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAL.CartDaoImpl;
import DAL.StoreDaoImpl;
import Util.*;
import Models.*;
import BLL.*;
import Enum.*;


public class MainMenuManager {
    private static Scanner scanner = new Scanner(System.in);

    //display all available products from the current store
    private static String chooseProductFromMenu(String storeId) {
        int id = Integer.valueOf(storeId);
        //list of products from the chosen store
        ArrayList<Product> listOfProducts =
                new BLLManagerImpl().processProductsByStore(id);
        //display the products on the screen
        MenuHelper.displayProducts(listOfProducts, id);
        //validated user choice
        String idOfChosenProduct = Helper.interpretUserInput(scanner.nextLine());
        if (idOfChosenProduct.equals("0") || idOfChosenProduct.equals("-1"))
            return idOfChosenProduct;
        //check if there is id of store in current meu that is displayed on screen
        int sizeOfList =
                listOfProducts.stream()
                        .filter(product -> String.valueOf(product.getProductId()).equals(idOfChosenProduct))
                        .collect(Collectors.toList()).size();
        //if no id in current list, than return "-1" error, else return user's choice
        return sizeOfList == 0 ? "-1" : idOfChosenProduct;
    }

    private static void sendProductToCartByAddingOrUpdating(int userId, String idOfChosenStore, String idOfChosenProduct, String quantityOfProduct) {
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
                } else if (Integer.valueOf(quantityOfProduct) > product.getQuantity()) {
                    System.out.println("Failed to add to Shopping Cart");
                    System.out.println("You requested " + quantityOfProduct + " items but we have only " + product.getQuantity());
                    System.out.println("Request less quantity or order from another location.");
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
                }

            }
        } catch (Exception e) {
            Message.wrongInputTryAgain();
        }
    }

    private static void chooseProduct(int userId, String idOfChosenStore) {
        boolean exitToListOfStores = false;
        while (!exitToListOfStores) {
            //Get all available products from the chosen store
            try {
                //get validate user's choice of choice
                String idOfChosenProduct = chooseProductFromMenu(idOfChosenStore);
                //return to Stores menu if "q"
                if (idOfChosenProduct.equals("0")) {
                    break;
                }
                //get quantity from user
                System.out.println("Enter quantity of product:");
                String quantityOfProduct = Helper.interpretUserInput(scanner.nextLine());

                if (quantityOfProduct.equals("0")) {
                    //return to Stores menu if "q"
                    break;
                } else if (idOfChosenProduct.equals("-1") || quantityOfProduct.equals("-1")) {
                    Message.wrongInputTryAgain();
                } else {
                    sendProductToCartByAddingOrUpdating(userId, idOfChosenStore, idOfChosenProduct, quantityOfProduct);
                }
            } catch (Exception e) {
                System.out.println("Failed to get products");
            }
        }
    }

    public static void processMainMenu(int userId) {
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        String[] mainMenuShopOptions = {"Go Shopping!", "Shopping Cart", "Account", "History", "Exit Store\n"};
        String[] menuSortingOptions = {"Order", "Store", "Category", "Time", "Back to prev menu\n"};
        boolean exit = false;

        while (!exit) {
            MenuHelper.displayMenu(mainMenuShopOptions, "Main menu:", "");
            userInput = scanner.nextLine();
            switch (userInput) {
                //List of stores
                case "1":
                    //Move to the store for shopping
                    while (true) {
                        String idOfChosenStore = MenuListOfStores.chooseStoreFromMenu(new BLLManagerImpl().processListOfStores());
                        if (Integer.valueOf(idOfChosenStore) > 0) {
                            chooseProduct(userId, idOfChosenStore);
                        } else if (Integer.valueOf(idOfChosenStore) < 0) {
                            Message.wrongInputTryAgain();
                        } else {
                            break;
                        }
                    }
                    break;
                case "2":
                    CartMenu.processCart(userId);
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
