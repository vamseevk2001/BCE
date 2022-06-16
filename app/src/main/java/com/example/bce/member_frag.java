package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
    String user_id;
    ArrayList<Membership> membersArrayList = new ArrayList<>();
    ArrayList<Membership> localMembersArrayList = new ArrayList<>();
    boolean onLocalList = false;

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

        binding = FragmentMemberFragBinding.inflate(inflater, container, false);
        MainActivity activity = (MainActivity) getActivity();
        user_id = activity.getUserId();
        binding.toolbar.setTitle("Members");
        binding.toolbar.setNavigationIcon(R.drawable.ic_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getActivity().onBackPressed();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment,new home_frag()).commit();

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //ArrayList<Members> membersArrayList = new ArrayList<Members>();
        localMemberList();
        binding.globalSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(getResources().getColor(R.color.red));
                binding.localSearch.setBackgroundColor(getResources().getColor(R.color.darkGreyFont));
                globalMemberList();

            }
        });

        binding.localSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(getResources().getColor(R.color.red));
                binding.globalSearch.setBackgroundColor(getResources().getColor(R.color.darkGreyFont));
                localMemberList();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    void localMemberList() {
        onLocalList = true;
        RecyclerView recyclerView = binding.memberListRecyclerView;

        MemberListAdapter mAdapter = new MemberListAdapter(localMembersArrayList, getContext(), binding.getRoot(), this);

        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        Call<MembersList> call = simpleApi.localmemberlist(params);
        call.enqueue(new Callback<MembersList>() {
            @Override
            public void onResponse(Call<MembersList> call, Response<MembersList> response) {
                if (response.isSuccessful()) {
                    for (Membership member : response.body().getMembershipList()) {
                        //Log.d("listsize", String.valueOf(response.body().getMembershipList().size()));
                        localMembersArrayList.add(member);
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
    }

    void globalMemberList() {
        onLocalList = false;
        RecyclerView recyclerView = binding.memberListRecyclerView;

        MemberListAdapter mAdapter = new MemberListAdapter(membersArrayList, getContext(), binding.getRoot(), this);

        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        Call<MembersList> call = simpleApi.membershipList(params);
        call.enqueue(new Callback<MembersList>() {
            @Override
            public void onResponse(Call<MembersList> call, Response<MembersList> response) {
                if (response.isSuccessful()) {
                    for (Membership member : response.body().getMembershipList()) {
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
    }


    @Override
    public void viewMemberDetails(int position) {

        if (onLocalList) {
            NavDirections action = member_fragDirections.actionMemberFragToMemberDetails(localMembersArrayList.get(position).getId());
            Navigation.findNavController(binding.getRoot()).navigate(action);
        }
        else {
            NavDirections action = member_fragDirections.actionMemberFragToMemberDetails(membersArrayList.get(position).getId());
            Navigation.findNavController(binding.getRoot()).navigate(action);
        }
    }
}