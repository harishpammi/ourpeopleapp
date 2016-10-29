package com.ourpeople.ourpeople.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ourpeople.ourpeople.R;
import com.ourpeople.ourpeople.asynctasks.HttpPostCalls;
import com.ourpeople.ourpeople.models.RegisterApiBody;
import com.ourpeople.ourpeople.services.CommonUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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

        emailInSignup.setOnFocusChangeListener((s, v) -> {
            if (!v) {
                if (!(Patterns.EMAIL_ADDRESS.matcher(emailInSignup.getText()).matches())) {
                    //Toast.makeText(RegisterScreen.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    emailInSignup.setError("Invalid Email");
                    emailInSignup.setText("");
                }
            }
        });

        passwordInSignup.setOnFocusChangeListener((s, v) -> {
            if (!v) {
                if (passwordInSignup.getText().length() < 6) {
                    //Toast.makeText(RegisterScreen.this, "Password should contain minimum 6 characters", Toast.LENGTH_SHORT).show();
                    passwordInSignup.setError("Password should contain minimum 6 characters");
                    passwordInSignup.setText("");
                }
            }
        });

        Button register = ((Button) findViewById(R.id.btn_register));
        if (register != null) {
            register.setOnClickListener(s -> {
                String emailTextString = String.valueOf(emailInSignup.getText());
                Log.i("OurService", "Email Entered: " + emailTextString);
                String passwordString = String.valueOf(passwordInSignup.getText());
                Log.i("OurService", "Password Entered: " + passwordString);

                if (emailTextString.equals("") || emailTextString == null || passwordString.equals("") || passwordString == null) {
                    Toast.makeText(RegisterScreen.this, "Please enter Email & Password", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(RegisterScreen.this, "Email:" + emailInSignup.getText() + ",Pwd:" + passwordInSignup.getText(), Toast.LENGTH_SHORT).show();

                    try {
                        new RegisterPostCall().execute(new String[]{"http://192.168.43.78:8080/signup",emailTextString}).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        (findViewById(R.id.ll_logintext)).setOnClickListener(s -> {
            Intent loginIntent = new Intent(RegisterScreen.this, SplashScreen.class);
            RegisterScreen.this.startActivity(loginIntent);
        });
    }

    public class RegisterPostCall extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String apiUrl = params[0];
            List<NameValuePair> requestBody = new ArrayList<NameValuePair>();
            requestBody.add(new BasicNameValuePair("first_name", "Anil Raju"));
            requestBody.add(new BasicNameValuePair("phone", "1234000003"));
            requestBody.add(new BasicNameValuePair("email", params[1]));
            requestBody.add(new BasicNameValuePair("father_name", "Anil Raju"));
            requestBody.add(new BasicNameValuePair("mother_name", "Anil Raju"));
            requestBody.add(new BasicNameValuePair("spouse_name", "Anil Raju"));
            requestBody.add(new BasicNameValuePair("address", "address"));
            requestBody.add(new BasicNameValuePair("community_id", "1"));
            RegisterApiBody registerApiBody = new RegisterApiBody("Anil Raju", "1234000003", params[1], "Anil R", "Anil R", "Test", "Address", "1");

            String apiResponse = executeRegisterCall(apiUrl, registerApiBody);
            return apiResponse;
        }

        @Override
        protected void onPostExecute(String registerResponse) {
            if(registerResponse != null) {

                Intent homeScreenIntent = new Intent(RegisterScreen.this, HomeScreen.class);
                RegisterScreen.this.startActivity(homeScreenIntent);
            } else {
                Toast.makeText(RegisterScreen.this, "Something went wrong, Please try again", Toast.LENGTH_SHORT).show();

            }
        }

        public String executeRegisterCall(String url, RegisterApiBody bodyParams) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            HttpResponse response = null;
            Log.i("OurService", "executePostHttpCall called for Url: " + url);
            try {
                //StringEntity stringEntity = new StringEntity("{\"first_name\": \"Anil Raj\",\"phone\": \"1234000003\",\"email\": \"raj1003@gmail.com\",\"father_name\": \"nag\",\"mother_name\": \"ind\",\"spouse_name\": \"lak\",\"address\": \"address\",\"community_id\": 1}");
                Gson gson = new Gson();
                StringEntity stringEntity = new StringEntity(gson.toJson(bodyParams));
                httpPost.setEntity(stringEntity);
                //httpPost.setEntity(new BufferedHttpEntity(bodyParams));
                Log.i("OurService", "Entity called for Url: ");
                httpPost.setHeader("Content-Type", "application/json");

                response = httpClient.execute(httpPost);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("OurService", "executeGetHttpCall response: " + response.toString());
            return response.toString();
        }
    }

}

