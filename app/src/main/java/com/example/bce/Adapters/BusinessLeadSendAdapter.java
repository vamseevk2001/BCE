package com.example.bce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BusinessLeadSendAdapter extends RecyclerView.Adapter<BusinessLeadSendAdapter.BusinessLeadDetailSendViewHolder> {

    ArrayList<BusinessLeadDetailModalClass.Send> businessLeadSend = new ArrayList<>();

    public BusinessLeadSendAdapter(ArrayList<BusinessLeadDetailModalClass.Send> businessLeadSend) {
        this.businessLeadSend = businessLeadSend;
    }

    public void updateBusinessLead(BusinessLeadDetailModalClass.Send item){
        if (!businessLeadSend.contains(item))
            businessLeadSend.add(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BusinessLeadDetailSendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.business_lead_send_item, parent, false);
        BusinessLeadDetailSendViewHolder viewHolder = new BusinessLeadDetailSendViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessLeadDetailSendViewHolder holder, int position) {
        holder.date.setText(businessLeadSend.get(position).getDateInfo().getDate());
        holder.stars.setText(businessLeadSend.get(position).getDateInfo().getRating());
        holder.title.setText(businessLeadSend.get(position).getDateInfo().getTitle());

        holder.name.setText(businessLeadSend.get(position).getBusinessLeadDetails().getName());
        holder.phone.setText(businessLeadSend.get(position).getBusinessLeadDetails().getMobile());
        holder.remark.setText(businessLeadSend.get(position).getBusinessLeadDetails().getRemark());

        holder.memberName.setText(businessLeadSend.get(position).getBusinessLeadGivenTo().getName());
        holder.memberClub.setText(businessLeadSend.get(position).getBusinessLeadGivenTo().getClubName());
        holder.memberDesig.setText(businessLeadSend.get(position).getBusinessLeadGivenTo().getCategory());

        if (!businessLeadSend.get(position).getBusinessLeadGivenTo().getImage().isEmpty())
            Picasso.get().load(businessLeadSend.get(position).getBusinessLeadGivenTo().getImage()).into(holder.profilePic);
        else
            Picasso.get().load("https://www.freeiconspng.com/uploads/customers-icon-3.png").into(holder.profilePic);

    }

    @Override
    public int getItemCount() {
        return businessLeadSend.size();
    }

    static class BusinessLeadDetailSendViewHolder extends RecyclerView.ViewHolder{

        TextView date, title, name, stars, phone, remark, amt;
        TextView memberName, memberClub, memberDesig;
        CircleImageView profilePic;
        public BusinessLeadDetailSendViewHolder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.date);
            this.title = itemView.findViewById(R.id.title);
            this.name = itemView.findViewById(R.id.profileName);
            this.stars = itemView.findViewById(R.id.rating);
            this.phone = itemView.findViewById(R.id.phone);
            this.remark = itemView.findViewById(R.id.remark);
            this.memberName = itemView.findViewById(R.id.memberName);
            this.memberClub = itemView.findViewById(R.id.memberClub);
            this.memberDesig = itemView.findViewById(R.id.memberDesig);
            this.profilePic = itemView.findViewById(R.id.profilePicMember);
        }
    }
}
