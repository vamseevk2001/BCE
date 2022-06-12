package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.databinding.FragmentGuestListBinding;
import com.example.bce.databinding.FragmentHomeFragBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuestList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuestList extends Fragment {

    FragmentGuestListBinding binding;

    public GuestList() {
        // Required empty public constructor
    }

    public static GuestList newInstance(String param1, String param2) {
        GuestList fragment = new GuestList();
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
        binding = FragmentGuestListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}