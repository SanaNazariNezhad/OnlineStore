package org.maktab.onlinestore.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.data.remote.retrofit.APIService;
import org.maktab.onlinestore.data.remote.NetworkParams;
import org.maktab.onlinestore.data.remote.retrofit.RetrofitInstanceProduct;
import org.maktab.onlinestore.data.remote.retrofit.RetrofitInstanceCategory;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OnlineStoreRepository {

    private static final String TAG = "PhotoRepository";

    private final APIService mAPIServiceProduct;
    private final APIService mAPIServiceCategory;
    private String mPage;
    private MutableLiveData<List<Product>> mProductItemsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<ProductCategory>> mCategoryItemsLiveData = new MutableLiveData<>();


    public MutableLiveData<List<Product>> getProductItemsLiveData() {
        return mProductItemsLiveData;
    }

    public MutableLiveData<List<ProductCategory>> getCategoryItemsLiveData() {
        return mCategoryItemsLiveData;
    }

    public void setProductItemsLiveData(MutableLiveData<List<Product>> productItemsLiveData) {
        mProductItemsLiveData = productItemsLiveData;
    }

    public String getPage() {
        return mPage;
    }

    public void setPage(String page) {
        mPage = page;
    }

    public OnlineStoreRepository() {
        Retrofit retrofit = RetrofitInstanceProduct.getInstance().getRetrofit();
        mAPIServiceProduct = retrofit.create(APIService.class);

        Retrofit retrofitCategory = RetrofitInstanceCategory.getInstance().getRetrofit();
        mAPIServiceCategory = retrofitCategory.create(APIService.class);
        mPage = "1";
    }

    //this method must run on background thread.
    public List<Product> fetchProductItems(String page) {
        Call<List<Product>> call = mAPIServiceProduct.products(NetworkParams.getProducts(page));
        try {
            Response<List<Product>> response = call.execute();
            return response.body();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
            return null;
        }
    }

    //this method can be run in any thread.
    public void fetchProductItemsAsync(String page) {
        Call<List<Product>> call =
                mAPIServiceProduct.products(NetworkParams.getProducts(page));

        call.enqueue(new Callback<List<Product>>() {

            //this run on main thread
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> items = response.body();

                //update adapter of recyclerview
                mProductItemsLiveData.postValue(items);
            }

            //this run on main thread
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

    public void fetchCategoryItemsAsync(String page) {
        Call<List<ProductCategory>> call =
                mAPIServiceCategory.categories(NetworkParams.getProducts(page));

        call.enqueue(new Callback<List<ProductCategory>>() {

            //this run on main thread
            @Override
            public void onResponse(Call<List<ProductCategory>> call, Response<List<ProductCategory>> response) {
                List<ProductCategory> items = response.body();

                //update adapter of recyclerview
                mCategoryItemsLiveData.postValue(items);
            }

            //this run on main thread
            @Override
            public void onFailure(Call<List<ProductCategory>> call, Throwable t) {
                Log.e(TAG, t.getMessage(), t);
            }
        });
    }

}