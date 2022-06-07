package com.example.bce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Models.LoginBody;
import com.example.bce.Models.LoginErrorModalClass;
import com.example.bce.Models.LoginModalClass;
import com.example.bce.databinding.ActivityLoginBinding;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    SimpleApi simpleApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateFeilds()){
                    if(isNetworkConnected()){
                        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
                        LoginBody body = new LoginBody(binding.Phone.getText().toString(), binding.password.getText().toString());
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("user_id", binding.Phone.getText().toString());
                        params.put("password", binding.password.getText().toString());
                        Call<LoginModalClass> call = simpleApi.login(params);
                        call.enqueue(new Callback<LoginModalClass>() {
                            @Override
                            public void onResponse(Call<LoginModalClass> call, Response<LoginModalClass> response) {
                                if(response.isSuccessful()){
                                    Log.d("TAG",response.code()+"");
                                    LoginModalClass loginDetails = response.body();
                                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                }
                                else {
                                    Gson gson = new Gson();
                                    LoginErrorModalClass loginErrorModalClass = gson.fromJson(response.errorBody().charStream(), LoginErrorModalClass.class);
                                    Toast.makeText(getApplicationContext(), loginErrorModalClass.getMsg(), Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onFailure(Call<LoginModalClass> call, Throwable t) {
                                call.cancel();
                            }
                        });
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    Boolean validateFeilds(){
        if (binding.Phone.length() == 0) {
            binding.Phone.setError("This field is required");
            return false;
        }
        if (binding.password.length() == 0) {
            binding.password.setError("This field is required");
            return false;
        }

        return true;
    }

}