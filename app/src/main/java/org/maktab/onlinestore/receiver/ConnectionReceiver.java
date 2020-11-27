package org.maktab.onlinestore.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import org.maktab.onlinestore.DisconnectActivity;
import org.maktab.onlinestore.controller.activity.HomeActivity;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;


public class ConnectionReceiver extends BroadcastReceiver {
    private OnlineStoreRepository mRepository;

    @Override
    public void onReceive(Context context, Intent intent) {

        int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
        mRepository = OnlineStoreRepository.getInstance();

        if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
            Intent intentActivity = DisconnectActivity.newIntent(context);
            context.startActivity(intentActivity);
//            mRepository.setConnection(false);

        }
        else if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
            mRepository.setConnection(true);
        }
    }
}