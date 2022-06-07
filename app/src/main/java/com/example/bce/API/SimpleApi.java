package com.example.bce.API;

import com.example.bce.Models.LoginBody;
import com.example.bce.Models.LoginModalClass;
import com.example.bce.Models.ProfileModalClass;
import com.example.bce.Utils.AppUrls;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SimpleApi {

    @POST(AppUrls.Login)
    @FormUrlEncoded
    Call<LoginModalClass> login(@FieldMap Map<String,String> params);

    @POST(AppUrls.forgetPassword)
    Call<LoginModalClass> forgotPass();

    @POST(AppUrls.GetProfile)
    @FormUrlEncoded
    Call<ProfileModalClass> getProfile(@FieldMap Map<String,String> params);

}
