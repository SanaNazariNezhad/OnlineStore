package org.maktab.onlinestore.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;
import org.maktab.onlinestore.view.activity.SubCategoriesActivity;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private OnlineStoreRepository mRepository;
    private List<Product> mProductList;
    private List<ProductCategory> mCategoryList;
    private Context mContext;


    public CategoryViewModel(@NonNull Application application) {
        super(application);
        mRepository = new OnlineStoreRepository();
    }

    public List<Product> getProductList() {
        return mProductList;
    }

    public void setProductList(List<Product> productList) {
        mProductList = productList;
    }

    public List<ProductCategory> getCategoryList() {
        return mCategoryList;
    }

    public void setCategoryList(List<ProductCategory> categoryList) {
        mCategoryList = categoryList;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void fetchCategoryItems() {
        mRepository.fetchCategoryItemsAsync();
    }

    public void fetchSubCategoryItems(String parentId) {
        mRepository.fetchSubCategoryItemsAsync(parentId);
    }

    public void fetchProductItemsWithParentId(String parentId) {
        mRepository.fetchProductItemsWithParentIdAsync(parentId);
    }

    public LiveData<List<ProductCategory>> getLiveDataCategoryItems(){
        return mRepository.getCategoryItemsLiveData();
    }

    public LiveData<List<Product>> getLiveDataProductWithParentId(){
        return mRepository.getProductWithParentIdLiveData();
    }

    public void onClickListItem(int productId,String state) {
        if (state.equalsIgnoreCase("product"))
            mContext.startActivity(ProductDetailActivity.newIntent(mContext,productId));
        else if (state.equalsIgnoreCase("category"))
            mContext.startActivity(SubCategoriesActivity.newIntent(mContext,productId));
    }
}
