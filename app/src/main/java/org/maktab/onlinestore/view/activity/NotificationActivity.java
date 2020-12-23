package org.maktab.onlinestore.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.maktab.onlinestore.view.fragment.NotificationFragment;

public class NotificationActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        return new Intent(context,NotificationActivity.class);
    }

    @Override
    public Fragment createFragment() {
        return NotificationFragment.newInstance();
    }

}