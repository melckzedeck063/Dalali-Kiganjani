package com.example.nyumbakiganjani;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @Multipart
    @POST("http://localhost/upload.php") // Replace with your server's endpoint
    Call<ResponseBody> uploadImage(
            @Part MultipartBody.Part image,
            @Query("description") String description); // Replace with any additional parameters you need
}

