package com.example.yopool;

import java.util.ArrayList;

import com.example.yopool.R.id;
import com.example.yopool.data.AvailableDrivers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DriverDetails extends Activity {
	private Context context;

	private TextView name;
	private TextView pickup;
	private TextView carModel;
	private TextView license;
	private ImageView pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.driver_details);

		context = getApplicationContext();

		name = (TextView) findViewById(R.id.nameTextView);
		pickup = (TextView) findViewById(R.id.value);
		carModel = (TextView) findViewById(R.id.modelNo);
		license = (TextView) findViewById(R.id.licenseNo);
		pic = (ImageView) findViewById(R.id.profilePicView);

		int selectedIndex = getIntent().getIntExtra("SelectedDriverIndex", 0);


		AvailableDrivers.loadData();
		ArrayList<String> names = AvailableDrivers.getNames();
		ArrayList<String> pickups = AvailableDrivers.getPickup_times();
		ArrayList<String> models = AvailableDrivers.getCar_models();
		ArrayList<String> licenses = AvailableDrivers.getLicense_nos();
		ArrayList<String> pics = AvailableDrivers.getPic_urls();

		name.setText(names.get(selectedIndex));
		pickup.setText(pickups.get(selectedIndex));
		carModel.setText(models.get(selectedIndex));
		license.setText(licenses.get(selectedIndex));
		
		int id = context.getResources().getIdentifier(pics.get(selectedIndex), "drawable", context.getPackageName());
		pic.setImageResource(id);
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
}
