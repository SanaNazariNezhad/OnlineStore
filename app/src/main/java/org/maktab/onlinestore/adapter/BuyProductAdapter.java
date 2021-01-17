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
import org.maktab.onlinestore.data.model.Cart;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.repository.CartDBRepository;
import org.maktab.onlinestore.databinding.ItemBuyBinding;
import org.maktab.onlinestore.databinding.ItemCartBinding;
import org.maktab.onlinestore.viewmodel.CartViewModel;

public class BuyProductAdapter extends RecyclerView.Adapter<BuyProductAdapter.ProductHolder> {

    private final CartViewModel mCartViewModel;
    private final LifecycleOwner mOwner;
    private Context mContext;

    public BuyProductAdapter(LifecycleOwner owner, Context context, CartViewModel cartViewModel) {
        mOwner = owner;
        mCartViewModel = cartViewModel;
        mCartViewModel.setContext(context);
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return mCartViewModel.getProductList().size();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCartViewModel.getApplication());
        ItemBuyBinding itemBuyBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_buy,
                parent,
                false);

        ProductHolder productHolder = new ProductHolder(itemBuyBinding);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = mCartViewModel.getProductList().get(position);
        holder.bindProduct(product);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        ItemBuyBinding mItemBuyBinding;
        CartDBRepository mCartDBRepository;

        public ProductHolder(ItemBuyBinding itemBuyBinding) {
            super(itemBuyBinding.getRoot());
            mItemBuyBinding = itemBuyBinding;
            mItemBuyBinding.setCartViewModel(mCartViewModel);
            mItemBuyBinding.setLifecycleOwner(mOwner);
            mCartDBRepository = CartDBRepository.getInstance(mContext);


        }

        public void bindProduct(Product product) {
            Cart cart = mCartDBRepository
                    .getCart(product.getId());
            if (cart != null) {
                mItemBuyBinding.setProductId(product.getId());
                mItemBuyBinding.textCartProductName.setText(product.getTitle());

                mItemBuyBinding.numberOfProduct
                        .setText(String.valueOf(cart.getProduct_count()));
            }

            mItemBuyBinding.textCartProductPrice.setText(product.getPrice());
            Glide.with(mItemBuyBinding.getRoot())
                    .load(product.getImages().get(0).getSrc())
                    .centerCrop()
                    .placeholder(R.drawable.ic_image)
                    .into(mItemBuyBinding.imageCartProduct);
        }
    }
}
