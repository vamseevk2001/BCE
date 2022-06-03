package com.example.bce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.databinding.FragmentHomeFragBinding;
import com.example.bce.databinding.FragmentProfileFragBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile_frag extends Fragment {


    public profile_frag() {
        // Required empty public constructor
    }

    public static profile_frag newInstance(String param1, String param2) {
        profile_frag fragment = new profile_frag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentProfileFragBinding binding = FragmentProfileFragBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}