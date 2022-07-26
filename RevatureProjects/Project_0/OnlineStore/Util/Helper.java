package Util;

import BLL.BLLManagerImpl;
import Interfaces.IArchiveDao;
import Models.Archive;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Helper {

    public static void displayArchive(int userId, IArchiveDao.SortingType type) {
        try {
            ArrayList<Archive> archives =
                    new BLLManagerImpl().processArchivesByUserId(userId, type);

            if (archives.size() == 0) {
                System.out.println("You do not have any records in the Archive.");
                return;
            }

            for (Archive archive : archives) {
//                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//                System.out.println("Store ID    " + archive.getStoreId());
//                System.out.println("Order ID    " + archive.getOrderId());
//                System.out.println("Category ID " + archive.getCategoryId());
//                System.out.println("Created:    " + archive.getOrderCreatedAt().toLocalDateTime().format(myFormatObj));
//                System.out.println("Product     " + archive.getProductName());
//                System.out.println("Description " + archive.getDescription());
//                System.out.println("Category    " + archive.getCategory());
//                System.out.println("Quantity    " + archive.getQuantity());
//                System.out.println("Price       " + archive.getPricePerItem());
//                System.out.println("Total price " + archive.getPriceTotal());
//                System.out.println("Store at    " + archive.getStoreLocation());
//                System.out.println(getDelimiter(20));
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                System.out.print("Created: " + archive.getOrderCreatedAt().toLocalDateTime().format(myFormatObj));
                System.out.print(" Order ID:[" + archive.getOrderId() + "]");
                System.out.print(" Category ID:[" + archive.getCategoryId() +"]");
                System.out.print(" Store ID:[" + archive.getStoreId() + "]");
                System.out.print(" Quantity:[" + archive.getQuantity() + "]");
                System.out.print(" Price:$" + archive.getPricePerItem());
                System.out.println(" Total price:$" + archive.getPriceTotal());
                System.out.print("Product: " + archive.getProductName());
                System.out.print(" Description: " + archive.getDescription());
                System.out.print(" Category: " + archive.getCategory());
                System.out.println(" Location: " + archive.getStoreLocation());
                System.out.println(getDelimiter(20));
            }
        } catch (NullPointerException e) {
            System.out.println("Failed to get records from Archive\n");
        }
    }

    private static String getDelimiter(int length) {
        String lineOfSymbols = "-";
        for (int i = 0; i < length; i++) {
            lineOfSymbols += "-";
        }
        return lineOfSymbols;
    }

    public static boolean archiveSortAndDisplay(boolean isExit, int userId, String[] menuSortingOptions) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Archive> archives =
                new BLLManagerImpl().processArchivesByUserId(userId, IArchiveDao.SortingType.order);

        if (archives.size() == 0) {
            System.out.println("You have no records in archive for the moment.\n");
            isExit = true;
        }

        while (!isExit) {
            MenuHelper.displayMenu(menuSortingOptions, "\nSort Archive by:", "");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    System.out.println("\nSorting by Order...");
                    Helper.displayArchive(userId, IArchiveDao.SortingType.order);
                    break;
                case "2":
                    System.out.println("\nSorting by Store...");
                    Helper.displayArchive(userId, IArchiveDao.SortingType.store);
                    break;
                case "3":
                    System.out.println("\nSorting by Category...");
                    Helper.displayArchive(userId, IArchiveDao.SortingType.category);
                    break;
                case "4":
                    System.out.println("\nSorting by Time...");
                    Helper.displayArchive(userId, IArchiveDao.SortingType.time);
                    break;
                case "q":
                    isExit = true;
                    break;
                default:
                    Message.wrongInput();
                    break;
            }
        }
        return false;
    }

    public static boolean validateEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.compile(regex).matcher(email).matches();
    }


    public static String validateUserInput(String userInput) {
        if (userInput.equals("q")) {
            return "q";
        }
        try {
            int result = Integer.valueOf(userInput);
            return result > 0 ? userInput : "0";
        } catch (NumberFormatException e) {
            return "-1";
        }
    }

    public static String interpretUserInput(String userInput) {
        if (userInput.equals("q")) {
            return "0";
        }
        try {
            int result = Integer.valueOf(userInput);
            return result > 0 ? userInput : "-1";
        } catch (NumberFormatException e) {
            return "-1";
        }
    }
}
