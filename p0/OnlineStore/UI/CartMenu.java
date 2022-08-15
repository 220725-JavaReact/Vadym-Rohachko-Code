package UI;

import BLL.BLLManagerImpl;
import Models.*;
import Util.MenuHelper;
import Util.Message;
import Util.Regex;

import java.util.ArrayList;
import java.util.Scanner;

public class CartMenu {
    private static Scanner scanner = new Scanner(System.in);

    static void processCart(int userId) {
        //print list from Cart using current user id only
        //menu to delete all products
        //to add any number of the distinct product
        //to substract any number of the distinct product
        //while substracting, check if it not negative
        //if it equals 0, then remove from the cart
        //return to shopping
        //return to main menu?
        ArrayList<Cart> carts = new BLLManagerImpl().processRecordsFromCartByUserId(userId);
        while (true) {
            MenuHelper.displayCartRecords(carts);
            String userCartRecordChoice = scanner.nextLine();
            if (userCartRecordChoice.equals("q")) {
                break;
            } else {
                if (Regex.isReadyToRemoveFromCart(userCartRecordChoice)) {
                    System.out.println("Remove the whole record");
                } else if (Regex.isReadyToIncreaseByOne(userCartRecordChoice)) {
                    System.out.println("Increase by one");
                } else if (Regex.isReadyToDecreaseByOne(userCartRecordChoice)) {
                    System.out.println("Decrease by one");
                } else if (Regex.isReadyToAddMultiple(userCartRecordChoice)) {
                    System.out.println("Add multiple");
                } else if (Regex.isReadyToSubstractMultiple(userCartRecordChoice)) {
                    System.out.println("Substract multiple");
                } else {
                    Message.wrongInputTryAgain();
                }
            }
        }
    }
}
