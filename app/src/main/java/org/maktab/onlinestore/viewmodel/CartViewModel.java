package org.maktab.onlinestore.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.maktab.onlinestore.adapter.OrderedProductAdapter;
import org.maktab.onlinestore.data.model.Cart;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.CartDBRepository;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.databinding.FragmentCartBinding;
import org.maktab.onlinestore.view.activity.CartActivity;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartDBRepository mCartDBRepository;
    private OnlineStoreRepository mStoreRepository;
    private LiveData<Product> mProductLiveData;
    private LiveData<List<Product>> mProductListLiveData;
    private List<Product> mProductList;
    private OrderedProductAdapter mOrderedProductAdapter;
    private FragmentCartBinding mFragmentCartBinding;
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

    public void insertToCart(Cart cart) {
        mCartDBRepository.insertCart(cart);
    }

    public void getOrderedProduct() {

        List<Cart> carts = mCartDBRepository.getCarts();
        for (int i = 0; i < carts.size(); i++) {
            mStoreRepository.fetchProductItemAsync(carts.get(i).getProduct_id());
            mProductLiveData = mStoreRepository.getProductLiveData();

        }

    }

    public LiveData<Product> getLiveDateProduct() {
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
        mContext.startActivity(ProductDetailActivity.newIntent(mContext, productId));
    }

    public void onClickToGoToCart() {
        mContext.startActivity(CartActivity.newIntent(getApplication()));
    }

    public void onClickToBuy(int productId) {
        Cart cart = mCartDBRepository.getCart(productId);
        if (cart == null) {
            insertToCart(new Cart(productId,1));
            Toast.makeText(mContext, "add to cart", Toast.LENGTH_SHORT).show();
        }else {
            int count = cart.getProduct_count() + 1;
            cart.setProduct_count(count);
            mCartDBRepository.updateCart(cart);
            Toast.makeText(mContext, "add to cart", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickToBuyAgain(int productId) {
        onClickToBuy(productId);
        mOrderedProductAdapter.notifyDataSetChanged();
        mFragmentCartBinding.totalPrice.setText(String.valueOf(getTotalPrice()));
    }

    public void onClickToDelete(int productId) {
        if (mCartDBRepository.getCart(productId).getProduct_count() == 1) {
            mCartDBRepository.deleteCart(mCartDBRepository.getCart(productId));
            for (int i = 0; i < mProductList.size(); i++) {
                if (mProductList.get(i).getId() == productId)
                    mProductList.remove(i);
            }
        }
        else {
            Cart updateCart = mCartDBRepository.getCart(productId);
            int count = updateCart.getProduct_count() - 1;
            updateCart.setProduct_count(count);
            mCartDBRepository.updateCart(updateCart);

        }
        if (mProductList.size() == 0){
            mFragmentCartBinding.recyclerCart.setVisibility(View.GONE);
            mFragmentCartBinding.layoutEmptyCart.setVisibility(View.VISIBLE);
        }
        mFragmentCartBinding.totalPrice.setText(String.valueOf(getTotalPrice()));
        mOrderedProductAdapter.notifyDataSetChanged();
    }

    public void setOrderedProductAdapter(OrderedProductAdapter orderedProductAdapter) {
        mOrderedProductAdapter = orderedProductAdapter;
    }

    public OrderedProductAdapter getOrderedProductAdapter() {
        return mOrderedProductAdapter;
    }

    public void setFragmentCartBinding(FragmentCartBinding fragmentCartBinding) {
        mFragmentCartBinding = fragmentCartBinding;
    }

    public Cart getCart(int productId){
        return mCartDBRepository.getCart(productId);
    }

    public int getTotalPrice (){
        int totalPrice = 0;
        for (int i = 0; i < mProductList.size(); i++) {
            int price = Integer.parseInt(mProductList.get(i).getPrice());
            int count = getCart(mProductList.get(i).getId()).getProduct_count();
            totalPrice += (price * count);
        }
        return totalPrice;
    }
}
