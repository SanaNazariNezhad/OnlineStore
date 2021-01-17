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
import org.maktab.onlinestore.databinding.ItemCartBinding;
import org.maktab.onlinestore.databinding.ItemSearchBinding;
import org.maktab.onlinestore.viewmodel.CartViewModel;
import org.maktab.onlinestore.viewmodel.SearchViewModel;

public class OrderedProductAdapter extends RecyclerView.Adapter<OrderedProductAdapter.ProductHolder> {

    private final CartViewModel mCartViewModel;
    private final LifecycleOwner mOwner;
    private Context mContext;

    public OrderedProductAdapter(LifecycleOwner owner, Context context, CartViewModel cartViewModel) {
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
        ItemCartBinding itemCartBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_cart,
                parent,
                false);

        ProductHolder productHolder = new ProductHolder(itemCartBinding);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        Product product = mCartViewModel.getProductList().get(position);
        holder.bindProduct(product);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        ItemCartBinding mItemCartBinding;
        CartDBRepository mCartDBRepository;

        public ProductHolder(ItemCartBinding itemCartBinding) {
            super(itemCartBinding.getRoot());
            mItemCartBinding = itemCartBinding;
            mItemCartBinding.setCartViewModel(mCartViewModel);
            mItemCartBinding.setLifecycleOwner(mOwner);
            mCartDBRepository = CartDBRepository.getInstance(mContext);


        }

        public void bindProduct(Product product) {
            Cart cart = mCartDBRepository
                    .getCart(product.getId());
            if (cart != null) {
                mItemCartBinding.setProductId(product.getId());
                mItemCartBinding.textCartProductName.setText(product.getTitle());

                mItemCartBinding.numberOfProduct
                        .setText(String.valueOf(cart.getProduct_count()));
            }

            mItemCartBinding.textCartProductPrice.setText(product.getPrice());
            Glide.with(mItemCartBinding.getRoot())
                    .load(product.getImages().get(0).getSrc())
                    .centerCrop()
                    .placeholder(R.drawable.ic_image)
                    .into(mItemCartBinding.imageCartProduct);
        }
    }
}
