package com.ourpeople.ourpeople.asynctasks;

import android.os.AsyncTask;

import com.ourpeople.ourpeople.services.CommonUtils;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hareesh.pammi on 17/10/16.
 */
public class HttpPostCalls extends AsyncTask<String, Integer, String> {
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

        String apiResponse = CommonUtils.executePostHttpCall(apiUrl, requestBody);
        return apiResponse;
    }


}
