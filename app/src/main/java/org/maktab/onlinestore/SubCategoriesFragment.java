package org.maktab.onlinestore;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.adapter.CategoryAdapter;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.ArrayList;
import java.util.List;

public class SubCategoriesFragment extends Fragment {

    public static final String BUNDLE_PARENT_ID = "Bundle_Parent_id";
    private int mParentId;
    private OnlineStoreRepository mRepository;
    private CategoryAdapter mCategoryAdapter;
    private RecyclerView mRecyclerView;
    private List<ProductCategory> mCategoryList;
    private LiveData<List<ProductCategory>> mCategoryItemsLiveData;

    public SubCategoriesFragment() {
        // Required empty public constructor
    }

    public static SubCategoriesFragment newInstance(int id) {
        SubCategoriesFragment fragment = new SubCategoriesFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_PARENT_ID,id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentId = getArguments().getInt(BUNDLE_PARENT_ID);
        mCategoryList = new ArrayList<>();
        mRepository = new OnlineStoreRepository();
        mRepository.fetchSubCategoryItemsAsync(String.valueOf(mParentId));
        mCategoryItemsLiveData = mRepository.getCategoryItemsLiveData();
        setObserver();
    }

    private void setObserver() {
        mCategoryItemsLiveData.observe(this, new Observer<List<ProductCategory>>() {
            @Override
            public void onChanged(List<ProductCategory> categories) {
                if (categories != null) {
                    mCategoryList.addAll(categories);
                    setAdapter(mCategoryList);
                }
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
        View view = inflater.inflate(R.layout.fragment_sub_categories, container, false);
        findView(view);
        initView();
        return  view;
    }

    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_sub_category);
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}