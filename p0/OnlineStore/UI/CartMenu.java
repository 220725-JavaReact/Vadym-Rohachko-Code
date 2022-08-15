package UI;

import BLL.BLLManagerImpl;
import DAL.CartDaoImpl;
import Models.*;
import Util.MenuHelper;
import Util.Message;
import Util.Regex;
import Enum.*;


import java.util.ArrayList;
import java.util.Scanner;

public class CartMenu {
    private static Scanner scanner = new Scanner(System.in);

    static void processCart(int userId) {
        while (true) {
            ArrayList<Cart> carts = new BLLManagerImpl().processRecordsFromCartByUserId(userId);
            MenuHelper.displayCartRecords(carts);
            String userCartRecordChoice = scanner.nextLine();
            if (userCartRecordChoice.equals("q")) {
                break;
            } else {
                if (Regex.isReadyToRemoveFromCart(userCartRecordChoice)) {
                    String[] strings = userCartRecordChoice.split("!", -1);
                    String cartId = strings[0];
                    new BLLManagerImpl().processDeleteRecordFromCart(userId, Integer.valueOf(cartId));
                    System.out.println("The product was removed successfully!\n");
                }else if(Regex.isReadyToRemoveAllFromCart(userCartRecordChoice)){
                    new BLLManagerImpl().processDeleteCart(userId);
                    System.out.println("All the products were successfully removed from cart!\n");
                } else if (Regex.isReadyToIncreaseByOne(userCartRecordChoice)) {
                    String[] strings = userCartRecordChoice.split("\\+", -1);
                    String cartId = strings[0];
                    CommandWord result =  new BLLManagerImpl().processUpdateRecordInCart(userId, Integer.valueOf(cartId), 1, CommandWord.INCREASE_SINGLAR);
                    if (result == CommandWord.UPDATE) {
                        System.out.println("Number of items was increased by one!");
                    } else {
                        System.out.println("Failure! No changes were done to the record...");
                    }
                } else if (Regex.isReadyToDecreaseByOne(userCartRecordChoice)) {
                    String[] strings = userCartRecordChoice.split("-", -1);
                    String cartId = strings[0];
                    CommandWord result =  new BLLManagerImpl().processUpdateRecordInCart(userId, Integer.valueOf(cartId), 1, CommandWord.DECREASE_SINGLAR);
                    if (result == CommandWord.UPDATE) {
                        System.out.println("Number of items was decreased by one!");
                    } else {
                        System.out.println("Failure! No changes were done to the record...");
                    }
                } else if (Regex.isReadyToAddMultiple(userCartRecordChoice)) {
                    String[] strings = userCartRecordChoice.split("\\+", -1);
                    String cartId = strings[0];
                    String qty = strings[1];
                    CommandWord result = new BLLManagerImpl().processUpdateRecordInCart(userId, Integer.valueOf(cartId), Integer.valueOf(qty), CommandWord.INCREASE_PLURAL);
                    if (result == CommandWord.UPDATE) {
                        System.out.println("Number of items was increased!");
                    } else {
                        System.out.println("Failure! No changes were done to the record...");
                    }
                } else if (Regex.isReadyToSubstractMultiple(userCartRecordChoice)) {
                    String[] strings = userCartRecordChoice.split("-", -1);
                    String cartId = strings[0];
                    String qty = strings[1];
                   CommandWord result =  new BLLManagerImpl().processUpdateRecordInCart(userId, Integer.valueOf(cartId), Integer.valueOf(qty), CommandWord.DECREASE_PLURAL);
                    if (result == CommandWord.UPDATE) {
                        System.out.println("Number of items was decreased!");
                    }else if(result == CommandWord.FAILURE_DUE_TO_HIGH_AMOUNT){
                        System.out.println("No changes were done! Quantity is too large... Choose less or try another location.");
                    } else {
                        System.out.println("Failure! No changes were done to the record...");
                    }

                } else {
                    Message.wrongInputTryAgain();
                }
            }
        }
    }
}
