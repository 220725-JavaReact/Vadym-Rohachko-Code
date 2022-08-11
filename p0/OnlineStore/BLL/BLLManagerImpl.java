package BLL;

import Interfaces.IBLLManager;
import DAL.*;
import Models.*;

import java.util.ArrayList;

public class BLLManagerImpl implements IBLLManager {

    @Override
    public boolean processLogin(String login, String password) {
        User user = new UserDaoImpl().getUserByLoginAndPassword(login, password);
        return user != null ? true : false;
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
    public ArrayList<Archive> processArchivesByUserId(int userId) { 
        return new ArchiveDaoImpl().getArchiveByUserId(userId);
    }
}

