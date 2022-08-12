package Util;

import BLL.BLLManagerImpl;
import Interfaces.IArchiveDao;
import Models.Archive;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
    public static void displayArchive(int userId, IArchiveDao.SortingType type) {
        try {
            ArrayList<Archive> archives =
                    new BLLManagerImpl().processArchivesByUserId(userId, type);
            for (Archive archive : archives) {
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                System.out.println("Store ID    " + archive.getStoreId());
                System.out.println("Order ID    " + archive.getOrderId());
                System.out.println("Category ID " + archive.getCategoryId());
                System.out.println("Created:    " + archive.getOrderCreatedAt().toLocalDateTime().format(myFormatObj));
                System.out.println("Product     " + archive.getProductName());
                System.out.println("Description " + archive.getDescription());
                System.out.println("Category    " + archive.getCategory());
                System.out.println("Quantity    " + archive.getQuantity());
                System.out.println("Price       " + archive.getPricePerItem());
                System.out.println("Total price " + archive.getPriceTotal());
                System.out.println("Store at    " + archive.getStoreLocation());
                String dash = "-";
                for (int i = 0; i < 20; i++) {
                    dash += "-";
                }
                System.out.println(dash);
            }
        } catch (NullPointerException e) {
            System.out.println("Failed to get records from Archive");
        }
    }

    public static boolean archiveSortAndDisplay(boolean isExit, int userId, String[] menuSortingOptions){
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            MenuHelper.displayMenu(menuSortingOptions, "Sort Archive by:");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    System.out.println("Sorting by Order...");
                    Helper.displayArchive(userId, IArchiveDao.SortingType.order);
                    break;
                case "2":
                    System.out.println("Sorting by Store...");
                    Helper.displayArchive(userId, IArchiveDao.SortingType.store);
                    break;
                case "3":
                    System.out.println("Sorting by Category...");
                    Helper.displayArchive(userId, IArchiveDao.SortingType.category);
                    break;
                case "4":
                    System.out.println("Sorting by Time...");
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
}
