package org.maktab.onlinestore.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.CategoryAdapter;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.databinding.FragmentCategoryBinding;
import org.maktab.onlinestore.viewmodel.CategoryViewModel;

import java.util.List;

public class CategoryFragment extends Fragment {

    private CategoryAdapter mCategoryAdapter;
    private CategoryViewModel mCategoryViewModel;
    private FragmentCategoryBinding mCategoryBinding;
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

        getCategoryFromCategoryViewModel();
        setObserver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mCategoryBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_category,
                container,
                false);
        initView();
        return mCategoryBinding.getRoot();
    }

    private void getCategoryFromCategoryViewModel() {
        mCategoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        mCategoryViewModel.getCategoryItems();
        mCategoryItemsLiveData = mCategoryViewModel.getLiveDataCategoryItems();
    }

    private void setObserver() {
        mCategoryItemsLiveData.observe(this, new Observer<List<ProductCategory>>() {
            @Override
            public void onChanged(List<ProductCategory> categories) {
                setAdapter(categories);
            }
        });
    }

    private void setAdapter(List<ProductCategory> categories) {
        mCategoryAdapter = new CategoryAdapter(getActivity(), categories);
        mCategoryBinding.recyclerCategory.setAdapter(mCategoryAdapter);
    }

    private void initView() {
        mCategoryBinding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}