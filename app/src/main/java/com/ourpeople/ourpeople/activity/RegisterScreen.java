package com.ourpeople.ourpeople.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.ourpeople.ourpeople.R;

/**
 * Created by hareesh.pammi on 29/08/16.
 */
public class RegisterScreen extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        TextInputEditText emailInSignup = (TextInputEditText) findViewById(R.id.et_signupemail);
        TextInputEditText passwordInSignup = ((TextInputEditText) findViewById(R.id.et_signuppassword));

        emailInSignup.setOnFocusChangeListener((s,v) -> {
            if (!v) {
                if (!(Patterns.EMAIL_ADDRESS.matcher(emailInSignup.getText()).matches())) {
                    //Toast.makeText(RegisterScreen.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    emailInSignup.setError("Invalid Email");
                    emailInSignup.setText("");
                }
            }
        });

        passwordInSignup.setOnFocusChangeListener((s,v) -> {
            if (!v) {
                if (passwordInSignup.getText().length() < 6) {
                    //Toast.makeText(RegisterScreen.this, "Password should contain minimum 6 characters", Toast.LENGTH_SHORT).show();
                    passwordInSignup.setError("Password should contain minimum 6 characters");
                    passwordInSignup.setText("");
                }
            }
        });

        Button register = ((Button) findViewById(R.id.btn_register));
        register.setOnClickListener(s -> {
            if(emailInSignup.getText().equals("") || emailInSignup.getText() == null || passwordInSignup.getText().equals("") || passwordInSignup.getText() == null) {
                Toast.makeText(RegisterScreen.this, "Please enter Email & Password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterScreen.this, "Email:"+emailInSignup.getText()+",Pwd:"+passwordInSignup.getText(), Toast.LENGTH_SHORT).show();
                Intent homeScreenIntent = new Intent(RegisterScreen.this, HomeScreen.class);
                RegisterScreen.this.startActivity(homeScreenIntent);
            }
        });

        (findViewById(R.id.ll_logintext)).setOnClickListener(s -> {
            Intent loginIntent = new Intent(RegisterScreen.this, SplashScreen.class);
            RegisterScreen.this.startActivity(loginIntent);
        });
    }
}
