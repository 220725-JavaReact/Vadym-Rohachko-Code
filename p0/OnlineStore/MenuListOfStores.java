package OnlineStore;

import java.util.HashMap;
import java.util.Scanner;

public class MenuListOfStores {
    public static int manageMenuOfStores(HashMap<Integer, String> stores) {
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
                if (stores.containsKey(choice)) {
                    //Return the ID of the store
                    exit = true;
                } else if (choice == 0) {
                    //Exit menu (code 0)
                    exit = true;
                } else {
                    System.out.printf("We have no store with ID %d. Try again...\n\n", choice);
                }
            } catch (Exception e) {
                Message.wrongInputTryAgain();
            }
        }
        return choice;
    }
}
