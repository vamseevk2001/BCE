package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.databinding.BusinessLeadItemBinding;
import com.example.bce.databinding.FragmentBusinessLeadDetailBinding;
import com.example.bce.databinding.FragmentGuestListBinding;
import com.example.bce.databinding.FragmentHomeFragBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessLeadDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessLeadDetailFragment extends Fragment {


//    private var _binding: FragmentDashboardFragBinding? = null
//    private val binding get() = _binding!!


    public BusinessLeadDetailFragment() {
        // Required empty public constructor
    }

    public static BusinessLeadDetailFragment newInstance(String param1, String param2) {
        BusinessLeadDetailFragment fragment = new BusinessLeadDetailFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentBusinessLeadDetailBinding binding = FragmentBusinessLeadDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}