package com.example.spref_r7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    Fragment loginF, signupF;
    View loginV, signupV;

    // login frag hooks
    TextInputEditText etUsername, etPassword;
    Button btnCancel, btnLogin;
    TextView tvSignup;

    // signup frag hooks
    TextInputEditText etSUsername, etSPassword, etSCPassword;
    Button btnSCancel, btnSignup;
    TextView tvLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction()
                        .show(signupF)
                        .hide(loginF)
                        .commit();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.beginTransaction()
                        .hide(signupF)
                        .show(loginF)
                        .commit();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etSUsername.getText().toString().trim();
                String pass = etSPassword.getText().toString();
                String cpass = etSCPassword.getText().toString();

                SharedPreferences sPref = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = sPref.edit();
                editor.putString("key_username", username);
                editor.putString("key_password", pass);
                editor.apply();
                manager.beginTransaction()
                        .hide(signupF)
                        .show(loginF)
                        .commit();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String pass = etPassword.getText().toString();
                SharedPreferences sPref = getSharedPreferences("user", MODE_PRIVATE);
                String fName = sPref.getString("key_username", "");
                String fPass = sPref.getString("key_password", "");

                if(username.equals(fName) && pass.equals(fPass))
                {
                    SharedPreferences.Editor editor = sPref.edit();
                    editor.putBoolean("key_isLogin", true);
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, Home.class));
                    finish();
                }

            }
        });
    }



    private void init()
    {
        manager = getSupportFragmentManager();
        loginF = manager.findFragmentById(R.id.loginfrag);
        signupF = manager.findFragmentById(R.id.signupfrag);
        loginV = loginF.getView();
        signupV = signupF.getView();

        etUsername = loginV.findViewById(R.id.etUsername);
        etPassword = loginV.findViewById(R.id.etPassword);
        btnCancel = loginV.findViewById(R.id.btnCancel);
        btnLogin = loginV.findViewById(R.id.btnLogin);
        tvSignup = loginV.findViewById(R.id.tvSignup);

        etSUsername = signupV.findViewById(R.id.etSUsername);
        etSPassword = signupV.findViewById(R.id.etSPassword);
        etSCPassword = signupV.findViewById(R.id.etSCPassword);
        btnSCancel = signupV.findViewById(R.id.btnCancel);
        btnSignup = signupV.findViewById(R.id.btnSignup);
        tvLogin = signupV.findViewById(R.id.tvLogin);

    }
}