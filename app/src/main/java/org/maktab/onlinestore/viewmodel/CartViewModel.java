package org.maktab.onlinestore.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.maktab.onlinestore.adapter.OrderedProductAdapter;
import org.maktab.onlinestore.data.model.BillingAddress;
import org.maktab.onlinestore.data.model.Cart;
import org.maktab.onlinestore.data.model.Comment;
import org.maktab.onlinestore.data.model.Coupons;
import org.maktab.onlinestore.data.model.Customer;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.ShippingAddress;
import org.maktab.onlinestore.data.repository.CartDBRepository;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.databinding.FragmentCartBinding;
import org.maktab.onlinestore.databinding.FragmentEditCommentBinding;
import org.maktab.onlinestore.view.activity.BuyActivity;
import org.maktab.onlinestore.view.activity.CartActivity;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class CartViewModel extends AndroidViewModel {

    private CartDBRepository mCartDBRepository;
    private OnlineStoreRepository mStoreRepository;
    private LiveData<Customer> mCustomerLiveData;
    private List<Product> mProductList;
    private OrderedProductAdapter mOrderedProductAdapter;
    private FragmentCartBinding mFragmentCartBinding;
    private FragmentEditCommentBinding mEditCommentBinding;
    private Context mContext;
    private MutableLiveData<Integer> mLiveDataRate = new MutableLiveData<>();


    public void setEditCommentBinding(FragmentEditCommentBinding editCommentBinding) {
        mEditCommentBinding = editCommentBinding;
    }

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
        }

    }

    public LiveData<Product> getLiveDateProduct() {
        return mStoreRepository.getProductLiveData();
    }

    public LiveData<Customer> getLiveDateCustomer() {
        return mStoreRepository.getCustomerLiveData();
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
        }else {
            int count = cart.getProduct_count() + 1;
            cart.setProduct_count(count);
            mCartDBRepository.updateCart(cart);
        }
        Toast.makeText(mContext, "add to cart", Toast.LENGTH_SHORT).show();

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

    public void onClickContinueBuy(){
        if (mProductList.size()==0){
            Toast.makeText(mContext,"Your cart is Empty!",Toast.LENGTH_SHORT).show();
        }else {
            mContext.startActivity(BuyActivity.newIntent(mContext));
        }
    }

    public void onclickBuy () {
        BillingAddress billingAddresses = new BillingAddress("sana","nazari","maktab",
                "rodaki","andishe","kermanshah","west","6714844718",
                "iran","sana.n@gmail.com","9187287311");

        ShippingAddress shippingAddresses = new ShippingAddress("sana","nazari","maktab",
                "rodaki","andishe","kermanshah","west","6714844718",
                "iran");

        Customer customer = new Customer("Sana.n@gmail.com","sana",
                "nazari","sana.nazari",
                billingAddresses,shippingAddresses);

        mStoreRepository.postCreateCustomerAsync(customer);
    }



    public void onClickAddComment(Comment comment){
        mStoreRepository.postCommentAsync(comment);
    }

    public void fetchOneComment(int commentId){
        mStoreRepository.fetchOneCommentAsync(commentId);
    }

    public void fetchPutComment(Comment comment){
        mStoreRepository.fetchPUTCommentAsync(comment);
    }

    public void fetchDeleteComment(int commentId){
        mStoreRepository.fetchDeleteCommentAsync(commentId);
    }

    public void onClickAddRate(int rate){
        mLiveDataRate.setValue(rate);
    }

    public void onClickEditComment(){
        mEditCommentBinding.name.setEnabled(true);
        mEditCommentBinding.email.setEnabled(true);
        mEditCommentBinding.comment.setEnabled(true);
        mEditCommentBinding.star1Edit.setEnabled(true);
        mEditCommentBinding.star2Edit.setEnabled(true);
        mEditCommentBinding.star3Edit.setEnabled(true);
        mEditCommentBinding.star4Edit.setEnabled(true);
        mEditCommentBinding.star5Edit.setEnabled(true);
    }

    public MutableLiveData<Integer> getLiveDataRate() {
        return mLiveDataRate;
    }

    public MutableLiveData<Comment> getLiveDataOneComment() {
        return mStoreRepository.getLiveDataOneComment();
    }

    public void fetchCoupons() {
        mStoreRepository.fetchCouponsAsync();
    }

    public LiveData<List<Coupons>> getLiveDataCoupons() {
        return mStoreRepository.getLiveDataCoupons();
    }
}
