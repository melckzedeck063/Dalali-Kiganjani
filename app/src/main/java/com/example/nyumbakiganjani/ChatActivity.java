package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    EditText message_input;
    private ArrayList<MessageModel> messageModelArrayList;
    private  MessageAdapter messageAdapter;
    Button sendBtn;
    private int currentUser,receiverID;

    private ProgressDialog progressDialog;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String chatURL = "http://192.168.43.33/Dkiganjani/chat.php";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();

        SharedPreferenceHelper sharedPreferenceHelper  = new SharedPreferenceHelper(this);
        currentUser = sharedPreferenceHelper.getId();
        receiverID= intent.getIntExtra("receiverID",0);

        recyclerView =  findViewById(R.id.messageRecycler);
        message_input = findViewById(R.id.messageInput);
        sendBtn = findViewById(R.id.sendButton);

        createMessage();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createChat();
            }
        });

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


    private void createChat(){

        final int sender = currentUser;
        final int  receiver = receiverID;
        final String message = message_input.getText().toString();

        if(message.isEmpty()){
            message_input.setError("Cant send empty message");
            message_input.requestFocus();
            return;
        }

        if(sender <= 0){
            message_input.setError("Sender id not found");
            message_input.requestFocus();
            return;
        }
        if(receiver <= 0){
            message_input.setError("Receiver ID no found");
            message_input.requestFocus();
            return;
        }

        stringRequest = new StringRequest(Request.Method.POST, chatURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results = jsonObject.getString("success");

                            if(results.equals("1")){
                                if(progressDialog != null && progressDialog.isShowing()){
                                    progressDialog.dismiss();
                                }

                                progressDialog = ProgressDialog.show(ChatActivity.this, "", "Sending message", true);

                                new Handler().postDelayed((Runnable) () ->{
                                    try {
                                        if(progressDialog != null && progressDialog.isShowing()){
                                            progressDialog.dismiss();
                                        }

                                        Toast.makeText(ChatActivity.this, jsonObject.getString("message"),Toast.LENGTH_SHORT);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                },2000);
                            }
                            else {
                                Toast.makeText(ChatActivity.this, jsonObject.getString("message"),Toast.LENGTH_LONG);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChatActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("user_id", String.valueOf(sender));
                params.put("receiver_id", String.valueOf(receiver));
                params.put("message",message);
                return  params;
            }
        };

        requestQueue = Volley.newRequestQueue(ChatActivity.this);
        requestQueue.add(stringRequest);
    }


}