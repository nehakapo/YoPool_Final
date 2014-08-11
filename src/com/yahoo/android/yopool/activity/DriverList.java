package com.yahoo.android.yopool.activity;

import java.util.ArrayList;

import com.yahoo.android.yopool.R;
import com.yahoo.android.yopool.backend.AvailableDrivers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DriverList extends Activity {
	private ListView list;
	private CustomAdapter adapter;
	private Context context;

	private ArrayList<String> d_names;
	private ArrayList<String> d_pickup_times;
	private ArrayList<String> d_pic_urls;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);

		context = getApplicationContext();

		AvailableDrivers.loadData();
		d_names = AvailableDrivers.getNames();
		d_pickup_times = AvailableDrivers.getPickup_times();
		d_pic_urls = AvailableDrivers.getPic_urls();

		list = (ListView) findViewById(R.id.driverList);
		adapter = new CustomAdapter(context, d_names,d_pickup_times,d_pic_urls);

		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						DriverList.this);

				// set title
				alertDialogBuilder.setTitle("Confirm ride!");

				// set dialog message
				alertDialogBuilder
				.setMessage("Do you want to confirm this ride?")
				.setCancelable(false)
				.setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						Intent intent = new Intent(context,DriverDetails.class);
						intent.putExtra("SelectedDriverIndex", position);
						startActivity(intent);
					}
				})
				.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
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
			Intent intent = new Intent(context, ProfileActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
