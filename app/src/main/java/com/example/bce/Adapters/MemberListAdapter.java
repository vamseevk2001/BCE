package com.example.bce.Adapters;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bce.Models.MemberShipListModalClass;
import com.example.bce.Models.Members;
import com.example.bce.Models.Membership;
import com.example.bce.Models.ProfileModalClass;
import com.example.bce.R;
import com.example.bce.Utils.ApiCalls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MemberListAdapter extends RecyclerView.Adapter<MemberListAdapter.MemberListViewHolder> implements ApiCalls.APIResult {

    private ArrayList<MemberShipListModalClass> MemberItemList = new ArrayList<MemberShipListModalClass>();
    private ViewMemberDetailInterface viewMemberDetailInterface;

    private String profile = "";
    private Context con;
    private View fragmentView;

    public MemberListAdapter(ArrayList<MemberShipListModalClass> memberItemList, Context con, View fragmentView, ViewMemberDetailInterface viewMemberDetailInterface) {
        this.MemberItemList = memberItemList;
        this.con = con;
        this.fragmentView = fragmentView;
        this.viewMemberDetailInterface = viewMemberDetailInterface;
    }

    public void updateList(ArrayList<MemberShipListModalClass> newList){
//        MemberItemList.clear();
//        MemberItemList.addAll(newList);
        MemberItemList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MemberListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.member_item, parent, false);
        MemberListViewHolder viewHolder = new MemberListViewHolder(listItem, viewMemberDetailInterface);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.memberName.setText(MemberItemList.get(position).getMembership().getName().toString());
        holder.memberClub.setText(MemberItemList.get(position).getMembership().getClubName());
        holder.memberDesig.setText(MemberItemList.get(position).getMembership().getCategory() + "-" + MemberItemList.get(position).getMembership().getSubCategory());

        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMemberDetailInterface.viewMemberDetails(MemberItemList.get(position).getMembership());
            }
        });

        //Picasso.get().load(MemberItemList.get(position).getProfilePic()).into(holder.profilePic);
        if (!MemberItemList.get(position).getProfilePic().isEmpty())
            Picasso.get().load(MemberItemList.get(position).getProfilePic()).into(holder.profilePic);
        else
            Picasso.get().load("https://www.freeiconspng.com/uploads/customers-icon-3.png").into(holder.profilePic);
//        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Navigation.findNavController(fragmentView).navigate(R.id.action_member_frag_to_memberDetails);
//            }
//        });

//        ApiCalls apiCalls = new ApiCalls();
//        ProfileModalClass profileModalClass = apiCalls.getProfile(MemberItemList.get(position).getId(), this);
      //Picasso.get().load(profile).into(holder.profilePic);


//
//        Log.d("VAMSEE KRISHANANAAANANA", "onBindViewHolder: "+profile.toString());

    }


    @Override
    public int getItemCount() {
        return MemberItemList.size();
    }

    public void updateMemberList(MemberShipListModalClass members) {
        //MemberItemList.clear();
        if (!MemberItemList.contains(members))
            MemberItemList.add(members);
        notifyDataSetChanged();
    }



    @Override
    public void success(ProfileModalClass profileModalClass) {
        this.profile = profileModalClass.getImg();
    }

    @Override
    public void error(Throwable t) {

    }

    public static class MemberListViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView profilePic;
        public TextView memberName, memberClub, memberDesig;
        public TextView viewDetails;
        ViewMemberDetailInterface viewMemberDetailInterface;

        public MemberListViewHolder(@NonNull View itemView, ViewMemberDetailInterface viewMemberDetailInterface) {
            super(itemView);
            this.profilePic = itemView.findViewById(R.id.profilePicMember);
            this.memberName = (TextView) itemView.findViewById(R.id.memberName);
            this.memberClub = itemView.findViewById(R.id.memberClub);
            this.memberDesig = itemView.findViewById(R.id.memberDesig);
            this.viewDetails = itemView.findViewById(R.id.viewMemberDetails);
            this.viewMemberDetailInterface = viewMemberDetailInterface;

        }


    }

    public interface ViewMemberDetailInterface{
        void viewMemberDetails(Membership member);
    }


}



