package com.example.breathesafe;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class WeathersAPI {
    private static final String API_KEY = "/?token=a5e02f669ccb7164c28ac9c093d25917bec265c5";
    private static final String USER_AGENT = "Mozilla/5.0";

    public static String search(String keyword) {

        String result=null;
        String pm25="";
        String pressure="";
        String hum="";
        String tep="";

        keyword = keyword.trim().replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        try {
            url = new URL("https://api.waqi.info/feed/geo:"+ keyword + API_KEY);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
           // connection.setRequestProperty("Content-Type", "application/json");
            //connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        try {
            JSONObject info = new JSONObject(textResult);
            JSONObject weathers = info.getJSONObject( "data" );
            //JSONObject weather = weathers.getJSONObject(0);
            JSONObject weatherItem = weathers.getJSONObject( "iaqi" );
            //JSONArray nutrients = weatherItem.getJSONArray( "nutrients" );


            pm25 = getpm(weatherItem);
            pressure=getp(weatherItem);
            hum=geth(weatherItem);
            tep=gettemp(weatherItem);

            //servingUNT =  getUnit(nutrients);
           // foodName = getFoodName(foodItem);
            //calorieAMO = getCalorieAMO(nutrients);
           // fatAMO =  getFatAMO(nutrients);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        result =  pm25+","+pressure+","+hum+","+tep;

        return result;



    }

    //---------------------------------------------------------
// extract food detail methods
    private static String getpm(JSONObject job){
        try {

            int pm = job.getJSONObject("pm25").getInt("v");
            JSONObject desc = job.getJSONObject( "pm25" );
            //String pm25 = desc.getString( "v" );
            String pm25=Integer.toString(pm);
            return pm25;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "no data";

    }
    private static String gettemp(JSONObject job){
        try {

            JSONObject desc = job.getJSONObject( "t" );
            String pm25 = desc.getString( "v" );
            return pm25;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "no data";

    }
    private static String geth(JSONObject job){
        try {

            JSONObject desc = job.getJSONObject( "h" );
            String pm25 = desc.getString( "v" );
            return pm25;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "no data";

    }
    private static String getp(JSONObject job){
        try {

            JSONObject desc = job.getJSONObject( "p" );
            String pm25 = desc.getString( "v" );
            return pm25;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "no data";

    }



}
