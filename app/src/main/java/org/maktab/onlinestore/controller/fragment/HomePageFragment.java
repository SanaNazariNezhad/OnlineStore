package org.maktab.onlinestore.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.ProductAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.List;

public class HomePageFragment extends Fragment {
    private RecyclerView mRecyclerViewMostVisited;
    private OnlineStoreRepository mRepository;
    private ProductAdapter mProductAdapter;
    private List<Product> mProductList;
    private LiveData<List<Product>> mProductItemsLiveData;


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
        mRepository.fetchProductItemsAsync();
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
        mRecyclerViewMostVisited
                .setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void setAdapter(List<Product> products) {
        mProductAdapter = new ProductAdapter(getActivity(),products);
        mRecyclerViewMostVisited.setAdapter(mProductAdapter);
    }

    private void findViews(View view) {
        mRecyclerViewMostVisited = view.findViewById(R.id.recycler_most_visited);
    }
}