package com.example.bce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bce.Models.MemberAskListModal;
import com.example.bce.R;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class memberAskAdapter extends RecyclerView.Adapter<memberAskAdapter.memberAskViewHolder> {


    ArrayList<MemberAskListModal.MemberAsk> memberAskList = new ArrayList<>();

    public memberAskAdapter(ArrayList<MemberAskListModal.MemberAsk> memberAskList) {
        this.memberAskList = memberAskList;
    }

    @NonNull
    @Override
    public memberAskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.member_ask_item, parent, false);
        memberAskViewHolder viewHolder = new memberAskViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull memberAskViewHolder holder, int position) {
        holder.memberName.setText(memberAskList.get(position).getFrom().getName());
        holder.memberClub.setText(memberAskList.get(position).getFrom().getClubName());
        holder.memberDesig.setText(memberAskList.get(position).getFrom().getCategory());
        holder.date.setText(memberAskList.get(position).getMeetingDate());
        holder.name.setText(memberAskList.get(position).getName());
        holder.department.setText(memberAskList.get(position).getDeparment());
        holder.company.setText(memberAskList.get(position).getConmpany());
        holder.reason.setText(memberAskList.get(position).getReason());

        if (!memberAskList.get(position).getFrom().getImage().isEmpty())
            Picasso.get().load(memberAskList.get(position).getFrom().getImage()).into(holder.profilePic);
        else
            Picasso.get().load("https://www.freeiconspng.com/uploads/customers-icon-3.png").into(holder.profilePic);

    }

    @Override
    public int getItemCount() {
        return memberAskList.size();
    }

    public void updateMemberAskList(MemberAskListModal.MemberAsk memberAsk) {
        if (!memberAskList.contains(memberAsk))
            memberAskList.add(memberAsk);
        notifyDataSetChanged();
    }

    class memberAskViewHolder extends RecyclerView.ViewHolder {
        TextView date, memberName, memberClub, memberDesig;
        TextInputEditText name, department, company, reason;
        CircleImageView profilePic;

        public memberAskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.date);
            this.memberName = itemView.findViewById(R.id.memberName);
            this.memberClub = itemView.findViewById(R.id.memberClub);
            this.memberDesig = itemView.findViewById(R.id.memberDesig);
            this.name = itemView.findViewById(R.id.askName);
            this.department = itemView.findViewById(R.id.askDepartment);
            this.company = itemView.findViewById(R.id.askCompany);
            this.reason = itemView.findViewById(R.id.askReason);
            this.profilePic = itemView.findViewById(R.id.profilePicMember);


        }
    }
}
