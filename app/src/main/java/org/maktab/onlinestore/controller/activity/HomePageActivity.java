package org.maktab.onlinestore.controller.activity;

import androidx.fragment.app.Fragment;

import org.maktab.onlinestore.controller.fragment.HomePageFragment;


public class HomePageActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return HomePageFragment.newInstance();
    }
}