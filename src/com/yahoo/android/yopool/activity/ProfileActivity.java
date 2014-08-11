package com.yahoo.android.yopool.activity;

import com.yahoo.android.yopool.R;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yahoo.android.yopool.time.DriverReadyAlarmReceiver;
import com.yahoo.android.yopool.time.TimeOfDay;

public class ProfileActivity extends Activity implements OnTimeSelectedListener {
    private Switch driverSwitch;
    private EditText carModelEditText;
    private EditText licenseNoEditText;

    private TextView to_start;
    private TextView to_end;

    private TextView from_start;
    private TextView from_end;

    private EditText home_addr;
    private EditText work_addr;

    private TextView name;
    private TextView phno;

    private ImageView pic_view;

    private static String typeOfTag = null;
    private DialogFragment timeFragment = new TimePickerFragment();

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        context = getApplicationContext();

        pic_view = (ImageView) findViewById(R.id.profilePicView);

        driverSwitch = (Switch) findViewById(R.id.driverSwitch);
        carModelEditText = (EditText) findViewById(R.id.carModelEditText);
        licenseNoEditText = (EditText) findViewById(R.id.licenseNoEditText);
        phno = (TextView) findViewById(R.id.phLabel);

        to_start = (TextView) findViewById(R.id.toStart);
        to_end = (TextView) findViewById(R.id.toEnd);
        from_start = (TextView) findViewById(R.id.fromStart);
        from_end = (TextView) findViewById(R.id.fromEnd);

        home_addr = (EditText) findViewById(R.id.homeEditText);
        work_addr = (EditText) findViewById(R.id.workEditText);
        name = (TextView) findViewById(R.id.nameTextView);
        home_addr = (EditText) findViewById(R.id.homeEditText);

        /*
         * if (driverSwitch.isChecked()) { carModelEditText.setClickable(true); licenseNoEditText.setClickable(true);
         * 
         * carModelEditText.setEnabled(true); licenseNoEditText.setEnabled(true); } else {
         * carModelEditText.setClickable(false); licenseNoEditText.setClickable(false);
         * 
         * carModelEditText.setEnabled(false); licenseNoEditText.setEnabled(false); }
         */


        name.setText(Profile.getName());
        phno.setText(Profile.getPhno());
        home_addr.setText(Profile.getHomeAddr());
        work_addr.setText(Profile.getWorkAddr());
        to_start.setText(Profile.getToStartStr());
        to_end.setText(Profile.getToEndStr());
        from_start.setText(Profile.getFromStartStr());
        from_end.setText(Profile.getFromEndStr());

        carModelEditText.setText(Profile.getCarModel());
        licenseNoEditText.setText(Profile.getLicenseNo());

        int id = context.getResources().getIdentifier(Profile.getPic(), "drawable", context.getPackageName());
        pic_view.setImageResource(id);

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
        if (id == R.id.action_notifications) {
            Intent intent = new Intent(getApplicationContext(), PassengerList.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggle_driver_pref(View view) {
        /*
         * if (driverSwitch.isChecked()) { carModelEditText.setClickable(true); licenseNoEditText.setClickable(true);
         * 
         * carModelEditText.setEnabled(true); licenseNoEditText.setEnabled(true); } else {
         * carModelEditText.setClickable(false); licenseNoEditText.setClickable(false);
         * 
         * carModelEditText.setEnabled(false); licenseNoEditText.setEnabled(false); }
         */
    }

    @Override
    public void onTimeSet(int hour, int minute) {
        
        // Convert to 12 hour format for user display
        String inputTime = hour + ":" + minute;
        String outputTime = "";
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("H:mm");
            Date dateObj = dateFormatter.parse(inputTime);
            outputTime = new SimpleDateFormat("K:mm a").format(dateObj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
      
        if (typeOfTag.equalsIgnoreCase("to_start")) {
            to_start.setText(outputTime);
        } else if (typeOfTag.equalsIgnoreCase("to_end")) {
            to_end.setText(outputTime);
        } else if (typeOfTag.equalsIgnoreCase("from_start")) {
            from_start.setText(outputTime);
        } else if (typeOfTag.equalsIgnoreCase("from_end")) {
            from_end.setText(outputTime);
        }

    }



    public void toStart(View v) {
        typeOfTag = "to_start";
        timeFragment.show(getFragmentManager(), "ToStartTimePicker");
    }

    public void toEnd(View v) {
        typeOfTag = "to_end";
        timeFragment.show(getFragmentManager(), "ToEndTimePicker");
    }

    public void fromStart(View v) {
        typeOfTag = "from_start";
        timeFragment.show(getFragmentManager(), "FromStartTimePicker");
    }

    public void fromEnd(View v) {
        typeOfTag = "from_end";
        timeFragment.show(getFragmentManager(), "FromEndTimePicker");
    }

    public void save_profile(View view) {

        Profile.setHomeAddr(home_addr.getText().toString());
        Profile.setWorkAddr(work_addr.getText().toString());
        Profile.setToStartStr(to_start.getText().toString());
        Profile.setToEndStr(to_end.getText().toString());
        Profile.setFromStartStr(from_start.getText().toString());
        Profile.setFromEndStr(from_end.getText().toString());
        Profile.setCarModel(carModelEditText.getText().toString());
        Profile.setLicenseNo(licenseNoEditText.getText().toString());

        boolean isDriverInput = driverSwitch.isChecked();
        Profile.setDriver(isDriverInput);

        Toast.makeText(context, "Profile saved!", Toast.LENGTH_SHORT).show();
        
        DriverReadyAlarmReceiver readyAlarm = new DriverReadyAlarmReceiver();
        readyAlarm.setAlarm(this, Profile.getHour(Profile.getToStartStr()), Profile.getMin(Profile.getToStartStr()), TimeOfDay.MORNING);
        readyAlarm.setAlarm(this, Profile.getHour(Profile.getFromStartStr()), Profile.getMin(Profile.getFromStartStr()), TimeOfDay.EVENING);

        boolean isDriver = Profile.isDriver();

        if (isDriver) {
            Intent intent = new Intent(context, DriverDashboard.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(context, MapActivity.class);
            startActivity(intent);
        }

    }
}
