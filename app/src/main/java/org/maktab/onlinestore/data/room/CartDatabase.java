package org.maktab.onlinestore.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import org.maktab.onlinestore.data.model.Cart;

@Database(entities = {Cart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDatabaseDAO getCartDatabaseDAO();

}
