package com.example.breathesafe.Utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.breathesafe.ClockAlarmActivity;
import com.example.breathesafe.MainActivity;
import com.example.breathesafe.R;

/**
 * Created by loongggdroid on 2016/3/21.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String msg = intent.getStringExtra("msg");
        long intervalMillis = intent.getLongExtra("intervalMillis", 0);
        if (intervalMillis != 0) {
            AlarmManagerUtil.setAlarmTime(context, System.currentTimeMillis() + intervalMillis,
                    intent);
        }
        int flag = intent.getIntExtra("soundOrVibrator", 0);
        Intent clockIntent = new Intent(context, ClockAlarmActivity.class);
        clockIntent.putExtra("msg", msg);
        clockIntent.putExtra("flag", flag);
        clockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(clockIntent);
        Intent intent2 = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent2, 0);
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_home_black_24dp)
                .setTicker("Time to have drugs！")
                .setContentTitle("")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(""))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        manager.notify(1, notify);

    }


}
