package com.example.bce.Adapters;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bce.API.RetrofitInstance;
import com.example.bce.API.SimpleApi;
import com.example.bce.MainActivity;
import com.example.bce.Models.ConnectionListModalClass;
import com.example.bce.Models.DialogBoxModalClass;
import com.example.bce.Models.FavouriteConnectionModalClass;
import com.example.bce.R;
import com.example.bce.Utils.ApiCalls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestReceivedAdapter extends RecyclerView.Adapter<RequestReceivedAdapter.RequestReceiveViewHolder>{

    ArrayList<ConnectionListModalClass.ResuestReceived> resuestReceiveds = new ArrayList<>();
    Context con;
    AcceptRequestInterface acceptRequestInterface;

    public RequestReceivedAdapter(ArrayList<ConnectionListModalClass.ResuestReceived> resuestReceiveds, Context con, AcceptRequestInterface acceptRequestInterface) {
        this.resuestReceiveds = resuestReceiveds;
        this.con = con;
        this.acceptRequestInterface = acceptRequestInterface;
    }

    @NonNull
    @Override
    public RequestReceiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.request_recieved, parent, false);
        RequestReceiveViewHolder viewHolder = new RequestReceiveViewHolder(listItem, acceptRequestInterface);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestReceiveViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.date.setText(resuestReceiveds.get(position).getDate());
        holder.company.setText(resuestReceiveds.get(position).getRequestFrom().getCompany());
        holder.clubName.setText(resuestReceiveds.get(position).getRequestFrom().getClubName());
        holder.address.setText(resuestReceiveds.get(position).getRequestFrom().getAddress());
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.acceptRequestInterface.acceptRequest(resuestReceiveds.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return resuestReceiveds.size();
    }

    public void updateList(ConnectionListModalClass.ResuestReceived resuestReceived) {
        //MemberItemList.clear();z
        if (!resuestReceiveds.contains(resuestReceived))
            resuestReceiveds.add(resuestReceived);
        notifyDataSetChanged();
    }





    class RequestReceiveViewHolder extends RecyclerView.ViewHolder {
        TextView date, company, clubName, address;
        Button accept;
        AcceptRequestInterface acceptRequestInterface;


        public RequestReceiveViewHolder(@NonNull View itemView, AcceptRequestInterface acceptRequestInterface) {
            super(itemView);
            this.date = itemView.findViewById(R.id.date);
            this.company = itemView.findViewById(R.id.companyName);
            this.clubName = itemView.findViewById(R.id.clubName);
            this.address = itemView.findViewById(R.id.Address);
            this.accept = itemView.findViewById(R.id.acceptReq);
            this.acceptRequestInterface = acceptRequestInterface;
        }

    }

    public interface AcceptRequestInterface{
        void acceptRequest(ConnectionListModalClass.ResuestReceived resuestReceived);
    }
}
