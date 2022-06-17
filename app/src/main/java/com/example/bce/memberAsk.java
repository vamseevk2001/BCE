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
import com.example.bce.Adapters.MemberListAdapter;
import com.example.bce.Adapters.memberAskAdapter;
import com.example.bce.Models.MemberAskListModal;
import com.example.bce.Models.MembersList;
import com.example.bce.Models.Membership;
import com.example.bce.databinding.FragmentMemberAskBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link memberAsk#newInstance} factory method to
 * create an instance of this fragment.
 */
public class memberAsk extends Fragment {

    FragmentMemberAskBinding binding;
    SimpleApi simpleApi;
    ArrayList<MemberAskListModal.MemberAsk> memberAskList = new ArrayList<>();

    public memberAsk() {
        // Required empty public constructor
    }

    public static memberAsk newInstance(String param1, String param2) {
        memberAsk fragment = new memberAsk();
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
        RecyclerView recyclerView = binding.askRecyckerView;

        memberAskAdapter mAdapter = new memberAskAdapter(memberAskList);

        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("ur_clb_id", "1");
        Call<MemberAskListModal> call = simpleApi.memberAsk(params);

        call.enqueue(new Callback<MemberAskListModal>() {
            @Override
            public void onResponse(Call<MemberAskListModal> call, Response<MemberAskListModal> response) {
                if (response.isSuccessful()) {
                    for (MemberAskListModal.MemberAsk memberAsk : response.body().getMemberAskList()) {
                        memberAskList.add(memberAsk);
                        mAdapter.updateMemberAskList(memberAsk);
                    }
                }
            }

            @Override
            public void onFailure(Call<MemberAskListModal> call, Throwable t) {
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
        binding = FragmentMemberAskBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}