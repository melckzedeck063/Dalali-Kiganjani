package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class PropertyDetailActivity extends AppCompatActivity {

    TextView p_name, p_location, p_price, duration, description, p_status, p_duration,p_bedroom,p_bathroom,p_parking;
    ImageView image, icon;
    Button chatBtn, bookBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        SharedPreferenceHelper sharedPreferenceHelper =  new SharedPreferenceHelper(this);

        int logedUser = sharedPreferenceHelper.getId();

        Intent intent = getIntent();
        String propertyName = intent.getStringExtra("property");
        String propertyLocation = intent.getStringExtra("location");
        String propertyDuration = intent.getStringExtra("duration");
        String propertyPrice = intent.getStringExtra("price");
        String propertyDescription = intent.getStringExtra("description");
        String bedrooms = intent.getStringExtra("bedrooms");
        String bathrooms = intent.getStringExtra("bathrooms");
        String parking = intent.getStringExtra("parking");
        String imageURl = intent.getStringExtra("image")+".jpg"; // Corrected variable name to imageURl
        int owner_id = intent.getIntExtra("owner_id",0);
        String status =  intent.getStringExtra("status");

        Toolbar toolbar = findViewById(R.id.toolbar_item);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(propertyName);


//        if(owner_id >=1){
//            Toast.makeText(PropertyDetailActivity.this, "owner_id : " + owner_id, Toast.LENGTH_LONG).show();
//        }

        image = findViewById(R.id.property_image);
        p_name = findViewById(R.id.property_name);
        icon = findViewById(R.id.location_icon);
        p_location = findViewById(R.id.property_location);
        p_price = findViewById(R.id.property_price);
        p_status = findViewById(R.id.property_status);
        p_duration = findViewById(R.id.payment_mode);
        description = findViewById(R.id.description);
        p_bedroom = findViewById(R.id.bedrooms_text);
        p_bathroom = findViewById(R.id.bedrooms_text);
        p_parking = findViewById(R.id.parking_text);
        chatBtn = findViewById(R.id.chat_button);
//        bookBtn = findViewById(R.id.book_button);

        // Use Glide to load and display the image from the URL
        Glide.with(this)
                .load(imageURl) // Use imageURl here
                .into(image);

        p_name.setText(propertyName);
        p_location.setText(propertyLocation);
        p_price.setText(propertyPrice);
        p_duration.setText(propertyDuration);
        description.setText(propertyDescription);
        p_status.setText(status);
        p_bathroom.setText(bathrooms);
        p_bedroom.setText(bedrooms);
        p_parking.setText(parking);


        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(logedUser == owner_id){
                    Toast.makeText(PropertyDetailActivity.this, "You can't chat with youself", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(PropertyDetailActivity.this, ChatActivity.class);

                    intent.putExtra("receiverID", owner_id);

                    startActivity(intent);
                }
            }
        });


    }
}
