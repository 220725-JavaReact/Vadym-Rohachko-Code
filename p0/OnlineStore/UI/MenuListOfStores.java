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
    public static String chooseStoreFromMenu(ArrayList<Store> stores) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                MenuHelper.displayMenu(stores);
                String userStoreChoice = scanner.nextLine();
                String storeId = Helper.interpretUserInput(userStoreChoice);
                switch (storeId) {
                    case "0":
                        return "0";
                    case "-1":
                        Message.wrongInputTryAgain();
                        break;
                    default:
                        //check whether there is a store in DB with storeId
                        List result =
                                stores.stream()
                                        .filter(store -> String.valueOf(store.getStoreId()).equals(storeId))
                                        .collect(Collectors.toList());
                        if (!result.isEmpty()) {
                            return userStoreChoice;
                        } else {
                            Message.wrongInputTryAgain();
                        }
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
