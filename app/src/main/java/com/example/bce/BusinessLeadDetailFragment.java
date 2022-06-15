package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Adapters.BusinessLeadDetailAdapter;
import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.Models.ReviewItem;
import com.example.bce.Models.ReviewListModalClass;
import com.example.bce.databinding.BusinessLeadItemBinding;
import com.example.bce.databinding.FragmentBusinessLeadDetailBinding;
import com.example.bce.databinding.FragmentGuestListBinding;
import com.example.bce.databinding.FragmentHomeFragBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusinessLeadDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusinessLeadDetailFragment extends Fragment {

    FragmentBusinessLeadDetailBinding binding;
    BusinessLeadDetailAdapter mAdapter;
    ArrayList<BusinessLeadDetailModalClass.BusinessReceiveList> businessLead = new ArrayList<>();
    SimpleApi simpleApi;
    String user_id;

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

        mAdapter = new BusinessLeadDetailAdapter(businessLead);
        RecyclerView recyclerView = binding.businessLeadDetailRecyclerView;

        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);

        Call<BusinessLeadDetailModalClass> call = simpleApi.businessRecieve(params);

        call.enqueue(new Callback<BusinessLeadDetailModalClass>() {
            @Override
            public void onResponse(Call<BusinessLeadDetailModalClass> call, Response<BusinessLeadDetailModalClass> response) {
                if (response.isSuccessful()){
                    for(BusinessLeadDetailModalClass.BusinessReceiveList businessReceiveList : response.body().getReceiveList()){

                        businessLead.add(businessReceiveList);
                        mAdapter.updateBusinessLead(businessReceiveList);
                    }
                }
            }

            @Override
            public void onFailure(Call<BusinessLeadDetailModalClass> call, Throwable t) {
                call.cancel();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(mAdapter);

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        user_id = activity.getUserId();
        binding = FragmentBusinessLeadDetailBinding.inflate(inflater, container, false);
        binding.toolbar.setTitle("Business Lead Details");
        binding.toolbar.setNavigationIcon(R.drawable.ic_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return binding.getRoot();
    }

    void businessGiven(){

    }
}