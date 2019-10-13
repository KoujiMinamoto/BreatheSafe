package com.example.breathesafe;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
public class Howfeeling extends AppCompatActivity {


    @BindView(R.id.set_btnforno)
    Button set_btnforno;
    private EditText et_1;
    private EditText et_2;
    private EditText et_3;
    private EditText et_4;
    private EditText et_5;
    private int a1;
    private int a2;
    private int a3;
    private int a4;
    private int a5;
    private  String color;
    public CallingRestful callapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_no);
        ButterKnife.bind(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.toolBar));
//        setSupportActionBar(toolBar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            toolBarTitle.setText("服药提醒");
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
        initView();
    }

    private void initView() {
        //set_btnforact.setOnClickListener(this);
        set_btnforno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.this.finish();
//                //跳转到主界面，登录成功的状态传递到 MainActivity 中
//                startActivity(new Intent(MainActivity.this, ActActivity.class));
                //LitePal.deleteAll(ActDB.class);
                setClock();
                Toast.makeText(getApplicationContext(),"Successfully shared with your admin",Toast.LENGTH_LONG).show();
                Howfeeling.this.finish();

                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(Howfeeling.this, MainActivity.class));

            }
        });

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

    private void setClock() {
        et_1 = findViewById(R.id.et_1);
        et_2 = findViewById(R.id.et_2);
        et_3 = findViewById(R.id.et_3);
        et_4 = findViewById(R.id.et_4);
        et_5 = findViewById(R.id.et_5);
        a1 = Integer.valueOf(et_1.getText().toString().trim());
        a2 = Integer.valueOf(et_2.getText().toString().trim());
        a3 = Integer.valueOf(et_3.getText().toString().trim());
        a4 = Integer.valueOf(et_4.getText().toString().trim());
        a5 = Integer.valueOf(et_5.getText().toString().trim());
        if (a1>1 || a2>1 || a3>1 || a4>1 || a5>1)
        {
            color = "red";
            Thread name = new Thread(NameOfRunnable);
            name.start();

        }else if (a1 <1 && a2 <1 && a3<1 && a4<1 && a5<1)
        {
            color = "green";

        }
        else
        {

            color = "yellow";
            Thread name = new Thread(NameOfRunnable);
            name.start();
        }
//        Thread name = new Thread(NameOfRunnable);
//        name.start();




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
                    SharedPreferences preferences = getSharedPreferences("Uid",MODE_PRIVATE);
                    int uid = preferences.getInt("Uid",0);
//                int uid =3;
                    callapi.postno(1,color,uid);

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


