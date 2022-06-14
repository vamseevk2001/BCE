package com.example.bce;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Models.DialogBoxModalClass;
import com.example.bce.databinding.FragmentAddGuestBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddGuest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddGuest extends Fragment {

    FragmentAddGuestBinding binding;
    String user_id;
    SimpleApi simpleApi;
    Boolean isAllFieldsChecked;

    public AddGuest() {
        // Required empty public constructor
    }

    public static AddGuest newInstance(String param1, String param2) {
        AddGuest fragment = new AddGuest();
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

        binding.date.setMinDate(System.currentTimeMillis());

        binding.addGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllFieldsChecked = CheckOtherFeilds();
                if (isAllFieldsChecked) {
                    closeKeyboard();
                   // user_id = AddGuestArgs.fromBundle(getArguments()).getUserId();
                    MainActivity activity = (MainActivity) getActivity();
                    user_id = activity.getUserId();
                    simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
                    Map<String, String> params = new HashMap<>();
                    params.put("user_id", user_id);
                    params.put("g_name", binding.nameInp.getText().toString());
                    params.put("g_email", binding.gmailInp.getText().toString());
                    params.put("g_phone", binding.phoneInp.getText().toString());
                    params.put("g_business", binding.businessInp.getText().toString());
                    params.put("g_meeting_dt", binding.date.getDayOfMonth() + "-" + binding.date.getMonth() + "-" + binding.date.getYear());
                    Call<DialogBoxModalClass> call = simpleApi.addGuest(params);

                    call.enqueue(new Callback<DialogBoxModalClass>() {
                        @Override
                        public void onResponse(Call<DialogBoxModalClass> call, Response<DialogBoxModalClass> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addGuest_to_guestList);
                            }
                            else {
                                Toast.makeText(getContext(), response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DialogBoxModalClass> call, Throwable t) {
                            call.cancel();
                        }
                    });
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddGuestBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private Boolean CheckOtherFeilds() {
        if (binding.nameInp.length() == 0) {
            binding.nameInp.setError("This field is required");
            return false;
        }
        if (binding.phoneInp.length() == 0) {
            binding.phoneInp.setError("This field is required");
            return false;
        } else if (binding.phoneInp.length() != 10) {
            binding.phoneInp.setError("Phone no. must be of 10 digits");
            return false;
        }
        if (binding.gmailInp.length() == 0) {
            binding.gmailInp.setError("This field is required");
            return false;
        }

        if (binding.businessInp.length() == 0) {
            binding.businessInp.setError("This field is required");
            return false;
        }
//        if (binding.date.is == 0) {
//            binding.date.setError("This field is required");
//            return false;
//        }
        return true;
    }


    private void closeKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

}