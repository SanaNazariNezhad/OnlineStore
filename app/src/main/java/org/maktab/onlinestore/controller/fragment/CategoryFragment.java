package org.maktab.onlinestore.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.CategoryAdapter;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    private OnlineStoreRepository mRepository;
    private CategoryAdapter mCategoryAdapter;
    private RecyclerView mRecyclerView;
    private List<ProductCategory> mCategoryList;
    private LiveData<List<ProductCategory>> mCategoryItemsLiveData;

    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryList = new ArrayList<>();
        mRepository = OnlineStoreRepository.getInstance();
        mRepository.fetchCategoryItemsAsync();
//        mRepository.fetchCategoryItemsAsync("2");
        mCategoryItemsLiveData = mRepository.getCategoryItemsLiveData();
        setObserver();
    }

    private void setObserver() {
        mCategoryItemsLiveData.observe(this, new Observer<List<ProductCategory>>() {
            @Override
            public void onChanged(List<ProductCategory> categories) {
                mCategoryList.addAll(categories);
                setAdapter(mCategoryList);
            }
        });
    }

    private void setAdapter(List<ProductCategory> categories) {
        mCategoryAdapter = new CategoryAdapter(getActivity(), categories);
        mRecyclerView.setAdapter(mCategoryAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        findView(view);
        initView();
        return view;
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_category);
    }
}