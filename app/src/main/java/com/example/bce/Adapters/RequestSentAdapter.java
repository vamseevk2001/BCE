package com.example.bce.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bce.R;

public class RequestSentAdapter extends RecyclerView.Adapter<RequestSentAdapter.RequestSendViewHolder>{



    @NonNull
    @Override
    public RequestSendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.request_sent, parent, false);
        RequestSendViewHolder viewHolder = new RequestSendViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestSendViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RequestSendViewHolder extends RecyclerView.ViewHolder{
        TextView date, company, clubName, address, status;

        public RequestSendViewHolder(@NonNull View itemView) {
            super(itemView);
            this.date = itemView.findViewById(R.id.date);
            this.company = itemView.findViewById(R.id.companyName);
            this.clubName = itemView.findViewById(R.id.clubName);
            this.address = itemView.findViewById(R.id.Address);
            this.status = itemView.findViewById(R.id.requestStatus);
        }
    }
}
