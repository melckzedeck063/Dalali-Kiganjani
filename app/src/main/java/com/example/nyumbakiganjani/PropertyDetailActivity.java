package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PropertyDetailActivity extends AppCompatActivity {

    TextView p_name, p_location, p_price, duration, description, p_status, p_duration,p_bedroom,p_bathroom,p_parking;
    ImageView image, icon;
    Button chatBtn, bookBtn, btnYes,btnNo;
    private int logedUser,owner_id,property_id;

    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    private String BookURL = "http://192.168.43.33/Dkiganjani/book_now.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        SharedPreferenceHelper sharedPreferenceHelper =  new SharedPreferenceHelper(this);

         logedUser = sharedPreferenceHelper.getId();

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
        owner_id = intent.getIntExtra("owner_id",0);
        String status =  intent.getStringExtra("status");
        property_id = intent.getIntExtra("property_id",0);


        Toolbar toolbar = findViewById(R.id.toolbar_item);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(propertyName);

        final Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.booking_dialog);

        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(PropertyDetailActivity.this);
        alertDialogBuilder.setTitle("Success");
        alertDialogBuilder.setMessage("Booking placed successfully wait for confirmation");
        alertDialogBuilder.setPositiveButton("GOT IT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


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
        bookBtn = findViewById(R.id.book_button);
        btnNo  = customDialog.findViewById(R.id.buttonNo);
        btnYes = customDialog.findViewById(R.id.buttonYes);

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

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.show();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();

                placeBooking(alertDialogBuilder);
            }
        });


    }

    private void  placeBooking(androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder){
        stringRequest  =  new StringRequest(Request.Method.POST, BookURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject =  new JSONObject(response);

                            String results =  jsonObject.getString("success");
                            if(results.equals("1")){
                                androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();
                            }
                            else {
                                Toast.makeText(PropertyDetailActivity.this,jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PropertyDetailActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<>();
                params.put("property_id", String.valueOf(property_id));
                params.put("user_id", String.valueOf(logedUser));
                params.put("booking_status", "Pending");
                params.put("owner_id", String.valueOf(owner_id));

                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(PropertyDetailActivity.this);
        requestQueue.add(stringRequest);
    }
}
