package org.maktab.onlinestore.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.onlinestore.view.fragment.SearchFragment;

public class SearchActivity extends SingleFragmentActivity {

    public static final String SEARCH_QUERY = "search_query";
    public static final String REQUEST_CODE = "request_code";

    public static Intent newIntent (Context context, String query,String requestCode){
        Intent intent = new Intent(context,SearchActivity.class);
        intent.putExtra(SEARCH_QUERY,query);
        intent.putExtra(REQUEST_CODE,requestCode);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return SearchFragment.newInstance(getIntent().getStringExtra(SEARCH_QUERY),
                getIntent().getStringExtra(REQUEST_CODE));
    }
}