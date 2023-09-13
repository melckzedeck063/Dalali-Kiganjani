package com.example.nyumbakiganjani;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
public interface MyImageInterface {
    String IMAGEURL = "http://192.168.43.33/Dkiganjani/";
    @FormUrlEncoded
    @POST("upload_file.php")
    Call<String> getImageData(
            @Field("name") String name,
            @Field("image") String image
    );
}
