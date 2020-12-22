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
import org.maktab.onlinestore.adapter.ProductAdapter;
import org.maktab.onlinestore.adapter.SubCategoryAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.databinding.FragmentSubCategoriesBinding;
import org.maktab.onlinestore.view.activity.SearchActivity;
import org.maktab.onlinestore.viewmodel.CategoryViewModel;

import java.util.List;

public class SubCategoriesFragment extends VisibleFragment {

    public static final String BUNDLE_PARENT_ID = "Bundle_Parent_id";
    private int mParentId;
    private FragmentSubCategoriesBinding mSubCategoriesBinding;
    private SubCategoryAdapter mCategoryAdapter;
    private ProductAdapter mProductAdapter;
    private CategoryViewModel mCategoryViewModel;
    private LiveData<List<ProductCategory>> mCategoryItemsLiveData;
    private LiveData<List<Product>> mProductsLiveData;

    public SubCategoriesFragment() {
        // Required empty public constructor
    }

    public static SubCategoriesFragment newInstance(int id) {
        SubCategoriesFragment fragment = new SubCategoriesFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_PARENT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentId = getArguments().getInt(BUNDLE_PARENT_ID);
        setHasOptionsMenu(true);
        getSubCategoryFromCategoryViewModel();
        setObserver();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.home, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.menu_item_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        setSearchViewListeners(searchView);
    }

    private void setSearchViewListeners(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startActivity(SearchActivity.newIntent(getActivity(),query,"category"));
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
                String query = mCategoryViewModel.getQueryFromPreferences();
                if (query != null)
                    searchView.setQuery(query, false);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mSubCategoriesBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sub_categories,
                container,
                false);

        initView();
        return mSubCategoriesBinding.getRoot();
    }

    private void getSubCategoryFromCategoryViewModel() {
        mCategoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        mCategoryViewModel.fetchSubCategoryItems(String.valueOf(mParentId));
        mCategoryItemsLiveData = mCategoryViewModel.getLiveDataCategoryItems();
    }

    private void setObserver() {
        mCategoryItemsLiveData.observe(this, new Observer<List<ProductCategory>>() {
            @Override
            public void onChanged(List<ProductCategory> categories) {
                if (categories.size() != 0) {
                    mCategoryViewModel.setCategoryList(categories);
                    setSubCategoryAdapter();
                } else {
                    mCategoryViewModel.fetchProductItemsWithParentId(String.valueOf(mParentId));
                    mProductsLiveData = mCategoryViewModel.getLiveDataProductWithParentId();
                    setObserverForProduct();
                }
            }
        });
    }

    private void setSubCategoryAdapter() {
        mCategoryAdapter = new SubCategoryAdapter(this,getActivity(),mCategoryViewModel);
        mSubCategoriesBinding.recyclerSubCategory.setAdapter(mCategoryAdapter);
    }

    private void setObserverForProduct() {
        mProductsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mCategoryViewModel.setProductList(productList);
                setProductAdapter();

            }
        });
    }

    private void setProductAdapter() {
        mProductAdapter = new ProductAdapter(this,getActivity(),mCategoryViewModel);
        mSubCategoriesBinding.recyclerSubCategory.setAdapter(mProductAdapter);
    }

    private void initView() {
        mSubCategoriesBinding.recyclerSubCategory
                .setLayoutManager(new LinearLayoutManager(getContext()));
    }
}