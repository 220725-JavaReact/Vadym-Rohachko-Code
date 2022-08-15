package Interfaces;

import Models.*;

import java.util.ArrayList;

public interface ICartDao {
    void addProductToCart(Cart cart);
    void updateProductInCart(Cart cart);
    Cart getSingleRecordFromCart(Cart cart);
    ArrayList<Cart> getRecordsFromCart(int userId);
}
