package org.maktab.onlinestore.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.onlinestore.view.fragment.SearchFragment;

public class SearchActivity extends SingleFragmentActivity {

    public static final String SEARCH_QUERY = "search_query";

    public static Intent newIntent (Context context, String query){
        Intent intent = new Intent(context,SearchActivity.class);
        intent.putExtra(SEARCH_QUERY,query);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return SearchFragment.newInstance(getIntent().getStringExtra(SEARCH_QUERY));
    }
}