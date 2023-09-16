package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ConversationModel> conversationModelArrayList;
    private Context  context;

    public ConversationAdapter(ArrayList<ConversationModel> conversationModelArrayList, Context context) {
        inflater = LayoutInflater.from(context);
        this.conversationModelArrayList = conversationModelArrayList;
        this.context = context;
    }


    @Override
    public ConversationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.conversation_buble,parent,false);
        ConversationAdapter.ViewHolder holder = new ConversationAdapter.ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.sender.setText(conversationModelArrayList.get(position).getUserName());
        holder.time.setText(conversationModelArrayList.get(position).getTime().substring(11,16));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You have clicked " + conversationModelArrayList.get(position).getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return conversationModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sender,message,time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(R.id.senderName);
            time = itemView.findViewById(R.id.timeStamp2);
        }
    }
}
