package com.example.bce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.Models.ReviewItem;
import com.example.bce.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class BusinessLeadDetailAdapter extends RecyclerView.Adapter<BusinessLeadDetailAdapter.BusinessLeadDetailViewHolder> {

    ArrayList<BusinessLeadDetailModalClass.BusinessReceiveList> businessLead = new ArrayList<>();

    public BusinessLeadDetailAdapter(ArrayList<BusinessLeadDetailModalClass.BusinessReceiveList> businessLead) {
        this.businessLead = businessLead;
    }

    @NonNull
    @Override
    public BusinessLeadDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.business_lead_item, parent, false);
        BusinessLeadDetailViewHolder viewHolder = new BusinessLeadDetailViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessLeadDetailViewHolder holder, int position) {
        holder.date.setText(businessLead.get(position).getDateInfo().getDate());
        holder.stars.setText(businessLead.get(position).getDateInfo().getRating());
        holder.title.setText(businessLead.get(position).getDateInfo().getTitle());

        holder.name.setText(businessLead.get(position).getBusinessLeadDetails().getName());
        holder.phone.setText(businessLead.get(position).getBusinessLeadDetails().getMobile());
        holder.remark.setText(businessLead.get(position).getBusinessLeadDetails().getRemark());

        holder.status.setText(businessLead.get(position).getLeadStatus().getStatus());
        holder.amt.setText("Amount: "+businessLead.get(position).getLeadStatus().getAmount());
    }

    @Override
    public int getItemCount() {
        return businessLead.size();
    }

    public void updateBusinessLead(BusinessLeadDetailModalClass.BusinessReceiveList item){
        if (!businessLead.contains(item))
            businessLead.add(item);
        notifyDataSetChanged();
    }

    static class BusinessLeadDetailViewHolder extends RecyclerView.ViewHolder{

        TextView date, title, name, stars, phone, remark, amt;
        TextInputEditText status;
        public BusinessLeadDetailViewHolder(@NonNull View itemView) {
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
