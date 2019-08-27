package com.example.breathesafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Forecast extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView adr;

    private TextView airq;
    private TextView humv;
    private TextView prev;

    private DashBoard wpbView;
    public WeatherAPI weather;
    public Weather weatherinfo;
    private Spinner spin;
    private Geo geo;
    //private ArrayList<Geo> mData = null;
    private BaseAdapter myAdadpter = null;
    public String keyword="";

    public int getResult(){
        int result=0;
        String url = "https://air-quality.p.rapidapi.com/current/airquality?lon=-78.63&lat=35.5";
        String data = getRisklevel(url);





        return result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
       // final DashBoard d = (DashBoard) findViewById(R.id.dash);
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_forecast);
        spinnerview();
        //mTextMessage = findViewById(R.id.showtemperature);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //String result = WeathersAPI.search("-37.7783165;145.0306");
        //String result = WeathersAPI.search(keyword);
        //mTextMessage.setText(result);



        //设置此界面为竖屏

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in Action Bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getRisklevel(String url) {
        String result="";
        try {
           result= weather.run(url);


        }
        catch (IOException e){


        }


        return result;

    }

    private void spinnerview(){
        /*List<Geo> geo = new ArrayList<Geo>();
        spin=(Spinner) findViewById(R.id.spinner);
        geo.add(new Geo("Boxhill","-37.7783165;145.0306"));
        geo.add(new Geo("Caulfield","-37.8840;145.0266"));

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                R.layout.item_select, geo.indexOf(0));


        spinnerAdapter.setDropDownViewResource(R.layout.item_drop);
        //这个在不同的Theme下，显示的效果是不同的
        //spinnerAdapter.setDropDownViewTheme(Theme.LIGHT);
        spin.setAdapter(spinnerAdapter);*/
        final DashBoard d = (DashBoard) findViewById(R.id.dash);
        spin=(Spinner)findViewById(R.id.spinner);
        final List<Geo> geo = new ArrayList<Geo>();
        spin=(Spinner) findViewById(R.id.spinner);
        geo.add(new Geo("Boxhill","-37.7783165;145.0306"));
        geo.add(new Geo("Clayton","-37.8840;145.0266"));
        geo.add(new Geo("CHADSTONE","-37.886371999999994;145.082527"));
        geo.add(new Geo("MELBOURNE","-37.814563;144.97026699999998"));
        geo.add(new Geo("MALVERN","-37.861427;145.02850800000002"));
        geo.add(new Geo("RICHMOND","-37.818587;144.999181"));
        geo.add(new Geo("GLEN WAVERLEY","-37.877631;145.166222"));
        geo.add(new Geo("ELWOOD","-37.888121000000005;144.985026"));
        geo.add(new Geo("CARNEGIE","-37.889336;145.058121"));
        geo.add(new Geo("CAULFIELD","-37.880479;145.026806"));

        final String sub[]=new String[]{"Boxhill","Clayton","CHADSTONE","CBD","Malvern","RICHMOND","GLEN WAVERLEY","ELWOOD","CARNEGIE","CAULFIELD"};
        final ArrayAdapter<String> aa= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,sub);
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                // 将所选mySpinner 的值带入myTextView 中
                keyword=geo.get(arg2).getGeo();//文本说明
                adr.setText(geo.get(arg2).getSub());

                String result = WeathersAPI.search(keyword);
               /* String[] data = result.split(",");
                String pm25 = data[0];
                String pressure =data[1];
                String hum = data[2];
                String tep = data[3];
                mTextMessage.setText("Temperature"+"\n" + tep+"°C");
                airq.setText("Air Quality"+"\n"+pm25);
                humv.setText("Humidity"+"\n"+hum+"%");
                prev.setText("Pressure"+"\n"+pressure);
                int ipm25 = convertStringtoInt(pm25);
                int ipre = convertStringtoInt(pressure);
                int ihum = convertStringtoInt(hum);
                int itep = convertStringtoInt(tep);
                //--dashborad model
                double risklevel = 0;
                if (ipm25>=0&&ipm25<=12){
                    risklevel= risklevel+0;

                }else if(ipm25>=12.1&&ipm25<=35.4){risklevel= risklevel+0.41;}
                else if(ipm25>=35.5&&ipm25<=55.4){risklevel= risklevel+0.82;}
                else if(ipm25>=55.5&&ipm25<=150.4){risklevel= risklevel+1.23;}
                else if(ipm25>=150.5&&ipm25<=250.4){risklevel= risklevel+1.64;}
                else if(ipm25>=250.5&&ipm25<=350.4){risklevel= risklevel+2.05;}
                else {risklevel= risklevel+2.05;}

                if (itep>=26){risklevel= risklevel+0.25;}
                else if (itep>=17&&itep<26){risklevel= risklevel+0;}
                else if (itep>=14&&itep<17){risklevel= risklevel+0.25;}
                else if (itep>=7&&itep<14){risklevel= risklevel+0.5;}
                else {risklevel= risklevel+0.75;}

                if (ihum>=80&&ihum<=100){risklevel= risklevel+1.02;}
                else if (ihum>=60&&ihum<80){risklevel= risklevel+0.34;}
                else if (ihum>=40&&ihum<60){risklevel= risklevel+0;}
                else if (ihum>=20&&ihum<40){risklevel= risklevel+0.68;}
                else {risklevel= risklevel+1.32;}*/
               double risk = getrisklevel(result);
                float f =(float)risk;
                d.cgangePer(f/6f);





            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
                keyword=geo.get(0).getSub();
            }
        });


        mTextMessage = findViewById(R.id.showtemperature);
        prev = findViewById(R.id.showPressure);
        humv = findViewById(R.id.showHumidity);
        airq = findViewById(R.id.showairq);
        adr = findViewById(R.id.showadress);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //String result = WeathersAPI.search("-37.7783165;145.0306");
       // String result = WeathersAPI.search(keyword);
        //String result = keyword;
        //mTextMessage.setText(result);









    }


    private double getrisklevel(String result){

        String[] data = result.split(",");
        String pm25 = data[0];
        String pressure =data[1];
        String hum = data[2];
        String tep = data[3];
        mTextMessage.setText("Temperature"+"\n" + tep+"°C");
        airq.setText("Air Quality"+"\n"+pm25);
        humv.setText("Humidity"+"\n"+hum+"%");
        prev.setText("Pressure"+"\n"+pressure);
        int ipm25 = convertStringtoInt(pm25);
        int ipre = convertStringtoInt(pressure);
        int ihum = convertStringtoInt(hum);
        int itep = convertStringtoInt(tep);
        //--dashborad model
        double risklevel = 0;
        if (ipm25>=0&&ipm25<=12){
            risklevel= risklevel+0;

        }else if(ipm25>=12.1&&ipm25<=35.4){risklevel= risklevel+0.41;}
        else if(ipm25>=35.5&&ipm25<=55.4){risklevel= risklevel+0.82;}
        else if(ipm25>=55.5&&ipm25<=150.4){risklevel= risklevel+1.23;}
        else if(ipm25>=150.5&&ipm25<=250.4){risklevel= risklevel+1.64;}
        else if(ipm25>=250.5&&ipm25<=350.4){risklevel= risklevel+2.05;}
        else {risklevel= risklevel+2.05;}

        if (itep>=26){risklevel= risklevel+0.25;}
        else if (itep>=17&&itep<26){risklevel= risklevel+0;}
        else if (itep>=14&&itep<17){risklevel= risklevel+0.25;}
        else if (itep>=7&&itep<14){risklevel= risklevel+0.5;}
        else {risklevel= risklevel+0.75;}

        if (ihum>=80&&ihum<=100){risklevel= risklevel+1.02;}
        else if (ihum>=60&&ihum<80){risklevel= risklevel+0.34;}
        else if (ihum>=40&&ihum<60){risklevel= risklevel+0;}
        else if (ihum>=20&&ihum<40){risklevel= risklevel+0.68;}
        else {risklevel= risklevel+1.32;}

        return risklevel;




    }

    private void init() {

    }

    private int convertStringtoInt(String input) //method to convert String to Integer
    {
        //intialised variables
        String S = input;
        int i = 0;
        //try catch to handle NumberFormatException
        try
        {
            // the String to int conversion happens here
            i = Integer.parseInt(input.trim());

            // print out the value after the conversion
            //System.out.println("int i = " + i);
        }
        catch (NumberFormatException nfe)
        {
            //System.out.println("NumberFormatException: " + nfe.getMessage() + ", please input an integer!");
            i =0;
        }
        return i;
    }
}
