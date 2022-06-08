package com.example.bce;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.Models.LoginErrorModalClass;
import com.example.bce.Models.ProfileModalClass;
import com.example.bce.databinding.FragmentProfileFragBinding;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile_frag#newInstance} factory method to
 * create an instance of this fragment.
 */

public class profile_frag extends Fragment {

    private FragmentProfileFragBinding binding;
    boolean isAllCompanyFieldsChecked = false;
    boolean isAllLocationFieldsChecked = false;
    boolean isAllOtherInfoFieldsChecked = false;
    boolean isAllNomineeFieldsChecked = false;
    boolean isAllBasicInfoFieldsChecked = false;

    SimpleApi simpleApi;

    String user_id;

    public profile_frag() {
        // Required empty public constructor
    }

    public static profile_frag newInstance(String param1, String param2) {
        profile_frag fragment = new profile_frag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        binding = FragmentProfileFragBinding.inflate(inflater, container, false);
        MainActivity activity = (MainActivity) getActivity();
        user_id = activity.getUserId();
        //setEditableFalse();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        setData();

        binding.companyInfoEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setEditableTrue();


            }
        });
        binding.companyInfoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                isAllCompanyFieldsChecked = CheckCompanyFeilds();
                if (isAllCompanyFieldsChecked) {
                    closeKeyboard();
                    //add implementation if all fields are checked..
                    simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
                    Map<String, String> params = new HashMap<>();
                    params.put("ur_id", user_id);
                    params.put("ur_name", binding.nameInp.getText().toString());
                    params.put("cd_company", binding.companyNameInp.getText().toString());
                    params.put("cd_desig", binding.designationInp.getText().toString());
                    params.put("cd_website", binding.websiteInp.getText().toString());
                    params.put("cd_tags", binding.tagsInp.getText().toString());
                    params.put("ur_dob", binding.dobInp.getText().toString());
                    params.put("cd_photo", binding.companyLogoInp.getText().toString());
                    params.put("ur_photo", binding.profilePicInp.getText().toString());
                    Call<LoginErrorModalClass> call = simpleApi.editProfile(params);
                    call.enqueue(new Callback<LoginErrorModalClass>() {
                        @Override
                        public void onResponse(Call<LoginErrorModalClass> call, Response<LoginErrorModalClass> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(getContext(), response.body().getMsg(),Toast.LENGTH_SHORT).show();
                                setData();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginErrorModalClass> call, Throwable t) {
                            call.cancel();
                        }
                    });
                }
            }
        });

        binding.locationInfoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllLocationFieldsChecked = CheckLocationFeilds();
                if (isAllCompanyFieldsChecked) {
                    //add implementation if all fields are checked..
                }
            }
        });

        binding.otherInfoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllOtherInfoFieldsChecked = CheckOtherFeilds();
                if (isAllCompanyFieldsChecked) {
                    //add implementation if all fields are checked..
                }
            }
        });

        binding.nomineeUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllNomineeFieldsChecked = CheckNomineeFeilds();
                if (isAllCompanyFieldsChecked) {
                    //add implementation if all fields are checked..
                }
            }
        });

        binding.otherBasicInfoUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                closeKeyboard();

                simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
                Map<String, String> params = new HashMap<>();

                params.put("ur_id", user_id);
                params.put("cd_assorg", binding.otherOrganizationInp.getText().toString());
                params.put("cd_business_detail", binding.businessDetailInp.getText().toString());
                params.put("cd_experiences", binding.expInp.getText().toString());

                Call<LoginErrorModalClass> call = simpleApi.updateOtherBasic(params);
                call.enqueue(new Callback<LoginErrorModalClass>() {
                    @Override
                    public void onResponse(Call<LoginErrorModalClass> call, Response<LoginErrorModalClass> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getContext(), response.body().getMsg(),Toast.LENGTH_SHORT).show();
                            setData();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginErrorModalClass> call, Throwable t) {
                            call.cancel();
                    }
                });
                //isAllBasicInfoFieldsChecked = CheckBasicFeilds();
//                if (isAllCompanyFieldsChecked) {
//                    //add implementation if all fields are checked..
//                }
            }
        });


        super.onViewCreated(view, savedInstanceState);
    }


    private Boolean CheckCompanyFeilds() {
        if (binding.nameInp.length() == 0) {
            binding.nameInp.setError("This field is required");
            return false;
        }
        if (binding.emailIdInp.length() == 0) {
            binding.emailIdInp.setError("This field is required");
            return false;
        }
        if (binding.PhoneInp.length() == 0) {
            binding.PhoneInp.setError("This field is required");
            return false;
        } else if (binding.PhoneInp.length() != 10) {
            binding.PhoneInp.setError("Phone no. must be of 10 digits");
            return false;
        }
        if (binding.companyNameInp.length() == 0) {
            binding.companyNameInp.setError("This field is required");
            return false;
        }
        if (binding.designationInp.length() == 0) {
            binding.designationInp.setError("This field is required");
            return false;
        }
        if (binding.websiteInp.length() == 0) {
            binding.websiteInp.setError("This field is required");
            return false;
        }
        if (binding.tagsInp.length() == 0) {
            binding.tagsInp.setError("This field is required");
            return false;
        }
        if (binding.dobInp.length() == 0) {
            binding.dobInp.setError("This field is required");
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(false);
        try {
            Date d1 = sdf.parse(binding.dobInp.getText().toString());
        } catch (Exception e) {
            binding.dobInp.setError("please check the date format");
            return false;
        }

        return true;
    }

    private Boolean CheckLocationFeilds() {
        if (binding.addressInp.length() == 0) {
            binding.addressInp.setError("This field is required");
            return false;
        }
        if (binding.countryInp.length() == 0) {
            binding.countryInp.setError("This field is required");
            return false;
        }
        if (binding.stateInp.length() == 0) {
            binding.stateInp.setError("This field is required");
            return false;
        }
        if (binding.districtInp.length() == 0) {
            binding.districtInp.setError("This field is required");
            return false;
        }
        if (binding.cityInp.length() == 0) {
            binding.cityInp.setError("This field is required");
            return false;
        }
        return true;
    }

    private Boolean CheckOtherFeilds() {
        if (binding.panNoInp.length() == 0) {
            binding.panNoInp.setError("This field is required");
            return false;
        }
        if (binding.aadharNoInp.length() == 0) {
            binding.aadharNoInp.setError("This field is required");
            return false;
        }
        if (binding.gstNoInp.length() == 0) {
            binding.gstNoInp.setError("This field is required");
            return false;
        }
        return true;
    }

    private Boolean CheckNomineeFeilds() {
        if (binding.nominee1name.length() == 0) {
            binding.nominee1name.setError("This field is required");
            return false;
        }
        if (binding.nominee1mobile.length() == 0) {
            binding.nominee1mobile.setError("This field is required");
            return false;
        } else if (binding.nominee1mobile.length() != 10) {
            binding.nominee1mobile.setError("Phone no. must be of 10 digits");
            return false;
        }
        if (binding.nominee1desig.length() == 0) {
            binding.nominee1desig.setError("This field is required");
            return false;
        }

        if (binding.nominee2name.length() == 0) {
            binding.nominee2name.setError("This field is required");
            return false;
        }
        if (binding.nominee2mobile.length() == 0) {
            binding.nominee2mobile.setError("This field is required");
            return false;
        } else if (binding.nominee2mobile.length() != 10) {
            binding.nominee2mobile.setError("Phone no. must be of 10 digits");
            return false;
        }
        if (binding.nominee2desig.length() == 0) {
            binding.nominee2desig.setError("This field is required");
            return false;
        }

        if (binding.nominee3name.length() == 0) {
            binding.nominee3name.setError("This field is required");
            return false;
        }
        if (binding.nominee3mobile.length() == 0) {
            binding.nominee3mobile.setError("This field is required");
            return false;
        } else if (binding.nominee3mobile.length() != 10) {
            binding.nominee3mobile.setError("Phone no. must be of 10 digits");
            return false;
        }
        if (binding.nominee3desig.length() == 0) {
            binding.nominee3desig.setError("This field is required");
            return false;
        }
        return true;
    }


    void setData() {
        simpleApi = RetrofitInstance.getClient().create(SimpleApi.class);
        Map<String, String> params = new HashMap<>();
        params.put("user_id", user_id);
        Call<ProfileModalClass> call = simpleApi.getProfile(params);
        call.enqueue(new Callback<ProfileModalClass>() {
            @Override
            public void onResponse(Call<ProfileModalClass> call, Response<ProfileModalClass> response) {
                if (response.isSuccessful()) {
                    ProfileModalClass profileModalClass = response.body();

                    //profile
                    Picasso.get().load(profileModalClass.getImg()).into(binding.profileImage);
                    binding.nameProfile.setText(profileModalClass.getUserInfo().get(0).getUrName());
                    binding.clubNameProfile.setText("Club: " + profileModalClass.getUserInfo().get(0).getClbName());
                    binding.CompanyProfile.setText("Company: " + profileModalClass.getUserInfo().get(0).getCdCompany());
                    binding.locationProfile.setText("Location: " + profileModalClass.getUserInfo().get(0).getDistName() + ", " + profileModalClass.getUserInfo().get(0).getCitName());


                    //company Info
                    binding.nameInp.setText(profileModalClass.getUserInfo().get(0).getUrName());
                    binding.emailIdInp.setText(profileModalClass.getUserInfo().get(0).getUrEmail());
                    binding.PhoneInp.setText(profileModalClass.getUserInfo().get(0).getUrMobile());
                    binding.companyNameInp.setText(profileModalClass.getUserInfo().get(0).getCdCompany());
                    binding.designationInp.setText(profileModalClass.getUserInfo().get(0).getCdDesig());
                    binding.websiteInp.setText(profileModalClass.getUserInfo().get(0).getCdWebsite());
                    binding.tagsInp.setText(profileModalClass.getUserInfo().get(0).getCdTags());
                    if (profileModalClass.getUserInfo().get(0).getUrDob() != null)
                        binding.dobInp.setText(profileModalClass.getUserInfo().get(0).getUrDob().toString());
                    else
                        binding.dobInp.setText("-");
                    binding.profilePicInp.setText(profileModalClass.getUserInfo().get(0).getUrPhoto());
                    binding.companyLogoInp.setText(profileModalClass.getUserInfo().get(0).getCdPhoto());

                    //Location Info
                    binding.addressInp.setText(profileModalClass.getUserInfo().get(0).getUrAddress());
                    binding.districtInp.setText(profileModalClass.getUserInfo().get(0).getDistName());
                    binding.cityInp.setText(profileModalClass.getUserInfo().get(0).getCitName());

                    //Other Info
                    binding.panNoInp.setText(profileModalClass.getUserInfo().get(0).getCdPanNo());
                    binding.aadharNoInp.setText(profileModalClass.getUserInfo().get(0).getCdAadharNo());
                    binding.gstNoInp.setText(profileModalClass.getUserInfo().get(0).getCdGstNo());

                    //Nominee
                    binding.nominee1name.setText(profileModalClass.getUserInfo().get(0).getCdNomineeName1());
                    binding.nominee1mobile.setText(profileModalClass.getUserInfo().get(0).getCdNomineePhone1());
                    binding.nominee1desig.setText(profileModalClass.getUserInfo().get(0).getCdNomineeRel1());

                    binding.nominee2name.setText(profileModalClass.getUserInfo().get(0).getCdNomineeName2());
                    binding.nominee2mobile.setText(profileModalClass.getUserInfo().get(0).getCdNomineePhone2());
                    binding.nominee2desig.setText(profileModalClass.getUserInfo().get(0).getCdNomineeRel2());

                    binding.nominee3name.setText(profileModalClass.getUserInfo().get(0).getCdNomineeName3());
                    binding.nominee3mobile.setText(profileModalClass.getUserInfo().get(0).getCdNomineePhone3());
                    binding.nominee3desig.setText(profileModalClass.getUserInfo().get(0).getCdNomineeRel3());

                    //other basic info
                    binding.otherOrganizationInp.setText(profileModalClass.getUserInfo().get(0).getCdAssorg());
                    binding.businessDetailInp.setText(profileModalClass.getUserInfo().get(0).getCdBusinessDetail());
                    binding.expInp.setText(profileModalClass.getUserInfo().get(0).getCdExperiences());


                } else {

                }
            }

            @Override
            public void onFailure(Call<ProfileModalClass> call, Throwable t) {
                call.cancel();
            }
        });

    }

    void setEditableFalse() {

//        binding.nameInp.setEnabled(false);
//        binding.emailIdInp.setFocusable(false);
//        binding.PhoneInp.setFocusable(false);
//        binding.companyNameInp.setFocusable(false);
//        binding.designationInp.setFocusable(false);
//        binding.websiteInp.setFocusable(false);
//        binding.tagsInp.setFocusable(false);
//        binding.dobInp.setFocusable(false);
//        binding.profilePicInp.setFocusable(false);
//
//        //Location Info
//        binding.addressInp.setFocusable(false);
//        binding.districtInp.setFocusable(false);
//        binding.cityInp.setFocusable(false);
//
//        //Other Info
//        binding.panNoInp.setFocusable(false);
//        binding.aadharNoInp.setFocusable(false);
//        binding.gstNoInp.setFocusable(false);
//
//        //Nominee
//        binding.nominee1name.setFocusable(false);
//        binding.nominee1mobile.setFocusable(false);
//        binding.nominee1desig.setFocusable(false);
//
//        binding.nominee2name.setFocusable(false);
//        binding.nominee2mobile.setFocusable(false);
//        binding.nominee2desig.setFocusable(false);
//
//        binding.nominee3name.setFocusable(false);
//        binding.nominee3mobile.setFocusable(false);
//        binding.nominee3desig.setFocusable(false);
//
//        //other basic info
//        binding.otherOrganizationInp.setFocusable(false);
//        binding.businessDetailInp.setFocusable(false);
//        binding.expInp.setFocusable(false);
//


        //binding.nameInp.setClickable(false);
        binding.emailIdInp.setClickable(false);
        binding.PhoneInp.setClickable(false);
        binding.companyNameInp.setClickable(false);
        binding.designationInp.setClickable(false);
        binding.websiteInp.setClickable(false);
        binding.tagsInp.setClickable(false);
        binding.dobInp.setClickable(false);
        binding.profilePicInp.setClickable(false);

        //Location Info
        binding.addressInp.setClickable(false);
        binding.districtInp.setClickable(false);
        binding.cityInp.setClickable(false);

        //Other Info
        binding.panNoInp.setClickable(false);
        binding.aadharNoInp.setClickable(false);
        binding.gstNoInp.setClickable(false);

        //Nominee
        binding.nominee1name.setClickable(false);
        binding.nominee1mobile.setClickable(false);
        binding.nominee1desig.setClickable(false);

        binding.nominee2name.setClickable(false);
        binding.nominee2mobile.setClickable(false);
        binding.nominee2desig.setClickable(false);

        binding.nominee3name.setClickable(false);
        binding.nominee3mobile.setClickable(false);
        binding.nominee3desig.setClickable(false);

        //other basic info
        binding.otherOrganizationInp.setClickable(false);
        binding.businessDetailInp.setClickable(false);
        binding.expInp.setClickable(false);

    }

    void setEditableTrue() {

//        binding.nameInp.setEnabled(true);
//        binding.emailIdInp.setFocusable(true);
//        binding.PhoneInp.setFocusable(true);
//        binding.companyNameInp.setFocusable(true);
//        binding.designationInp.setFocusable(true);
//        binding.websiteInp.setFocusable(true);
//        binding.tagsInp.setFocusable(true);
//        binding.dobInp.setFocusable(true);
//        binding.profilePicInp.setFocusable(true);
//
//        //Location Info
//        binding.addressInp.setFocusable(true);
//        binding.districtInp.setFocusable(true);
//        binding.cityInp.setFocusable(true);
//
//        //Other Info
//        binding.panNoInp.setFocusable(true);
//        binding.aadharNoInp.setFocusable(true);
//        binding.gstNoInp.setFocusable(true);
//
//        //Nominee
//        binding.nominee1name.setFocusable(true);
//        binding.nominee1mobile.setFocusable(true);
//        binding.nominee1desig.setFocusable(true);
//
//        binding.nominee2name.setFocusable(true);
//        binding.nominee2mobile.setFocusable(true);
//        binding.nominee2desig.setFocusable(true);
//
//        binding.nominee3name.setFocusable(true);
//        binding.nominee3mobile.setFocusable(true);
//        binding.nominee3desig.setFocusable(true);
//
//        //other basic info
//        binding.otherOrganizationInp.setFocusable(true);
//        binding.businessDetailInp.setFocusable(true);
//        binding.expInp.setFocusable(true);
//


        //binding.nameInp.setClickable(true);
        binding.emailIdInp.setClickable(true);
        binding.PhoneInp.setClickable(true);
        binding.companyNameInp.setClickable(true);
        binding.designationInp.setClickable(true);
        binding.websiteInp.setClickable(true);
        binding.tagsInp.setClickable(true);
        binding.dobInp.setClickable(true);
        binding.profilePicInp.setClickable(true);

        //Location Info
        binding.addressInp.setClickable(true);
        binding.districtInp.setClickable(true);
        binding.cityInp.setClickable(true);

        //Other Info
        binding.panNoInp.setClickable(true);
        binding.aadharNoInp.setClickable(true);
        binding.gstNoInp.setClickable(true);

        //Nominee
        binding.nominee1name.setClickable(true);
        binding.nominee1mobile.setClickable(true);
        binding.nominee1desig.setClickable(true);

        binding.nominee2name.setClickable(true);
        binding.nominee2mobile.setClickable(true);
        binding.nominee2desig.setClickable(true);

        binding.nominee3name.setClickable(true);
        binding.nominee3mobile.setClickable(true);
        binding.nominee3desig.setClickable(true);

        //other basic info
        binding.otherOrganizationInp.setClickable(true);
        binding.businessDetailInp.setClickable(true);
        binding.expInp.setClickable(true);

    }

    private void closeKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

//    private Boolean CheckBasicFeilds(){
//        if (binding.otherOrganizationInp.length() == 0) {
//            binding.otherOrganizationInp.setError("This field is required");
//            return false;
//        }
//        if (binding.businessDetailInp.length() == 0) {
//            binding.businessDetailInp.setError("This field is required");
//            return false;
//        }
//        if (binding.expInp.length() == 0) {
//            binding.expInp.setError("This field is required");
//            return false;
//        }
//        return true;
//    }

}