package org.maktab.onlinestore.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.SearchProductAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.FragmentSearchBinding;
import org.maktab.onlinestore.view.BottomSheet;
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
        setHasOptionsMenu(true);

        mProductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.fetchSearchItemsAsync(mQuery);
        mProductViewModel.setQueryInPreferences(mQuery);
        mLiveDataSearchProducts = mProductViewModel.getSearchItemsLiveData();
        observers();
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.search, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        setSearchViewListeners(searchView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_id:
                BottomSheet bottomSheet = new BottomSheet();
                bottomSheet.show(getActivity().getSupportFragmentManager(),bottomSheet.getTag());
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    private void setSearchViewListeners(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProductViewModel.fetchSearchItemsAsync(query);
                mProductViewModel.setQueryInPreferences(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mProductViewModel.getQueryFromPreferences();
                if (query != null)
                    searchView.setQuery(query, false);
            }
        });
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

    private void setSearchAdapter() {
        mSearchProductAdapter = new SearchProductAdapter(this,getActivity(),mProductViewModel);
        mFragmentSearchBinding.recyclerSearch.setAdapter(mSearchProductAdapter);
    }

    private void initView() {
        mFragmentSearchBinding.recyclerSearch.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}