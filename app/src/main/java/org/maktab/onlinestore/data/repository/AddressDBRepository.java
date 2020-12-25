package org.maktab.onlinestore.data.repository;

import android.content.Context;

import androidx.room.Room;

import org.maktab.onlinestore.data.model.MapAddress;
import org.maktab.onlinestore.data.room.CartDatabase;
import org.maktab.onlinestore.data.room.CartDatabaseDAO;

import java.util.List;

public class AddressDBRepository implements IAddressRepository {

    private static AddressDBRepository sInstance;

    private CartDatabaseDAO mCartDAO;
    private Context mContext;
    private List<MapAddress> mMapAddresses;

    public static AddressDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new AddressDBRepository(context);

        return sInstance;
    }

    private AddressDBRepository(Context context) {
        mContext = context.getApplicationContext();
        CartDatabase cartDatabase = Room.databaseBuilder(mContext,
                CartDatabase.class,
                "cart.db")
                .allowMainThreadQueries()
                .build();

        mCartDAO = cartDatabase.getCartDatabaseDAO();
    }

    @Override
    public void updateAddress(MapAddress mapAddress) {
        mCartDAO.updateAddress(mapAddress);
    }

    @Override
    public void insertAddress(MapAddress mapAddress) {
        mCartDAO.insertAddress(mapAddress);
    }

    @Override
    public void insertAddresses(List<MapAddress> mapAddresses) {
        mCartDAO.insertAddresses(mapAddresses);
    }

    @Override
    public void deleteAddress(MapAddress mapAddress) {
        mCartDAO.deleteAddress(mapAddress);
    }

    @Override
    public List<MapAddress> getMapAddresses() {
        return mCartDAO.getAddresses();
    }
}
