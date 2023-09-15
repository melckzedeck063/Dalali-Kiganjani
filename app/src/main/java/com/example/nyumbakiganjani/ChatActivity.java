package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    EditText message_input;
    private ArrayList<MessageModel> messageModelArrayList;
    private  MessageAdapter messageAdapter;
    Button sendBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView =  findViewById(R.id.messageRecycler);
        message_input = findViewById(R.id.messageInput);
        sendBtn = findViewById(R.id.sendButton);

        createMessage();


    }

    private void createMessage(){
        messageModelArrayList = new ArrayList<>();
        messageModelArrayList.add(new MessageModel(7,15,"Hello Meck","08:46 am"));
        messageModelArrayList.add(new MessageModel(15,7,"Hello dear","09:16 am"));
        messageModelArrayList.add(new MessageModel(7,15,"Hello Meck","09:19 am"));
        messageModelArrayList.add(new MessageModel(15,7,"Hello dear","09:26 am"));


        messageAdapter =  new MessageAdapter(messageModelArrayList,this);
        recyclerView.setAdapter(messageAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(false);
    }
}