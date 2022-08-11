package Interfaces;

import Models.*;

import java.util.ArrayList;

public interface IProductDao {
    Product getAvailableProductFromInventoryById(int productId);
    ArrayList<Product> getAvailableProductsFromInventoryByCategory(int categoryId);
    ArrayList<Product> getAvailableProductsFromInventoryByStore(int storeId);
}
