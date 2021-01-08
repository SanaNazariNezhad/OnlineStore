package org.maktab.onlinestore.data.repository;

import android.content.Context;

import org.maktab.onlinestore.data.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SplashDBRepository {

    private static SplashDBRepository sInstance;

    private Context mContext;
    private List<Product> mMostVisitedProduct;
    private List<Product> mLatestProduct;
    private List<Product> mHighestScoreProduct;
    private List<Product> mSpecialProduct;

    public static SplashDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new SplashDBRepository(context);

        return sInstance;
    }

    private SplashDBRepository(Context context) {
        mContext = context.getApplicationContext();
        mMostVisitedProduct = new ArrayList<>();
        mLatestProduct = new ArrayList<>();
        mHighestScoreProduct = new ArrayList<>();
        mSpecialProduct = new ArrayList<>();
    }

    public List<Product> getMostVisitedProduct() {
        return mMostVisitedProduct;
    }

    public void setMostVisitedProduct(List<Product> mostVisitedProduct) {
        mMostVisitedProduct = mostVisitedProduct;
    }

    public List<Product> getLatestProduct() {
        return mLatestProduct;
    }

    public void setLatestProduct(List<Product> latestProduct) {
        mLatestProduct = latestProduct;
    }

    public List<Product> getHighestScoreProduct() {
        return mHighestScoreProduct;
    }

    public void setHighestScoreProduct(List<Product> highestScoreProduct) {
        mHighestScoreProduct = highestScoreProduct;
    }

    public List<Product> getSpecialProduct() {
        return mSpecialProduct;
    }

    public void setSpecialProduct(List<Product> specialProduct) {
        mSpecialProduct = specialProduct;
    }
}
