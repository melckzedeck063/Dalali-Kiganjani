package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingsActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private  PropertyAdapter propertyAdapter;
    private ArrayList<Property> propertyArrayList;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private SearchView searchView;
    String propertDataUrl="http://192.168.43.33/Dkiganjani/my_properties.php";
    private int userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        SharedPreferenceHelper sharedPreferenceHelper =  new SharedPreferenceHelper(this);
        userId =  sharedPreferenceHelper.getId();

        recyclerView =  findViewById(R.id.recycler_view4);
        Toolbar toolbar = findViewById(R.id.toolbar_header);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Properties");


        propertyArrayList = new ArrayList<>();

        // Initialize the propertyAdapter
        propertyAdapter = new PropertyAdapter(propertyArrayList, BookingsActivity.this);
        recyclerView.setAdapter(propertyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookingsActivity.this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(false);

        propertyData();
    }

    private void propertyData(){
        stringRequest =  new StringRequest(Request.Method.POST, propertDataUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");

                            if (success.equals("1")) {

                                JSONArray jsonArray = jsonResponse.getJSONArray("properties_data");
                                propertyArrayList = new ArrayList<>(); // Initialize the ArrayList

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject  jsonObject = jsonArray.getJSONObject(i);
                                    propertyArrayList.add(new Property(
                                            jsonObject.getString("property"),
                                            jsonObject.getString("location"),
                                            jsonObject.getString("price"),
                                            jsonObject.getString("bedrooms"),
                                            jsonObject.getString("bathrooms"),
                                            jsonObject.getString("parking"),
                                            jsonObject.getString("duration"),
                                            jsonObject.getString("photo"),
                                            jsonObject.getString("description"),
                                            jsonObject.getString("status"),
                                            jsonObject.getInt("owner"),
                                            jsonObject.getInt("property_id")
                                    ));

                                }
                                propertyAdapter = new PropertyAdapter(propertyArrayList, BookingsActivity.this);
                                recyclerView.setAdapter(propertyAdapter);
                                recyclerView.setLayoutManager(new LinearLayoutManager(BookingsActivity.this, LinearLayoutManager.VERTICAL, false));
                                recyclerView.setNestedScrollingEnabled(false);


                            } else {
                                Toast.makeText(BookingsActivity.this, jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookingsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params =  new HashMap<>();
                params.put("user_id", String.valueOf(userId));

                return params;
            }
        }
        ;
        requestQueue = Volley.newRequestQueue(BookingsActivity.this);
        requestQueue.add(stringRequest);
    }

}