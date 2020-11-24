package org.maktab.onlinestore.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.remote.retrofit.APIService;
import org.maktab.onlinestore.data.remote.NetworkParams;
import org.maktab.onlinestore.data.remote.retrofit.RetrofitInstance;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OnlineStoreRepository {

    private static final String TAG = "PhotoRepository";

    private final APIService mAPIService;
    private String mPage;
    private MutableLiveData<List<Product>> mProductItemsLiveData = new MutableLiveData<>();


    public MutableLiveData<List<Product>> getProductItemsLiveData() {
        return mProductItemsLiveData;
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
        Retrofit retrofit = RetrofitInstance.getInstance().getRetrofit();
        mAPIService = retrofit.create(APIService.class);
        mPage = "1";
    }

    //this method must run on background thread.
    public List<Product> fetchProductItems(String page) {
        Call<List<Product>> call = mAPIService.listItems(NetworkParams.getProducts(page));
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
                mAPIService.listItems(NetworkParams.getProducts(page));

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

}