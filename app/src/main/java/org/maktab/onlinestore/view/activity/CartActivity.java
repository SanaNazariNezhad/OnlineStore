package org.maktab.onlinestore.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.maktab.onlinestore.view.fragment.CartFragment;

public class CartActivity extends SingleFragmentActivity {

    public static Intent newIntent (Context context){
        return new Intent(context,CartActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return CartFragment.newInstance();
    }
}