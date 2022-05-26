package com.example.gamesearch;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class JSONParser {

    static JSONObject jObj = null;
    static String json = "";
    private final OkHttpClient client = new OkHttpClient();
    // constructor
    public JSONParser() {}

    public JSONObject getJSONFromUrl(String url) {
        StrictMode.ThreadPolicy gfgPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(gfgPolicy);
        // Making HTTP request
        try{
            // Create a new HTTP Client
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = null;
            // Grab the response
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (response != null) {
                System.out.println("Не пиздец");
                json = response.body().string();
                response.close();
            }
            else System.out.println("Пиздец");
        } catch(Exception e){
            // In your production code handle any errors and catch the individual exceptions
            e.printStackTrace();
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }
}