package Interfaces;

import Models.*;

import java.util.ArrayList;

import Enum.*;

public interface IBLLManager {
    //    boolean processLogin(String login, String password);
    int processLogin(String login, String password);

    boolean processRegister(String login, String password, String name, String surname, String cardNumber);

    Product processProductById(int id);

    Product processProductById(int storeId, int productId);

    ArrayList<Product> processProductsByCategory(int id);

    ArrayList<Product> processProductsByStore(int id);

    ArrayList<Product> processAllProductsFromInventory();

    ArrayList<Archive> processArchivesByUserId(int userId, IArchiveDao.SortingType type);

    ArrayList<Store> processListOfStores();

    Store processStoreById(int storeId);

    Cart processSingleRecordFromCart(Cart cart, CommandWord command);

    ArrayList<Cart> processRecordsFromCartByUserId(int userId);

}

