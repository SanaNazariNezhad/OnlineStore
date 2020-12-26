package org.maktab.onlinestore.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.databinding.FragmentSettingsBinding;
import org.maktab.onlinestore.viewmodel.SettingViewModel;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding mSettingsBinding;
    private SettingViewModel mSettingViewModel;

    public SettingsFragment() {
        // Required empty public constructor
    }
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
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
        mSettingsBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_settings,
                container,
                false);

        initView();
        return mSettingsBinding.getRoot();
    }

    private void initView() {
        mSettingViewModel.setContext(getActivity());
        mSettingsBinding.setSettingViewModel(mSettingViewModel);
    }
}