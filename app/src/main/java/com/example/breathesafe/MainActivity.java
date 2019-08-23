package com.example.breathesafe;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Context mContext = MainActivity.this;
    private TextView mTextMessage;
    private Button btn_for;
    private Button btn_tip;
    private Button btn_homepage;
    private Button btn_prototype;
    private Button btn_setting;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_setting:
                    mTextMessage.setText(R.string.title_setting);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTextMessage = findViewById(R.id.message);

        init();

    }



    private void init() {
        btn_for = findViewById(R.id.forecast);
        btn_tip = findViewById(R.id.tips);


        //back home page
        /*
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.getMenu().getItem(0).setChecked(true);//设置默认选中的Tab
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment nextFragment = null;
                /*通过判断进行选择Tab之后想要的操作，一般是进行fragment的动态切换
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        mTextMessage.setText(R.string.title_home);
                        nextFragment = new MainFragment();
                        return true;

                }
                return false;
            }
        });
        */
        btn_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(MainActivity.this, Tips.class));



            }
        });
        //go to forecast
        btn_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
                //跳转到主界面，登录成功的状态传递到 MainActivity 中
                startActivity(new Intent(MainActivity.this, Forecast.class));



            }
        });

    }

}
