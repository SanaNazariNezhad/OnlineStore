package org.maktab.onlinestore.view.activity;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.receiver.ConnectionReceiver;
import org.maktab.onlinestore.viewmodel.SplashViewModel;


public abstract class SingleFragmentActivity extends AppCompatActivity {

    public ViewDataBinding mBinding;
    private SplashViewModel mSplashViewModel;
    private ConnectionReceiver mConnectionReceiver;

    public abstract Fragment createFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_fragment);

        mConnectionReceiver = new ConnectionReceiver();
        mSplashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        mSplashViewModel.setInConnectionActivity(false);

        FragmentManager fragmentManager = getSupportFragmentManager();

        //check if fragment exists in container (configuration changes save the fragments)
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);

        //create an add fragment transaction for CrimeDetailFragment
        {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, createFragment())
                    .commit();
        }
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
