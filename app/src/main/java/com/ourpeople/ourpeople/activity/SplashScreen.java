package com.ourpeople.ourpeople.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Toast;

import com.ourpeople.ourpeople.R;

/**
 * Created by hareesh.pammi on 29/07/16.
 */
public class SplashScreen extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        //View loginView = findViewById(R.id.ll_login);

        (findViewById(R.id.ll_signuptext)).setOnClickListener(s -> {
            Intent signupIntent = new Intent(SplashScreen.this, RegisterScreen.class);
            SplashScreen.this.startActivity(signupIntent);
        });

        TextInputEditText emailText = (TextInputEditText) findViewById(R.id.email);
        emailText.setOnFocusChangeListener((s,v) ->{
            if(!v) {
                if (!(Patterns.EMAIL_ADDRESS.matcher(emailText.getText()).matches())) {
                    Toast.makeText(SplashScreen.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    emailText.setError("Invalid Email");
                    emailText.setText("");
                }
            }
        });

        

        TextInputEditText password = ((TextInputEditText) findViewById(R.id.pwd));
        password.setOnFocusChangeListener((s,v) -> {
            if(!v) {
                if (!(password.getText().length() < 6)) {
                    Toast.makeText(SplashScreen.this, "Password should contain minimum 6 characters", Toast.LENGTH_SHORT).show();
                    password.setError("Password should contain minimum 6 characters");
                    password.setText("");
                }
            }
        });

    }

}
