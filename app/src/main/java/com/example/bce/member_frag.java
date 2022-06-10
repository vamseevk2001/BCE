package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Adapters.MemberListAdapter;
import com.example.bce.Models.Members;
import com.example.bce.Models.MembersList;
import com.example.bce.Models.Membership;
import com.example.bce.databinding.FragmentBusinessLeadDetailBinding;
import com.example.bce.databinding.FragmentMemberFragBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class member_frag extends Fragment implements MemberListAdapter.ViewMemberDetailInterface {

    private FragmentMemberFragBinding binding;
    MemberListAdapter mAdapter;
    SimpleApi simpleApi;
    ArrayList<Membership> membersArrayList = new ArrayList<>();

    public member_frag() {
        // Required empty public constructor
    }

    public static member_frag newInstance(String param1, String param2) {
        member_frag fragment = new member_frag();
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
        binding = FragmentMemberFragBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //ArrayList<Members> membersArrayList = new ArrayList<Members>();
        RecyclerView recyclerView = binding.memberListRecyclerView;

        MemberListAdapter mAdapter = new MemberListAdapter(membersArrayList, getContext(), binding.getRoot(), this);

        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        Call<MembersList> call = simpleApi.membershipList(params);
        call.enqueue(new Callback<MembersList>() {
            @Override
            public void onResponse(Call<MembersList> call, Response<MembersList> response) {
                if(response.isSuccessful()){
                    for(Membership member : response.body().getMembershipList()){
                        //Log.d("listsize", String.valueOf(response.body().getMembershipList().size()));
                        membersArrayList.add(member);
                        mAdapter.updateMemberList(member);
                    }
                }
            }

            @Override
            public void onFailure(Call<MembersList> call, Throwable t) {
                call.cancel();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(mAdapter);
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void viewMemberDetails(int position) {
        NavDirections action = member_fragDirections.actionMemberFragToMemberDetails(membersArrayList.get(position).getId());
        Navigation.findNavController(binding.getRoot()).navigate(action);
    }
}