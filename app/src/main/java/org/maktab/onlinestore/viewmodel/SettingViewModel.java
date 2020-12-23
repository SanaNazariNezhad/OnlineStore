package org.maktab.onlinestore.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.databinding.FragmentNotificationBinding;
import org.maktab.onlinestore.utilities.QueryPreferences;
import org.maktab.onlinestore.view.activity.LocationActivity;
import org.maktab.onlinestore.view.activity.NotificationActivity;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;
import org.maktab.onlinestore.worker.PollWorker;

import java.util.List;

public class SettingViewModel extends AndroidViewModel {

    private OnlineStoreRepository mRepository;
    private Context mContext;
    private FragmentNotificationBinding mNotificationBinding;

    public void setNotificationBinding(FragmentNotificationBinding notificationBinding) {
        mNotificationBinding = notificationBinding;
    }

    public SettingViewModel(@NonNull Application application) {
        super(application);
        mRepository = new OnlineStoreRepository();

    }

    public void setContext(Context context) {
        mContext = context;
    }

    public void onClickNotificationItem() {
        mContext.startActivity(NotificationActivity.newIntent(mContext));
    }

    public void onClickLocationItem() {
        mContext.startActivity(LocationActivity.newIntent(mContext));
    }

    public void togglePolling() {
        boolean isOn = PollWorker.isWorkEnqueued(getApplication());
        PollWorker.enqueueWork(getApplication(), !isOn);
    }

    public boolean isTaskScheduled() {
        return PollWorker.isWorkEnqueued(getApplication());
    }

    public void onClickNotificationSwitch() {
        togglePolling();
        if (isTaskScheduled()) {
            mNotificationBinding.switchNotification.setText(R.string.on);
            mNotificationBinding.switchNotification.setChecked(true);
        } else {
            mNotificationBinding.switchNotification.setText(R.string.off);
            mNotificationBinding.switchNotification.setChecked(false);
        }
    }

}
