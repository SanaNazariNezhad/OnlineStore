package org.maktab.onlinestore.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.maktab.onlinestore.adapter.ProductCommentAdapter;
import org.maktab.onlinestore.data.model.Cart;
import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.ProductDetailAdapter;
import org.maktab.onlinestore.data.model.Comment;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.FragmentProductDetailBinding;
import org.maktab.onlinestore.viewmodel.CartViewModel;
import org.maktab.onlinestore.viewmodel.ProductViewModel;

import java.util.List;

public class ProductDetailFragment extends VisibleFragment {

    public static final int REQUEST_CODE_ADD_COMMENT = 0;
    public static final String FRAGMENT_TAG_ADD = "AddComment";
    private ProductDetailAdapter mDetailAdapter;
    private ProductCommentAdapter mCommentAdapter;
    private ProductViewModel mProductViewModel;
    private CartViewModel mCartViewModel;
    private LiveData<Product> mProductLiveData;
    private LiveData<List<Comment>> mCommentLiveData;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;
        if (requestCode == REQUEST_CODE_ADD_COMMENT){
            fetchComment();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCommentAdapter != null) {
            fetchComment();
        }
    }

    private void fetchComment() {
        mProductViewModel.fetchComment(String.valueOf(mProductId));
        mCommentLiveData = mProductViewModel.getLiveDateComment();
    }

    private void checkRating(Product product) {
        float rate = Float.parseFloat(product.getAverage_rating());
        if (rate > 0.0 && rate <= 0.5) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        } else if (rate > 0.5 && rate <= 1.00) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        } else if (rate > 1.00 && rate <= 1.5) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        } else if (rate > 1.5 && rate <= 2.00) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        } else if (rate > 2.00 && rate <= 2.5) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        } else if (rate > 2.5 && rate <= 3.00) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        } else if (rate > 3.00 && rate <= 3.5) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        } else if (rate > 3.5 && rate <= 4.00) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        } else if (rate > 4.00 && rate <= 4.5) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_half));
        } else if (rate > 4.5 && rate <= 5.00) {
            mProductDetailBinding.imageViewStar1.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar2.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar3.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar4.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
            mProductDetailBinding.imageViewStar5.setImageDrawable(getResources().getDrawable(R.drawable.ic_star_rate));
        }
        mProductDetailBinding.textViewRate.setText(String.valueOf(product.getRating_count()));
    }

    private void listeners() {

        mProductDetailBinding.layoutAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCommentFragment addCommentFragment = AddCommentFragment.newInstance(mProductId);
                addCommentFragment.setTargetFragment(
                        ProductDetailFragment.this,
                        REQUEST_CODE_ADD_COMMENT);
                addCommentFragment.show(
                        getActivity().getSupportFragmentManager(),
                        FRAGMENT_TAG_ADD);
            }
        });
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
                        + "\n\n";
                mProductDetailBinding.textviewDescription.setText(detail);

                mProductDetailBinding.textViewPrice.setText(product.getPrice());
            }
        });
        mCommentLiveData.observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                if (comments != null) {
                    mProductViewModel.setCommentList(comments);
                    if (mCommentAdapter == null) {
                        setCommentAdapter();
                    }
                    else {
                        mCommentAdapter.notifyItemRangeChanged(0,comments.size());
                    }
                }
            }
        });
    }

    private void setCommentAdapter() {
        mCommentAdapter = new ProductCommentAdapter(this, mProductViewModel,getActivity());
        mProductDetailBinding.recyclerComment.setAdapter(mCommentAdapter);
    }

    private void setAdapterProductDetail() {
        mDetailAdapter = new ProductDetailAdapter(this, mProductViewModel);
        mProductDetailBinding.recyclerProductDetail.setAdapter(mDetailAdapter);
    }

    private void getProductFromProductViewModel() {
        mProductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.fetchProductItems(mProductId);
        fetchComment();
        mProductLiveData = mProductViewModel.getLiveDateProduct();
    }

    private void initView() {
        mProductDetailBinding.setCartViewModel(mCartViewModel);
        mProductDetailBinding.setLifecycleOwner(getActivity());
        mProductDetailBinding.setProductId(mProductId);
        mProductDetailBinding.recyclerProductDetail
                .setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));

        mProductDetailBinding.recyclerComment
                .setLayoutManager(new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));
    }
}