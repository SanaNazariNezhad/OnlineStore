package org.maktab.onlinestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.databinding.ItemCategoryBinding;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.viewmodel.CategoryViewModel;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private final CategoryViewModel mCategoryViewModel;
    private final LifecycleOwner mOwner;

    public ProductAdapter(LifecycleOwner owner,Context context,CategoryViewModel categoryViewModel) {
        mOwner = owner;
        mCategoryViewModel = categoryViewModel;
        mCategoryViewModel.setContext(context);
    }
    @Override
    public int getItemCount() {
        return mCategoryViewModel.getProductList().size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCategoryViewModel.getApplication());
        ItemCategoryBinding itemCategoryBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_category,
                parent,
                false);

        ProductHolder productHolder = new ProductHolder(itemCategoryBinding);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = mCategoryViewModel.getProductList().get(position);
        holder.bindProduct(product);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        ItemCategoryBinding mItemCategoryBinding;

        public ProductHolder(ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            mItemCategoryBinding = itemCategoryBinding;
            mItemCategoryBinding.setCategoryViewModel(mCategoryViewModel);
            mItemCategoryBinding.setState("product");
            mItemCategoryBinding.setLifecycleOwner(mOwner);


        }

        public void bindProduct(Product product) {
            mItemCategoryBinding.setParentId(product.getId());

            mItemCategoryBinding.textCategory.setText(product.getTitle());
            Glide.with(mItemCategoryBinding.getRoot())
                    .load(product.getImages().get(0).getSrc())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mItemCategoryBinding.imageCategory);
        }
    }
}
