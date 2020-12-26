package org.maktab.onlinestore.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.maktab.onlinestore.view.fragment.EditCommentFragment;

public class EditCommentActivity extends SingleFragmentActivity {

    public static final String EXTRA_COMMENT_ID = "Extra_CommentId";

    public static Intent newIntent(Context context, int commentId){
        Intent intent = new Intent(context,EditCommentActivity.class);
        intent.putExtra(EXTRA_COMMENT_ID,commentId);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return EditCommentFragment.newInstance(getIntent().getIntExtra(EXTRA_COMMENT_ID,0));
    }

}