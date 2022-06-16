package com.example.bce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class BusinessLeadSendAdapter extends RecyclerView.Adapter<BusinessLeadSendAdapter.BusinessLeadDetailSendViewHolder> {

//    ArrayList<BusinessLeadDetailModalClass.Send> businessLead = new ArrayList<>();
//
//    public void updateBusinessLead(BusinessLeadDetailModalClass.Send item){
//        if (!businessLead.contains(item))
//            businessLead.add(item);
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public BusinessLeadDetailSendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessLeadDetailSendViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class BusinessLeadDetailSendViewHolder extends RecyclerView.ViewHolder{

        TextView date, title, name, stars, phone, remark, amt;
        TextInputEditText status;
        public BusinessLeadDetailSendViewHolder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.date);
            this.title = itemView.findViewById(R.id.title);
            this.name = itemView.findViewById(R.id.profileName);
            this.stars = itemView.findViewById(R.id.rating);
            this.phone = itemView.findViewById(R.id.phone);
            this.remark = itemView.findViewById(R.id.remark);
            this.amt = itemView.findViewById(R.id.amount);
            this.status = itemView.findViewById(R.id.BusinessStatus);
        }
    }
}
