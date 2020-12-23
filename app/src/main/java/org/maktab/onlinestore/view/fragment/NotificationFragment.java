package org.maktab.onlinestore.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.databinding.FragmentNotificationBinding;
import org.maktab.onlinestore.viewmodel.ProductViewModel;
import org.maktab.onlinestore.viewmodel.SettingViewModel;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding mNotificationBinding;
    private SettingViewModel mSettingViewModel;


    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingViewModel = new ViewModelProvider(this).get(SettingViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mNotificationBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_notification,
                container,
                false);


        mSettingViewModel.setNotificationBinding(mNotificationBinding);
        mNotificationBinding.setSettingViewModel(mSettingViewModel);
        initView();
        return mNotificationBinding.getRoot();
    }

    private void initView() {
        if (mSettingViewModel.isTaskScheduled()) {
            mNotificationBinding.switchNotification.setText(R.string.on);
            mNotificationBinding.switchNotification.setChecked(true);
            mSettingViewModel.togglePolling();
        } else {
            mNotificationBinding.switchNotification.setText(R.string.off);
            mNotificationBinding.switchNotification.setChecked(false);
        }
    }

}