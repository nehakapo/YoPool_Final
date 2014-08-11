package com.yahoo.android.yopool.time;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import java.util.Calendar;

import com.yahoo.android.yopool.activity.DriverReadyAlarmDialog;

/**
 * When the alarm fires, this WakefulBroadcastReceiver receives the broadcast Intent 
 * and then starts the DriverReadyAlarmDialog activity.
 */
public class DriverReadyAlarmReceiver extends WakefulBroadcastReceiver {

    private AlarmManager alarmMgr;

    private PendingIntent toAlarmIntent;
    private PendingIntent fromAlarmIntent;
    
    private static String ALARM_TAG = "Ready Alarm";

    @Override
    public void onReceive(Context context, Intent intent) { 
        Log.i(ALARM_TAG, "Receiver works!");
        Intent dialogIntent = new Intent(context, DriverReadyAlarmDialog.class);
        dialogIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(dialogIntent);
    }

    /**
     * Sets a repeating alarm that runs once a day at the specified time. When the
     * alarm fires, the app broadcasts an Intent to this WakefulBroadcastReceiver.
     */
    public void setAlarm(Context context, int hour, int min, TimeOfDay type) {
        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DriverReadyAlarmReceiver.class);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);

        if (type == TimeOfDay.MORNING) {
            toAlarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,  
                            calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, toAlarmIntent);
        } else if (type == TimeOfDay.EVENING) {
            fromAlarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,  
                            calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, fromAlarmIntent);
        }

        // Enable {@code BootReceiver} to automatically restart the alarm when the
        // device is rebooted.
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);           
    }

    /**
     * Cancels all alarms.
     * @param context
     */
    public void cancelAllAlarms(Context context) {
        // If the alarm has been set, cancel it.
        if (alarmMgr!= null) {
            alarmMgr.cancel(toAlarmIntent);
            alarmMgr.cancel(fromAlarmIntent);
        }

        // Disable {@code BootReceiver} so that it doesn't automatically restart the 
        // alarm when the device is rebooted.
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
    }
}
