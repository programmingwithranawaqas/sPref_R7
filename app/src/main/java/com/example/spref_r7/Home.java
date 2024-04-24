package com.example.spref_r7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set isLogin flag to false in sharedPreference
                SharedPreferences sPref = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sPref.edit();
                editor.putBoolean("key_isLogin", false);
                editor.apply();

                startActivity(new Intent(Home.this, MainActivity.class));
                finish();
            }
        });
    }
}