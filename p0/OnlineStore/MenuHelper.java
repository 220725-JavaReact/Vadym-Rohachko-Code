package OnlineStore;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class MenuHelper {
    public static void displayMenu(String[] menu) {
        for (int i = 0; i < menu.length; i++) {
            if (i == menu.length - 1) {
                System.out.printf("[%s] - %s", "q", menu[i]);
            } else {
                System.out.printf("[%d] - %s\n", i + 1, menu[i]);
            }
        }
        System.out.printf("Choose your option: ");
    }

    public static void displayMenu(HashMap menu) {
        ArrayList<Integer> listOfKeys = new ArrayList<Integer>(menu.keySet());
        for (int i = 0; i < listOfKeys.size(); i++) {
            System.out.printf("[%d] - %s\n", listOfKeys.get(i), menu.get(listOfKeys.get(i)));
        }
        System.out.printf("[%s] - %s\n", "q", "Back to prev menu");
        System.out.printf("Choose your option: ");
    }

    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.compile(regex).matcher(email).matches();
    }
}
