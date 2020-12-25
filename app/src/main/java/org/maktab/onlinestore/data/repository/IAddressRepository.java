package org.maktab.onlinestore.data.repository;

import org.maktab.onlinestore.data.model.MapAddress;

import java.util.List;

public interface IAddressRepository {

    void updateAddress(MapAddress mapAddress);
    void insertAddress(MapAddress mapAddress);
    void insertAddresses(List<MapAddress> mapAddresses);
    void deleteAddress(MapAddress mapAddress);
    List<MapAddress> getMapAddresses();
}
