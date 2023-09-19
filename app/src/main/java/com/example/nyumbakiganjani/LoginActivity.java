package com.example.nyumbakiganjani;

import android.app.ProgressDialog;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private ProgressDialog progressDialog;
    private SharedPreferencesBackupHelper sharedPreferencesBackupHelper;
    private SharedPreferenceHelper sharedPreferenceHelper;

    private String LoginURL = "http://192.168.43.33/Dkiganjani/";

    EditText email, passcode;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferenceHelper =  new SharedPreferenceHelper(this);
        int loged_user;
        String logedUser = sharedPreferenceHelper.getUsername();
        loged_user = sharedPreferenceHelper.getId();
        if(loged_user <= 0 && logedUser.isEmpty()){
//            Toast.makeText(LoginActivity.this, "loged_user is null", Toast.LENGTH_SHORT).show();
        }else {
//            Toast.makeText(LoginActivity.this, loged_user.toString(), Toast.LENGTH_SHORT).show();
            navigateHome();
        }


        email =  findViewById(R.id.email);
        passcode =  findViewById(R.id.password);

        loginBtn = findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });


    }

    private  void LoginUser(){

        final String username = email.getText().toString();
        final String password =  passcode.getText().toString();

        if(username.isEmpty()){
            email.setError("Username is required");
            email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            passcode.setError("Password is required");
            passcode.requestFocus();
            return;
        }
        if(password.length() < 8){
            passcode.setError("Strong password is required");
            passcode.requestFocus();
            return;
        }

        stringRequest  =   new StringRequest(Request.Method.POST, LoginURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        requestQueue.getCache().clear();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String results = jsonObject.getString("success");
                            if(results.equals("1")){
                                email.setText("");
                                passcode.setText("");

                                JSONObject jsonObject1= jsonObject.getJSONObject("user_data");
                                sharedPreferenceHelper.setFirstname(jsonObject1.getString("firstname"));
                                sharedPreferenceHelper.setLastname(jsonObject1.getString("lastname"));
                                sharedPreferenceHelper.setUsername(jsonObject1.getString("username"));
                                sharedPreferenceHelper.setPhone(jsonObject1.getString("phone"));
                                sharedPreferenceHelper.setRole(jsonObject1.getString("role"));
                                sharedPreferenceHelper.setId(jsonObject1.getInt("id"));

                                if(progressDialog != null  &&  progressDialog.isShowing()){
                                    progressDialog.dismiss();
                                }

                                progressDialog =  ProgressDialog.show(LoginActivity.this, "", "Loggin in....", true);

                                new Handler().postDelayed((Runnable) () ->{
                                    if(progressDialog != null  &&  progressDialog.isShowing()){
                                        progressDialog.dismiss();
                                    }
                                    try {
                                        Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                        navigateHome();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                },3000);
                            }
                            if(results.equals("0")){
                                email.setText("");
                                passcode.setText("");

                                Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
                        Log.e("Volley Error", error.toString());
                    }
                }
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params =  new HashMap<String,String>();
                params.put("username",username);
                params.put("password", password);
                return  params;
            }
        };

        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        requestQueue.add(stringRequest);
    }

    public void navigateHome(){
        Intent intent =  new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}