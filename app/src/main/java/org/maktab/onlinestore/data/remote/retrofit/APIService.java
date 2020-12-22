package org.maktab.onlinestore.data.remote.retrofit;

import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.data.model.SalesReport;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface APIService {

    @GET("products")
    Call<List<Product>> products(@QueryMap Map<String, String> options);

    @GET("products/categories")
    Call<List<ProductCategory>> categories(@QueryMap Map<String, String> options);

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int id,@QueryMap Map<String, String> options);

    @GET("reports/sales/top_sellers")
    Call<List<Product>> topSellers(@QueryMap Map<String, String> options);

    @GET("reports/sales")
    Call<SalesReport> sales(@QueryMap Map<String, String> options);


}
