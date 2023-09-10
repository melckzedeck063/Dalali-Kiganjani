package com.example.nyumbakiganjani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateLogin(View view){
        Intent intent =  new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void navigateRegister(View view){
        Intent intent =  new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}