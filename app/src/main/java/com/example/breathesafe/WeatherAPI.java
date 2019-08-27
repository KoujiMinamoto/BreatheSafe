package com.example.breathesafe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeatherAPI {


    OkHttpClient client = new OkHttpClient();
    String run(String url) throws IOException {

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "air-quality.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "1e50092e6cmsh1177ab01cd91139p19d4fdjsn0918e5de6b15")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();

    }

}
