package org.maktab.onlinestore.data.repository;

import org.maktab.onlinestore.data.model.Cart;
import java.util.List;

public interface IRepository {

    void updateCart(Cart cart);
    void insertCart(Cart cart);
    void insertCarts(List<Cart> carts);
    void deleteCart(Cart cart);
    void deleteAllCart();
    List<Cart> getCarts();
}
