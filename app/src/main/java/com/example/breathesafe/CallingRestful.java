package com.example.breathesafe;

import android.net.wifi.hotspot2.pps.Credential;
import android.util.Log;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.text.SimpleDateFormat;


import javax.net.ssl.HttpsURLConnection;

public class CallingRestful {

    private static final String BASE_URL =
            "https://breathesafely20191003034507.azurewebsites.net/api/";
    //private static final String USER_AGENT = "Mozilla/5.0";

    public static String findAllorganisations() {
        final String methodPath = "organisations/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";

        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.setRequestProperty("User-Agent", USER_AGENT);
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return textResult;
    }

//    private static String getorname(JSONObject job){
//        try {
//
//            String pm = job.getJSONObject("pm25").getString("name");
//            JSONObject desc = job.getJSONObject( "pm25" );
//            //String pm25 = desc.getString( "v" );
//            String pm25=Integer.toString(pm);
//            return pm25;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return "no data";
//
//    }
    public static String finddepartment() {
        final String methodPath = "departments/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.setRequestProperty("User-Agent", USER_AGENT);
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;
    }

    public static String findAllworkers() {
        final String methodPath = "workers/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";

        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.setRequestProperty("User-Agent", USER_AGENT);
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return textResult;
    }

    public static String findworkersid(String useremail) {
        final String methodPath = "workers/";
        //initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";

        //Making HTTP request
        try {
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to GET
            conn.setRequestMethod("GET");
            //add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //conn.setRequestProperty("User-Agent", USER_AGENT);
            //Read the response
            Scanner inStream = new Scanner(conn.getInputStream());
            //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return textResult;
    }


    public static void postUser(int id,String fname,String lname,String email,String ecname,String ecworlc,int oid,int did) {
        //initialise


        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "workers/";
        try {
            Gson gson = new Gson();
            User user = new User(id, fname, lname, email,ecname, ecworlc, oid, did);
            //Credential credential = new Credential(userId, username, hashedpwd, signUpDate);
            String stringCredentialJson = gson.toJson(user);
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringCredentialJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringCredentialJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public static void postgreenzone(int id,String c_medication,String c_dose,String c_form,String c_freq,String r_medication,String r_dose,String r_form,String r_freq,int workid) {
        //initialise


        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "greenzones/";
        try {
            Gson gson = new Gson();
            greenzone gz = new greenzone(id, c_medication, c_dose,c_form,c_freq, r_medication, r_dose, r_form,r_freq,workid);
            //Credential credential = new Credential(userId, username, hashedpwd, signUpDate);
            String stringCredentialJson = gson.toJson(gz);
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //conn.setRequestMethod("PUT");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringCredentialJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringCredentialJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
    public static void postyellowzone(int id,String c_medication,String c_dose,String c_form,String c_freq,String r_medication,String r_dose,String r_form,String r_freq,int workid) {
        //initialise


        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "yellozones/";
        try {
            Gson gson = new Gson();
            yellowzone yz = new yellowzone(id, c_medication, c_dose,c_form,c_freq, r_medication, r_dose, r_form,r_freq,workid);
            //Credential credential = new Credential(userId, username, hashedpwd, signUpDate);
            String stringCredentialJson = gson.toJson(yz);
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //conn.setRequestMethod("PUT");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringCredentialJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringCredentialJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    public static void postredzone(int id,String c_medication,String c_dose,String c_form,String c_freq,int workid) {
        //initialise


        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "redzones/";
        try {
            Gson gson = new Gson();
            redzone rz = new redzone(id, c_medication, c_dose,c_form,c_freq,workid);
            //Credential credential = new Credential(userId, username, hashedpwd, signUpDate);
            String stringCredentialJson = gson.toJson(rz);
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //conn.setRequestMethod("PUT");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringCredentialJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringCredentialJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
    public static void postno(int id,String c_form,int workerid) {
        //initialise


        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        String c_medication = sdf.format(date);
        String c_dose = sdf.format(date);
        URL url = null;
        HttpURLConnection conn = null;
        final String methodPath = "notifications/";
        try {
            Gson gson = new Gson();
            notifications no = new notifications(id, c_medication, c_dose,c_form,workerid);
            //Credential credential = new Credential(userId, username, hashedpwd, signUpDate);
            String stringCredentialJson = gson.toJson(no);
            url = new URL(BASE_URL + methodPath);
            //open the connection
            conn = (HttpsURLConnection) url.openConnection();
            //set the timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //set the connection method to POST
            conn.setRequestMethod("POST");
            //set the output to true
            conn.setDoOutput(true);
            //set length of the data you want to send
            conn.setFixedLengthStreamingMode(stringCredentialJson.getBytes().length);
            //add HTTP headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            //Send the POST out
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(stringCredentialJson);
            out.close();
            Log.i("error", new Integer(conn.getResponseCode()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }


}
