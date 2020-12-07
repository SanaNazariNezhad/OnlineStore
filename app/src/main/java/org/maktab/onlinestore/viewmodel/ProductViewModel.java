package org.maktab.onlinestore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private OnlineStoreRepository mRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = new OnlineStoreRepository();
    }

    public void getProductItems(int productId){
        mRepository.fetchProductItemAsync(productId);
    }

    public void getMostVisitedProductItems(){
        mRepository.fetchMostVisitedProductItemsAsync();
    }

    public void getLatestProductItems(){
        mRepository.fetchLatestProductItemsAsync();
    }

    public void getHighestScoreProductItems(){
        mRepository.fetchHighestScoreProductItemsAsync();
    }

    public LiveData<List<Product>> getLiveDateMostVisitedProducts(){
        return mRepository.getMostVisitedProductsLiveData();
    }

    public LiveData<List<Product>> getLiveDateLatestProducts(){
        return mRepository.getLatestProductsLiveData();
    }

    public LiveData<Product> getLiveDateProduct(){
        return mRepository.getProductLiveData();
    }

    public LiveData<List<Product>> getLiveDateHighestScoreProducts(){
        return mRepository.getHighestScoreProductsLiveData();
    }
}
