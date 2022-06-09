package com.example.bce.API;

import com.example.bce.Models.ResponseModalClass;
import com.example.bce.Models.LoginModalClass;
import com.example.bce.Models.ProfileModalClass;
import com.example.bce.Utils.AppUrls;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SimpleApi {

    @POST(AppUrls.Login)
    @FormUrlEncoded
    Call<LoginModalClass> login(@FieldMap Map<String,String> params);

    @POST(AppUrls.forgetPassword)
    @FormUrlEncoded
    Call<ResponseModalClass> forgotPass(@FieldMap Map<String,String> params);

    @POST(AppUrls.GetProfile)
    @FormUrlEncoded
    Call<ProfileModalClass> getProfile(@FieldMap Map<String,String> params);

    @POST(AppUrls.EditProfile)
    @FormUrlEncoded
    Call<ResponseModalClass> editProfile(@FieldMap Map<String,String> params);

    @POST(AppUrls.UpdateOtherBasic)
    @FormUrlEncoded
    Call<ResponseModalClass> updateOtherBasic(@FieldMap Map<String,String> params);

    @POST(AppUrls.UpdateProfileNominee)
    @FormUrlEncoded
    Call<ResponseModalClass> updateNominee(@FieldMap Map<String,String> params);

}
