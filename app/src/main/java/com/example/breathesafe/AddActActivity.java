package com.example.breathesafe;

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

public class AddActActivity extends AppCompatActivity implements View.OnClickListener {




    @BindView(R.id.set_btnforact)
    Button set_btnforact;
//    @BindView(R.id.all_layout)
//    LinearLayout allLayout;

    private TimePickerView pvTime;
    private EditText et_greenrename;
    private EditText et_greenredosage;
    private EditText et_greenreform;
    private EditText et_greenrefrequency;
    private EditText et_greenconname;
    private EditText et_greencondosage;
    private EditText et_greenconform;
    private EditText et_greenconfrequency;
    private EditText et_yellowrename;
    private EditText et_yellowredosage;
    private EditText et_yellowreform;
    private EditText et_yellowrefrequency;
    private EditText et_yellowconname;
    private EditText et_yellowcondosage;
    private EditText et_yellowconform;
    private EditText et_yellowconfrequency;
    private EditText et_redrename;
    private EditText et_redredosage;
    private EditText et_redreform;
    private EditText et_redrefrequency;

    private String greenrename;
    private String greenredosage;
    private String greenreform;
    private String greenrefrequency;
    private String greenconname;
    private String greencondosage;
    private String greenconform;
    private String greenconfrequency;
    private String yellowrename;
    private String yellowredosage;
    private String yellowreform;
    private String yellowrefrequency;
    private String yellowconname;
    private String yellowcondosage;
    private String yellowconform;
    private String yellowconfrequency;
    private String redrename;
    private String redredosage;
    private String redreform;
    private String redrefrequency;
    private int cycle;
    private int ring;
    public CallingRestful callapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_actplan);
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
        set_btnforact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.this.finish();
//                //跳转到主界面，登录成功的状态传递到 MainActivity 中
//                startActivity(new Intent(MainActivity.this, ActActivity.class));
                LitePal.deleteAll(ActDB.class);
                setClock();




            }
        });
//        repeat_rl.setOnClickListener(this);
//        ring_rl.setOnClickListener(this);

//        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {
//                time = getTime(date);
//                date_tv.setText(getTime(date));
//            }
//        })
//                .setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
//                .setCancelText("Cancel")//取消按钮文字
//                .setSubmitText("Save")//确认按钮文字
//                .setContentTextSize(18)//滚轮文字大小
//                .setTitleSize(20)//标题文字大小
//                .setTitleText("choose time")//标题文字
//                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
//                .isCyclic(true)//是否循环滚动
//                .setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLACK)//确定按钮文字颜色
//                .setCancelColor(Color.BLACK)//取消按钮文字颜色
//                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
//                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
//                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .isDialog(false)//是否显示为对话框样式
//                .build();
//
//        date_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                pvTime.show();
//            }
//        });

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.repeat_rl:
//                selectRemindCycle();
//                break;
//            case R.id.ring_rl:
//                selectRingWay();
//                break;
            case R.id.set_btn:
                setClock();
                break;
            default:
                break;
        }
    }

    private void setClock() {


        et_greenrename = findViewById(R.id.et_greenrename);
        greenrename = et_greenrename.getText().toString().trim();
        et_greenredosage = findViewById(R.id.et_greenredosage);
        greenredosage = et_greenredosage.getText().toString().trim();
        et_greenreform = findViewById(R.id.et_greenreform);
        greenreform = et_greenreform.getText().toString().trim();
        et_greenrefrequency = findViewById(R.id.et_greenrefrequency);
        greenrefrequency = et_greenrefrequency.getText().toString().trim();

        et_greenconname = findViewById(R.id.et_greenconname);
        greenconname = et_greenconname.getText().toString().trim();
        et_greencondosage = findViewById(R.id.et_greencondosage);
        greencondosage = et_greencondosage.getText().toString().trim();
        et_greenconform = findViewById(R.id.et_greenconform);
        greenconform = et_greenconform.getText().toString().trim();
        et_greenconfrequency = findViewById(R.id.et_greenconfrequency);
        greenconfrequency = et_greenconfrequency.getText().toString().trim();

        et_yellowrename = findViewById(R.id.et_yellowrename);
        yellowrename = et_yellowrename.getText().toString().trim();
        et_yellowredosage = findViewById(R.id.et_yellowredosage);
        yellowredosage = et_yellowredosage.getText().toString().trim();
        et_yellowreform = findViewById(R.id.et_yellowreform);
        yellowreform = et_yellowreform.getText().toString().trim();
        et_yellowrefrequency = findViewById(R.id.et_yellowrefrequency);
        yellowrefrequency = et_yellowrefrequency.getText().toString().trim();

        et_yellowconname = findViewById(R.id.et_yellowconname);
        yellowconname = et_yellowconname.getText().toString().trim();
        et_yellowcondosage = findViewById(R.id.et_yellowcondosage);
        yellowcondosage = et_yellowcondosage.getText().toString().trim();
        et_yellowconform = findViewById(R.id.et_yellowconform);
        yellowconform = et_yellowconform.getText().toString().trim();
        et_yellowconfrequency = findViewById(R.id.et_yellowconfrequency);
        yellowconfrequency = et_yellowconfrequency.getText().toString().trim();

        et_redrename = findViewById(R.id.et_redrename);
        redrename = et_redrename.getText().toString().trim();
        et_redredosage = findViewById(R.id.et_redredosage);
        redredosage = et_redredosage.getText().toString().trim();
        et_redreform = findViewById(R.id.et_redreform);
        redreform = et_redreform.getText().toString().trim();
        et_redrefrequency = findViewById(R.id.et_redrefrequency);
        redrefrequency = et_redrefrequency.getText().toString().trim();


        if(TextUtils.isEmpty(greenrename)||TextUtils.isEmpty(greenredosage)||TextUtils.isEmpty(redrefrequency)){
            Toast.makeText(AddActActivity.this, "Please input Something", Toast.LENGTH_SHORT).show();
            return;

        }
        else{
        int actID = 0;
        List<ActDB> all = LitePal.findAll(ActDB.class);

        if (0 != all.size()) {
            ActDB last = LitePal.findLast(ActDB.class);
            actID = last.getActID();
        }

//        if (time != null && time.length() > 0) {
//            String[] times = time.split(":");
//            if (cycle == 0) {//是每天的闹钟
//                AlarmManagerUtil.setAlarm(this, 0, Integer.parseInt(times[0]), Integer.parseInt
//                        (times[1]), alarmID + 1, 0, "Medication time", ring);
//                AlarmDB alarmDB = new AlarmDB();
//                alarmDB.setAlarmID(alarmID + 1);
//                alarmDB.setHourNum(Integer.parseInt(times[0]));
//                alarmDB.setMinNum(Integer.parseInt(times[1]));
//                alarmDB.setSoundOrVibrator(ring);
//                alarmDB.setFlag(0);
//                alarmDB.setCycleNum(0);
//                alarmDB.setAlarmName(med);
//                alarmDB.setDosage(med2);
//                alarmDB.setForm(med3);
//                alarmDB.setEnabled(true);
//                alarmDB.save();
//            }
//            if (cycle == -1) {//是只响一次的闹钟
//                AlarmManagerUtil.setAlarm(this, 1, Integer.parseInt(times[0]), Integer.parseInt
//                        (times[1]), alarmID + 1, 0, "Medication time", ring);
//                AlarmDB alarmDB = new AlarmDB();
//                alarmDB.setAlarmID(alarmID + 1);
//                alarmDB.setHourNum(Integer.parseInt(times[0]));
//                alarmDB.setMinNum(Integer.parseInt(times[1]));
//                alarmDB.setSoundOrVibrator(ring);
//                alarmDB.setAlarmName(med);
//                alarmDB.setDosage(med2);
//                alarmDB.setForm(med3);
//                alarmDB.setFlag(1);
//                alarmDB.setCycleNum(-1);
//                alarmDB.setEnabled(true);
//                alarmDB.save();
//            }

            ActDB actDB = new ActDB();
            actDB.setActID(actID+1);
            actDB.setGreenrename(greenrename);
            actDB.setGreenredosage(greenredosage);
            actDB.setGreenreform(greenreform);
            actDB.setGreenrefrequency(greenrefrequency);
            actDB.setGreenconname(greenconname);
            actDB.setGreencondosage(greencondosage);
            actDB.setGreenconform(greenconform);
            actDB.setGreenconfrequency(greenconfrequency);
            actDB.setYellowrename(yellowrename);
            actDB.setYellowredosage(yellowredosage);
            actDB.setYellowreform(yellowreform);
            actDB.setYellowrefrequency(yellowrefrequency);
            actDB.setYellowconname(yellowconname);
            actDB.setYellowcondosage(yellowcondosage);
            actDB.setYellowconform(yellowconform);
            actDB.setYellowconfrequency(yellowconfrequency);
            actDB.setRedrename(redrename);
            actDB.setRedredosage(redredosage);
            actDB.setRedreform(redreform);
            actDB.setRedrefrequency(redrefrequency);
            actDB.save();

            Thread name = new Thread(NameOfRunnable);
            name.start();

            //int uid = preferences.getInt("Uid",0);

            //callapi.postredzone(uid,"da","dsd","dsad","dad");
            //callapi.postgreenzone(uid,greenconname,greencondosage,greenconform,greenconfrequency,greenrename,greenredosage,greenreform,greenrefrequency);
            //callapi.postyellowzone(uid,yellowconname,yellowcondosage,yellowconform,yellowconfrequency,yellowrename,yellowredosage,yellowreform,yellowrefrequency);
//                alarmDB.setAlarmID(alarmID + 1);
//                alarmDB.setHourNum(Integer.parseInt(times[0]));
//                alarmDB.setMinNum(Integer.parseInt(times[1]));
//                alarmDB.setSoundOrVibrator(ring);
//                alarmDB.setFlag(0);
//                alarmDB.setCycleNum(0);
//                alarmDB.setAlarmName(med);
//                alarmDB.setDosage(med2);
//                alarmDB.setForm(med3);
//                alarmDB.setEnabled(true);
//                alarmDB.save();

            Intent intent = new Intent();
            setResult(1, intent);
            finish();
            Toast.makeText(this, "Setting Successful", Toast.LENGTH_LONG).show();


    }}


//    public void selectRemindCycle() {
//        final SelectRemindCyclePopup fp = new SelectRemindCyclePopup(this);
//        fp.showPopup(allLayout);
//        fp.setOnSelectRemindCyclePopupListener(new SelectRemindCyclePopup
//                .SelectRemindCyclePopupOnClickListener() {
//
//            @Override
//            public void obtainMessage(int flag, String ret) {
//                switch (flag) {
//                    case 8:
//                        tv_repeat_value.setText("every day");
//                        cycle = 0;
//                        fp.dismiss();
//                        break;
//                    case 9:
//                        tv_repeat_value.setText("one time");
//                        cycle = -1;
//                        fp.dismiss();
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//    }


//    public void selectRingWay() {
//        SelectRemindWayPopup fp = new SelectRemindWayPopup(this);
//        fp.showPopup(allLayout);
//        fp.setOnSelectRemindWayPopupListener(new SelectRemindWayPopup
//                .SelectRemindWayPopupOnClickListener() {
//
//            @Override
//            public void obtainMessage(int flag) {
//                switch (flag) {
//                    // 震动
//                    case 0:
//                        tv_ring_value.setText("震动");
//                        ring = 0;
//                        break;
//                    // 铃声
//                    case 1:
//                        tv_ring_value.setText("Ring");
//                        ring = 1;
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });
//    }
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
                callapi.postredzone(uid,redrename,redredosage,redreform,redrefrequency,uid);
                callapi.postgreenzone(uid,greenconname,greencondosage,greenconform,greenconfrequency,greenrename,greenredosage,greenreform,greenrefrequency,uid);
                callapi.postyellowzone(uid,yellowconname,yellowcondosage,yellowconform,yellowconfrequency,yellowrename,yellowredosage,yellowreform,yellowrefrequency,uid);
//
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
