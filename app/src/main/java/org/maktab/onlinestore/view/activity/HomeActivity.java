package org.maktab.onlinestore.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.databinding.ActivityHomeBinding;
import org.maktab.onlinestore.receiver.ConnectionReceiver;
import org.maktab.onlinestore.viewmodel.SplashViewModel;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding mBinding;
    private ConnectionReceiver mConnectionReceiver;
    private SplashViewModel mSplashViewModel;

    private AppBarConfiguration mAppBarConfiguration;

    public static Intent newIntent (Context context){
        return new Intent(context,HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mConnectionReceiver = new ConnectionReceiver();
        mSplashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        mSplashViewModel.setInConnectionActivity(false);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CartActivity.newIntent(getApplication()));
            }
        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        setNavigationView();
    }

    private void setNavigationView() {
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_category,R.id.nav_setting)
                .setDrawerLayout(mBinding.drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mBinding.navView, navController);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(mConnectionReceiver, filter);

    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(mConnectionReceiver);
    }
}