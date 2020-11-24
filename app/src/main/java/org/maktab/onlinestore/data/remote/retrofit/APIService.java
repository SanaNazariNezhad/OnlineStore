package org.maktab.onlinestore.data.remote.retrofit;

import org.maktab.onlinestore.data.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface APIService {

    @GET("products")
    Call<List<Product>> listItems(@QueryMap Map<String, String> options);
}
