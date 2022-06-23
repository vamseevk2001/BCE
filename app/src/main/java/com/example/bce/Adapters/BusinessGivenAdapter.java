package com.example.bce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bce.Models.BusinessGivenModalClass;
import com.example.bce.Models.BusinessLeadDetailModalClass;
import com.example.bce.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class BusinessGivenAdapter extends RecyclerView.Adapter<BusinessGivenAdapter.BusinessGivenViewholder> {

    ArrayList<BusinessGivenModalClass.Receive> businessGivenList = new ArrayList<>();

    public BusinessGivenAdapter(ArrayList<BusinessGivenModalClass.Receive> businessGivenList) {
        this.businessGivenList = businessGivenList;
    }

    @NonNull
    @Override
    public BusinessGivenViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.business_send_item, parent, false);
        BusinessGivenViewholder viewHolder = new BusinessGivenViewholder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessGivenViewholder holder, int position) {
        holder.date.setText(businessGivenList.get(position).getDate());
        holder.remark.setText("Remarks : "+businessGivenList.get(position).getSlipDetails().getRemark());
        holder.amt.setText("Amount : "+businessGivenList.get(position).getSlipDetails().getAmount());
        holder.memberName.setText(businessGivenList.get(position).getReceiveFrom().getName());
        holder.memberClub.setText(businessGivenList.get(position).getReceiveFrom().getClubName());
        holder.memberDesig.setText(businessGivenList.get(position).getReceiveFrom().getCategory());

        if (!businessGivenList.get(position).getReceiveFrom().getImage().isEmpty())
            Picasso.get().load(businessGivenList.get(position).getReceiveFrom().getImage()).into(holder.profilePic);
        else
            Picasso.get().load("https://www.freeiconspng.com/uploads/customers-icon-3.png").into(holder.profilePic);
    }

    @Override
    public int getItemCount() {
        return businessGivenList.size();
    }

    public void updateBusinessGiven(BusinessGivenModalClass.Receive item){
        if (!businessGivenList.contains(item))
            businessGivenList.add(item);
        notifyDataSetChanged();
    }


    class BusinessGivenViewholder extends RecyclerView.ViewHolder{
        TextView date, remark, amt, label, invoice;
        TextView memberName, memberClub, memberDesig;
        CircleImageView profilePic;
        public BusinessGivenViewholder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.date);
            this.remark = itemView.findViewById(R.id.remark);
            this.memberName = itemView.findViewById(R.id.memberName);
            this.memberClub = itemView.findViewById(R.id.memberClub);
            this.memberDesig = itemView.findViewById(R.id.memberDesig);
            this.profilePic = itemView.findViewById(R.id.profilePicMember);
            this.amt = itemView.findViewById(R.id.Amount);
            this.label = itemView.findViewById(R.id.BusinessLabel);
            this.invoice = itemView.findViewById(R.id.invoiceDownload);

        }
    }
}
