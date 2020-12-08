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
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.ItemCategoryBinding;
import org.maktab.onlinestore.databinding.ItemSearchBinding;
import org.maktab.onlinestore.viewmodel.CategoryViewModel;
import org.maktab.onlinestore.viewmodel.ProductViewModel;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ProductHolder> {

    private final ProductViewModel mProductViewModel;
    private final LifecycleOwner mOwner;

    public SearchProductAdapter(LifecycleOwner owner, Context context, ProductViewModel productViewModel) {
        mOwner = owner;
        mProductViewModel = productViewModel;
        mProductViewModel.setContext(context);
    }
    @Override
    public int getItemCount() {
        return mProductViewModel.getSearchProduct().size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mProductViewModel.getApplication());
        ItemSearchBinding itemSearchBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_search,
                parent,
                false);

        ProductHolder productHolder = new ProductHolder(itemSearchBinding);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = mProductViewModel.getSearchProduct().get(position);
        holder.bindProduct(product);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        ItemSearchBinding mItemSearchBinding;

        public ProductHolder(ItemSearchBinding itemSearchBinding) {
            super(itemSearchBinding.getRoot());
            mItemSearchBinding = itemSearchBinding;
            mItemSearchBinding.setProductViewModel(mProductViewModel);
            mItemSearchBinding.setLifecycleOwner(mOwner);


        }

        public void bindProduct(Product product) {
            mItemSearchBinding.setProductId(product.getId());

            mItemSearchBinding.textSearchProduct.setText(product.getTitle());
            Glide.with(itemView)
                    .load(product.getImages().get(0).getSrc())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mItemSearchBinding.imageSearchProduct);
        }
    }
}
