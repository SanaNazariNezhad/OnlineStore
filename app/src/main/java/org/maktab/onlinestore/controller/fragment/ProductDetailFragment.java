package org.maktab.onlinestore.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.ProductDetailAdapter;
import org.maktab.onlinestore.data.model.Images;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

import java.util.List;

public class ProductDetailFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProductDetailAdapter mDetailAdapter;
    private OnlineStoreRepository mRepository;
    private LiveData<Product> mProductLiveData;
    private TextView mTextViewProductName, mTextViewProductDetail;
    private Product mProduct;

    public static final String BUNDLE_KEY_PRODUCT_ID = "bundle_key_product_id";
    private int mProductId;

    public ProductDetailFragment() {
        // Required empty public constructor
    }

    public static ProductDetailFragment newInstance(int id) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_PRODUCT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProductId = getArguments().getInt(BUNDLE_KEY_PRODUCT_ID);
        }

        mRepository = OnlineStoreRepository.getInstance();
        mRepository.fetchProductItemAsync(mProductId);
        mProductLiveData = mRepository.getProductLiveData();
        setObserver();
    }

    private void setObserver() {
        mProductLiveData.observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mProduct = product;
                List<Images> imagesList = product.getImages();
                setAdapterProductDetail(imagesList);
                mTextViewProductName.setText(mProduct.getTitle());
                String detail = mProduct.getShort_description() + "\n" + mProduct.getDescription()
                        + "\n" + " Average Rating: \t " + mProduct.getAverage_rating() + "\n\n"
                        + " Price: \t" + mProduct.getPrice() + "\n\n";
                mTextViewProductDetail.setText(detail);
            }
        });
    }

    private void setAdapterProductDetail(List<Images> imagesList) {
        mDetailAdapter = new ProductDetailAdapter(imagesList, getActivity());
        mRecyclerView.setAdapter(mDetailAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
        findView(view);
        initView();
        return view;
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void findView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_product_detail);
        mTextViewProductName = view.findViewById(R.id.text_product_name);
        mTextViewProductDetail = view.findViewById(R.id.textView_product_detail);
    }
}