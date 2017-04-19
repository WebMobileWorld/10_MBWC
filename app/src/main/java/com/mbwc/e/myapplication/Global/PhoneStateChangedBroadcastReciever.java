package com.mbwc.e.myapplication.Global;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by E on 10/28/2016.
 */

public class PhoneStateChangedBroadcastReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getSimpleName(), "onReceive()");
    }
}
