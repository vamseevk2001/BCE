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
import android.widget.Toast;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Adapters.RequestReceivedAdapter;
import com.example.bce.Adapters.RequestSentAdapter;
import com.example.bce.Models.ConnectionListModalClass;
import com.example.bce.Models.DialogBoxModalClass;
import com.example.bce.databinding.FragmentRequestAlertBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestAlert extends Fragment implements RequestReceivedAdapter.AcceptRequestInterface {

    FragmentRequestAlertBinding binding;
    String user_id;
    RequestReceivedAdapter requestReceivedAdapter;
    RequestSentAdapter requestSentAdapter;
    ArrayList<ConnectionListModalClass.ResuestReceived> resuestReceiveds = new ArrayList<>();
    ArrayList<ConnectionListModalClass.ResuestSend> requestSends = new ArrayList<>();

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
        sendRequest();
        binding.requestReceiveRecyclerView.setVisibility(View.GONE);
        binding.sendReceiveRecyclerView.setVisibility(View.VISIBLE);
        binding.btnReceiveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(getResources().getColor(R.color.red));
                binding.btnSendList.setBackgroundColor(getResources().getColor(R.color.darkGreyFont));
                binding.requestReceiveRecyclerView.setVisibility(View.VISIBLE);
                binding.sendReceiveRecyclerView.setVisibility(View.GONE);
                receiveRequests();
            }
        });

        binding.btnSendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(getResources().getColor(R.color.red));
                binding.btnReceiveList.setBackgroundColor(getResources().getColor(R.color.darkGreyFont));
                binding.requestReceiveRecyclerView.setVisibility(View.GONE);
                binding.sendReceiveRecyclerView.setVisibility(View.VISIBLE);
                sendRequest();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    void receiveRequests() {

        requestReceivedAdapter = new RequestReceivedAdapter(resuestReceiveds, getContext(), this);
        RecyclerView recyclerView = binding.requestReceiveRecyclerView;
        SimpleApi simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        Call<ConnectionListModalClass> call = simpleApi.connectionlist(params);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Data Retrieved Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        call.enqueue(new Callback<ConnectionListModalClass>() {
            @Override
            public void onResponse(Call<ConnectionListModalClass> call, Response<ConnectionListModalClass> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    resuestReceiveds.clear();
                    for (ConnectionListModalClass.ResuestReceived resuestReceived : response.body().getResuestReceived()) {
                        if (!resuestReceiveds.contains(resuestReceived))
                            resuestReceiveds.add(resuestReceived);
                        requestReceivedAdapter.updateList(resuestReceived);
                    }
                }
            }

            @Override
            public void onFailure(Call<ConnectionListModalClass> call, Throwable t) {
                progressDialog.dismiss();
                call.cancel();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(requestReceivedAdapter);

    }

    void sendRequest() {

        requestSentAdapter = new RequestSentAdapter(requestSends);
        RecyclerView recyclerView = binding.sendReceiveRecyclerView;
        SimpleApi simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        Call<ConnectionListModalClass> call = simpleApi.connectionlist(params);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Data Retrieved Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        call.enqueue(new Callback<ConnectionListModalClass>() {
            @Override
            public void onResponse(Call<ConnectionListModalClass> call, Response<ConnectionListModalClass> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    requestSends.clear();
                    for (ConnectionListModalClass.ResuestSend requestSent : response.body().getResuestSend()) {
                        if (!requestSends.contains(requestSent))
                            requestSends.add(requestSent);
                        requestSentAdapter.updateList(requestSent);
                    }
                }
            }

            @Override
            public void onFailure(Call<ConnectionListModalClass> call, Throwable t) {
                progressDialog.dismiss();
                call.cancel();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(requestSentAdapter);

    }


    void accptRequest(String user_id, String request_id) {
        SimpleApi simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("request_id", request_id);
        Call<DialogBoxModalClass> call = simpleApi.acceptRequest(params);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Accepting Request Please Wait...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        call.enqueue(new Callback<DialogBoxModalClass>() {
            @Override
            public void onResponse(Call<DialogBoxModalClass> call, Response<DialogBoxModalClass> response) {
                if (response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DialogBoxModalClass> call, Throwable t) {
                progressDialog.dismiss();
                call.cancel();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRequestAlertBinding.inflate(inflater, container, false);
        binding.toolbar.setTitle("Request Alert");
        binding.toolbar.setNavigationIcon(R.drawable.ic_back);
        MainActivity activity = (MainActivity) getActivity();
        user_id = activity.getUserId();
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getActivity().onBackPressed();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment, new home_frag()).commit();
            }
        });
        return binding.getRoot();
    }


    @Override
    public void acceptRequest(ConnectionListModalClass.ResuestReceived resuestReceived) {
        accptRequest(user_id, resuestReceived.getRequestId());
    }
}