package com.example.breathesafe;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.Calendar;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.jaeger.library.StatusBarUtil;
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

public class AddAlarmActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.date_tv)
    TextView date_tv;
    @BindView(R.id.tv_repeat)
    TextView tvRepeat;
    @BindView(R.id.arrow_one)
    TextView arrowOne;
    @BindView(R.id.tv_repeat_value)
    TextView tv_repeat_value;
    @BindView(R.id.repeat_rl)
    RelativeLayout repeat_rl;
    @BindView(R.id.ring_tv)
    TextView ringTv;
    @BindView(R.id.arrow_two)
    TextView arrowTwo;
    @BindView(R.id.tv_ring_value)
    TextView tv_ring_value;
    @BindView(R.id.ring_rl)
    RelativeLayout ring_rl;
    @BindView(R.id.set_btn)
    Button set_btn;
    @BindView(R.id.all_layout)
    LinearLayout allLayout;

    private NotificationManagerCompat notificationManager;
    private static final int INTERVAL = 1000 * 60 * 60 * 24;

    private TimePickerView pvTime;
    private EditText medname;
    private EditText meddosage;
    private EditText medform;
    private String med;
    private String med2;
    private String med3;
    private String time;
    private int cycle;
    private int ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);
        ButterKnife.bind(this);
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
        set_btn.setOnClickListener(this);
        repeat_rl.setOnClickListener(this);
        ring_rl.setOnClickListener(this);

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                time = getTime(date);
                date_tv.setText(getTime(date));
            }
        })
                .setType(new boolean[]{false, false, false, true, true, false})// 默认全部显示
                .setCancelText("Cancel")//取消按钮文字
                .setSubmitText("Save")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("choose time")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLACK)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setLabel("年", "月", "日", " ", " ", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();

        date_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pvTime.show();
            }
        });

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
                setResult(3, intent);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.repeat_rl:
                selectRemindCycle();
                break;
            case R.id.ring_rl:
                selectRingWay();
                break;
            case R.id.set_btn:
                setClock();
                break;
            default:
                break;
        }
    }

    private void setClock() {



        medname = findViewById(R.id.et_med_name);
        med = medname.getText().toString().trim();
        meddosage = findViewById(R.id.et_dosage);
        med2 = meddosage.getText().toString().trim();
        medform = findViewById(R.id.et_form);
        med3 = medform.getText().toString().trim();
        if(TextUtils.isEmpty(med)||TextUtils.isEmpty(med2)||TextUtils.isEmpty(med3)){
            Toast.makeText(AddAlarmActivity.this, "Please input Something", Toast.LENGTH_SHORT).show();
            return;

        }
        else{
        int alarmID = 0;
        List<AlarmDB> all = LitePal.findAll(AlarmDB.class);

        if (0 != all.size()) {
            AlarmDB last = LitePal.findLast(AlarmDB.class);
            alarmID = last.getAlarmID();
        }

        if (time != null && time.length() > 0) {
            String[] times = time.split(":");
            if (cycle == 0) {//是每天的闹钟
//                AlarmManagerUtil.setAlarm(this, 0, Integer.parseInt(times[0]), Integer.parseInt
//                        (times[1]), alarmID + 1, 0, "Medication time", ring);
                AlarmManagerUtil.setAlarm(this,0,Integer.parseInt(times[0]),Integer.parseInt
                        (times[1]), alarmID,0,med,ring);
                AlarmDB alarmDB = new AlarmDB();
                alarmDB.setAlarmID(alarmID + 1);
                alarmDB.setHourNum(Integer.parseInt(times[0]));
                alarmDB.setMinNum(Integer.parseInt(times[1]));
                alarmDB.setSoundOrVibrator(ring);
                alarmDB.setFlag(0);
                alarmDB.setCycleNum(0);
                alarmDB.setAlarmName(med);
                alarmDB.setDosage(med2);
                alarmDB.setForm(med3);
                alarmDB.setEnabled(true);
                alarmDB.save();
                //notificationManager = NotificationManagerCompat.from(getApplicationContext());
                createAlarm("It's time to have "+med,Integer.parseInt(times[0]),Integer.parseInt(times[1]),alarmID+1);

            }
            if (cycle == -1) {//是只响一次的闹钟
                AlarmManagerUtil.setAlarm(this, 1, Integer.parseInt(times[0]), Integer.parseInt
                        (times[1]), alarmID + 1, 0, "Medication time", ring);
                AlarmDB alarmDB = new AlarmDB();
                alarmDB.setAlarmID(alarmID + 1);
                alarmDB.setHourNum(Integer.parseInt(times[0]));
                alarmDB.setMinNum(Integer.parseInt(times[1]));
                alarmDB.setSoundOrVibrator(ring);
                alarmDB.setAlarmName(med);
                alarmDB.setDosage(med2);
                alarmDB.setForm(med3);
                alarmDB.setFlag(1);
                alarmDB.setCycleNum(-1);
                alarmDB.setEnabled(true);
                alarmDB.save();
                createAlarm1("It's time to have "+med,Integer.parseInt(times[0]),Integer.parseInt(times[1]),alarmID+1);
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
//                        .setSmallIcon(R.drawable.ic_home_black_24dp)
//                        .setContentTitle("My notification")
//                        .setContentText("Hello World!")
//                        .setWhen(System.currentTimeMillis());
//                NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//                manager.notify(alarmID+1, builder.build());
//                Bundle bundle = new Bundle();
//                Intent alarmintent = new Intent(this, Alarm.class);
//                bundle.putString("med",med);
//                alarmintent.putExtras(bundle);
//                int min_1 = Integer.parseInt(times[0]);
//                int sec_1 = Integer.parseInt(times[1]);
//                PendingIntent sender = PendingIntent.getBroadcast(this,
//                        0, alarmintent, PendingIntent.FLAG_CANCEL_CURRENT);
//                // Schedule the alarm!
//                AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY, min_1);
//                calendar.set(Calendar.MINUTE,sec_1);
//                calendar.set(Calendar.SECOND, 0);
//                calendar.set(Calendar.MILLISECOND, 0);
//                am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVAL, sender);
            }
//            Bundle bundle = new Bundle();
//            Intent alarmintent = new Intent(this, Alarm.class);
//            bundle.putString("med",med);
//            alarmintent.putExtras(bundle);
//            int min_1 = Integer.parseInt(times[0]);
//            int sec_1 = Integer.parseInt(times[1]);
//            PendingIntent sender = PendingIntent.getBroadcast(this,
//                    0, alarmintent, PendingIntent.FLAG_CANCEL_CURRENT);
//            // Schedule the alarm!
//            AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, min_1);
//            calendar.set(Calendar.MINUTE,sec_1);
//            calendar.set(Calendar.SECOND, 0);
//            calendar.set(Calendar.MILLISECOND, 0);
//            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVAL, sender);

            Intent intent = new Intent();
            setResult(1,intent );
            finish();
            Toast.makeText(this, "Setting Successful", Toast.LENGTH_LONG).show();
        }

    }}


    public void selectRemindCycle() {
        final SelectRemindCyclePopup fp = new SelectRemindCyclePopup(this);
        fp.showPopup(allLayout);
        fp.setOnSelectRemindCyclePopupListener(new SelectRemindCyclePopup
                .SelectRemindCyclePopupOnClickListener() {

            @Override
            public void obtainMessage(int flag, String ret) {
                switch (flag) {
                    case 8:
                        tv_repeat_value.setText("every day");
                        cycle = 0;
                        fp.dismiss();
                        break;
                    case 9:
                        tv_repeat_value.setText("one time");
                        cycle = -1;
                        fp.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
    }


    public void selectRingWay() {
        SelectRemindWayPopup fp = new SelectRemindWayPopup(this);
        fp.showPopup(allLayout);
        fp.setOnSelectRemindWayPopupListener(new SelectRemindWayPopup
                .SelectRemindWayPopupOnClickListener() {

            @Override
            public void obtainMessage(int flag) {
                switch (flag) {
                    // 震动
                    case 0:
                        tv_ring_value.setText("Vibration");
                        ring = 0;
                        break;
                    // 铃声
                    case 1:
                        tv_ring_value.setText("Ring");
                        ring = 1;
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void createAlarm(String message, int hour, int minutes, int resId) {
        ArrayList<Integer> testDays = new ArrayList<>();
        testDays.add(Calendar.MONDAY);//周一
        testDays.add(Calendar.TUESDAY);//周二
        testDays.add(Calendar.THURSDAY);
        testDays.add(Calendar.FRIDAY);//周五
        testDays.add(Calendar.WEDNESDAY);
        testDays.add(Calendar.SATURDAY);
        testDays.add(Calendar.SUNDAY);

        String packageName = getApplication().getPackageName();
        //Uri ringtoneUri = Uri.parse("android.resource://" + packageName + "/" + resId);
        //action为AlarmClock.ACTION_SET_ALARM
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                //闹钟的小时
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                //闹钟的分钟
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                //响铃时提示的信息
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                //用于指定该闹铃触发时是否振动
                .putExtra(AlarmClock.EXTRA_VIBRATE, true)
                //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
                //如需使用默认铃声，则无需指定此 extra。
                //.putExtra(AlarmClock.EXTRA_RINGTONE, ringtoneUri)
                //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
                // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
                //对于一次性闹铃，无需指定此 extra
                .putExtra(AlarmClock.EXTRA_DAYS, testDays)
                //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void createAlarm1(String message, int hour, int minutes, int resId) {
        ArrayList<Integer> testDays = new ArrayList<>();
        testDays.add(Calendar.MONDAY);//周一
        testDays.add(Calendar.TUESDAY);//周二
        testDays.add(Calendar.THURSDAY);
        testDays.add(Calendar.FRIDAY);//周五
        testDays.add(Calendar.WEDNESDAY);
        testDays.add(Calendar.SATURDAY);
        testDays.add(Calendar.SUNDAY);

        String packageName = getApplication().getPackageName();
        //Uri ringtoneUri = Uri.parse("android.resource://" + packageName + "/" + resId);
        //action为AlarmClock.ACTION_SET_ALARM
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                //闹钟的小时
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                //闹钟的分钟
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                //响铃时提示的信息
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                //用于指定该闹铃触发时是否振动
                .putExtra(AlarmClock.EXTRA_VIBRATE, true)
                //一个 content: URI，用于指定闹铃使用的铃声，也可指定 VALUE_RINGTONE_SILENT 以不使用铃声。
                //如需使用默认铃声，则无需指定此 extra。
                //.putExtra(AlarmClock.EXTRA_RINGTONE, ringtoneUri)
                //一个 ArrayList，其中包括应重复触发该闹铃的每个周日。
                // 每一天都必须使用 Calendar 类中的某个整型值（如 MONDAY）进行声明。
                //对于一次性闹铃，无需指定此 extra
                //.putExtra(AlarmClock.EXTRA_DAYS, testDays)
                //如果为true，则调用startActivity()不会进入手机的闹钟设置界面
                .putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
