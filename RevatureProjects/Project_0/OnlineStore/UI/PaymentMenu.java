package UI;

import java.math.BigDecimal;
import java.util.*;

import UI.*;
import Models.*;
import Util.MenuHelper;
import Enum.*;
import Util.Message;

public class PaymentMenu {
    public static ArrayList<Cart> processPayment(ArrayList<Cart> carts) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            BigDecimal totalPrice = carts
                    .stream()
                    .map(record -> record.getPricePerItem().multiply(BigDecimal.valueOf(record.getQuantity())))
                    .reduce(BigDecimal.ZERO, (a, b) ->  a.add(b));
            int totalItemQuantity = carts
                    .stream()
                    .map(record -> record.getQuantity())
                    .reduce(0, (a, b) -> a + b);
            System.out.println("\nYour cart contains:");
            System.out.printf("Total quantity: %d\n", totalItemQuantity);
            System.out.printf("Total price   : %.2f\n", totalPrice);
            MenuHelper.displayMenu2(new String[]{"Pay now"}, "\nConfirm payment:", "");
            String userInput = scanner.nextLine();
            if (userInput.equals("q")) {
                return null;
            } else if (userInput.equals("1")) {
            return carts;
            } else {
                Message.wrongInputTryAgain();
            }
        }
    }
}
