package com.example.nyumbakiganjani;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    private RecyclerView recyclerView;
    private PropertyAdapter propertyAdapter;
    private ArrayList<Property> propertyArrayList;
    private SharedPreferenceHelper sharedPreferenceHelper;
    Button logoutBtn;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferenceHelper =  new SharedPreferenceHelper(this);

//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        frameLayout = findViewById(R.id.frame_layout);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar); // Set the Toolbar as the ActionBar
        getSupportActionBar().setTitle("");


        navigationView =  findViewById(R.id.nav_menu);
        drawerLayout =  findViewById(R.id.drawer_container);
        logoutBtn = findViewById(R.id.std_logout);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferenceHelper.setFirstname(null);
                sharedPreferenceHelper.setLastname(null);
                sharedPreferenceHelper.setUsername(null);
                sharedPreferenceHelper.setPhone(null);
                sharedPreferenceHelper.setId(-1);

                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

//        recyclerView =  findViewById(R.id.recycler_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.user_menu:
//                            drawerLayout.closeDrawer(GravityCompat.START);
//                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        Toast.makeText(HomeActivity.this, "Not yet implemented", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.home_menu:
//                            drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class));
                        break;
                    case R.id.settings_menu:
//                                drawerLayout.closeDrawer(GravityCompat.START);
                        startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                        break;
                    case R.id.new_prop:
                        startActivity(new Intent(HomeActivity.this, NewPropertyActivity.class));
                        break;
                    // Add more cases for other menu items
//                    case  R.id.booking_menu:
//                        startActivity(new Intent(HomeActivity.this, BookingsActivity.class));
//                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        replaceFragment(new HomeFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    return true;
                case R.id.navigation_cart:
                    replaceFragment(new CartFragment());
                    return true;
            }
            return false;
        });

    }


    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }



}