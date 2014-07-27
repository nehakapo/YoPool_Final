package com.example.yopool;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.SupportMapFragment;


public class DriverRoute extends android.support.v4.app.FragmentActivity {
	
	GoogleMap googleMap;
    MarkerOptions markerOptions;
    LatLng passengerLatLng;
    LatLng driverLatLng;
    LatLng destinationLatLng;
    String driver_address="";
    String passenger_address= "";
    String destination_address="";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_route);
        
        driver_address="718 Old San Francisco Road, Sunnyvale, California";
        passenger_address= "East El Camino Real, Sunnyvale, California";
        destination_address="701 first Avenue, Sunnyvale, California";
        setUpMapIfNeeded();
        GetPickupLocation();
        //GetCurrentLocation();
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
			Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
    
    
    private void GetPickupLocation()
    {
    	Geocoder coder = new Geocoder(this);
        try {
            ArrayList<Address> p_adresses = (ArrayList<Address>) coder.getFromLocationName(passenger_address, 1);
            ArrayList<Address> d_adresses = (ArrayList<Address>) coder.getFromLocationName(driver_address, 1);
            ArrayList<Address> dest_adresses = (ArrayList<Address>) coder.getFromLocationName(destination_address, 1);
            
                //if (statement) {//Controls to ensure it is right address such as country etc.
                    passengerLatLng = new LatLng(p_adresses.get(0).getLatitude(),p_adresses.get(0).getLongitude());
                    driverLatLng = new LatLng(d_adresses.get(0).getLatitude(),p_adresses.get(0).getLongitude());
                    destinationLatLng = new LatLng(dest_adresses.get(0).getLatitude(),p_adresses.get(0).getLongitude());
                    showLocation();
                //}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void setUpMapIfNeeded() {
        if (googleMap == null) {

            Log.e("", "Into null map");
            googleMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

            if (googleMap != null) {
                Log.e("", "Into full map");
                googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);
                googleMap.getUiSettings().setZoomControlsEnabled(false);
            }
        }
    }

    private void GetCurrentLocation() {

        double[] d = getlocation();
        double lat = d[0];
        double lng = d[1];
        passengerLatLng = new LatLng(lat, lng);
        showLocation();
    }
    
    private void showLocation(){
    	
    	googleMap.clear();
    	
        googleMap
                .addMarker(new MarkerOptions()
                        .position(destinationLatLng)
                        .title("Current Location")
                        .icon(BitmapDescriptorFactory
                               .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                               //.fromResource(R.drawable.ic_launcher)));

        googleMap
        .addMarker(new MarkerOptions()
                .position(passengerLatLng)
                .title("Current Location")
                .icon(BitmapDescriptorFactory
                       .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        googleMap
        .addMarker(new MarkerOptions()
                .position(driverLatLng)
                .title("Current Location")
                .icon(BitmapDescriptorFactory
                       .defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        

        //zoom to the centroid of all three locations
         // Zoom in, animating the camera.
         googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(passengerLatLng, 15), 1000, null);
    }

    public double[] getlocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        Location l = null;
        for (int i = 0; i < providers.size(); i++) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null)
                break;
        }
        double[] gps = new double[2];

        if (l != null) {
            gps[0] = l.getLatitude();
            gps[1] = l.getLongitude();
        }
        return gps;
    }
}