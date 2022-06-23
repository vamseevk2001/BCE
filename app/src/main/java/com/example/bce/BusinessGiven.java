package com.example.bce;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Adapters.BusinessGivenAdapter;
import com.example.bce.Models.BusinessGivenModalClass;
import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.databinding.FragmentBusinessGivenBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BusinessGiven extends Fragment {

    String user_id;
    FragmentBusinessGivenBinding binding;
    RecyclerView businessReceiveRecyclerView;
    RecyclerView sendRecyclerView;
    BusinessGivenAdapter mAdapter;
    ArrayList<BusinessGivenModalClass.Receive> businessGivenList = new ArrayList<>();
    ArrayList<BusinessGivenModalClass.Receive> businessSendList = new ArrayList<>();

    public BusinessGiven() {
        // Required empty public constructor
    }
    public static BusinessGiven newInstance(String param1, String param2) {
        BusinessGiven fragment = new BusinessGiven();
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
        businessReceiveRecyclerView = binding.businessGivenRecyclerView;
        sendRecyclerView = binding.businessSendRecyclerView;

        ReceiveList();
        binding.btnSendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendList();
            }
        });

        binding.btnReceiveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReceiveList();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    void ReceiveList(){
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Data Retrived Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        sendRecyclerView.setVisibility(View.GONE);
        businessReceiveRecyclerView.setVisibility(View.VISIBLE);

        binding.btnReceiveList.setBackgroundColor(binding.btnReceiveList.getContext().getResources().getColor(R.color.red));
        binding.btnSendList.setBackgroundColor(binding.btnSendList.getContext().getResources().getColor(R.color.lightGreyFont));

        SimpleApi simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);

        Call<BusinessGivenModalClass> call = simpleApi.BusinessGiven(params);

        mAdapter = new BusinessGivenAdapter(businessGivenList, getActivity(), false);

        call.enqueue(new Callback<BusinessGivenModalClass>() {
            @Override
            public void onResponse(Call<BusinessGivenModalClass> call, Response<BusinessGivenModalClass> response) {
                if (response.isSuccessful()){

                    progressDialog.dismiss();
                    businessGivenList.clear();
                    for(BusinessGivenModalClass.Receive businessReceiveList : response.body().getReceiveList()){
                        businessGivenList.add(businessReceiveList);
                        mAdapter.updateBusinessGiven(businessReceiveList);
                    }
                }
            }

            @Override
            public void onFailure(Call<BusinessGivenModalClass> call, Throwable t) {
                call.cancel();
                progressDialog.dismiss();
            }
        });

        businessReceiveRecyclerView.setHasFixedSize(true);
        businessReceiveRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        businessReceiveRecyclerView.setAdapter(mAdapter);

    }

    void SendList(){
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Data Retrived Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        sendRecyclerView.setVisibility(View.VISIBLE);
        businessReceiveRecyclerView.setVisibility(View.GONE);

        binding.btnReceiveList.setBackgroundColor(binding.btnReceiveList.getContext().getResources().getColor(R.color.lightGreyFont));
        binding.btnSendList.setBackgroundColor(binding.btnSendList.getContext().getResources().getColor(R.color.red));

        SimpleApi simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);

        Call<BusinessGivenModalClass> call = simpleApi.BusinessGiven(params);

        mAdapter = new BusinessGivenAdapter(businessSendList, getActivity(), true);

        call.enqueue(new Callback<BusinessGivenModalClass>() {
            @Override
            public void onResponse(Call<BusinessGivenModalClass> call, Response<BusinessGivenModalClass> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    businessSendList.clear();
                    for(BusinessGivenModalClass.Receive businessReceiveList : response.body().getSendList()){
                        businessGivenList.add(businessReceiveList);
                        mAdapter.updateBusinessGiven(businessReceiveList);
                    }
                }
            }

            @Override
            public void onFailure(Call<BusinessGivenModalClass> call, Throwable t) {
                call.cancel();
                progressDialog.dismiss();
            }
        });

        sendRecyclerView.setHasFixedSize(true);
        sendRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        sendRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBusinessGivenBinding.inflate(inflater, container, false);
        MainActivity activity = (MainActivity) getActivity();
        user_id = activity.getUserId();

        binding.toolbar.setTitle("Business Given Details");
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