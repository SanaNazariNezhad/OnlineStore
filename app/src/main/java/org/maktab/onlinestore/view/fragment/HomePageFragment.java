package org.maktab.onlinestore.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;
import org.maktab.onlinestore.view.activity.SearchActivity;
import org.maktab.onlinestore.adapter.HighestScoreProductAdapter;
import org.maktab.onlinestore.adapter.LatestProductAdapter;
import org.maktab.onlinestore.adapter.MostVisitedProductAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.FragmentHomePageBinding;
import org.maktab.onlinestore.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends Fragment {

    private HighestScoreProductAdapter mHighestScoreProductAdapter;
    private MostVisitedProductAdapter mMostVisitedProductAdapter;
    private LatestProductAdapter mLatestProductAdapter;
    private ProductViewModel mProductViewModel;
    private LiveData<List<Product>> mMostVisitedProductItemsLiveData;
    private LiveData<List<Product>> mLatestProductItemsLiveData;
    private LiveData<List<Product>> mHighestScoreProductItemsLiveData;
    private FragmentHomePageBinding mHomePageBinding;
    private LiveData<List<Product>> mSpecialProductsLiveData1;
    private LiveData<List<Product>> mSpecialProductsLiveData2;
    private LiveData<List<Product>> mSpecialProductsLiveData3;
    private List<Product> mSpecialProducts;
    List<SlideModel> mSlideModels;
    private int loading = 1;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    public HomePageFragment() {
        // Required empty public constructor
    }

    public static HomePageFragment newInstance() {
        HomePageFragment fragment = new HomePageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        mSpecialProducts = new ArrayList<>();
        mSlideModels = new ArrayList<>();
        getProductsFromProductViewModel();
        setObserver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mHomePageBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_home_page,
                container,
                false);

        initView();
        return mHomePageBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.home, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.menu_item_search_home);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        setSearchViewListeners(searchView);

        MenuItem togglePollingItem = menu.findItem(R.id.menu_item_poll_toggling);
        if (mProductViewModel.isTaskScheduled()) {
            togglePollingItem.setIcon(R.drawable.ic_notifications_off);
        } else {
            togglePollingItem.setIcon(R.drawable.ic_notifications_active);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_poll_toggling:
                mProductViewModel.togglePolling();
                getActivity().invalidateOptionsMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setSearchViewListeners(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                startActivity(SearchActivity.newIntent(getActivity(), query,"home"));
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

    private void getProductsFromProductViewModel() {
        mProductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.fetchMostVisitedProductItems();
        mProductViewModel.fetchLatestProductItems();
        mProductViewModel.fetchHighestScoreProductItems();
        mMostVisitedProductItemsLiveData = mProductViewModel.getLiveDateMostVisitedProducts();
        mLatestProductItemsLiveData = mProductViewModel.getLiveDateLatestProducts();
        mHighestScoreProductItemsLiveData = mProductViewModel.getLiveDateHighestScoreProducts();

        for (int i = 1; i < 4; i++) {
            mProductViewModel.fetchSpecialProductItems(String.valueOf(119), i + "");
            if (i == 1)
                mSpecialProductsLiveData1 = mProductViewModel.getLiveDataSpecialProduct1();
            else if (i==2)
                mSpecialProductsLiveData2 = mProductViewModel.getLiveDataSpecialProduct2();
            else if (i==3)
                mSpecialProductsLiveData3 = mProductViewModel.getLiveDataSpecialProduct3();
        }

    }

    private void setObserver() {
        mMostVisitedProductItemsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mProductViewModel.setProductListMostVisited(products);
                setAdapterMostVisited();
            }
        });
        mLatestProductItemsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mProductViewModel.setProductListLatest(products);
                setAdapterLatest();
            }
        });
        mHighestScoreProductItemsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mProductViewModel.setProductListHighestScore(products);
                setAdapterHighestScore();
            }
        });
        mSpecialProductsLiveData1.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mSpecialProducts.addAll(productList);

            }
        });
        mSpecialProductsLiveData2.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mSpecialProducts.addAll(productList);

            }
        });
        mSpecialProductsLiveData3.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mSpecialProducts.addAll(productList);
                showSlideImage(mSpecialProducts);

            }
        });
    }

    private void showSlideImage(List<Product> products) {

            for (int i = 0; i < products.size(); i++) {
                String uri = products.get(i).getImages().get(0).getSrc();
                SlideModel slideModel = new SlideModel(uri, i + 1 + "", ScaleTypes.CENTER_INSIDE);
                mSlideModels.add(slideModel);
            }
            mHomePageBinding.imageSlider.setImageList(mSlideModels);
            mHomePageBinding.imageSlider.setItemClickListener(new ItemClickListener() {
                @Override
                public void onItemSelected(int i) {
                    String url = mSlideModels.get(i).getImageUrl();
                    for (int j = 0; j < mSpecialProducts.size(); j++) {
                        if (mSpecialProducts.get(j).getImages().get(0).getSrc().equalsIgnoreCase(url))
                            startActivity(ProductDetailActivity.newIntent(getActivity(),mSpecialProducts.get(j).getId()));
                    }
                }
            });
    }

    private void initView() {
        mHomePageBinding.recyclerHighestScore
                .setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));

        mHomePageBinding.recyclerMostVisited
                .setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));

        mHomePageBinding.recyclerLatest
                .setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));

    }

    private void setAdapterMostVisited() {
        mMostVisitedProductAdapter = new MostVisitedProductAdapter(this, getActivity(), mProductViewModel);
        mHomePageBinding.recyclerMostVisited.setAdapter(mMostVisitedProductAdapter);
        /*mProductAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                //your code goes here
                mRepository.setPage("2");
                mRepository.fetchProductItemsAsync(mRepository.getPage());
            }
        });*/
       /* mRecyclerViewMostVisited.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = mGridLayoutManager.getChildCount();
                    totalItemCount = mGridLayoutManager.getItemCount();
                    pastVisiblesItems = mGridLayoutManager.findFirstVisibleItemPosition();

                    if (loading!=10)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading +=1;
                            mRepository.setPage(String.valueOf(loading));
                            mRepository.fetchProductItemsAsync(mRepository.getPage());
                        }
                    }
                }
            }
        });*/
    }

    private void setAdapterLatest() {
        mLatestProductAdapter = new LatestProductAdapter(this, getActivity(), mProductViewModel);
        mHomePageBinding.recyclerLatest.setAdapter(mLatestProductAdapter);
    }

    private void setAdapterHighestScore() {
        mHighestScoreProductAdapter = new HighestScoreProductAdapter(this, getActivity(), mProductViewModel);
        mHomePageBinding.recyclerHighestScore.setAdapter(mHighestScoreProductAdapter);
    }
}