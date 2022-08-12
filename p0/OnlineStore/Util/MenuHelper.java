package Util;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.ArrayList;
public class MenuHelper {
    public static void displayMenu(String[] menu, String... header) {
        //Header for the menu if needed
        if(!header.equals("")) System.out.println((String)header[0]);
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
}

