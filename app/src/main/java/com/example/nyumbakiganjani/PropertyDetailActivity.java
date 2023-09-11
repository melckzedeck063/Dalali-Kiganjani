package com.example.nyumbakiganjani;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PropertyDetailActivity extends AppCompatActivity {

    TextView p_name, p_location, p_price,duration,description, p_status, owner_name, owner_phone, owner_email;
    ImageView image,icon;
    Button chatBtn, bookBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        Intent intent = getIntent();
        String propertyName = intent.getStringExtra("property_name");
        String propertyLocation = intent.getStringExtra("property_location");
        String propertyDuration = intent.getStringExtra("property_duration");
        double propertyPrice = intent.getDoubleExtra("property_price", 0.0);
        int propertyRooms = intent.getIntExtra("property_rooms", 0);
        String propertyDescription  = intent.getStringExtra("description");
//        int propertyImage =  intent.getIntExtra("image",R.mipmap.);

        image = findViewById(R.id.property_image);
        p_name =   findViewById(R.id.property_name);
        icon =  findViewById(R.id.location_icon);
        p_location  = findViewById(R.id.property_location);
        p_price =  findViewById(R.id.property_price);
        p_status =  findViewById(R.id.property_status);
        description =  findViewById(R.id.description);
        chatBtn =  findViewById(R.id.chat_button);
        bookBtn =  findViewById(R.id.book_button);

//        image.setImageResource();

    }
}