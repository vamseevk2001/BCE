package com.example.bce;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Models.ResponseModalClass;
import com.example.bce.databinding.ActivityForgotPasswordBinding;
import com.example.bce.databinding.ActivityVerifyOtpBinding;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class verifyOTP extends AppCompatActivity {
    String email;
    ActivityVerifyOtpBinding binding;
    Boolean isAllFieldsChecked = false;
    SimpleApi simpleApi;
    Boolean otpVerified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyOtpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //getSupportActionBar().hide();

        if (getIntent().hasExtra("email"))
            email = getIntent().getStringExtra("email");


        binding.updatePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog = new ProgressDialog(verifyOTP.this);
                progressDialog.setMessage("Processing...");
                progressDialog.show();

                closeKeyboard();
                isAllFieldsChecked = CheckFeilds();
                if (isAllFieldsChecked) {
                    simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);

                    Map<String, String> params = new HashMap<>();
                    params.put("type", "verify_otp");
                    params.put("email", email);
                    params.put("otp", binding.otp.getText().toString());

                    Call<ResponseModalClass> call = simpleApi.forgotPass(params);
                    call.enqueue(new Callback<ResponseModalClass>() {
                        @Override
                        public void onResponse(Call<ResponseModalClass> call, Response<ResponseModalClass> response) {

                            progressDialog.dismiss();

                            if (response.isSuccessful()) {


                                //Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                otpVerified = true;
                                finish();
                            } else {
                                otpVerified = false;
                                Gson gson = new Gson();
                                ResponseModalClass responseModalClass = gson.fromJson(response.errorBody().charStream(), ResponseModalClass.class);
                                Toast.makeText(getApplicationContext(), responseModalClass.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModalClass> call, Throwable t) {
                            call.cancel();

                            progressDialog.dismiss();
                        }
                    });

                    if (otpVerified) {


                        ProgressDialog progressDialog1 = new ProgressDialog(verifyOTP.this);
                        progressDialog.setMessage("Processing...");
                        progressDialog.show();

                        Map<String, String> params2 = new HashMap<>();
                        params2.put("type", "update_password");
                        params2.put("email", email);
                        params2.put("pass", binding.newPassword.getText().toString());
                        Call<ResponseModalClass> call2 = simpleApi.forgotPass(params2);
                        call2.enqueue(new Callback<ResponseModalClass>() {
                            @Override
                            public void onResponse(Call<ResponseModalClass> call, Response<ResponseModalClass> response) {

                                progressDialog1.dismiss();

                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Gson gson = new Gson();
                                    ResponseModalClass responseModalClass = gson.fromJson(response.errorBody().charStream(), ResponseModalClass.class);
                                    Toast.makeText(getApplicationContext(), responseModalClass.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModalClass> call, Throwable t) {
                                call.cancel();

                                progressDialog1.dismiss();
                            }
                        });

                    }


                }
            }
        });


    }
    private void closeKeyboard() {

        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager manager
                    = (InputMethodManager)
                    getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

    private Boolean CheckFeilds() {
        if (binding.otp.length() == 0) {
            binding.otp.setError("This field is required");
            return false;
        }
        if (binding.newPassword.length() == 0) {
            binding.newPassword.setError("This field is required");
            return false;
        }
        return true;
    }
}