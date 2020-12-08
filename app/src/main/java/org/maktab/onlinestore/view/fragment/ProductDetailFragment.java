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
import org.maktab.onlinestore.adapter.ProductDetailAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.FragmentProductDetailBinding;
import org.maktab.onlinestore.viewmodel.ProductViewModel;

public class ProductDetailFragment extends Fragment {

    private ProductDetailAdapter mDetailAdapter;
    private ProductViewModel mProductViewModel;
    private LiveData<Product> mProductLiveData;
    private FragmentProductDetailBinding mProductDetailBinding;

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
        getProductFromProductViewModel();
        setObserver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mProductDetailBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_product_detail,
                container,
                false);

        initView();
        return mProductDetailBinding.getRoot();
    }

    private void setObserver() {
        mProductLiveData.observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mProductViewModel.setDetailedProduct(product);
                setAdapterProductDetail();
                mProductDetailBinding.textProductName.setText(product.getTitle());
                String detail = product.getShort_description() + "\n" + product.getDescription()
                        + "\n" + " Average Rating: \t " + product.getAverage_rating() + "\n\n"
                        + " Price: \t" + product.getPrice() + "\n\n";
                mProductDetailBinding.textViewProductDetail.setText(detail);
            }
        });
    }

    private void setAdapterProductDetail() {
        mDetailAdapter = new ProductDetailAdapter(this,mProductViewModel);
        mProductDetailBinding.recyclerProductDetail.setAdapter(mDetailAdapter);
    }

    private void getProductFromProductViewModel() {
        mProductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.fetchProductItems(mProductId);
        mProductLiveData = mProductViewModel.getLiveDateProduct();
    }

    private void initView() {
        mProductDetailBinding.recyclerProductDetail
                .setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));
    }
}