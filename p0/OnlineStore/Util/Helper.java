package Util;

import BLL.BLLManagerImpl;
import Interfaces.IArchiveDao;
import Models.Archive;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

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
}
