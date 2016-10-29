package com.ourpeople.ourpeople.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.ourpeople.ourpeople.R;
import com.ourpeople.ourpeople.services.CommonUtils;

/**
 * Created by hareesh.pammi on 29/07/16.
 */
public class SplashScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        (findViewById(R.id.ll_signuptext)).setOnClickListener(s -> {
            Intent signupIntent = new Intent(SplashScreen.this, RegisterScreen.class);
            SplashScreen.this.startActivity(signupIntent);
        });

        TextInputEditText emailText = (TextInputEditText) findViewById(R.id.email);
        emailText.setOnFocusChangeListener((s, v) -> {
            if (!v) {
                if (!(Patterns.EMAIL_ADDRESS.matcher(emailText.getText()).matches())) {
                    //Toast.makeText(SplashScreen.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    emailText.setError("Invalid Email");
                    emailText.setText("");
                }
            }
        });

        TextInputEditText password = ((TextInputEditText) findViewById(R.id.pwd));
        password.setOnFocusChangeListener((s, v) -> {
            if (!v) {
                if (password.getText().length() < 6) {
                    //Toast.makeText(SplashScreen.this, "Password should contain minimum 6 characters", Toast.LENGTH_SHORT).show();
                    password.setError("Password should contain minimum 6 characters");
                    password.setText("");
                }
            }
        });

        Button loginBtn = ((Button) findViewById(R.id.btn_login));
        loginBtn.setOnClickListener(s -> {
            if (emailText.getText().toString().equals("") || emailText.getText().toString() == null || password.getText().toString().equals("") || password.getText().toString() == null) {
                Toast.makeText(SplashScreen.this, "Please enter Email & Password", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(SplashScreen.this, "Email:"+emailText.getText()+",Pwd:"+password.getText(), Toast.LENGTH_SHORT).show();
                String loginApi = "http://192.168.0.8:8080/login?email=" + emailText.getText();
                String loginResponse = CommonUtils.executeGetHttpCall(loginApi);
                if(loginResponse!=null) {
                    Intent homeScreenIntent = new Intent(SplashScreen.this, HomeScreen.class);
                    SplashScreen.this.startActivity(homeScreenIntent);
                } else {
                    Toast.makeText(SplashScreen.this, "Invalid Email & Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
