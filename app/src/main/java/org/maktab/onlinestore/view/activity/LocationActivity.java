package org.maktab.onlinestore.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.maktab.onlinestore.view.fragment.LocationFragment;

public class LocationActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        return new Intent(context,LocationActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return LocationFragment.newInstance();
    }
}