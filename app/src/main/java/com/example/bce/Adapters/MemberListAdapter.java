package com.example.bce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bce.Models.Members;
import com.example.bce.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberListViewHolder> {

    private ArrayList<Members> MemberItemList;

    private Context con;
    private View fragmentView;

    public MemberListAdapter(ArrayList<Members> memberItemList, Context con, View fragmentView) {
        this.MemberItemList = memberItemList;
        this.con = con;
        this.fragmentView = fragmentView;
    }

    @NonNull
    @Override
    public MemberListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.member_item, parent, false);
        MemberListViewHolder viewHolder = new MemberListViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberListViewHolder holder, int position) {
        holder.memberName.setText(MemberItemList.get(position).getName().toString());
        holder.memberClub.setText(MemberItemList.get(position).getClubName());
        holder.memberDesig.setText(MemberItemList.get(position).getDesignation());
        Picasso.get().load(MemberItemList.get(position).getProfilePic()).into(holder.profilePic);
        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(fragmentView).navigate(R.id.action_member_frag_to_memberDetails);
            }
        });
    }


    @Override
    public int getItemCount() {
        return MemberItemList.size();
    }

    public void updateMemberList(ArrayList<Members> members) {
        MemberItemList.clear();
        MemberItemList.addAll(members);
        notifyDataSetChanged();
    }

    public static class MemberListViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView profilePic;
        public TextView memberName, memberClub, memberDesig;
        public TextView viewDetails;

        public MemberListViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profilePic = itemView.findViewById(R.id.profilePicMember);
            this.memberName = (TextView) itemView.findViewById(R.id.memberName);
            this.memberClub = itemView.findViewById(R.id.memberClub);
            this.memberDesig = itemView.findViewById(R.id.memberDesig);
            this.viewDetails = itemView.findViewById(R.id.viewMemberDetails);
        }
    }


}

//interface ViewMemberDetailInterface{
//    void viewMemberDetails(Members member);
//}

