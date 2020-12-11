package org.maktab.onlinestore.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.databinding.LayoutBottomSheetFilterBinding;

public class BottomSheetFilter extends BottomSheetDialogFragment {


    public static final String EXTRA_FILTER_COLOR = "extra_filter_color";
    BottomSheetBehavior bottomSheetBehavior;
    LayoutBottomSheetFilterBinding mFilterBinding;
    String mColor;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        //inflating layout
        View view = View.inflate(getContext(), R.layout.layout_bottom_sheet_filter, null);

        //binding views to data binding.
        mFilterBinding = DataBindingUtil.bind(view);

        //setting layout with bottom sheet
        bottomSheet.setContentView(view);

        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));


        //setting Peek at the 16:9 ratio keyline of its parent.
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);


        //setting max height of bottom sheet
        mFilterBinding.extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels) / 2);


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
                    showView(mFilterBinding.appBarLayout, getActionBarSize());
                    hideAppBar(mFilterBinding.colorLayout);

                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
                    hideAppBar(mFilterBinding.appBarLayout);
                    showView(mFilterBinding.colorLayout, getActionBarSize());
                }

                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    dismiss();
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });

        //aap bar cancel button clicked
        listeners();


        //hiding app bar at the start
        hideAppBar(mFilterBinding.appBarLayout);


        return bottomSheet;
    }

    private void listeners() {
        mFilterBinding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColor();
                dismiss();
                sendResult(mColor);
            }
        });
        mFilterBinding.black.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "مشکی";
                sendResult(mColor);
            }
        });
        mFilterBinding.white.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "سفید";
                sendResult(mColor);
            }
        });
        mFilterBinding.brown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "قهوه ای";
                sendResult(mColor);
            }
        });
        mFilterBinding.red.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "قرمز";
                sendResult(mColor);
            }
        });
        mFilterBinding.orange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "نارنجی";
                sendResult(mColor);
            }
        });
        mFilterBinding.yellow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "زرد";
                sendResult(mColor);
            }
        });
        mFilterBinding.green.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "سبز";
                sendResult(mColor);
            }
        });
        mFilterBinding.blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "آبی";
                sendResult(mColor);
            }
        });
        mFilterBinding.purple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "بنفش";
                sendResult(mColor);
            }
        });
        mFilterBinding.pink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mColor = "صورتی";
                sendResult(mColor);
            }
        });

    }

    private void setColor() {
        if (mFilterBinding.black.isChecked())
            mColor = "مشکی";
        else if (mFilterBinding.white.isChecked())
            mColor = "سفید";
        else if (mFilterBinding.white.isChecked())
            mColor = "قهوه ای";
        else if (mFilterBinding.white.isChecked())
            mColor = "قرمز";
        else if (mFilterBinding.white.isChecked())
            mColor = "نارنجی";
        else if (mFilterBinding.white.isChecked())
            mColor = "زرد";
        else if (mFilterBinding.white.isChecked())
            mColor = "سبز";
        else if (mFilterBinding.white.isChecked())
            mColor = "آبی";
        else if (mFilterBinding.white.isChecked())
            mColor = "بنفش";
        else if (mFilterBinding.white.isChecked())
            mColor = "صورتی";
    }

    @Override
    public void onStart() {
        super.onStart();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void hideAppBar(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);

    }

    private void showView(View view, int size) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = size;
        view.setLayoutParams(params);
    }

    private int getActionBarSize() {
        final TypedArray array = getContext().getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int size = (int) array.getDimension(0, 0);
        return size;
    }

    private void sendResult(String color) {
        Fragment fragment = getTargetFragment();
        int requestCode = getTargetRequestCode();
        int resultCode = Activity.RESULT_OK;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_FILTER_COLOR, color);

        fragment.onActivityResult(requestCode, resultCode, intent);
    }
}
