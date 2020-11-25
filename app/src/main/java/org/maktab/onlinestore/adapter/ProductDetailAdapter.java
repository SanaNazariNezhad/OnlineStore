package org.maktab.onlinestore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.Images;

import java.util.List;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ProductHolder> {

    private List<Images> mImages;
    private Context mContext;

    public List<Images> getImages() {
        return mImages;
    }

    public void setImages(List<Images> images) {
        mImages = images;
    }

    public ProductDetailAdapter(List<Images> images, Context context) {
        mImages = images;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_highest_score,parent,false);

        ProductHolder productHolder = new ProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Images images = mImages.get(position);
        holder.bindProduct(images);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private Images mImage;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_highest_score);

        }

        public void bindProduct(Images image) {
            mImage = image;

            Glide.with(itemView)
                    .load(image.getSrc())
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(mImageView);
        }
    }
}
