package com.mbwc.e.myapplication.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mbwc.e.myapplication.Activitys.AlarmAlertActivity;
import com.mbwc.e.myapplication.Global.StaticWakeLock;
import com.mbwc.e.myapplication.Models.Alarm;


/**
 * Created by E on 10/27/2016.
 */

public class AlarmAlertBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mathAlarmServiceIntent = new Intent(
                context,
                AlarmServiceBroadcastReciever.class);
        context.sendBroadcast(mathAlarmServiceIntent, null);

        StaticWakeLock.lockOn(context);
        Bundle bundle = intent.getExtras();
        final Alarm alarm = (Alarm) bundle.getSerializable("alarm");

        Intent mathAlarmAlertActivityIntent;

        mathAlarmAlertActivityIntent = new Intent(context, AlarmAlertActivity.class);

        mathAlarmAlertActivityIntent.putExtra("alarm", alarm);

        mathAlarmAlertActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(mathAlarmAlertActivityIntent);
    }

}