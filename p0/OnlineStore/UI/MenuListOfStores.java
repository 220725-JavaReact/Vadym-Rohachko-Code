package UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Util.Helper;
import Util.MenuHelper;
import Util.Message;
import Models.*;

public class MenuListOfStores {
    public static String manageMenuOfStores(ArrayList<Store> stores) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            MenuHelper.displayMenu(stores);
            String userStoreChoice = scanner.nextLine();

            try {
                String storeId = Helper.validateUserInput(userStoreChoice);
                switch (storeId) {
                    case "q":
                        return "q";
                    case "0":
                    case "-1":
                        return "-1";
                    default:
                        //check whether there is a store in DB with storeId
                        List result =
                                stores.stream()
                                        .filter(store -> String.valueOf(store.getStoreId()).equals(storeId))
                                        .collect(Collectors.toList());
                        if (!result.isEmpty()) {
                            return userStoreChoice;
                        } else {
                            return "0";
                        }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "-1";
            }
        }
        return "-1";
    }
}
