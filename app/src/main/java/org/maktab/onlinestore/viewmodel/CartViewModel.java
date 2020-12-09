package org.maktab.onlinestore.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.maktab.onlinestore.data.model.Cart;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.CartDBRepository;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartDBRepository mCartDBRepository;
    private OnlineStoreRepository mStoreRepository;
    private LiveData<Product> mProductLiveData;
    private LiveData<List<Product>> mProductListLiveData;
    private List<Product> mProductList;
    private List<Product> mProductListMostVisited;
    private List<Product> mProductListLatest;
    private List<Product> mProductListHighestScore;
    private List<Product> mSearchProduct;
    private Context mContext;


    public CartViewModel(@NonNull Application application) {
        super(application);
        mCartDBRepository = CartDBRepository.getInstance(application);
        mStoreRepository = new OnlineStoreRepository();

    }
    public void insertToCart(Cart cart){
        mCartDBRepository.insertCart(cart);
    }

    public void getOrderedProduct(){

        List<Cart> carts = mCartDBRepository.getCarts();
        for (int i = 0; i < carts.size(); i++) {
            mStoreRepository.fetchProductItemAsync(carts.get(i).getProduct_id());
            mProductLiveData = mStoreRepository.getProductLiveData();

        }

    }

    public LiveData<Product> getLiveDateProduct(){
        return mStoreRepository.getProductLiveData();
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public List<Product> getProductList() {
        return mProductList;
    }

    public void setProductList(List<Product> productList) {
        mProductList = productList;
    }

    public void onClickListItem(int productId) {
        mContext.startActivity(ProductDetailActivity.newIntent(mContext,productId));
    }
}
