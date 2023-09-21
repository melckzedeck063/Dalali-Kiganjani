package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {
 EditText fname,lname,username,phone;
 Button updateBtn;

 private SharedPreferenceHelper  sharedPreferenceHelper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        sharedPreferenceHelper =  new SharedPreferenceHelper(this);


        fname =findViewById(R.id.user_fname);
        lname = findViewById(R.id.user_lname);
        username = findViewById(R.id.user_email);
        phone = findViewById(R.id.user_phone);


        fname.setText(sharedPreferenceHelper.getFirstname());
        lname.setText(sharedPreferenceHelper.getLastname());
        username.setText(sharedPreferenceHelper.getUsername());
        phone.setText(sharedPreferenceHelper.getPhone());


    }
}