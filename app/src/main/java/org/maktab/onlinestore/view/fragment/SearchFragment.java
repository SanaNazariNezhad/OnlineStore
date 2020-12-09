package org.maktab.onlinestore.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.SearchProductAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.FragmentSearchBinding;
import org.maktab.onlinestore.view.BottomSheetFilter;
import org.maktab.onlinestore.view.BottomSheetSort;
import org.maktab.onlinestore.viewmodel.SearchViewModel;

import java.util.List;

public class SearchFragment extends Fragment {

    public static final String SEARCH_QUERY = "search_query";
    public static final String EXTRA_SORT_ID = "extra_sort_id";
    public static final int REQUEST_CODE_Filter = 0;
    public static final String TAG_BOTTOM_SHEET_FILTER = "tag_Bottom_sheet_filter";
    public static final int REQUEST_CODE_SORT = 1;
    public static final String TAG_BOTTOM_SHEET_SORT = "tag_bottom_sheet_sort";
    private SearchViewModel mSearchViewModel;
    private SearchProductAdapter mSearchProductAdapter;
    private String mQuery;
    private FragmentSearchBinding mFragmentSearchBinding;
    private LiveData<List<Product>> mLiveDataSearchProducts;
    private LiveData<List<Product>> mLiveDataSortedLowToHighSearchProducts;
    private LiveData<List<Product>> mLiveDataSortedHighToLowSearchProducts;
    private LiveData<List<Product>> mLiveDataTopSellersSearchProducts;

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

        mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        mSearchViewModel.fetchSearchItemsAsync(mQuery);
        mSearchViewModel.setQueryInPreferences(mQuery);
        mLiveDataSearchProducts = mSearchViewModel.getSearchItemsLiveData();
        mLiveDataSortedLowToHighSearchProducts = mSearchViewModel.getSortedLowToHighSearchItemsLiveData();
        mLiveDataSortedHighToLowSearchProducts = mSearchViewModel.getSortedHighToLowSearchItemsLiveData();
        mLiveDataTopSellersSearchProducts = mSearchViewModel.getSortedTopSellersSearchItemsLiveData();
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
                BottomSheetFilter bottomSheetFilter =
                        new BottomSheetFilter();
                bottomSheetFilter.setTargetFragment(
                        SearchFragment.this,
                        REQUEST_CODE_Filter);

                bottomSheetFilter.show(
                        getActivity().getSupportFragmentManager(),
                        TAG_BOTTOM_SHEET_FILTER);
//                BottomSheetFilter bottomSheetFilter = new BottomSheetFilter();
//                bottomSheetFilter.show(getActivity().getSupportFragmentManager(), bottomSheetFilter.getTag());
                return true;
            case R.id.sort_id:
                BottomSheetSort bottomSheetSort =
                        new BottomSheetSort();
                bottomSheetSort.setTargetFragment(
                        SearchFragment.this,
                        REQUEST_CODE_SORT);

                bottomSheetSort.show(
                        getActivity().getSupportFragmentManager(),
                        TAG_BOTTOM_SHEET_SORT);
//                BottomSheetSort bottomSheetSort = new BottomSheetSort();
//                bottomSheetSort.show(getActivity().getSupportFragmentManager(), bottomSheetSort.getTag());
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_Filter) {
            Toast.makeText(getContext(),"filteeeeeeeer",Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_SORT) {
            Toast.makeText(getContext(),"SorrrrrrrT",Toast.LENGTH_SHORT).show();
            checkSort(data.getIntExtra(EXTRA_SORT_ID,4));
        }
    }

    private void checkSort(int intExtra) {
        if (intExtra == BottomSheetSort.TOP_SELLERS){

            Toast.makeText(getContext(),"Sort Top Seller",Toast.LENGTH_SHORT).show();

        }else if (intExtra == BottomSheetSort.PRICES_LOW_TO_HIGH){

            mSearchViewModel.fetchSortedLowToHighSearchItemsAsync(mQuery);
            mSearchViewModel.setQueryInPreferences(mQuery);

        }else if (intExtra == BottomSheetSort.PRICES_HIGH_TO_LOW){

            mSearchViewModel.fetchSortedHighToLowSearchItemsAsync(mQuery);
            mSearchViewModel.setQueryInPreferences(mQuery);

        }else if (intExtra == BottomSheetSort.THE_NEWEST){

            mSearchViewModel.fetchSearchItemsAsync(mQuery);
            mSearchViewModel.setQueryInPreferences(mQuery);
        }
    }

    private void setSearchViewListeners(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchViewModel.fetchSearchItemsAsync(query);
                mSearchViewModel.setQueryInPreferences(query);
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
                String query = mSearchViewModel.getQueryFromPreferences();
                if (query != null)
                    searchView.setQuery(query, false);
            }
        });
    }

    private void observers() {
        mLiveDataSearchProducts.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mSearchViewModel.setSearchProduct(productList);
                Toast.makeText(getContext(),"Sort Newest" + productList.size() ,Toast.LENGTH_SHORT).show();
                setSearchAdapter();
            }
        });

        mLiveDataSortedLowToHighSearchProducts.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mSearchViewModel.setSearchProduct(productList);
                Toast.makeText(getContext(),"Sort Low to High" + productList.size(),Toast.LENGTH_SHORT).show();
                setSearchAdapter();
            }
        });

        mLiveDataSortedHighToLowSearchProducts.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mSearchViewModel.setSearchProduct(productList);
                Toast.makeText(getContext(),"Sort High to Low" + productList.size(),Toast.LENGTH_SHORT).show();
                setSearchAdapter();
            }
        });

        mLiveDataTopSellersSearchProducts.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mSearchViewModel.setSearchProduct(productList);
                setSearchAdapter();
            }
        });
    }

    private void setSearchAdapter() {
        mSearchProductAdapter = new SearchProductAdapter(this,getActivity(), mSearchViewModel);
        mFragmentSearchBinding.recyclerSearch.setAdapter(mSearchProductAdapter);
    }

    private void initView() {
        mFragmentSearchBinding.recyclerSearch.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}