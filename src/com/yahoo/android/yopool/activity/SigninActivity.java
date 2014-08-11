package com.yahoo.android.yopool.activity;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.yahoo.android.yopool.time.TimeOfDay;

import com.yahoo.android.yopool.time.DriverReadyAlarmReceiver;



import com.yahoo.android.yopool.R;
import com.yahoo.android.yopool.backend.DataPopulate;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SigninActivity extends Activity {
	private Context context;
	private EditText usernameEditText;
	private EditText passwordEditText;
	
	private static String ALARM_TAG = "Ready Alarm";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin_screen);
		
		context = getApplicationContext();
		
		usernameEditText = (EditText) findViewById(R.id.usernameEditText);
		passwordEditText = (EditText) findViewById(R.id.pswdEditText);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_profile) {
			Intent intent = new Intent(context, ProfileActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void validate_login(View view) throws ParseException {
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
		boolean isFirstLogin = sharedPref.getBoolean("FirstLogin", true);
		
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putBoolean("FirstLogin",false);
		editor.commit();
		
		DataPopulate dp = new DataPopulate(usernameEditText.getText().toString(), passwordEditText.getText().toString());
		dp.populateData();
		
		if (isFirstLogin) {
			DriverReadyAlarmReceiver readyAlarm = new DriverReadyAlarmReceiver();
			readyAlarm.setAlarm(this, Profile.getHour(Profile.getToStartStr()), Profile.getMin(Profile.getToStartStr()), TimeOfDay.MORNING);
			readyAlarm.setAlarm(this, Profile.getHour(Profile.getFromStartStr()), Profile.getMin(Profile.getFromStartStr()), TimeOfDay.EVENING);
			Log.i(ALARM_TAG, "Alarm is set");
			Intent intent = new Intent(context,ProfileActivity.class);
			startActivity(intent);
		} else {			
			boolean isDriver = Profile.isDriver();
			
			if (isDriver) {
				Intent intent = new Intent(context,DriverDashboard.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(context,MapActivity.class);
				startActivity(intent);
			}
		}
	}
}
