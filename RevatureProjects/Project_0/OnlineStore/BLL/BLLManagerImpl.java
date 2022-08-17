package BLL;

import Interfaces.IArchiveDao;
import Interfaces.IBLLManager;
import DAL.*;
import Models.*;
import Enum.*;

import java.util.ArrayList;

public class BLLManagerImpl implements IBLLManager {

    @Override
    public int processLogin(String login, String password) {
        User user = new UserDaoImpl().getUserByLoginAndPassword(login, password);
        return user != null ? user.getId() : -1;
    }

    @Override
    public boolean processRegister(String login, String password, String name, String surname, String cardNumber) {
        return new UserDaoImpl().insertUser(login, password, name, surname, cardNumber);
    }

    @Override
    public Product processProductById(int productId) {
        Product product = new ProductDaoImpl().getAvailableProductFromInventoryById(productId);
        return product != null ? product : null;
    }

    @Override
    public Product processProductById(int storeId, int productId) {
        Product product = new ProductDaoImpl().getAvailableProductFromInventoryById(storeId, productId);
        return product != null ? product : null;
    }

    @Override
    public ArrayList<Product> processProductsByCategory(int categoryId) {
        ArrayList<Product> products = new ProductDaoImpl().getAvailableProductsFromInventoryByCategory(categoryId);
        return products != null ? products : null;
    }

    @Override
    public ArrayList<Product> processProductsByStore(int storeId) {
        ArrayList<Product> products = new ProductDaoImpl().getAvailableProductsFromInventoryByStore(storeId);
        return products != null ? products : null;
    }

    @Override
    public ArrayList<Product> processAllProductsFromInventory() {
        ArrayList<Product> products = new ProductDaoImpl().getAllAvailableProductsFromInventory();
        return products != null ? products : null;
    }

    @Override
    public ArrayList<Archive> processArchivesByUserId(int userId, IArchiveDao.SortingType type) {
        ArrayList<Archive> archives = new ArchiveDaoImpl().getArchiveByUserId(userId, type);
        return archives != null ? archives : null;
    }

    @Override
    public ArrayList<Store> processListOfStores() {
        ArrayList<Store> stores = new StoreDaoImpl().getStores();
        return stores != null ? stores : null;
    }

    @Override
    public Store processStoreById(int storeId) {
        Store store = new StoreDaoImpl().getStore(storeId);
        return store != null ? store : null;
    }

    @Override
    public Cart processSingleRecordFromCart(Cart cart, CommandWord command) {
        switch(command){
            case SELECT:
                return new CartDaoImpl().getSingleRecordFromCart(cart);
            case UPDATE:
                new CartDaoImpl().updateProductInCart(cart);
                break;
            case INSER:
                new CartDaoImpl().addProductToCart(cart);
                break;
        }
        return null;
    }

    @Override
    public ArrayList<Cart> processRecordsFromCartByUserId(int userId) {
        ArrayList<Cart> carts = new CartDaoImpl().getRecordsFromCart(userId);
        return carts;
    }

    @Override
    public void processDeleteRecordFromCart(int userId, int cartId) {
        new CartDaoImpl().deleteProductFromCart(userId, cartId);
    }

    @Override
    public void processDeleteCart(int userId) {
        new CartDaoImpl().deleteCart(userId);
    }

    @Override
    public CommandWord processUpdateRecordInCart(int userId, int cartId, int quantity, CommandWord command) {
        switch (command){
            case INCREASE_SINGLAR:
               return new CartDaoImpl().increaseQuantityInRecordByOne(userId, cartId);
            case DECREASE_SINGLAR:
               return new CartDaoImpl().decreaseQuantityInRecordByOne(userId, cartId);
            case INCREASE_PLURAL:
               return new CartDaoImpl().increaseQuantityInRecordByMany(userId, cartId, quantity);
            case DECREASE_PLURAL:
               return new CartDaoImpl().decreaseQuantityInRecordByMany(userId,cartId,quantity);
            default:
                return CommandWord.FAILURE;
        }
    }

    @Override
    public ArrayList<Cart> processPayment(ArrayList<Cart> carts) {
        return new CartDaoImpl().processPayment(carts);
    }
}


