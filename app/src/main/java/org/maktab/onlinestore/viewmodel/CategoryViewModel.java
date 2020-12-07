package org.maktab.onlinestore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private OnlineStoreRepository mRepository;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        mRepository = new OnlineStoreRepository();
    }

    public void getCategoryItems() {
        mRepository.fetchCategoryItemsAsync();
    }
    public void getSubCategoryItems(String parentId) {
        mRepository.fetchSubCategoryItemsAsync(parentId);
    }
    public void getProductItemsWithParentId(String parentId) {
        mRepository.fetchProductItemsWithParentIdAsync(parentId);
    }

    public LiveData<List<ProductCategory>> getLiveDataCategoryItems(){
        return mRepository.getCategoryItemsLiveData();
    }
    public LiveData<List<Product>> getLiveDataProductWithParentId(){
        return mRepository.getProductWithParentIdLiveData();
    }
}
