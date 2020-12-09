package org.maktab.onlinestore.view;

import android.app.Dialog;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;
import org.maktab.onlinestore.databinding.LayoutBottomSheetFilterBinding;
import org.maktab.onlinestore.databinding.LayoutBottomSheetSortBinding;

public class BottomSheetSort extends BottomSheetDialogFragment {


    BottomSheetBehavior bottomSheetBehavior;
    LayoutBottomSheetSortBinding bi;
    public static final int THE_BEST_SELLERS = 0;
    public static final int PRICES_HIGH_TO_LOW = 1;
    public static final int PRICES_LOW_TO_HIGH = 2;
    public static final int THE_NEWEST = 3;
    private OnlineStoreRepository mRepository;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        //inflating layout
        View view = View.inflate(getContext(), R.layout.layout_bottom_sheet_sort, null);

        //binding views to data binding.
        bi = DataBindingUtil.bind(view);

        //setting layout with bottom sheet
        bottomSheet.setContentView(view);

        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));


        //setting Peek at the 16:9 ratio keyline of its parent.
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);


        //setting max height of bottom sheet
        bi.extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels) / 2);


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
                    showView(bi.appBarLayout, getActionBarSize());
                    hideAppBar(bi.profileLayout);

                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
                    hideAppBar(bi.appBarLayout);
                    showView(bi.profileLayout, getActionBarSize());
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
        bi.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dismiss();
            }
        });

        //hiding app bar at the start
        hideAppBar(bi.appBarLayout);

        mRepository = new OnlineStoreRepository();

        bi.tvBestSellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRepository.setSort(THE_BEST_SELLERS);
                checkVisibility();
            }
        });

        bi.tvPriceHighToLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRepository.setSort(PRICES_HIGH_TO_LOW);
                checkVisibility();
            }
        });

        bi.tvPriceLowToHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRepository.setSort(PRICES_LOW_TO_HIGH);
                checkVisibility();
            }
        });
        bi.tvNewest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRepository.setSort(THE_NEWEST);
                checkVisibility();
            }
        });

        checkVisibility();


        return bottomSheet;
    }

    private void checkVisibility() {
        if (mRepository.getSort() == THE_BEST_SELLERS){
            bi.ivBestSellers.setVisibility(View.VISIBLE);
            bi.ivPriceHighToLow.setVisibility(View.GONE);
            bi.ivPriceLowToHigh.setVisibility(View.GONE);
            bi.ivNewest.setVisibility(View.GONE);
        }else if (mRepository.getSort() == PRICES_HIGH_TO_LOW){
            bi.ivBestSellers.setVisibility(View.GONE);
            bi.ivPriceHighToLow.setVisibility(View.VISIBLE);
            bi.ivPriceLowToHigh.setVisibility(View.GONE);
            bi.ivNewest.setVisibility(View.GONE);
        }else if (mRepository.getSort() == PRICES_LOW_TO_HIGH){
            bi.ivBestSellers.setVisibility(View.GONE);
            bi.ivPriceHighToLow.setVisibility(View.GONE);
            bi.ivPriceLowToHigh.setVisibility(View.VISIBLE);
            bi.ivNewest.setVisibility(View.GONE);
        }else if (mRepository.getSort() == THE_NEWEST){
            bi.ivBestSellers.setVisibility(View.GONE);
            bi.ivPriceHighToLow.setVisibility(View.GONE);
            bi.ivPriceLowToHigh.setVisibility(View.GONE);
            bi.ivNewest.setVisibility(View.VISIBLE);
        }
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
}
