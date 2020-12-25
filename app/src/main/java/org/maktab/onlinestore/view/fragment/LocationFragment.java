package org.maktab.onlinestore.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.AddressAdapter;
import org.maktab.onlinestore.adapter.ProductAdapter;
import org.maktab.onlinestore.databinding.FragmentLocationBinding;
import org.maktab.onlinestore.viewmodel.SettingViewModel;

public class LocationFragment extends Fragment {
    private FragmentLocationBinding mLocationBinding;
    private SettingViewModel mSettingViewModel;
    private AddressAdapter mAddressAdapter;

    public LocationFragment() {
        // Required empty public constructor
    }
    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
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

        mLocationBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_location,
                container,
                false);

        mLocationBinding.setSettingViewModel(mSettingViewModel);
        mSettingViewModel.setContext(getActivity());
        if (mSettingViewModel.getAddresses().size() != 0)
            setProductAdapter();

        initView();
        return mLocationBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setProductAdapter();
    }

    @Override
    public void onPause() {
        super.onPause();
        setProductAdapter();
    }

    private void setProductAdapter() {
        mAddressAdapter = new AddressAdapter(this,getActivity(),mSettingViewModel);
        mLocationBinding.recyclerLocation.setAdapter(mAddressAdapter);
    }

    private void initView() {
        mLocationBinding.recyclerLocation
                .setLayoutManager(new LinearLayoutManager(getContext()));
    }
}