package org.maktab.onlinestore;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.maktab.onlinestore.controller.activity.HomeActivity;
import org.maktab.onlinestore.data.repository.OnlineStoreRepository;

public class DisconnectFragment extends Fragment {
    private Button mButton;
    private OnlineStoreRepository mRepository;

    public DisconnectFragment() {
        // Required empty public constructor
    }

    public static DisconnectFragment newInstance() {
        DisconnectFragment fragment = new DisconnectFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRepository = OnlineStoreRepository.getInstance();
        mRepository.setConnection(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_disconnect, container, false);
        findView(view);
        listeners();
        return view;
    }

    private void listeners() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRepository.isConnection()){
                    Intent intentActivity = HomeActivity.newIntent(getActivity());
                    getActivity().startActivity(intentActivity);
                }
            }
        });
    }

    private void findView(View view) {
        mButton = view.findViewById(R.id.button_try_again);
    }
}