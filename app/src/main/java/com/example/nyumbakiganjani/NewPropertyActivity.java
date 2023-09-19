package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NewPropertyActivity extends AppCompatActivity {

    EditText propertyName, propertyLocation, propertyPrice, bedroomsNo,bathroomsNo,parkingNo,payduration,propertyDescription;
    Button uploadBtn, submitBtn;
    ImageView imageView;
    private static final int GALLERY_REQUEST_CODE = 123;
    private ProgressBar  progressBar;
    private SharedPreferenceHelper sharedPreferenceHelper;
    int loged_user  =0;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ProgressDialog progressDialog;
    private String NewPropertyURL = "http://192.168.43.33/Dkiganjani/new_property.php";
    private  String  imageName="";
    private Uri coverPhoto;
    private String uploadedImageName = ""; // Initialize with an empty string
    private Uri uploadedImageUri = null; // Initialize with null


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_property);

        sharedPreferenceHelper =  new SharedPreferenceHelper(this);
        loged_user = sharedPreferenceHelper.getId();
        imageName = sharedPreferenceHelper.getImageName();


        propertyName =  findViewById(R.id.editPropertyName);
        propertyLocation = findViewById(R.id.editLocation);
        propertyPrice = findViewById(R.id.editPrice);
        bedroomsNo =  findViewById(R.id.editBedrooms);
        bathroomsNo = findViewById(R.id.editBathrooms);
        parkingNo = findViewById(R.id.editParking);
        payduration = findViewById(R.id.editPaymentDuration);
        uploadBtn  =  findViewById(R.id.editImage);
        imageView =  findViewById(R.id.imageView);
        propertyDescription =  findViewById(R.id.editDescription);
        progressBar = findViewById(R.id.progressBar); // Initialize progressBar

        if(!imageName.isEmpty()){
            Toast.makeText(NewPropertyActivity.this, imageName.toString(), Toast.LENGTH_LONG).show();
        }



        submitBtn = findViewById(R.id.submit_btn);

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerProperty();
            }
        });
    }

    private void registerProperty(){
        final String property = propertyName.getText().toString();
        final String location =  propertyLocation.getText().toString();
        final String price = propertyPrice.getText().toString();
        final String  bedrooms = bedroomsNo.getText().toString();
        final String bathrooms = bathroomsNo.getText().toString();
        final   String parking =  parkingNo.getText().toString();
        final String duration =  payduration.getText().toString();
        final String description =  propertyDescription.getText().toString();
        final int owner = loged_user;
        imageName = sharedPreferenceHelper.getImageName();
        if(!imageName.isEmpty()){
            Toast.makeText(NewPropertyActivity.this, imageName.toString(), Toast.LENGTH_LONG).show();
        }


        if(property.isEmpty()){
            propertyName.setError("Property name is  required");
            propertyName.requestFocus();
            return;
        }
        if(location.isEmpty()){
            propertyLocation.setError("Property location is  required");
            propertyLocation.requestFocus();
            return;
        }
        if(price.isEmpty()){
            propertyPrice.setError("Property price is  required");
            propertyPrice.requestFocus();
            return;
        }
        if(bedrooms.isEmpty()){
            bedroomsNo.setError("Bedrooms no is  required");
            bedroomsNo.requestFocus();
            return;
        }
        if(bathrooms.isEmpty()){
            bedroomsNo.setError("Bathrooms no is  required");
            bathroomsNo.requestFocus();
            return;
        }
        if(parking.isEmpty()){
            parkingNo.setError("Parking no is  required");
            parkingNo.requestFocus();
            return;
        }
        if(duration.isEmpty()){
            payduration.setError("Payment installment is  required");
            payduration.requestFocus();
            return;
        }
        if(description.isEmpty()){
            propertyDescription.setError("Description is  required");
            propertyDescription.requestFocus();
            return;
        }
        if (uploadedImageName.isEmpty()) {
            Toast.makeText(NewPropertyActivity.this, "Image name is empty", Toast.LENGTH_LONG).show();
            return;
        }

        stringRequest = new StringRequest(Request.Method.POST, NewPropertyURL,
               new com.android.volley.Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {
                       try {
                           JSONObject jsonObject = new JSONObject(response);

                           String results =  jsonObject.getString("success");

                           if(results.equals("1")){

                               if(progressDialog != null && progressDialog.isShowing()){
                                   progressDialog.dismiss();
                               }

                               progressDialog =  ProgressDialog.show(NewPropertyActivity.this, "", "Creating new property ......", true);

                               new Handler().postDelayed((Runnable) () ->{
                                   try {
                                       if(progressDialog != null && progressDialog.isShowing()){
                                           progressDialog.dismiss();
                                       }
                                       Toast.makeText(NewPropertyActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                       sharedPreferenceHelper.setImageName("");
                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               },3000 );
                           } else {
                               Toast.makeText(NewPropertyActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               },
               new com.android.volley.Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(NewPropertyActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                   }
               }
       ) {
           @Nullable
           @Override
           protected Map<String, String> getParams(){
               Map<String, String> params =  new HashMap<String, String>();
               params.put("property",property);
               params.put("location", location);
               params.put("price",price);
               params.put("bedrooms",bedrooms);
               params.put("bathrooms",bathrooms);
               params.put("parking",parking);
               params.put("duration", duration);
               params.put("photo", uploadedImageUri.toString());
               params.put("description",description);
               params.put("owner", String.valueOf(owner));
               params.put("status", "Available");

               return  params;
           }
       };
        requestQueue  = Volley.newRequestQueue(NewPropertyActivity.this);
        requestQueue.add(stringRequest);

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
            coverPhoto = imageUri;
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


    private void uploadImageUsingRetrofit(Bitmap bitmap) {
        progressBar.setVisibility(View.VISIBLE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        String name = String.valueOf(Calendar.getInstance().getTimeInMillis());
        sharedPreferenceHelper.setImageName(name); // Update the shared preference with the new image name

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyImageInterface.IMAGEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MyImageInterface myImageInterface = retrofit.create(MyImageInterface.class);
        Call<String> call = myImageInterface.getImageData(name, image);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Toast.makeText(NewPropertyActivity.this, "Image uploaded successfully", Toast.LENGTH_LONG).show();

                        // Update the uploaded image name and URI
                        uploadedImageName = name;
                        uploadedImageUri = Uri.parse(MyImageInterface.IMAGEURL+"uploads/" + name);
                    } else {
                        Toast.makeText(NewPropertyActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Response not successful " + response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }




//    @Override
            public void onFailure(Call<String> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();

            }



}