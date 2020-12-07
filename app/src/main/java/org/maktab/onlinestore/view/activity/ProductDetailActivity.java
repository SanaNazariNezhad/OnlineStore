package org.maktab.onlinestore.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.onlinestore.view.fragment.ProductDetailFragment;

public class ProductDetailActivity extends SingleFragmentActivity {

    public static final String EXTRA_PRODUCT_ID = "extra_product_id";

    public static Intent newIntent (Context context, int id){
        Intent intent = new Intent(context,ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID,id);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return ProductDetailFragment.newInstance(getIntent().getIntExtra(EXTRA_PRODUCT_ID,0));
    }
}