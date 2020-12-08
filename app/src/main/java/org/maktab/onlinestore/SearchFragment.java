package org.maktab.onlinestore;

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

import org.maktab.onlinestore.adapter.MostVisitedProductAdapter;
import org.maktab.onlinestore.adapter.SearchProductAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.FragmentSearchBinding;
import org.maktab.onlinestore.viewmodel.ProductViewModel;

import java.util.List;

public class SearchFragment extends Fragment {

    public static final String SEARCH_QUERY = "search_query";
    private ProductViewModel mProductViewModel;
    private SearchProductAdapter mSearchProductAdapter;
    private String mQuery;
    private FragmentSearchBinding mFragmentSearchBinding;
    private LiveData<List<Product>> mLiveDataSearchProducts;

    public SearchFragment() {
        // Required empty public constructor
    }
    public static SearchFragment newInstance(String query) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(SEARCH_QUERY,query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mQuery = getArguments().getString(SEARCH_QUERY);
        }

        mProductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.fetchSearchItemsAsync(mQuery);
        mProductViewModel.setQueryInPreferences(mQuery);
        mLiveDataSearchProducts = mProductViewModel.getSearchItemsLiveData();
        observers();
    }

    private void observers() {
        mLiveDataSearchProducts.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mProductViewModel.setSearchProduct(productList);
                setSearchAdapter();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentSearchBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_search,
                container,
                false);

        initView();

        return mFragmentSearchBinding.getRoot();
    }

    private void setSearchAdapter() {
        mSearchProductAdapter = new SearchProductAdapter(this,getActivity(),mProductViewModel);
        mFragmentSearchBinding.recyclerSearch.setAdapter(mSearchProductAdapter);
    }

    private void initView() {
        mFragmentSearchBinding.recyclerSearch.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}