package org.maktab.onlinestore.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.Images;
import org.maktab.onlinestore.databinding.ItemHighestScoreBinding;
import org.maktab.onlinestore.databinding.ItemImageDetailBinding;
import org.maktab.onlinestore.viewmodel.ProductViewModel;


public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ProductHolder> {

    private final ProductViewModel mProductViewModel;
    private final LifecycleOwner mOwner;

    public ProductDetailAdapter(LifecycleOwner owner,ProductViewModel productViewModel) {
        mOwner = owner;
        mProductViewModel = productViewModel;
    }

    @Override
    public int getItemCount() {
        return mProductViewModel.getDetailedProduct().getImages().size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mProductViewModel.getApplication());
        ItemImageDetailBinding itemImageDetailBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_image_detail,
                parent,
                false);

        ProductHolder productHolder = new ProductHolder(itemImageDetailBinding);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Images images = mProductViewModel.getDetailedProduct().getImages().get(position);
        holder.bindProduct(images);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final ItemImageDetailBinding mDetailBinding;

        public ProductHolder(ItemImageDetailBinding itemImageDetailBinding) {
            super(itemImageDetailBinding.getRoot());

            mDetailBinding = itemImageDetailBinding;
            mDetailBinding.setLifecycleOwner(mOwner);
        }

        public void bindProduct(Images image) {

            Glide.with(mDetailBinding.getRoot())
                    .load(image.getSrc())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mDetailBinding.imageDetail);
        }
    }
}
