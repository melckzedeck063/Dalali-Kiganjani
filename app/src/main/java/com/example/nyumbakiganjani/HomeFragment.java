package com.example.nyumbakiganjani;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
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


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private  PropertyAdapter propertyAdapter;
    private ArrayList<Property> propertyArrayList;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private SearchView searchView;
    String propertDataUrl="http://192.168.43.33/Dkiganjani/all_properties.php";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView =  view.findViewById(R.id.recycler_view2);
        searchView = view.findViewById(R.id.search_view);

        propertyArrayList = new ArrayList<>();

        // Initialize the propertyAdapter
        propertyAdapter = new PropertyAdapter(propertyArrayList, getContext());
        recyclerView.setAdapter(propertyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setNestedScrollingEnabled(false);

//        createProperty();
        propertyData();

        // Set up SearchView listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle search query submission (if needed)
                performSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the property list based on user input
                if (propertyAdapter != null) {
                    propertyAdapter.filter(newText);
                }
                return true;
            }
        });


        return view;
    }



    private void propertyData(){
        stringRequest =  new StringRequest(Request.Method.GET, propertDataUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String success = jsonResponse.getString("success");

                            if (success.equals("1")) {

                                      JSONArray  jsonArray = jsonResponse.getJSONArray("properties_data");
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
                                    propertyAdapter = new PropertyAdapter(propertyArrayList, getContext());
                                    recyclerView.setAdapter(propertyAdapter);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                                    recyclerView.setNestedScrollingEnabled(false);


                            } else {
                                Toast.makeText(getContext(), jsonResponse.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


    private void performSearch(String query) {
        // Filter the property list based on the search query
        propertyAdapter.filter(query);

        // Optionally, you can clear the focus from the SearchView
        searchView.clearFocus();
    }

}