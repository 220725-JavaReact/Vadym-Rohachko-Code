package Interfaces;

import Models.*;
import Enum.*;

import java.util.ArrayList;

public interface ICartDao {
    void addProductToCart(Cart cart);
    void updateProductInCart(Cart cart);
    Cart getSingleRecordFromCart(Cart cart);
    Cart getSingleRecordFromCart(int cartId);
    ArrayList<Cart> getRecordsFromCart(int userId);
    void deleteProductFromCart(int userId, int cartId);
    void deleteCart(int userId);
    CommandWord increaseQuantityInRecordByOne(int userId, int cartId);
    CommandWord decreaseQuantityInRecordByOne(int userId, int cartId);
    CommandWord increaseQuantityInRecordByMany(int userId, int cartId, int qty);
    CommandWord decreaseQuantityInRecordByMany(int userId, int cartId, int qty);
}
