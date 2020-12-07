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

import org.maktab.onlinestore.view.activity.ProductDetailActivity;
import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.Product;

import java.util.List;

public class MostVisitedProductAdapter extends RecyclerView.Adapter<MostVisitedProductAdapter.ProductHolder> {

    private List<Product> mProducts;
    private Context mContext;
    private OnBottomReachedListener mOnBottomReachedListener;

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }

    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){

        mOnBottomReachedListener = onBottomReachedListener;
    }

    public MostVisitedProductAdapter(Context context, List<Product> products) {
        mContext = context;
        mProducts = products;
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_most_visited,parent,false);

        ProductHolder productHolder = new ProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = mProducts.get(position);
        /*if (position == (mProducts.size() - 1)){

            mOnBottomReachedListener.onBottomReached(position);

        }*/
        holder.bindProduct(product);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;
        private Product mProduct;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_most_visited);
            mTextView = itemView.findViewById(R.id.textView_name_most_visited);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = ProductDetailActivity.newIntent(mContext,mProduct.getId());
                    mContext.startActivity(intent);
                }
            });
            /*mTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            mTextView.setSingleLine(true);
            mTextView.setSelected(true);
            mTextView.setMarqueeRepeatLimit(-1);*/

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
