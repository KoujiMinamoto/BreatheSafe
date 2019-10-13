package com.example.breathesafe;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.breathesafe.User;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;
import com.mongodb.BasicDBObject;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.breathesafe.DBFolder.ActDB;
import com.example.breathesafe.DBFolder.AlarmDB;
import com.example.breathesafe.Utils.AlarmManagerUtil;
import com.example.breathesafe.View.SelectRemindCyclePopup;
import com.example.breathesafe.View.SelectRemindWayPopup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Firsttime extends AppCompatActivity {

    @BindView(R.id.set_btnforuser)
    Button set_btnforuser;

    private EditText et_fname;
    private EditText et_lname;
    private EditText et_email;
    private EditText et_emname;
    private EditText et_emloc;
    private TextView textView16;
    SharedPreferences preferences;

    private String fname;
    private String lname;
    private String email;
    private String emname;
    private String emloc;

    public CallingRestful callapii;
    private String or;
    private String dep;
    private String[] a;
    private String[] b;
    private Spinner spino;
    private int oid;
    private int did;
    private int uid;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        textView16 = findViewById(R.id.textView16);

        //int oid = 0;

        initView();
    }


    private  void draw(){

        or = callapii.findAllorganisations();
        dep = callapii.finddepartment();
        try {
            //JSONObject info = new JSONObject(or);
            JSONArray jsonArray = new JSONArray(or);
            //JSONArray json = JSONArray.fromObject(or);
            String[] a = new String[jsonArray.length()];
            for (int i=0; i < jsonArray.length(); i++)    {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name1 = jsonObject.getString("name");
                //a[i] = name;
                a[i] = name1;

            }

            spino=(Spinner) findViewById(R.id.spinner_o);
            final ArrayAdapter<String> aa= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,a);
            spino.setAdapter(aa);
            spino.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    // TODO Auto-generated method stub

                    oid = arg2+1;

                }

                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    //keyword=geo.get(0).getSub();
                    oid = 1;
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            //JSONObject info = new JSONObject(or);
            JSONArray jsonArray = new JSONArray(dep);
            //JSONArray json = JSONArray.fromObject(or);
            String[] b = new String[jsonArray.length()];
            for (int i=0; i < jsonArray.length(); i++)    {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name1 = jsonObject.getString("name");
                //a[i] = name;
                b[i] = name1;

            }

            spino=(Spinner) findViewById(R.id.spinner_d);
            final ArrayAdapter<String> bb= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,b);
            spino.setAdapter(bb);
            spino.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {//选择item的选择点击监听事件
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    // TODO Auto-generated method stub

                    did = arg2+1;

                }

                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    //keyword=geo.get(0).getSub();
                    did = 1;
                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }





    }
    private void initView() {
        //set_btnforact.setOnClickListener(this);
//        try {
//            //JSONObject info = new JSONObject(or);
//            JSONArray jsonArray = new JSONArray(or);
//            //JSONArray json = JSONArray.fromObject(or);
//            String[] a = new String[jsonArray.length()];
//            for (int i=0; i < jsonArray.length(); i++)    {
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String name1 = jsonObject.getString("name");
//                //a[i] = name;
//                a[i] = name1;
//
//            }
//            textView16.setText(a[1]);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        draw();
        set_btnforuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.this.finish();
//                //跳转到主界面，登录成功的状态传递到 MainActivity 中
//                startActivity(new Intent(MainActivity.this, ActActivity.class));
                // LitePal.deleteAll(ActDB.class);

                setinfo();


            }
        });
    }

    private void setinfo() {

        et_fname = findViewById(R.id.et_fname);
        fname = et_fname.getText().toString().trim();
        et_lname = findViewById(R.id.et_lname);
        lname = et_lname.getText().toString().trim();
        et_email = findViewById(R.id.et_email);
        email = et_email.getText().toString().trim();
        et_emname = findViewById(R.id.et_ec_name);
        emname = et_emname.getText().toString().trim();
        et_emloc = findViewById(R.id.et_ec_workloc);
        emloc = et_emloc.getText().toString().trim();


        //textView16.setText(Integer.toString(oid));
        String worker = callapii.findAllworkers();
        try {
            //JSONObject info = new JSONObject(or);
            JSONArray jsonArray = new JSONArray(worker);
            //JSONArray json = JSONArray.fromObject(or);
            uid = jsonArray.length()+1;


        } catch (JSONException e) {
            e.printStackTrace();
        }

        preferences = getSharedPreferences("count",MODE_PRIVATE);
        preferences = getSharedPreferences("Uid",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Uid",uid);
        editor.putInt("count",1);
        editor.commit();
        Thread name = new Thread(NameOfRunnable);
        name.start();
        //callapii.postUser(uid,fname,lname,email,emname,emloc,oid,did);

        Firsttime.this.finish();
        //跳转到主界面，登录成功的状态传递到 MainActivity 中
        startActivity(new Intent(Firsttime.this, MainActivity.class));







    }
    public Runnable NameOfRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            if (true)
            {
                // TODO add code to refresh in background
                try
                {

                    Thread.sleep(1000);// sleeps 1 second
//                    SharedPreferences preferences = getSharedPreferences("Uid",MODE_PRIVATE);
//                    int uid = preferences.getInt("Uid",0);
//                int uid =3;
                    callapii.postUser(uid,fname,lname,email,emname,emloc,oid,did);
//                    callapi.postredzone(uid,redrename,redredosage,redreform,redrefrequency,uid);
//                    callapi.postgreenzone(uid,greenconname,greencondosage,greenconform,greenconfrequency,greenrename,greenredosage,greenreform,greenrefrequency,uid);
//                    callapi.postyellowzone(uid,yellowconname,yellowcondosage,yellowconform,yellowconfrequency,yellowrename,yellowredosage,yellowreform,yellowrefrequency,uid);
////
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    };

}
