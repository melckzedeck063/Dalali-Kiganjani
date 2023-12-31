package com.example.nyumbakiganjani;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
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
    private int senderID;
    private SharedPreferenceHelper  sharedPreferenceHelper;

//    SharedPreferenceHelper sharedPreferenceHelper  = new SharedPreferenceHelper(context);
private SharedPreferences sharedPreferences;
//    private int current_user = 15;


    int currentUser;

    public MessageAdapter(ArrayList<MessageModel> messageModelArrayList,Context context ) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.messageModelArrayList = messageModelArrayList;

        sharedPreferenceHelper = new SharedPreferenceHelper(context);
        currentUser = sharedPreferenceHelper.getId();

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
        holder.time.setText(messageModelArrayList.get(position).getTime().substring(11,19));
        senderID = messageModelArrayList.get(position).getReceiver_id();
//        Toast.makeText(context, "senderID and currentUser" + senderID + " " +currentUser,Toast.LENGTH_LONG).show();

        if(currentUser == senderID){
            holder.message.setBackgroundResource(R.drawable.sender_bg);
            holder.message.setGravity(Gravity.END);
            setLeftMargin(holder.message, 700);
        }
        else {
            holder.message.setBackgroundResource(R.drawable.receiver_bg);
            holder.message.setGravity(Gravity.START);
            setLeftMargin(holder.message, 0);
        }
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
            time = itemView.findViewById(R.id.timestamp1);
        }
    }

    private void setLeftMargin(View view, int leftMargin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        params.leftMargin = leftMargin;
        view.setLayoutParams(params);
    }
}
