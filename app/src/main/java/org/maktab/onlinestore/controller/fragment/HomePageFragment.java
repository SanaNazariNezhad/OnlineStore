package org.maktab.onlinestore.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.HighestScoreProductAdapter;
import org.maktab.onlinestore.adapter.LatestProductAdapter;
import org.maktab.onlinestore.adapter.MostVisitedProductAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.List;

public class HomePageFragment extends Fragment {
    private RecyclerView mRecyclerViewLatest;
    private RecyclerView mRecyclerViewHighestScore;
    private RecyclerView mRecyclerViewMostVisited;
    private OnlineStoreRepository mRepository;
    private HighestScoreProductAdapter mHighestScoreProductAdapter;
    private MostVisitedProductAdapter mMostVisitedProductAdapter;
    private LatestProductAdapter mLatestProductAdapter;
    private List<Product> mProductList;
    private LiveData<List<Product>> mMostVisitedProductItemsLiveData;
    private LiveData<List<Product>> mLatestProductItemsLiveData;
    private LiveData<List<Product>> mHighestScoreProductItemsLiveData;
    private int loading = 1;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLinearLayoutManager;



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
        if (getArguments() != null) {
        }
        mRepository = new OnlineStoreRepository();
        mRepository.fetchMostVisitedProductItemsAsync();
        mRepository.fetchLatestProductItemsAsync();
        mRepository.fetchHighestScoreProductItemsAsync();

        mMostVisitedProductItemsLiveData = mRepository.getMostVisitedProductsLiveData();
        mLatestProductItemsLiveData = mRepository.getLatestProductsLiveData();
        mHighestScoreProductItemsLiveData = mRepository.getHighestScoreProductsLiveData();
        setObserver();
    }

    private void setObserver() {
        mMostVisitedProductItemsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapterMostVisited(products);
            }
        });
        mLatestProductItemsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapterLatest(products);
            }
        });
        mHighestScoreProductItemsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapterHighestScore(products);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        findViews(view);
        initView();
        return view;
    }

    private void initView() {
        mRecyclerViewHighestScore.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerViewMostVisited
                .setLayoutManager(mLinearLayoutManager);
        mRecyclerViewMostVisited.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        mRecyclerViewLatest.setLayoutManager(new GridLayoutManager(getContext(),3));
    }

    private void setAdapterMostVisited(List<Product> products) {
        mMostVisitedProductAdapter = new MostVisitedProductAdapter(getActivity(),products);
        mRecyclerViewMostVisited.setAdapter(mMostVisitedProductAdapter);
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

    private void setAdapterLatest(List<Product> products) {
        mLatestProductAdapter = new LatestProductAdapter(getActivity(), products);
        mRecyclerViewLatest.setAdapter(mLatestProductAdapter);
    }

    private void setAdapterHighestScore(List<Product> products) {
        mHighestScoreProductAdapter = new HighestScoreProductAdapter(getActivity(), products);
        mRecyclerViewHighestScore.setAdapter(mHighestScoreProductAdapter);
    }

    private void findViews(View view) {
        mRecyclerViewLatest = view.findViewById(R.id.recycler_latest);
        mRecyclerViewHighestScore = view.findViewById(R.id.recycler_highest_score);
        mRecyclerViewMostVisited = view.findViewById(R.id.recycler_most_visited);
        mLinearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
    }
}