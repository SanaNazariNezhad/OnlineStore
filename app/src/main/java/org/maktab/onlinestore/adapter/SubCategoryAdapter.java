package org.maktab.onlinestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.SubCategoriesActivity;
import org.maktab.onlinestore.data.model.ProductCategory;

import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.CategoryHolder> {

    private List<ProductCategory> mCategoryList;
    private Context mContext;
    private OnBottomReachedListener mOnBottomReachedListener;

    public List<ProductCategory> getCategoryList() {
        return mCategoryList;
    }

    public void setCategoryList(List<ProductCategory> categoryList) {
        mCategoryList = categoryList;
    }

    public SubCategoryAdapter(Context context, List<ProductCategory> categoryList) {
        mContext = context;
        mCategoryList = categoryList;
    }

    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_category,parent,false);

        CategoryHolder categoryHolder = new CategoryHolder(view);
        return categoryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        ProductCategory category = mCategoryList.get(position);
        holder.bindProduct(category);
    }

    class CategoryHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;
        private ProductCategory mProductCategory;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_category);
            mTextView = itemView.findViewById(R.id.text_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(SubCategoriesActivity.newIntent(mContext,mProductCategory.getId(),mProductCategory.getName()));
                }
            });

        }

        public void bindProduct(ProductCategory category) {
            mProductCategory = category;
            mTextView.setText(category.getName());
            Glide.with(itemView)
                    .load(category.getImage())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mImageView);
        }
    }
}
