package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Adapters.GuestListAdapter;
import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.Models.GuestListModalClass;
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
 * Use the {@link GuestList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuestList extends Fragment {

    FragmentGuestListBinding binding;
    SimpleApi simpleApi;
    GuestListAdapter mAdapter;
    ArrayList<GuestListModalClass.Guest> guestList = new ArrayList<>();
    String user_id;

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

        RecyclerView recyclerView = binding.guestListRecyclerView;
        mAdapter = new GuestListAdapter(guestList);

        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);

        Call<GuestListModalClass> call = simpleApi.guestList(params);
        call.enqueue(new Callback<GuestListModalClass>() {
            @Override
            public void onResponse(Call<GuestListModalClass> call, Response<GuestListModalClass> response) {
                if (response.isSuccessful()) {
                    for (GuestListModalClass.Guest guest : response.body().getGuestList()) {

                        guestList.add(guest);
                        mAdapter.updateGuestList(guest);
                    }
                }
            }

            @Override
            public void onFailure(Call<GuestListModalClass> call, Throwable t) {
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
        binding = FragmentGuestListBinding.inflate(inflater, container, false);
        binding.toolbar.setTitle("Guest List");
        binding.toolbar.setNavigationIcon(R.drawable.ic_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return binding.getRoot();
    }
}