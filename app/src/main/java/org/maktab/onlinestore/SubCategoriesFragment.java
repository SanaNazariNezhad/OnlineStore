package org.maktab.onlinestore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.maktab.onlinestore.adapter.CategoryAdapter;
import org.maktab.onlinestore.adapter.ProductAdapter;
import org.maktab.onlinestore.adapter.SubCategoryAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriesFragment extends Fragment {

    public static final String BUNDLE_PARENT_ID = "Bundle_Parent_id";
    public static final String BUNDLE_PARENT_NAME = "bundle_parent_name";
    private int mParentId;
    private OnlineStoreRepository mRepository;
    private SubCategoryAdapter mCategoryAdapter;
    private ProductAdapter mProductAdapter;
    private RecyclerView mRecyclerView;
    private List<ProductCategory> mSubCategoryList;
    private LiveData<List<ProductCategory>> mCategoryItemsLiveData;
    private LiveData<List<Product>> mProductsLiveData;
    private String mParentName;
    public static final String checkConnection = "Check Connection";
    private IntentFilter mIntentFilter;
    private RelativeLayout mRelativeLayout;

    public SubCategoriesFragment() {
        // Required empty public constructor
    }

    public static SubCategoriesFragment newInstance(int id, String parentName) {
        SubCategoriesFragment fragment = new SubCategoriesFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_PARENT_ID, id);
        args.putString(BUNDLE_PARENT_NAME, parentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentId = getArguments().getInt(BUNDLE_PARENT_ID);
        mParentName = getArguments().getString(BUNDLE_PARENT_NAME);
        mSubCategoryList = new ArrayList<>();
        mRepository = new OnlineStoreRepository();
        mRepository.fetchSubCategoryItemsAsync(String.valueOf(mParentId));
        mCategoryItemsLiveData = mRepository.getCategoryItemsLiveData();
        setObserver();
    }

    private void checkInternet() {
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(checkConnection);
        Intent serviceIntent = new Intent(getActivity(), ConnectionService.class);
        getActivity().startService(serviceIntent);

        if (isOnline(getActivity().getApplicationContext())) {
            Set_Visibility_ON();
        } else {
            Set_Visibility_OFF();
        }
    }

    public BroadcastReceiver MyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(checkConnection)) {
                if (intent.getStringExtra("online_status").equals("true")) {
                    Set_Visibility_ON();
                } else {
                    Set_Visibility_OFF();
                }
            }
        }
    };

    public boolean isOnline(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }

    public void Set_Visibility_ON() {
        mRelativeLayout.setVisibility(View.GONE);
    }

    public void Set_Visibility_OFF() {
        mRelativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(MyReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(MyReceiver, mIntentFilter);
    }

    private void setObserver() {
        mCategoryItemsLiveData.observe(this, new Observer<List<ProductCategory>>() {
            @Override
            public void onChanged(List<ProductCategory> categories) {
                if (categories.size() != 0) {
//                    mSubCategoryList.addAll(categories);
                    setSubCategoryAdapter(categories);
                } else {
                    mRepository.fetchProductItemsWithParentIdAsync(String.valueOf(mParentName));
                    mProductsLiveData = mRepository.getProductWithParentIdLiveData();
                    setObserverForProduct();
                }
            }
        });
    }

    private void setObserverForProduct() {
        mProductsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {

                setProductAdapter(productList);

            }
        });
    }

    private void setSubCategoryAdapter(List<ProductCategory> categories) {
        mCategoryAdapter = new SubCategoryAdapter(getActivity(), categories);
        mRecyclerView.setAdapter(mCategoryAdapter);
    }

    private void setProductAdapter(List<Product> productList) {
        mProductAdapter = new ProductAdapter(productList, getActivity());
        mRecyclerView.setAdapter(mProductAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_categories, container, false);
        findView(view);
        checkInternet();
        initView();
        return view;
    }

    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_sub_category);
        mRelativeLayout = view.findViewById(R.id.disconnect_layout_sub_category);
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}