package com.example.bce.API;

import com.example.bce.Models.BusinessGivenModalClass;
import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.Models.ConnectionListModalClass;
import com.example.bce.Models.DialogBoxModalClass;
import com.example.bce.Models.FavouriteConnectionModalClass;
import com.example.bce.Models.GuestListModalClass;
import com.example.bce.Models.HelpDeskModalClass;
import com.example.bce.Models.HomeModalClass;
import com.example.bce.Models.MemberAskListModal;
import com.example.bce.Models.MembersList;
import com.example.bce.Models.PaymentModalClass;
import com.example.bce.Models.ResponseModalClass;
import com.example.bce.Models.LoginModalClass;
import com.example.bce.Models.ProfileModalClass;
import com.example.bce.Models.ReviewListModalClass;
import com.example.bce.Models.SendReviewModalClass;
import com.example.bce.Models.TenderListModalClass;
import com.example.bce.Utils.AppUrls;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
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

    @POST(AppUrls.EditProfileLocation)
    @FormUrlEncoded
    Call<ResponseModalClass> editLocation(@FieldMap Map<String,String> params);

    @POST(AppUrls.UpdateOtherBasic)
    @FormUrlEncoded
    Call<ResponseModalClass> updateOtherBasic(@FieldMap Map<String,String> params);

    @POST(AppUrls.UpdateProfileOtherInfo)
    @FormUrlEncoded
    Call<ResponseModalClass> updateOtherProfile(@FieldMap Map<String,String> params);

    @POST(AppUrls.UpdateProfileNominee)
    @FormUrlEncoded
    Call<ResponseModalClass> updateNominee(@FieldMap Map<String,String> params);

    @POST(AppUrls.membeershipList)
    @FormUrlEncoded
    Call<MembersList> membershipList(@FieldMap Map<String,String> params);

    @POST(AppUrls.localmemberlist)
    @FormUrlEncoded
    Call<MembersList> localmemberlist(@FieldMap Map<String,String> params);

    @POST(AppUrls.sendReview)
    @FormUrlEncoded
    Call<DialogBoxModalClass> sendReview(@FieldMap Map<String,String> params);

    @POST(AppUrls.sendThanksNote)
    @FormUrlEncoded
    Call<DialogBoxModalClass> sendThankNote(@FieldMap Map<String,String> params);

    @POST(AppUrls.SendLead)
    @FormUrlEncoded
    Call<DialogBoxModalClass> SendLead(@FieldMap Map<String,String> params);

    @POST(AppUrls.reviewList)
    @FormUrlEncoded
    Call<ReviewListModalClass> reviewList(@FieldMap Map<String,String> params);

    @POST(AppUrls.BusinessReceive)
    @FormUrlEncoded
    Call<BusinessLeadDetailModalClass> businessRecieve(@FieldMap Map<String,String> params);

    @POST(AppUrls.guestList)
    @FormUrlEncoded
    Call<GuestListModalClass> guestList(@FieldMap Map<String,String> params);

    @POST(AppUrls.addGuestList)
    @FormUrlEncoded
    Call<DialogBoxModalClass> addGuest(@FieldMap Map<String,String> params);

    @POST(AppUrls.home)
    @FormUrlEncoded
    Call<HomeModalClass> getHome(@FieldMap Map<String,String> params);

    @POST(AppUrls.tenderList)
    Call<TenderListModalClass> tenderList();

    @POST(AppUrls.paymentList)
    @FormUrlEncoded
    Call<PaymentModalClass> getPayment(@FieldMap Map<String,String> params);

    @POST(AppUrls.helpdesklist)
    @FormUrlEncoded
    Call<HelpDeskModalClass> helpdesklist(@FieldMap Map<String,String> params);

    @POST(AppUrls.helpDeskSubmit)
    @FormUrlEncoded
    Call<DialogBoxModalClass> helpDeskSubmit(@FieldMap Map<String,String> params);

    @POST(AppUrls.askHistoryList)
    @FormUrlEncoded
    Call<MemberAskListModal> askHistoryList(@FieldMap Map<String,String> params);

    @POST(AppUrls.submitAsk)
    @FormUrlEncoded
    Call<DialogBoxModalClass> submitAsk(@FieldMap Map<String,String> params);

    @POST(AppUrls.sendRequest)
    @FormUrlEncoded
    Call<DialogBoxModalClass> sendRequest(@FieldMap Map<String,String> params);

    @POST(AppUrls.acceptRequest)
    @FormUrlEncoded
    Call<DialogBoxModalClass> acceptRequest(@FieldMap Map<String,String> params);

    @POST(AppUrls.favoriteConnection)
    @FormUrlEncoded
    Call<FavouriteConnectionModalClass> favouriteconnection(@FieldMap Map<String,String> params);

    @POST(AppUrls.connectionlist)
    @FormUrlEncoded
    Call<ConnectionListModalClass> connectionlist(@FieldMap Map<String,String> params);

    @POST(AppUrls.BusinessGiven)
    @FormUrlEncoded
    Call<BusinessGivenModalClass> BusinessGiven(@FieldMap Map<String,String> params);

    @POST(AppUrls.leadstatusupdate)
    @FormUrlEncoded
    Call<DialogBoxModalClass> leadstatusupdate(@FieldMap Map<String,String> params);


}
