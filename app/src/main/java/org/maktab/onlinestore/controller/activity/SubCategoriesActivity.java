package org.maktab.onlinestore.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.onlinestore.controller.fragment.SubCategoriesFragment;

public class SubCategoriesActivity extends SingleFragmentActivity {

    public static final String PARENT_ID = "org.maktab.onlinestore.Parent_id";
    public static final String PARENT_NAME = "parent_name";

    public static Intent newIntent(Context context, int id,String parentName){
        Intent intent = new Intent(context,SubCategoriesActivity.class);
        intent.putExtra(PARENT_ID,id);
        intent.putExtra(PARENT_NAME,parentName);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return SubCategoriesFragment.newInstance(getIntent().getIntExtra(PARENT_ID,0),
                getIntent().getStringExtra(PARENT_NAME));
    }
}