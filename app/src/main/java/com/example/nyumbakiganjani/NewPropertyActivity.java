package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewPropertyActivity extends AppCompatActivity {

    EditText propertyName, propertyLocation, getPropertyPrice, bedrooms,bathrooms,parking,duration,propertyDescription;
    Button uploadBtn, submitBtn;
    ImageView imageView;
    private static final int GALLERY_REQUEST_CODE = 123;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_property);

        propertyName =  findViewById(R.id.editPropertyName);
        propertyLocation = findViewById(R.id.editLocation);
        getPropertyPrice = findViewById(R.id.editPrice);
        bedrooms =  findViewById(R.id.editBedrooms);
        bathrooms = findViewById(R.id.editBathrooms);
        parking = findViewById(R.id.editParking);
        duration = findViewById(R.id.editPaymentDuration);
        uploadBtn  =  findViewById(R.id.editImage);
        imageView =  findViewById(R.id.imageView);
        propertyDescription =  findViewById(R.id.editDescription);

        submitBtn = findViewById(R.id.submit_btn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openGallery(){
        Intent galleryIntent =  new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            imageView.setImageURI(selectedImageUri);
        } else {
            Toast.makeText(this, "Gallery selection canceled or failed", Toast.LENGTH_SHORT).show();
        }
    }




}