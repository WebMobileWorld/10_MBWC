package com.mbwc.e.myapplication.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by E on 10/28/2016.
 */

public class AlarmServiceBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, AlarmService.class);
        context.startService(serviceIntent);
    }

}
