package org.maktab.onlinestore;

import androidx.fragment.app.Fragment;


public class HomePageActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return HomePageFragment.newInstance();
    }
}