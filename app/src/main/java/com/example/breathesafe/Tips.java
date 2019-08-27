package com.example.breathesafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Tips extends AppCompatActivity{

    private TextView mTextMessage;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_tips);
        mTextMessage = findViewById(R.id.message);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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




    private void init() {

        btn_1=findViewById(R.id.Temperature);
        btn_2=findViewById(R.id.Humidity);
        btn_3=findViewById(R.id.Pressure);
        btn_4=findViewById(R.id.air);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tips.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(Tips.this, Temperature.class));



            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tips.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(Tips.this, Hum.class));



            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tips.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(Tips.this, pre.class));



            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tips.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(Tips.this, aqi.class));



            }
        });
    }
}
