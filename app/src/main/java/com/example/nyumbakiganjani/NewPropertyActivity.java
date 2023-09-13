package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NewPropertyActivity extends AppCompatActivity {

    EditText propertyName, propertyLocation, getPropertyPrice, bedrooms,bathrooms,parking,duration,propertyDescription;
    Button uploadBtn, submitBtn;
    ImageView imageView;
    private static final int GALLERY_REQUEST_CODE = 123;
    private ProgressBar  progressBar;

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
        progressBar = findViewById(R.id.progressBar); // Initialize progressBar


        submitBtn = findViewById(R.id.submit_btn);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }

    private  void  openGallery(){
         Intent galleryIntent =  new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
         startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
             Uri imageUri = data.getData();

            imageView.setImageURI(imageUri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);
                uploadImageUsingRetrofit(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(NewPropertyActivity.this, "Failed to select image!", Toast.LENGTH_SHORT).show();
//                tv.setText("Failed to select image!");
//                tv.setTextColor(Color.parseColor("#FF0000"));
            }
        }
        else {
            Toast.makeText(NewPropertyActivity.this, "Failed to select an image", Toast.LENGTH_SHORT).show();
        }
    }


    private void uploadImageUsingRetrofit(Bitmap bitmap){
        progressBar.setVisibility(View.VISIBLE);
//        tv.setText("");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        String name = String.valueOf(Calendar.getInstance().getTimeInMillis());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyImageInterface.IMAGEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MyImageInterface myImageInterface = retrofit.create(MyImageInterface.class);
        Call<String> call = myImageInterface.getImageData(name,image);
        call.enqueue(new Callback<String>() {
            private Call<String> call;
            private Response<String> response;

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(NewPropertyActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                        tv.setText("Image Uploaded Successfully!!");
//                        tv.setTextColor(Color.parseColor("#008000"));
                    } else {
//                        tv.setText("No response from the server");
                        Toast.makeText(NewPropertyActivity.this, "Failed to select image!!", Toast.LENGTH_SHORT).show();
//                        tv.setTextColor(Color.parseColor("#FF0000"));
                    }
                }else{
//                    tv.setText("Response not successful "+response.toString());
//                    tv.setTextColor(Color.parseColor("#FF0000"));
                    Toast.makeText(getApplicationContext(), "Response not successful "+response.toString(), Toast.LENGTH_SHORT).show();
                }
            }



            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
//                tv.setText("Error occurred during upload");
//                tv.setTextColor(Color.parseColor("#FF0000"));
            }
        });
    }



}