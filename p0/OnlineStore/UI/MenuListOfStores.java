package UI;

import java.util.HashMap;
import java.util.Scanner;

import Util.MenuHelper;
import Util.Message;

public class MenuListOfStores {
    public static String manageMenuOfStores(HashMap<Integer, String> stores) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        String userInput = "";
        int choice = 0;

        while (!exit) {
            Message.listOfStores();
            MenuHelper.displayMenu(stores);

            userInput = scanner.nextLine();

            try {
                choice = Integer.parseInt(userInput);
                if (stores.containsKey(choice) == true) {
                    //Return the ID of the store
                    return userInput;
                } else {
                    System.out.printf("We have no store with ID %d. Try again...\n\n", choice);
                }
            } catch (Exception e) {
                if (userInput.equals("q")) {
                    //Exit menu (code "q")
                    return userInput;
                } else {
                    Message.wrongInputTryAgain();
                }
            }
        }
        return "";
    }

}
