package com.example.bce.Adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.Models.ReviewItem;
import com.example.bce.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class BusinessLeadDetailAdapter extends RecyclerView.Adapter<BusinessLeadDetailAdapter.BusinessLeadDetailViewHolder> {

    ArrayList<BusinessLeadDetailModalClass.BusinessReceiveList> businessLead = new ArrayList<>();
    Context context;
    private static final int REQUEST_PHONE_CALL = 1001;

    public BusinessLeadDetailAdapter(FragmentActivity activity, ArrayList<BusinessLeadDetailModalClass.BusinessReceiveList> businessLead) {
        this.businessLead = businessLead;
        this.context = activity;
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
    public void onBindViewHolder(@NonNull BusinessLeadDetailViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.date.setText(businessLead.get(position).getDateInfo().getDate());
        holder.stars.setText(businessLead.get(position).getDateInfo().getRating());
        holder.title.setText(businessLead.get(position).getDateInfo().getTitle());

        holder.name.setText(businessLead.get(position).getBusinessLeadDetails().getName());
        holder.phone.setText(businessLead.get(position).getBusinessLeadDetails().getMobile());
        holder.remark.setText(businessLead.get(position).getBusinessLeadDetails().getRemark());

        holder.status.setText(businessLead.get(position).getLeadStatus().getStatus());
        holder.amt.setText("Amount: "+businessLead.get(position).getLeadStatus().getAmount());

        holder.imageCalling.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                else
                {
                    String number = businessLead.get(position).getBusinessLeadDetails().getMobile();
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+number));
                    context.startActivity(callIntent);
                }
            }
        });
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
        ImageView imageCalling;
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
            this.imageCalling = itemView.findViewById(R.id.imageCalling);
        }
    }
}