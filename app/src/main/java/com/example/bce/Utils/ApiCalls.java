package com.example.bce.Utils;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Models.ProfileModalClass;
import com.example.bce.Models.TenderListModalClass;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalls {

    public ProfileModalClass profileModalClass;

    public ProfileModalClass getProfile(String id, APIResult result){

        final ProfileModalClass[] profileModalClass = new ProfileModalClass[1];

        SimpleApi simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", id);
        Call<ProfileModalClass> call = simpleApi.getProfile(params);
        call.enqueue(new Callback<ProfileModalClass>() {
            @Override
            public void onResponse(Call<ProfileModalClass> call, Response<ProfileModalClass> response) {
                if (response.isSuccessful()) {
                    profileModalClass[0] = response.body();
                    result.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProfileModalClass> call, Throwable t) {
                call.cancel();
            }
        });

        return profileModalClass[0];
    }

    public interface APIResult{
        void success(ProfileModalClass profileModalClass);
        void error(Throwable t);
    }


    void getTenderList(){
        SimpleApi simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Call<TenderListModalClass> call = simpleApi.tenderList();
        call.enqueue(new Callback<TenderListModalClass>() {
            @Override
            public void onResponse(Call<TenderListModalClass> call, Response<TenderListModalClass> response) {

            }

            @Override
            public void onFailure(Call<TenderListModalClass> call, Throwable t) {

            }
        });
    }





}
