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
import com.example.bce.Adapters.ReviewListAdapter;
import com.example.bce.Models.ReviewItem;
import com.example.bce.Models.ReviewListModalClass;
import com.example.bce.databinding.FragmentHomeFragBinding;
import com.example.bce.databinding.FragmentReviewBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {

    FragmentReviewBinding binding;
    SimpleApi simpleApi;
    String user_id;
    ReviewListAdapter mReviewAdapter;
    ArrayList<ReviewItem> reviewList = new ArrayList<>();


    public ReviewFragment() {
        // Required empty public constructor
    }

    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
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

        RecyclerView recyclerView = binding.reviewsRecyclerView;
        mReviewAdapter = new ReviewListAdapter(reviewList);

        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);

        Call<ReviewListModalClass> call = simpleApi.reviewList(params);

        call.enqueue(new Callback<ReviewListModalClass>() {
            @Override
            public void onResponse(Call<ReviewListModalClass> call, Response<ReviewListModalClass> response) {
                if(response.isSuccessful()){
                    for(ReviewItem reviewItem : response.body().getSendList()){
                        reviewList.add(reviewItem);
                        mReviewAdapter.updateReview(reviewItem);
                    }
                }
            }

            @Override
            public void onFailure(Call<ReviewListModalClass> call, Throwable t) {

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(mReviewAdapter);


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReviewBinding.inflate(inflater, container, false);
        MainActivity activity = (MainActivity) getActivity();
        user_id = activity.getUserId();
        return binding.getRoot();
    }
}