package com.yahoo.android.yopool.time;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.yahoo.android.yopool.activity.Profile;

/**
 * This BroadcastReceiver automatically (re)starts the alarm when the device is
 * rebooted. This receiver is set to be disabled (android:enabled="false") in the
 * application's manifest file. When the user sets the alarm, the receiver is enabled.
 * When the user cancels the alarm, the receiver is disabled, so that rebooting the
 * device will not trigger this receiver.
 */
public class BootReceiver extends BroadcastReceiver {
    DriverReadyAlarmReceiver readyAlarm = new DriverReadyAlarmReceiver();
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        {
            readyAlarm.setAlarm(context, Profile.getHour(Profile.getToStartStr()), Profile.getMin(Profile.getToStartStr()), TimeOfDay.MORNING);
            readyAlarm.setAlarm(context, Profile.getHour(Profile.getFromStartStr()), Profile.getMin(Profile.getFromStartStr()), TimeOfDay.EVENING);
        }
    }
}

