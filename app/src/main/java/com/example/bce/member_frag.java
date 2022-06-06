package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bce.Adapters.MemberListAdapter;
import com.example.bce.Models.Members;
import com.example.bce.databinding.FragmentBusinessLeadDetailBinding;
import com.example.bce.databinding.FragmentMemberFragBinding;

import java.util.ArrayList;


public class member_frag extends Fragment {

    private FragmentMemberFragBinding binding;
    MemberListAdapter mAdapter;
    //ArrayList<Members> membersArrayList;

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
        ArrayList<Members> membersArrayList = new ArrayList<Members>();
        RecyclerView recyclerView = binding.memberListRecyclerView;
        membersArrayList.add(new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"));
        membersArrayList.add(new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"));
        membersArrayList.add(new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"));
        membersArrayList.add(new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"));
        membersArrayList.add(new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"));
        membersArrayList.add(new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"));
//
//        Members[] memberList = new Members[]{
//                new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"),
//                new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"),
//                new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"),
//                new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer"),
//                new Members("https://static1.srcdn.com/wordpress/wp-content/uploads/2021/08/tom-holland-spider-man.jpg?q=50&fit=crop&w=960&h=500&dpr=1.5", "Vamsee Krishna", "BCE Bhubaneswar", "Developer")
//        };

        //Toast.makeText(getContext(), String.valueOf(membersArrayList.size()), Toast.LENGTH_SHORT).show();

        MemberListAdapter mAdapter = new MemberListAdapter(membersArrayList, getContext(), binding.getRoot());
        //mAdapter.updateMemberList(membersArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(mAdapter);
        super.onViewCreated(view, savedInstanceState);
    }
}