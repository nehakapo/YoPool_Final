package com.example.yopool;
 


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
 
public class DriverDialogFragment extends Activity {
 
	Context context;
	private Button button3, button2;
	private DriverDialogFragment activity;

 
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		setContentView(R.layout.driver_dialog);
		
		activity = this;
		
		button3 = (Button) findViewById(R.id.confirmBtn);
		 
		// add button listener
		button3.setOnClickListener(new OnClickListener() {
 
		  @Override
		  public void onClick(View arg0) {
			  Toast.makeText(context, "You'll soon hear about your Yo!Rider", Toast.LENGTH_SHORT).show();
			  activity.finish();
		  }
		});
		
		button2 = (Button) findViewById(R.id.notTodayBtn);
		 
		// add button listener
		button2.setOnClickListener(new OnClickListener() {
 
		  @Override
		  public void onClick(View arg0) {
			  Toast.makeText(context, "We would see you soon", Toast.LENGTH_SHORT).show();
			  activity.finish();
		  }
		});
 
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
			Intent intent = new Intent(context, ProfileActivity .class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void setTime(View view) {
		
	}
}