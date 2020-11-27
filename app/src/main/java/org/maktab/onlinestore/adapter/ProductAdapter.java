package org.maktab.onlinestore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.controller.activity.ProductDetailActivity;
import org.maktab.onlinestore.data.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> mProductList;
    private Context mContext;

    public List<Product> getProductList() {
        return mProductList;
    }

    public void setProductList(List<Product> productList) {
        mProductList = productList;
    }

    public ProductAdapter(List<Product> productList, Context context) {
        mProductList = productList;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_category,parent,false);

        ProductHolder productHolder = new ProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = mProductList.get(position);
        holder.bindProduct(product);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;
        private Product mProduct;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_category);
            mTextView = itemView.findViewById(R.id.text_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = ProductDetailActivity.newIntent(mContext,mProduct.getId());
                    mContext.startActivity(intent);
                }
            });

        }

        public void bindProduct(Product product) {
            mProduct = product;
            mTextView.setText(product.getTitle());
            Glide.with(itemView)
                    .load(product.getImages().get(0).getSrc())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mImageView);
        }
    }
}
