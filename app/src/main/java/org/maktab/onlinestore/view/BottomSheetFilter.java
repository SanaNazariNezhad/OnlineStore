package org.maktab.onlinestore.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.data.model.Attributes;
import org.maktab.onlinestore.data.model.ColorAttribute;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.LayoutBottomSheetFilterBinding;
import org.maktab.onlinestore.databinding.LayoutBottomSheetFilterCategoryBinding;
import org.maktab.onlinestore.receiver.ConnectionReceiver;
import org.maktab.onlinestore.viewmodel.SearchViewModel;
import org.maktab.onlinestore.viewmodel.SplashViewModel;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetFilter extends BottomSheetDialogFragment {


    public static final String EXTRA_FILTER_COLOR = "extra_filter_color";
    private SplashViewModel mSplashViewModel;
    private ConnectionReceiver mConnectionReceiver;
    BottomSheetBehavior bottomSheetBehavior;
    LayoutBottomSheetFilterBinding mFilterBinding;
    LayoutBottomSheetFilterCategoryBinding mFilterCategoryBinding;
    private int REQUEST_CODE;
    private SearchViewModel mSearchViewModel;
    private LiveData<List<ColorAttribute>> mColorsLiveData;
    private String mColor;
    private List<ColorAttribute> mColorAttributes;
    private int mProductId;
    private LiveData<Product> mProductLiveData;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);


        REQUEST_CODE = getTargetRequestCode();
        mSearchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        if (REQUEST_CODE == 0) {

            setLayoutForFilterHome(bottomSheet);

            mSearchViewModel.fetchColorAttributeAsync();
            mColorAttributes = new ArrayList<>();
            mColorsLiveData = mSearchViewModel.getColorsLiveData();

            observer();
            updateUI();
            listeners();
            hideAppBar(mFilterBinding.appBarLayout);

        } else {
            setLayoutForFilter(bottomSheet);
            String id = mSearchViewModel.getProductIdForFilterFromPreferences();
            mProductId = Integer.parseInt(id);
            mSearchViewModel.fetchProductItems(mProductId);
            mProductLiveData = mSearchViewModel.getLiveDateProduct();
            observerCategory();
            listenersForFilterCategory();

            hideAppBar(mFilterCategoryBinding.appBarLayout);

        }

        return bottomSheet;
    }

    private void observerCategory() {
        if (mProductLiveData == null)
            return;
        mProductLiveData.observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                List<Attributes> attributesList = product.getAttributes();
                StringBuilder attribute = new StringBuilder();
                for (int i = 0; i < attributesList.size(); i++) {
                    attribute.append(attributesList.get(i).getName());
                }
                mFilterCategoryBinding.userName.setText(attribute + "Attributes");
            }
        });
    }

    private void observer() {
        mColorsLiveData.observe(this, new Observer<List<ColorAttribute>>() {
            @Override
            public void onChanged(List<ColorAttribute> colorAttributes) {
                mColorAttributes.addAll(colorAttributes);
                setColorToView();
                updateUI();
            }
        });
    }

    private void setColorToView() {
        mFilterBinding.blue.setText(mColorAttributes.get(0).getName());
        mFilterBinding.white.setText(mColorAttributes.get(1).getName());
        mFilterBinding.pink.setText(mColorAttributes.get(2).getName());
        mFilterBinding.coral.setText(mColorAttributes.get(3).getName());
        mFilterBinding.black.setText(mColorAttributes.get(4).getName());
        mFilterBinding.orange.setText(mColorAttributes.get(5).getName());

        setColorDrawable(0, mFilterBinding.color1);
        setColorDrawable(1, mFilterBinding.color2);
        setColorDrawable(2, mFilterBinding.color3);
        setColorDrawable(3, mFilterBinding.color4);
        setColorDrawable(4, mFilterBinding.color5);
        setColorDrawable(5, mFilterBinding.color6);
    }

    private void setColorDrawable(int i, ImageView p) {
        if (mColorAttributes.get(i).getName().equals("سفید"))
            p.setImageDrawable(getResources().getDrawable(R.drawable.ic__color_white));
        else if (mColorAttributes.get(i).getName().equals("آبی"))
            p.setImageDrawable(getResources().getDrawable(R.drawable.ic_color_blue));
        else if (mColorAttributes.get(i).getName().equals("صورتی"))
            p.setImageDrawable(getResources().getDrawable(R.drawable.ic_color_pink));
        else if (mColorAttributes.get(i).getName().equals("مرجانی"))
            p.setImageDrawable(getResources().getDrawable(R.drawable.ic_color_coral));
        else if (mColorAttributes.get(i).getName().equals("مشکی"))
            p.setImageDrawable(getResources().getDrawable(R.drawable.ic_color_black));
        else if (mColorAttributes.get(i).getName().equals("نارنجی"))
            p.setImageDrawable(getResources().getDrawable(R.drawable.ic_color_orange));
    }

    private void updateUI() {
        String color = mSearchViewModel.getColorFromPreferences();
        String colorIdWhite = "", colorIdBlue = "", colorIdPink = "", colorIdCoral = "",
                colorIdBlack = "", colorIdOrange = "";
        for (int i = 0; i < mColorAttributes.size(); i++) {
            if (mColorAttributes.get(i).getName().equalsIgnoreCase("سفید"))
                colorIdWhite = String.valueOf(mColorAttributes.get(i).getId());
            else if (mColorAttributes.get(i).getName().equalsIgnoreCase("آبی"))
                colorIdBlue = String.valueOf(mColorAttributes.get(i).getId());
            else if (mColorAttributes.get(i).getName().equalsIgnoreCase("صورتی"))
                colorIdPink = String.valueOf(mColorAttributes.get(i).getId());
            else if (mColorAttributes.get(i).getName().equalsIgnoreCase("مرجانی"))
                colorIdCoral = String.valueOf(mColorAttributes.get(i).getId());
            else if (mColorAttributes.get(i).getName().equalsIgnoreCase("مشکی"))
                colorIdBlack = String.valueOf(mColorAttributes.get(i).getId());
            else if (mColorAttributes.get(i).getName().equalsIgnoreCase("نارنجی"))
                colorIdOrange = String.valueOf(mColorAttributes.get(i).getId());
        }
        if (color != null) {
            if (color.equalsIgnoreCase(colorIdWhite))
                mFilterBinding.white.setChecked(true);
            else if (color.equalsIgnoreCase(colorIdBlue))
                mFilterBinding.blue.setChecked(true);
            else if (color.equalsIgnoreCase(colorIdPink))
                mFilterBinding.pink.setChecked(true);
            else if (color.equalsIgnoreCase(colorIdCoral))
                mFilterBinding.coral.setChecked(true);
            else if (color.equalsIgnoreCase(colorIdBlack))
                mFilterBinding.black.setChecked(true);
            else if (color.equalsIgnoreCase(colorIdOrange))
                mFilterBinding.orange.setChecked(true);
            else if (color.equalsIgnoreCase(""))
                mFilterBinding.noFilter.setChecked(true);
        }
    }

    private void setLayoutForFilterHome(BottomSheetDialog bottomSheet) {
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
    }

    private void setLayoutForFilter(BottomSheetDialog bottomSheet) {
        View view = View.inflate(getContext(), R.layout.layout_bottom_sheet_filter_category, null);

        //binding views to data binding.
        mFilterCategoryBinding = DataBindingUtil.bind(view);

        //setting layout with bottom sheet
        bottomSheet.setContentView(view);

        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));


        //setting Peek at the 16:9 ratio keyline of its parent.
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        //setting max height of bottom sheet
        mFilterCategoryBinding.extraSpace.setMinimumHeight((Resources.getSystem().getDisplayMetrics().heightPixels) / 2);


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (BottomSheetBehavior.STATE_EXPANDED == i) {
                    showView(mFilterCategoryBinding.appBarLayout, getActionBarSize());
                    hideAppBar(mFilterCategoryBinding.colorLayout);

                }
                if (BottomSheetBehavior.STATE_COLLAPSED == i) {
                    hideAppBar(mFilterCategoryBinding.appBarLayout);
                    showView(mFilterCategoryBinding.colorLayout, getActionBarSize());
                }

                if (BottomSheetBehavior.STATE_HIDDEN == i) {
                    dismiss();
                }

            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    private void listenersForFilterCategory() {

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
                if (b) {

                    mColor = getColorId("مشکی");
                    mSearchViewModel.setColorInPreferences(null);
                    mSearchViewModel.setColorInPreferences(mColor);
                    sendResult(mColor);
                }
            }
        });
        mFilterBinding.white.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mColor = getColorId("سفید");
                    mSearchViewModel.setColorInPreferences(null);
                    mSearchViewModel.setColorInPreferences(mColor);
                    sendResult(mColor);
                }
            }
        });
        mFilterBinding.coral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mColor = getColorId("مرجانی");
                    mSearchViewModel.setColorInPreferences(null);
                    mSearchViewModel.setColorInPreferences(mColor);
                    sendResult(mColor);
                }
            }
        });
        mFilterBinding.orange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mColor = getColorId("نارنجی");
                    mSearchViewModel.setColorInPreferences(null);
                    mSearchViewModel.setColorInPreferences(mColor);
                    sendResult(mColor);
                }
            }
        });
        mFilterBinding.blue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mColor = getColorId("آبی");
                    mSearchViewModel.setColorInPreferences(null);
                    mSearchViewModel.setColorInPreferences(mColor);
                    sendResult(mColor);
                }
            }
        });
        mFilterBinding.pink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mColor = getColorId("صورتی");
                    mSearchViewModel.setColorInPreferences(null);
                    mSearchViewModel.setColorInPreferences(mColor);
                    sendResult(mColor);
                }
            }
        });
        mFilterBinding.noFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mColor = "";
                    mSearchViewModel.setColorInPreferences(null);
                    mSearchViewModel.setColorInPreferences(mColor);
                    sendResult(mColor);
                }
            }
        });

    }

    private String getColorId(String color) {
        String colorId = "";
        for (int i = 0; i < mColorAttributes.size(); i++) {
            if (mColorAttributes.get(i).getName().equalsIgnoreCase(color))
                colorId = String.valueOf(mColorAttributes.get(i).getId());
        }
        return colorId;
    }

    private void setColor() {
        if (mFilterBinding.white.isChecked())
            mColor = getColorId("سفید");
        else if (mFilterBinding.blue.isChecked())
            mColor = getColorId("آبی");
        else if (mFilterBinding.pink.isChecked())
            mColor = getColorId("صورتی");
        else if (mFilterBinding.coral.isChecked())
            mColor = getColorId("مرجانی");
        else if (mFilterBinding.black.isChecked())
            mColor = getColorId("مشکی");
        else if (mFilterBinding.orange.isChecked())
            mColor = getColorId("نارنجی");
        else if (mFilterBinding.noFilter.isChecked())
            mColor = "";
    }

    @Override
    public void onStart() {
        super.onStart();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        getActivity().registerReceiver(mConnectionReceiver, filter);
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
        mSearchViewModel.setColorInPreferences(color);
        Fragment fragment = getTargetFragment();
        int requestCode = getTargetRequestCode();
        int resultCode = Activity.RESULT_OK;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_FILTER_COLOR, color);
        fragment.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConnectionReceiver = new ConnectionReceiver();
        mSplashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        mSplashViewModel.setInConnectionActivity(false);
    }

    @Override
    public void onStop() {
        super.onStop();

        getActivity().unregisterReceiver(mConnectionReceiver);
    }
}
