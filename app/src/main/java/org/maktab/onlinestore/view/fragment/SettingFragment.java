package org.maktab.onlinestore.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.databinding.FragmentSearchBinding;
import org.maktab.onlinestore.databinding.FragmentSettingBinding;
import org.maktab.onlinestore.viewmodel.ProductViewModel;
import org.maktab.onlinestore.viewmodel.SearchViewModel;
import org.maktab.onlinestore.viewmodel.SettingViewModel;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding mSettingBinding;
    private SettingViewModel mSettingViewModel;

    public SettingFragment() {
        // Required empty public constructor
    }
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
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
        mSettingBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_setting,
                container,
                false);

        initView();
//        listeners();
        return mSettingBinding.getRoot();
    }

    private void listeners() {
        mSettingBinding.imageViewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSettingViewModel.onClickLocationItem();
            }
        });
    }

    private void initView() {
        mSettingViewModel.setContext(getActivity());
        mSettingBinding.setSettingViewModel(mSettingViewModel);
    }
}