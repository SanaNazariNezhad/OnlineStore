package org.maktab.onlinestore.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.SplashDBRepository;

import java.util.List;

public class SplashViewModel extends AndroidViewModel {

    private SplashDBRepository mRepositorySplash;


    public SplashViewModel(@NonNull Application application) {
        super(application);
        mRepositorySplash = SplashDBRepository.getInstance(application);

    }

    public List<Product> getMostVisitedProduct() {
        return mRepositorySplash.getMostVisitedProduct();
    }

    public void setMostVisitedProduct(List<Product> mostVisitedProduct) {
       mRepositorySplash.setMostVisitedProduct(mostVisitedProduct);
    }

    public List<Product> getLatestProduct() {
        return mRepositorySplash.getLatestProduct();
    }

    public void setLatestProduct(List<Product> latestProduct) {
        mRepositorySplash.setLatestProduct(latestProduct);
    }

    public List<Product> getHighestScoreProduct() {
        return mRepositorySplash.getHighestScoreProduct();
    }

    public void setHighestScoreProduct(List<Product> highestScoreProduct) {
        mRepositorySplash.setHighestScoreProduct(highestScoreProduct);
    }

    public List<Product> getSpecialProduct() {
        return mRepositorySplash.getSpecialProduct();
    }

    public void setSpecialProduct(List<Product> specialProduct) {
        mRepositorySplash.setSpecialProduct(specialProduct);
    }

    public boolean isWiFiEnable() {
        return mRepositorySplash.isWiFiEnable();
    }

    public void setWiFiEnable(boolean wiFiEnable) {
        mRepositorySplash.setWiFiEnable(wiFiEnable);
    }

    public void setInConnectionActivity(boolean inConnectionActivity) {
        mRepositorySplash.setInConnectionActivity(inConnectionActivity);
    }
}
