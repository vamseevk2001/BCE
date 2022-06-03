package com.example.bce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.databinding.FragmentMoreFragBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link more_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class more_frag extends Fragment {

    public more_frag() {
        // Required empty public constructor
    }

    public static more_frag newInstance(String param1, String param2) {
        more_frag fragment = new more_frag();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentMoreFragBinding binding = FragmentMoreFragBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}