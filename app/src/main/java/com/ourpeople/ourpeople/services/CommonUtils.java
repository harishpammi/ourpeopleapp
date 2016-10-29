package com.ourpeople.ourpeople.services;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by hareesh.pammi on 16/10/16.
 */
public class CommonUtils {

    public static String executeGetHttpCall(String url) {
        Log.i("OurService", "executeGetHttpCall called for Url: " + url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        String response = null;

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            httpGet.setHeader("Content-Type", "application/json");
            response = httpClient.execute(httpGet, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("OurService", "executeGetHttpCall response: " + response);
        return response;
    }

    public static String executePostHttpCall(String url, List<NameValuePair> bodyParams) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response = null;
        Log.i("OurService", "executePostHttpCall called for Url: " + url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(bodyParams));
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
