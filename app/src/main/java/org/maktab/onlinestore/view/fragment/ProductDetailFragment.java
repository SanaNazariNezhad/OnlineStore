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
import android.widget.Toast;

import org.maktab.onlinestore.data.model.Cart;
import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.ProductDetailAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.FragmentProductDetailBinding;
import org.maktab.onlinestore.viewmodel.CartViewModel;
import org.maktab.onlinestore.viewmodel.ProductViewModel;

public class ProductDetailFragment extends VisibleFragment {

    private ProductDetailAdapter mDetailAdapter;
    private ProductViewModel mProductViewModel;
    private CartViewModel mCartViewModel;
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
        mCartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        mCartViewModel.setContext(getActivity());
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
        listeners();
        return mProductDetailBinding.getRoot();
    }

    private void checkRating(Product product) {
        float rate = Float.parseFloat(product.getAverage_rating());
        if (rate > 0.0 && rate <= 0.5){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        }else if (rate > 0.5 && rate <= 1.00){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        }else if (rate > 1.00 && rate <= 1.5){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        }else if (rate > 1.5 && rate <= 2.00){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        }else if (rate > 2.00 && rate <= 2.5){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        }else if (rate > 2.5 && rate <= 3.00){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        }else if (rate > 3.00 && rate <= 3.5){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        }else if (rate > 3.5 && rate <= 4.00){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        }else if (rate > 4.00 && rate <= 4.5){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        }else if (rate > 4.5 && rate <= 5.00){
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        }
        mProductDetailBinding.textViewRate.setText(String.valueOf(product.getRating_count()));
    }

    private void listeners() {
        mProductDetailBinding.setCartViewModel(mCartViewModel);
        mProductDetailBinding.setLifecycleOwner(getActivity());
        mProductDetailBinding.setProductId(mProductId);

    }

    private void setObserver() {
        mProductLiveData.observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                checkRating(product);
                mProductViewModel.setDetailedProduct(product);
                setAdapterProductDetail();
                mProductDetailBinding.textProductName.setText(product.getTitle());
                String detail = product.getShort_description() + "\n" + product.getDescription()
                        + "\n" + "Total Sales:" + "\t" + product.getTotal_sales()
                        +"\n\n";
                mProductDetailBinding.textviewDescription.setText(detail);

                mProductDetailBinding.textViewPrice.setText(product.getPrice());
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