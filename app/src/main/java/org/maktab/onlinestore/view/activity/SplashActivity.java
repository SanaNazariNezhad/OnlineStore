package org.maktab.onlinestore.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding mSplashBinding;

    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashBinding = DataBindingUtil.setContentView(this,R.layout.activity_splash);

        getSupportActionBar().hide();

        LottieAnimationView lottieAnimationView = findViewById(R.id.lottie_splash_screen);
        LottieAnimationView lottieAnimationProgressBar= findViewById(R.id.lottie_progressBar);
        //request from server using retrofit. [play animation]
        lottieAnimationView.playAnimation();
        lottieAnimationProgressBar.playAnimation();


        //get the result in observer livedata.[go to home activity and finish this one]
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isOnline()) {
//                    mSplashBinding.progressBar.setVisibility(View.GONE);
                    Snackbar snackbar = Snackbar
                            .make(mSplashBinding.splashLayout,"No Internet Access",Snackbar.LENGTH_INDEFINITE)
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    recreate();
                                }
                            });
                    View view = snackbar.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
                    params.gravity = Gravity.TOP;
                    view.setLayoutParams(params);
                    snackbar.show();
                } else {
                    startActivity(HomeActivity.newIntent(SplashActivity.this));
                    finish();
                }
            }
        }, 10000);
    }

    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}