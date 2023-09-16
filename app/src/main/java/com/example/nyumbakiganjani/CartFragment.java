package com.example.nyumbakiganjani;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment {

    private ConversationAdapter conversationAdapter;
    private ArrayList<ConversationModel> conversationModelArrayList;
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String convURL="http://192.168.43.33/Dkiganjani/all_coversation.php";
    private int currentUser;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_cart, container, false);
         recyclerView =  view.findViewById(R.id.recycler_view3);

         SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(getContext());
         currentUser = sharedPreferenceHelper.getId();

         fetchConversation();

        return view;
    }


    private void fetchConversation (){
        stringRequest =  new StringRequest(Request.Method.POST, convURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject =  new JSONObject(response);
                            String results = jsonObject.getString("success");
                            if(results.equals("1")){
                                JSONArray jsonArray = jsonObject.getJSONArray("conversations");
                                conversationModelArrayList =  new ArrayList<>();

                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    conversationModelArrayList.add(new ConversationModel(
                                            jsonObject1.getString("user1_id"),
                                            jsonObject1.getString("date_created")

                                    ));
                                }

                                conversationAdapter = new ConversationAdapter(conversationModelArrayList,getContext());
                                recyclerView.setAdapter(conversationAdapter);
//                                conversationAdapter.notifyDataSetChanged();

                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                                recyclerView.setNestedScrollingEnabled(false);
                            }
                            else  {
                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("user_id", String.valueOf(currentUser));
                return params;
            }
        };
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }


}