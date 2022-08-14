package Interfaces;

import Models.*;

public interface ICartDao {
    void addProductToCart(Cart cart);
    void updateProductInCart(Cart cart);
    Cart getSingleRecordFromCart(Cart cart);
}
