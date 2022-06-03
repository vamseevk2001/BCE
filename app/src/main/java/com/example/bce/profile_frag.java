package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.databinding.FragmentProfileFragBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile_frag#newInstance} factory method to
 * create an instance of this fragment.
 */

public class profile_frag extends Fragment {

    private FragmentProfileFragBinding binding;
    boolean isAllCompanyFieldsChecked = false;
    boolean isAllLocationFieldsChecked = false;

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
        binding = FragmentProfileFragBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.companyInfoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllCompanyFieldsChecked = CheckCompanyFeilds();
                if (isAllCompanyFieldsChecked) {
                 //add implementation if all fields are checked..
                }
            }
        });

        binding.locationInfoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllLocationFieldsChecked = CheckLocationFeilds();
                if (isAllCompanyFieldsChecked) {
                    //add implementation if all fields are checked..
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }


    private Boolean CheckCompanyFeilds(){
        if (binding.nameInp.length() == 0) {
            binding.nameInp.setError("This field is required");
            return false;
        }
        if (binding.emailIdInp.length() == 0) {
            binding.emailIdInp.setError("This field is required");
            return false;
        }
        if (binding.PhoneInp.length() == 0) {
            binding.PhoneInp.setError("This field is required");
            return false;
        }else if (binding.PhoneInp.length() != 10) {
            binding.PhoneInp.setError("Phone no. must be of 10 digits");
            return false;
        }
        if (binding.companyNameInp.length() == 0) {
            binding.companyNameInp.setError("This field is required");
            return false;
        }
        if (binding.designationInp.length() == 0) {
            binding.designationInp.setError("This field is required");
            return false;
        }
        if (binding.websiteInp.length() == 0) {
            binding.websiteInp.setError("This field is required");
            return false;
        }
        if (binding.tagsInp.length() == 0) {
            binding.tagsInp.setError("This field is required");
            return false;
        }
        if (binding.dobInp.length() == 0) {
            binding.dobInp.setError("This field is required");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        try {
            Date d1 = sdf.parse(binding.dobInp.getText().toString());
        } catch (Exception e) {
            binding.dobInp.setError("please check the date format");
            return false;
        }

        return true;
    }

    private Boolean CheckLocationFeilds(){
        if (binding.addressInp.length() == 0) {
            binding.addressInp.setError("This field is required");
            return false;
        }
        if (binding.countryInp.length() == 0) {
            binding.companyNameInp.setError("This field is required");
            return false;
        }
        if (binding.stateInp.length() == 0) {
            binding.stateInp.setError("This field is required");
            return false;
        }
        if (binding.districtInp.length() == 0) {
            binding.districtInp.setError("This field is required");
            return false;
        }
        if (binding.cityInp.length() == 0) {
            binding.cityInp.setError("This field is required");
            return false;
        }
        return true;
    }


}