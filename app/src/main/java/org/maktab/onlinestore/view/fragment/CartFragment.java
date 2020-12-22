package org.maktab.onlinestore.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab.onlinestore.R;
import org.maktab.onlinestore.adapter.OrderedProductAdapter;
import org.maktab.onlinestore.adapter.SearchProductAdapter;
import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.databinding.FragmentCartBinding;
import org.maktab.onlinestore.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends VisibleFragment {

    FragmentCartBinding mFragmentCartBinding;
    private CartViewModel mCartViewModel;
    private LiveData<Product> mProductLiveData;
    private List<Product> mProductList;
    private OrderedProductAdapter mOrderedProductAdapter;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductList = new ArrayList<>();
        mCartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        mCartViewModel.getOrderedProduct();
        mProductLiveData = mCartViewModel.getLiveDateProduct();
        observer();
        
    }

    private void observer() {
        mProductLiveData.observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mProductList.add(product);
                mCartViewModel.setProductList(mProductList);
                mOrderedProductAdapter = new OrderedProductAdapter(getActivity(),getActivity(), mCartViewModel);
                mFragmentCartBinding.recyclerCart.setAdapter(mOrderedProductAdapter);
                mCartViewModel.setOrderedProductAdapter(mOrderedProductAdapter);
                int totalPrice = 0;
                for (int i = 0; i < mProductList.size(); i++) {
                    int price = Integer.parseInt(mProductList.get(i).getPrice());
                    int count = mCartViewModel.getCart(mProductList.get(i).getId()).getProduct_count();
                    totalPrice += (price * count);
                }
                mFragmentCartBinding.totalPrice.setText(String.valueOf(totalPrice));
                if (mProductList.size() == 0) {
                    mFragmentCartBinding.layoutEmptyCart.setVisibility(View.VISIBLE);
                    mFragmentCartBinding.constraintLayoutContinue.setVisibility(View.GONE);
                }
                else {
                    mFragmentCartBinding.layoutEmptyCart.setVisibility(View.GONE);
                    mFragmentCartBinding.constraintLayoutContinue.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mFragmentCartBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_cart,
                container,
                false);

        mCartViewModel.setFragmentCartBinding(mFragmentCartBinding);

        initView();
        return mFragmentCartBinding.getRoot();
    }

    private void initView() {
        mFragmentCartBinding.recyclerCart.setLayoutManager(new LinearLayoutManager(getContext()));
        mFragmentCartBinding.layoutEmptyCart.setVisibility(View.VISIBLE);
        mFragmentCartBinding.constraintLayoutContinue.setVisibility(View.GONE);
    }
}