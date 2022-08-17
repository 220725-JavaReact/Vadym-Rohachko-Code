package Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import BLL.BLLManagerImpl;
import Models.*;

public class MenuHelper {
    public static void displayMenu(String[] menu, String header, String footer) {
        //Header for the menu if needed
        if (!header.equals("")) System.out.println(header);
        //Menu with dynamic numbering
        for (int i = 0; i < menu.length; i++) {
            if (i == menu.length - 1) {
                System.out.printf("[%s] - %s", "q", menu[i]);
            } else {
                System.out.printf("[%d] - %s\n", i + 1, menu[i]);
            }
        }
        //footer for the menu if needed
        if (!footer.equals("")) System.out.println(header);
        System.out.printf("Choose your option: ");
    }

    public static void displayMenu2(String[] menu, String header, String footer) {
        //Header for the menu if needed
        if (!header.equals("")) System.out.println(header);
        //Menu with dynamic numbering
        for (int i = 0; i < menu.length; i++) {
            System.out.printf("[%d] - %s\n", i + 1, menu[i]);
        }
        //footer for the menu if needed
        if (!footer.equals("")) System.out.println(header);
        {
            System.out.println("[q] - Back to prev menu");
            System.out.println("Choose your option: ");
        }

    }

    public static void displayMenu(ArrayList<Store> stores) {
        System.out.println("\nList of available stores:");
        //List<Integer> collect = stores.stream().map(x -> x.getStoreId()).collect(Collectors.toList());
        stores.stream().forEach(x -> System.out.printf("[%d] - %s\n", x.getStoreId(), x.getStoreLocation()));
        System.out.printf("[%s] - %s\n", "q", "Back to prev menu");
        System.out.println("Choose your option: ");
    }

    public static void displayCartRecords(ArrayList<Cart> carts) {
        if (carts.size() == 0) {
            System.out.println("Your cart is empty!");
            System.out.println("[q] to return to previous menu");
            return;
        }
        System.out.println("List of available records in cart:");
        System.out.println("[ID]");
        carts.stream().forEach(x -> System.out.printf("" +
                        "[%d]  " +
                        "|qty %d|" +
                        "\t|price $%.2f|" +
                        "\t|total price $%.2f|" +
                        "\t|tittle: %s|" +
                        "\t|description: %s|" +
                        "\t|category: %s|" +
                        "\t|store at %s|" +
                        "\n",
                x.getCartId(),
                x.getQuantity(),
                x.getPricePerItem(),
                (x.getPricePerItem().multiply(new BigDecimal(x.getQuantity()))),
                x.getProductName(),
                x.getDescription(),
                x.getCategory(),
                x.getStoreLocation()));

        System.out.println("How to:");
        System.out.println("[ID][+] to increase by one");
        System.out.println("[ID][-] to decrease by one");
        System.out.println("[ID][+][qty] to increase quantity of a product");
        System.out.println("[ID][-][qty] to decrease quantity of a product");
        System.out.println("[ID][!] to remove product from Cart");
        System.out.println("[*] to clear Cart");
        System.out.printf("[%s] - %s\n", "q", "Back to prev menu");
        System.out.printf("[%s] - %s\n", "p", "Go to payment");
        System.out.print("Choose your option: ");
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

