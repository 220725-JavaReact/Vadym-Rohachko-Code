package Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import BLL.BLLManagerImpl;
import Models.*;

public class MenuHelper {
    public static void displayMenu(String[] menu, String... header) {
        //Header for the menu if needed
        if (!header.equals("")) System.out.println((String) header[0]);
        //Menu with dynamic numbering
        for (int i = 0; i < menu.length; i++) {
            if (i == menu.length - 1) {
                System.out.printf("[%s] - %s", "q", menu[i]);
            } else {
                System.out.printf("[%d] - %s\n", i + 1, menu[i]);
            }
        }
        System.out.printf("Choose your option: ");
    }

    //dynamic menu with its own numbering
    public static void displayMenu(HashMap menu) {
        ArrayList<Integer> listOfKeys = new ArrayList<Integer>(menu.keySet());
        for (int i = 0; i < listOfKeys.size(); i++) {
            System.out.printf("[%d] - %s\n", listOfKeys.get(i), menu.get(listOfKeys.get(i)));
        }
        System.out.printf("[%s] - %s\n", "q", "Back to prev menu");
        System.out.printf("Choose your option: ");
    }

    public static void displayMenu(ArrayList<Store> stores) {
        System.out.println("\nList of available stores:");
        //List<Integer> collect = stores.stream().map(x -> x.getStoreId()).collect(Collectors.toList());
        stores.stream().forEach(x -> System.out.printf("[%d] - %s\n", x.getStoreId(), x.getStoreLocation()));
        System.out.printf("[%s] - %s\n", "q", "Back to prev menu");
        System.out.println("Choose your option: ");
    }

    public static void displayProducts(ArrayList<Product> products, int storeId) {
        Store store = new BLLManagerImpl().processStoreById(storeId);
        System.out.printf("\nYou are shopping at %s\n", store.getStoreLocation());
        System.out.println("List of available products:");
        //List<Integer> collect = stores.stream().map(x -> x.getStoreId()).collect(Collectors.toList());
        products.stream().
                forEach(product -> System.out.printf("[%d] - %s\t%s\t%s\n",
                        product.getProductId(),
                        product.getProductName(),
                        product.getDescription(),
                        product.getPrice()));
        System.out.printf("[%s] - %s\n", "q", "Back to prev menu");
        System.out.println("Choose your option: ");
    }
}

