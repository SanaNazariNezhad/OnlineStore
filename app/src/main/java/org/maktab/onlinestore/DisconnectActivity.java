package org.maktab.onlinestore;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab.onlinestore.controller.activity.SingleFragmentActivity;

public class DisconnectActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        return new Intent(context, DisconnectActivity.class);
    }


    @Override
    public Fragment createFragment() {
        return DisconnectFragment.newInstance();
    }
}