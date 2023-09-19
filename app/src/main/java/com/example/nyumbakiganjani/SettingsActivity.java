package com.example.nyumbakiganjani;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Settings List
        ListView settingsList = findViewById(R.id.settings_list);

        // Define a list of settings options (replace with your own options)
        String[] settingsOptions = {
                "Account",
                "Privacy",
                "Security",
//                "Appearance",
                "About",
//                "Logout"
        };

        // Create a custom adapter for the settings list using your custom layout
        SettingsListAdapter adapter = new SettingsListAdapter(this, settingsOptions);

        // Set the adapter for the settings list
        settingsList.setAdapter(adapter);

        // Set item click listener for handling settings options
        settingsList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedOption = settingsOptions[position];
            // Handle the selected option, e.g., open a new activity or perform an action
            switch (selectedOption) {
                case "Account":
                    startActivity(new Intent(SettingsActivity.this, UserProfileActivity.class));
                    break;
                case "Privacy":
                    Toast.makeText(this, "You have clicked " + selectedOption, Toast.LENGTH_SHORT).show();
                    break;
                case "About" :
                    startActivity(new Intent(SettingsActivity.this, AboutActivity.class));
                default:
                    Toast.makeText(this, "You have clicked " + selectedOption, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
