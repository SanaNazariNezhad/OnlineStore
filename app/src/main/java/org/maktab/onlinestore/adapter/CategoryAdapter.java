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
import org.maktab.onlinestore.data.model.ProductCategory;
import org.maktab.onlinestore.viewmodel.CategoryViewModel;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder> {

    private final CategoryViewModel mCategoryViewModel;
    private final LifecycleOwner mOwner;
    private OnBottomReachedListener mOnBottomReachedListener;

    public CategoryAdapter(LifecycleOwner owner, Context context, CategoryViewModel categoryViewModel) {
        mOwner = owner;
        mCategoryViewModel = categoryViewModel;
        mCategoryViewModel.setContext(context);
    }

    @Override
    public int getItemCount() {
        return mCategoryViewModel.getCategoryList().size();
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCategoryViewModel.getApplication());
        ItemCategoryBinding itemCategoryBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_category,
                parent,
                false);

        CategoryHolder categoryHolder = new CategoryHolder(itemCategoryBinding);
        return categoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        ProductCategory category = mCategoryViewModel.getCategoryList().get(position);
        holder.bindProduct(category);
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        ItemCategoryBinding mItemCategoryBinding;

        public CategoryHolder(ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.getRoot());
            mItemCategoryBinding = itemCategoryBinding;
            mItemCategoryBinding.setCategoryViewModel(mCategoryViewModel);
            mItemCategoryBinding.setState("category");
            mItemCategoryBinding.setLifecycleOwner(mOwner);

        }

        public void bindProduct(ProductCategory category) {
            mItemCategoryBinding.setParentId(category.getId());
            mItemCategoryBinding.textCategory.setText(category.getName());
            Glide.with(mItemCategoryBinding.getRoot())
                    .load(category.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.ic_image)
                    .into(mItemCategoryBinding.imageCategory);
        }
    }
}
