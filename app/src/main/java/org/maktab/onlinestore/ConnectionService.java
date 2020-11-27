package org.maktab.onlinestore;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import org.maktab.onlinestore.controller.activity.HomeActivity;

public class ConnectionService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mHandler.post(periodicUpdate);
        return START_STICKY;
    }

    public boolean isOnline(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }

    Handler mHandler = new Handler();

    private Runnable periodicUpdate = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(periodicUpdate,1*1000 - SystemClock.elapsedRealtime()%1000);
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(HomeActivity.checkConnection);
            broadcastIntent.putExtra("online_status" , "" + isOnline(ConnectionService.this));
            sendBroadcast(broadcastIntent);
        }
    };
}
