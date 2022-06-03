package com.example.bce;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bce.databinding.FragmentBusinessLeadDetailBinding;
import com.example.bce.databinding.FragmentMemberDetailsBinding;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MemberDetails#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MemberDetails extends Fragment {

    private FragmentMemberDetailsBinding binding;
    TextInputEditText name;
    TextInputEditText mobile;
    TextInputEditText action;
    TextInputEditText remarks;

    TextInputEditText amount;
    TextInputEditText invoice;
    TextInputEditText remarksThanksNote;

    TextInputEditText remarksReview;


    Button newLeadBtn;
    Button thankNoteBtn;
    Button newReviewBtn;


    Boolean isAllBusinessLeadFieldChecked = false;
    Boolean isAllThankNoteFieldChecked = false;
    Boolean isAllReviewFieldsChecked = false;

    public MemberDetails() {
        // Required empty public constructor
    }

    public static MemberDetails newInstance(String param1, String param2) {
        MemberDetails fragment = new MemberDetails();
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
        binding.businessLeadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder sendNewBusinessLeadAlert = new AlertDialog.Builder(getContext());
                View newBusinessLeadView = getLayoutInflater().inflate(R.layout.new_business_lead_dialog_box,null);
                name = newBusinessLeadView.findViewById(R.id.nameInp);
                mobile = newBusinessLeadView.findViewById(R.id.mobileInp);
                action = newBusinessLeadView.findViewById(R.id.businessLeadInp);
                remarks = newBusinessLeadView.findViewById(R.id.remarkInp);
                newLeadBtn = newBusinessLeadView.findViewById(R.id.sendNewBusinessLeadBtn);
                sendNewBusinessLeadAlert.setView(newBusinessLeadView);
                final AlertDialog alertDialog = sendNewBusinessLeadAlert.create();
                alertDialog.setCanceledOnTouchOutside(true);

                newLeadBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isAllBusinessLeadFieldChecked = CheckBusinessLeadFields();
                        if (isAllBusinessLeadFieldChecked) {
                            //
                            alertDialog.dismiss();
                        }
                    }
                });
                alertDialog.show();
            }
        });

        binding.thanksNoteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder thankNoteAlert = new AlertDialog.Builder(getContext());
                View thankNoteView = getLayoutInflater().inflate(R.layout.thanks_note_dialog_box,null);
                amount = thankNoteView.findViewById(R.id.amountInp);
                invoice = thankNoteView.findViewById(R.id.invoiceFile);
                remarksThanksNote = thankNoteView.findViewById(R.id.remarkInpThank);
                thankNoteBtn = thankNoteView.findViewById(R.id.thankNoteSubmit);
                thankNoteAlert.setView(thankNoteView);
                final AlertDialog alertDialog = thankNoteAlert.create();
                alertDialog.setCanceledOnTouchOutside(true);

                thankNoteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isAllThankNoteFieldChecked = CheckThankNoteFields();
                        if (isAllThankNoteFieldChecked) {
                            //
                            alertDialog.dismiss();
                        }
                    }
                });
                alertDialog.show();
            }
        });

        binding.reviewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder sendNewReviewAlert = new AlertDialog.Builder(getContext());
                View newReviewView = getLayoutInflater().inflate(R.layout.new_review_dialog_box,null);

                remarksReview = newReviewView.findViewById(R.id.remarkInpReview);
                newReviewBtn = newReviewView.findViewById(R.id.newReviewBtn);
                sendNewReviewAlert.setView(newReviewView);
                final AlertDialog alertDialog = sendNewReviewAlert.create();
                alertDialog.setCanceledOnTouchOutside(true);

                newReviewBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        isAllReviewFieldsChecked = CheckReviewFields();
                        if (isAllReviewFieldsChecked) {
                            //
                            alertDialog.dismiss();
                        }
                    }
                });
                alertDialog.show();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private Boolean CheckBusinessLeadFields(){
        if (name.length() == 0) {
            name.setError("This field is required");
            return false;
        }
        if (mobile.length() == 0) {
            mobile.setError("This field is required");
            return false;
        }else if(mobile.length() != 10){
            mobile.setError("phone no. must contain 10 digits");
            return false;
        }
        if (action.length() == 0) {
            action.setError("This field is required");
            return false;
        }
        if (remarks.length() == 0) {
            remarks.setError("This field is required");
            return false;
        }

        return true;

    }

    private Boolean CheckThankNoteFields(){
        if (amount.length() == 0) {
            amount.setError("This field is required");
            return false;
        }
        if (invoice.length() == 0) {
            invoice.setError("This field is required");
            return false;
        }
        if (remarksThanksNote.length() == 0) {
            remarksThanksNote.setError("This field is required");
            return false;
        }
        return true;

    }

    private Boolean CheckReviewFields(){

        if (remarksReview.length() == 0) {
            remarksReview.setError("This field is required");
            return false;
        }
        return true;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMemberDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}