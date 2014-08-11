package com.yahoo.android.yopool.activity;

import com.yahoo.android.yopool.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SplashScreen extends ActionBarActivity {
	private static int ANIM_DURATION = 2000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		ImageView car_icon = (ImageView) findViewById(R.id.car_icon);
		
		Animation translateAnim = new TranslateAnimation(500,0,0,0);
		translateAnim.setDuration(ANIM_DURATION);
		translateAnim.setFillAfter(true);
		car_icon.startAnimation(translateAnim);
		
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void signin(View view) {
		Intent intent = new Intent(getApplicationContext(),SigninActivity.class);
		startActivity(intent);
	}
}
