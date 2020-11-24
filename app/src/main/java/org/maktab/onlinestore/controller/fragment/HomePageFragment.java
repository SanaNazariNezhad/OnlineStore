package org.maktab.onlinestore.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.OnBottomReachedListener;
import org.maktab.onlinestore.adapter.ProductAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.List;

public class HomePageFragment extends Fragment {
    private RecyclerView mRecyclerViewMostVisited;
    private RecyclerView mRecyclerViewHighestScore;
    private RecyclerView mRecyclerViewLatest;
    private OnlineStoreRepository mRepository;
    private ProductAdapter mProductAdapter;
    private List<Product> mProductList;
    private LiveData<List<Product>> mProductItemsLiveData;
    private int loading = 1;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private GridLayoutManager mGridLayoutManager;



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
        mRepository.fetchProductItemsAsync(mRepository.getPage());
        mProductItemsLiveData = mRepository.getProductItemsLiveData();
        setObserver();
    }

    private void setObserver() {
        mProductItemsLiveData.observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                setAdapter(products);
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
        //Todo... create adapter for each recyclerView
        mRecyclerViewMostVisited
                .setLayoutManager(mGridLayoutManager);
        mRecyclerViewHighestScore.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        mRecyclerViewLatest.setLayoutManager(new GridLayoutManager(getContext(),4));
    }

    private void setAdapter(List<Product> products) {
        mProductAdapter = new ProductAdapter(getActivity(),products);
        mRecyclerViewMostVisited.setAdapter(mProductAdapter);
        mRecyclerViewHighestScore.setAdapter(mProductAdapter);
        mRecyclerViewLatest.setAdapter(mProductAdapter);
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

    private void findViews(View view) {
        mRecyclerViewMostVisited = view.findViewById(R.id.recycler_most_visited);
        mRecyclerViewHighestScore = view.findViewById(R.id.recycler_highest_score);
        mRecyclerViewLatest = view.findViewById(R.id.recycler_latest);
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
    }
}