package com.example.nyumbakiganjani;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

public class BookingListActivity extends AppCompatActivity {

    private ArrayList<BookingModel> bookingModelArrayList;
    private BookingAdapter  bookingAdapter;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private RecyclerView recyclerView;
    private String booksURL="http://192.168.43.33/Dkiganjani/my_bookings.php";
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        recyclerView  =  findViewById(R.id.booking_recycler);
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(this);
        user_id = sharedPreferenceHelper.getId();

        recyclerView = findViewById(R.id.booking_recycler);
        bookingModelArrayList = new ArrayList<>(); // Initialize the ArrayList here
        bookingAdapter = new BookingAdapter(bookingModelArrayList, BookingListActivity.this);
        recyclerView.setAdapter(bookingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookingListActivity.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setNestedScrollingEnabled(false);

        getBookings();

    }

    private  void getBookings(){
        stringRequest =  new StringRequest(Request.Method.POST, booksURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results =  jsonObject.getString("success");
                            if(results.equals("1")){
                                JSONArray jsonArray =  jsonObject.getJSONArray("bookings_data");
                                bookingModelArrayList =  new ArrayList<>();

                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    bookingModelArrayList.add(new BookingModel(
                                            jsonObject1.getString("property"),
                                            jsonObject1.getString("booking_status"),
                                            jsonObject1.getString("date_booked"),
                                            jsonObject1.getString("user_firstname"),
                                            jsonObject1.getString("user_lastname"),
                                            jsonObject1.getInt("property_id"),
                                            jsonObject1.getInt("book_id"),
                                            jsonObject1.getInt("user_id"),
                                            jsonObject1.getInt("owner_id")
                                    ));
                                }

                                bookingAdapter =   new BookingAdapter(bookingModelArrayList, BookingListActivity.this);
                                recyclerView.setAdapter(bookingAdapter);

                                recyclerView.setLayoutManager(new LinearLayoutManager(BookingListActivity.this,LinearLayoutManager.VERTICAL,false));
                                recyclerView.setNestedScrollingEnabled(false);
                            }
                            else {
                                Toast.makeText(BookingListActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookingListActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", String.valueOf(user_id));

                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(BookingListActivity.this);
        requestQueue.add(stringRequest);

        bookingAdapter.notifyDataSetChanged();
//        Toast.makeText(BookingListActivity.this, "function called with id:  " + user_id, Toast.LENGTH_SHORT).show();
    }


}