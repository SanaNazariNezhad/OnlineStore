package org.maktab.onlinestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.onlinestore.databinding.ItemHighestScoreBinding;
import org.maktab.onlinestore.databinding.ItemLatestBinding;
import org.maktab.onlinestore.view.activity.ProductDetailActivity;
import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.viewmodel.ProductViewModel;

import java.util.List;

public class LatestProductAdapter extends RecyclerView.Adapter<LatestProductAdapter.ProductHolder> {

    private final ProductViewModel mProductViewModel;
    private final LifecycleOwner mOwner;
    private OnBottomReachedListener mOnBottomReachedListener;

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){

        mOnBottomReachedListener = onBottomReachedListener;
    }

    public LatestProductAdapter(LifecycleOwner owner,Context context,ProductViewModel productViewModel) {
        mOwner = owner;
        mProductViewModel = productViewModel;
        mProductViewModel.setContext(context);
    }

    @Override
    public int getItemCount() {
        return mProductViewModel.getProductListLatest().size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mProductViewModel.getApplication());
        ItemLatestBinding itemLatestBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_latest,
                parent,
                false);

        ProductHolder productHolder = new ProductHolder(itemLatestBinding);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = mProductViewModel.getProductListLatest().get(position);
        /*if (position == (mProducts.size() - 1)){

            mOnBottomReachedListener.onBottomReached(position);

        }*/
        holder.bindProduct(product);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final ItemLatestBinding mItemLatestBinding;

        public ProductHolder(ItemLatestBinding itemLatestBinding) {
            super(itemLatestBinding.getRoot());

            mItemLatestBinding = itemLatestBinding;

            /*mItemLatestBinding.textViewNameLatest.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            mItemLatestBinding.textViewNameLatest.setSingleLine(true);
            mItemLatestBinding.textViewNameLatest.setSelected(true);
            mItemLatestBinding.textViewNameLatest.setMarqueeRepeatLimit(-1);*/

            mItemLatestBinding.setProductViewModel(mProductViewModel);
            mItemLatestBinding.setLifecycleOwner(mOwner);

        }

        public void bindProduct(Product product) {
            mItemLatestBinding.setProductId(product.getId());
            mItemLatestBinding.textViewPriceLatest.setText(product.getPrice());
            mItemLatestBinding.textViewNameLatest.setText(product.getTitle());

            if (product.getImages().size() == 0){
                Glide.with(mItemLatestBinding.getRoot())
                        .load(R.drawable.ic_image)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image)
                        .into(mItemLatestBinding.imageLatest);
            }else {
                Glide.with(mItemLatestBinding.getRoot())
                        .load(product.getImages().get(0).getSrc())
                        .centerCrop()
                        .placeholder(R.drawable.ic_image)
                        .into(mItemLatestBinding.imageLatest);
            }
        }
    }
}
