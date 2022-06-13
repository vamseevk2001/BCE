package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bce.databinding.FragmentHomeFragBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_frag extends Fragment {

    FragmentHomeFragBinding binding;


    public home_frag() {
        // Required empty public constructor
    }


    public static home_frag newInstance(String param1, String param2) {
        home_frag fragment = new home_frag();
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
        String img_url1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiTVNHhNbyNuGl2NnIeaC72wk4zvapPEYOjcu6Wf3xYjPUaNpdWWdp-EI80NUsvVSzLw&usqp=CAU";
        String img_url2 = "https://thumbs.dreamstime.com/b/business-development-to-success-growth-banking-financial-global-network-businessman-hold-pointing-arrow-up-graph-227718315.jpg";
        String img_url3 = "https://www.silworld.in/wp-content/uploads/2019/10/career-banner-compressor.jpg";
        String img_url4 = "https://www.investni.com/sites/default/files/2020-06/business_support_investment_banner_904x466.jpg";
        String img_url5 = "https://researchleap.com/wp-content/uploads/2019/10/shutterstock_718547992-min-e1571736994121.jpg";

        ImageSlider imageSlider = binding.viewPager;
        List<SlideModel> imageList = new ArrayList<SlideModel>();
        imageList.add(new SlideModel(img_url1, ScaleTypes.FIT));
        imageList.add(new SlideModel(img_url2, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(img_url3, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(img_url4, ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(img_url5, ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(imageList);


        binding.reviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home_frag_to_reviewFragment);
            }
        });

        binding.businessReceivedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home_frag_to_businessLeadDetailFragment);
            }
        });

        binding.guestLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home_frag_to_guestList);
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.getSupportActionBar().setTitle("BCE Bhubaneswar");
//        activity.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        activity.getSupportActionBar().setDisplayShowCustomEnabled(true);
//        activity.getSupportActionBar().setCustomView(R.layout.custom_home_action_bar);

        //activity.getSupportActionBar().setLogo(R.drawable.logo);

        binding = FragmentHomeFragBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}