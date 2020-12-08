package org.maktab.onlinestore.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maktab.onlinestore.data.model.Images;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.utilities.QueryPreferences;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private OnlineStoreRepository mRepository;
    private List<Product> mProductListMostVisited;
    private List<Product> mProductListLatest;
    private List<Product> mProductListHighestScore;
    private List<Product> mProductList;
    private List<Product> mSearchProduct;
    private Context mContext;
    private Product mDetailedProduct;


    public ProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = new OnlineStoreRepository();

    }

    public List<Product> getSearchProduct() {
        return mSearchProduct;
    }

    public void setSearchProduct(List<Product> searchProduct) {
        mSearchProduct = searchProduct;
    }

    public List<Product> getProductListMostVisited() {
        return mProductListMostVisited;
    }

    public void setProductListMostVisited(List<Product> productListMostVisited) {
        mProductListMostVisited = productListMostVisited;
    }

    public List<Product> getProductListLatest() {
        return mProductListLatest;
    }

    public void setProductListLatest(List<Product> productListLatest) {
        mProductListLatest = productListLatest;
    }

    public List<Product> getProductListHighestScore() {
        return mProductListHighestScore;
    }

    public void setProductListHighestScore(List<Product> productListHighestScore) {
        mProductListHighestScore = productListHighestScore;
    }

    public List<Product> getProductList() {
        return mProductList;
    }

    public void setProductList(List<Product> productList) {
        mProductList = productList;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Product getDetailedProduct() {
        return mDetailedProduct;
    }

    public void setDetailedProduct(Product detailedProduct) {
        mDetailedProduct = detailedProduct;
    }

    public void fetchProductItems(int productId){
        mRepository.fetchProductItemAsync(productId);
    }

    public void fetchMostVisitedProductItems(){
        mRepository.fetchMostVisitedProductItemsAsync();
    }

    public void fetchLatestProductItems(){
        mRepository.fetchLatestProductItemsAsync();
    }

    public void fetchHighestScoreProductItems(){
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

    public LiveData<List<Product>> getSearchItemsLiveData() {
        return mRepository.getSearchProductsLiveData();
    }

    public void onClickListItem(int productId) {
        mContext.startActivity(ProductDetailActivity.newIntent(mContext,productId));
    }

    public void fetchSearchItemsAsync(String query) {
        mRepository.fetchSearchItemsAsync(query);
    }

    public void setQueryInPreferences(String query) {
        QueryPreferences.setSearchQuery(getApplication(), query);
    }

    public String getQueryFromPreferences() {
        return QueryPreferences.getSearchQuery(getApplication());
    }
}
