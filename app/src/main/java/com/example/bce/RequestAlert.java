package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.databinding.FragmentRequestAlertBinding;

public class RequestAlert extends Fragment {

    FragmentRequestAlertBinding binding;

    public RequestAlert() {
        // Required empty public constructor
    }

    public static RequestAlert newInstance(String param1, String param2) {
        RequestAlert fragment = new RequestAlert();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRequestAlertBinding.inflate(inflater, container, false);
        binding.toolbar.setTitle("Request Alert");
        binding.toolbar.setNavigationIcon(R.drawable.ic_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getActivity().onBackPressed();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment,new home_frag()).commit();
            }
        });
        return binding.getRoot();
    }
}