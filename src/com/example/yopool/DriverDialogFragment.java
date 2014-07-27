package com.example.yopool;
 
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
public class DriverDialogFragment extends Activity {
 
	Context context;
	private Button button3, button2;

 
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		setContentView(R.layout.driver_dialog);
		
		button3 = (Button) findViewById(R.id.button3);
		 
		// add button listener
		button3.setOnClickListener(new OnClickListener() {
 
		  @Override
		  public void onClick(View arg0) {
			  Intent intent = new Intent(getApplicationContext(),DriverRoute.class);
			  startActivity(intent);
		  }
		});
		
		button2 = (Button) findViewById(R.id.button2);
		 
		// add button listener
		button2.setOnClickListener(new OnClickListener() {
 
		  @Override
		  public void onClick(View arg0) {
			  Toast.makeText(context, "We would see you soon", Toast.LENGTH_SHORT).show();
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
}