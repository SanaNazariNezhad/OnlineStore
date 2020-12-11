package org.maktab.onlinestore.data.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import org.maktab.onlinestore.data.model.Cart;
import java.util.List;

@Dao
public interface CartDatabaseDAO {

    @Update
    void updateCart(Cart cart);

    @Insert
    void insertCart(Cart cart);

    @Insert
    void insertCarts(List<Cart> carts);

    @Delete
    void deleteTask(Cart cart);

    @Query("DELETE FROM cart")
    void deleteAllCart();

    @Query("SELECT * FROM cart")
    List<Cart> getCarts();

    @Query("SELECT * FROM cart WHERE product_id=:productId")
    Cart getCart(int productId);

}
