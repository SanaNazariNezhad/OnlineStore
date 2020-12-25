package org.maktab.onlinestore.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.maktab.onlinestore.view.fragment.BuyFragment;

public class BuyActivity extends SingleFragmentActivity {

    public static Intent newIntent (Context context){
        return new Intent(context,BuyActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return BuyFragment.newInstance();
    }
}