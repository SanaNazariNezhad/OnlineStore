package org.maktab.onlinestore.adapter;

import android.content.Context;
import android.net.MacAddress;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.MapAddress;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.ItemAddressBinding;
import org.maktab.onlinestore.viewmodel.SettingViewModel;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressHolder> {

    private final SettingViewModel mSettingViewModel;
    private final LifecycleOwner mOwner;

    public AddressAdapter(LifecycleOwner owner, Context context, SettingViewModel settingViewModel) {
        mOwner = owner;
        mSettingViewModel = settingViewModel;
        mSettingViewModel.setContext(context);
    }
    @Override
    public int getItemCount() {
        return mSettingViewModel.getAddresses().size();
    }

    @NonNull
    @Override
    public AddressHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mSettingViewModel.getApplication());
        ItemAddressBinding itemAddressBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.item_address,
                parent,
                false);

        AddressHolder addressHolder = new AddressHolder(itemAddressBinding);
        return addressHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressHolder holder, int position) {

        MapAddress address = mSettingViewModel.getAddresses().get(position);
        holder.bindProduct(address);
    }

    class AddressHolder extends RecyclerView.ViewHolder {

        ItemAddressBinding mItemAddressBinding;

        public AddressHolder(ItemAddressBinding itemAddressBinding) {
            super(itemAddressBinding.getRoot());
            mItemAddressBinding = itemAddressBinding;
            mItemAddressBinding.setLifecycleOwner(mOwner);


        }

        public void bindProduct(MapAddress address) {

            String mapAddress = address.getAddressName() + "\n\n"
                    + "Latitude: " + address.getAddress_lat() + "\n"
                    + "Longitude: " + address.getAddress_lng();
            mItemAddressBinding.textViewAddressName.setText(mapAddress);

        }
    }
}
