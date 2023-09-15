package com.example.nyumbakiganjani;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private ArrayList<MessageModel> messageModelArrayList;

    public MessageAdapter(ArrayList<MessageModel> messageModelArrayList,Context context ) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.messageModelArrayList = messageModelArrayList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.chat_bubble,parent,false);
        MessageAdapter.MyViewHolder holder = new MessageAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.message.setText(messageModelArrayList.get(position).getMessage());
        holder.time.setText(messageModelArrayList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return messageModelArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user_id, receiver_id,message,time;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.messageText);
            time = itemView.findViewById(R.id.timestamp);
        }
    }
}
