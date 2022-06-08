package com.example.bce;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setData();

        binding.companyInfoSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isAllCompanyFieldsChecked = CheckCompanyFeilds();
                if (isAllCompanyFieldsChecked) {
                    //add implementation if all fields are checked..
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
            binding.companyNameInp.setError("This field is required");
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
                if (response.isSuccessful()){
                    ProfileModalClass profileModalClass = response.body();

                    //profile
                    Picasso.get().load(profileModalClass.getImg()).into(binding.profileImage);
                    binding.nameProfile.setText(profileModalClass.getUserInfo().get(0).getUrName());
                    binding.clubNameProfile.setText("Club: "+profileModalClass.getUserInfo().get(0).getClbName());
                    binding.CompanyProfile.setText("Company: "+profileModalClass.getUserInfo().get(0).getCdCompany());
                    binding.locationProfile.setText("Location: "+profileModalClass.getUserInfo().get(0).getDistName()+", "+profileModalClass.getUserInfo().get(0).getCitName());


                    //company Info
                    binding.nameInp.setText(profileModalClass.getUserInfo().get(0).getUrName());
                    binding.emailIdInp.setText(profileModalClass.getUserInfo().get(0).getUrEmail());
                    binding.PhoneInp.setText(profileModalClass.getUserInfo().get(0).getUrMobile());
                    binding.companyNameInp.setText(profileModalClass.getUserInfo().get(0).getCdCompany());
                    binding.designationInp.setText(profileModalClass.getUserInfo().get(0).getCdDesig());
                    binding.websiteInp.setText(profileModalClass.getUserInfo().get(0).getCdWebsite());
                    binding.tagsInp.setText(profileModalClass.getUserInfo().get(0).getCdTags());
                    if(profileModalClass.getUserInfo().get(0).getUrDob() != null)
                        binding.dobInp.setText(profileModalClass.getUserInfo().get(0).getUrDob().toString());
                    else
                        binding.dobInp.setText("-");
                    binding.profilePicInp.setText(profileModalClass.getUserInfo().get(0).getCdPhoto());

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





                }else {

                }
            }

            @Override
            public void onFailure(Call<ProfileModalClass> call, Throwable t) {
                call.cancel();
            }
        });

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