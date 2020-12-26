package org.maktab.onlinestore.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.Comment;
import org.maktab.onlinestore.data.model.Images;
import org.maktab.onlinestore.databinding.ItemCommentBinding;
import org.maktab.onlinestore.databinding.ItemImageDetailBinding;
import org.maktab.onlinestore.viewmodel.ProductViewModel;


public class ProductCommentAdapter extends RecyclerView.Adapter<ProductCommentAdapter.ProductHolder> {

    private final ProductViewModel mProductViewModel;
    private final LifecycleOwner mOwner;

    public ProductCommentAdapter(LifecycleOwner owner, ProductViewModel productViewModel) {
        mOwner = owner;
        mProductViewModel = productViewModel;
    }

    @Override
    public int getItemCount() {
        return mProductViewModel.getCommentList().size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mProductViewModel.getApplication());
        ItemCommentBinding itemCommentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_comment,
                parent,
                false);

        ProductHolder productHolder = new ProductHolder(itemCommentBinding);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Comment comment = mProductViewModel.getCommentList().get(position);
        holder.bindProduct(comment);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private final ItemCommentBinding mCommentBinding;

        public ProductHolder(ItemCommentBinding itemCommentBinding) {
            super(itemCommentBinding.getRoot());

            mCommentBinding = itemCommentBinding;
            mCommentBinding.setLifecycleOwner(mOwner);
        }

        public void bindProduct(Comment comment) {
            mCommentBinding.userName.setText(comment.getReviewer());
            mCommentBinding.userEmail.setText(comment.getReviewer_email());
            mCommentBinding.userRate.setText(String.valueOf(comment.getRating()));
            mCommentBinding.userComment.setText(comment.getReview());
        }
    }
}
