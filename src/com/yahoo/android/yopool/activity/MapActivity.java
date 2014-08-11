package com.yahoo.android.yopool.activity;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yahoo.android.yopool.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.google.android.gms.maps.SupportMapFragment;


public class MapActivity extends android.support.v4.app.FragmentActivity implements OnItemClickListener {
	
	GoogleMap googleMap;
    MarkerOptions markerOptions;
    LatLng passengerLatLng;
    //LatLng driverLatLng;
    //LatLng destinationLatLng;
    String address;
    
    private static final String LOG_TAG = "ExampleApp";

    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place";
    private static final String TYPE_AUTOCOMPLETE = "/autocomplete";
    private static final String OUT_JSON = "/json";

    private static final String API_KEY = "AIzaSyCK6VT1cpb1yKyXFatQA6Mw6gCsFL4fdl8";
    private EditText edit_text = null;
    
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
    
    public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable{
    	
    	private ArrayList<String> resultList;

        public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public String getItem(int index) {
            return resultList.get(index);
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if (constraint != null) {
                        // Retrieve the autocomplete results.
                        resultList = autocomplete(constraint.toString());

                        // Assign the data to the FilterResults
                        filterResults.values = resultList;
                        filterResults.count = resultList.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    if (results != null && results.count > 0) {
                        notifyDataSetChanged();
                    }
                    else {
                        notifyDataSetInvalidated();
                    }
                }};
            return filter;
        }

    }


    private ArrayList<String> autocomplete(String input) {
        ArrayList<String> resultList = null;

        HttpURLConnection conn = null;
        StringBuilder jsonResults = new StringBuilder();
        try {
            StringBuilder sb = new StringBuilder(PLACES_API_BASE + TYPE_AUTOCOMPLETE + OUT_JSON);
            sb.append("?key=" + API_KEY);
            sb.append("&components=country:us");
            sb.append("&input=" + URLEncoder.encode(input, "utf8"));

            URL url = new URL(sb.toString());
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Load the results into a StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                jsonResults.append(buff, 0, read);
            }
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error processing Places API URL", e);
            return resultList;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to Places API", e);
            return resultList;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        try {
            // Create a JSON object hierarchy from the results
            JSONObject jsonObj = new JSONObject(jsonResults.toString());
            JSONArray predsJsonArray = jsonObj.getJSONArray("predictions");

            // Extract the Place descriptions from the results
            resultList = new ArrayList<String>(predsJsonArray.length());
            for (int i = 0; i < predsJsonArray.length(); i++) {
                resultList.add(predsJsonArray.getJSONObject(i).getString("description"));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Cannot process JSON results", e);
        }

        return resultList;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        AutoCompleteTextView autoCompView = (AutoCompleteTextView) findViewById(R.id.pickUpLocationText);
        autoCompView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.auto_list_item));
        autoCompView.setOnItemClickListener(this);
        edit_text = autoCompView;
        
        edit_text.setText(Profile.getHomeAddr());
        
        address = edit_text.getText().toString();
        setUpMapIfNeeded();
        GetPickupLocation();
        
        edit_text.setImeOptions(EditorInfo.IME_ACTION_DONE);
        
        edit_text.setOnEditorActionListener(new OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                String input;
                if(actionId == EditorInfo.IME_ACTION_DONE)
                {
                	//private void hideKeyboard(EditText editText)
                	//{
                	    InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                	    imm.hideSoftInputFromWindow(edit_text.getWindowToken(), 0);
                	//}
                    address= v.getText().toString();
                    setUpMapIfNeeded();
                    GetPickupLocation();
                    return true;
                }
                else
                	return false;
            }
        });
        /*edit_text.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                String input;
                EditText editText;

                if(!hasFocus)
                {
                    editText= (EditText) v;
                    address= editText.getText().toString();
                }
            }
        });*/
        
        //setUpMapIfNeeded();

        //GetPickupLocation();
        //GetCurrentLocation();
    }
    
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
       
        InputMethodManager imm= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    imm.hideSoftInputFromWindow(edit_text.getWindowToken(), 0);
	   
	    address = (String) adapterView.getItemAtPosition(position);
        setUpMapIfNeeded();
        GetPickupLocation();
        
        
    }

    
    private void GetPickupLocation()
    {
    	Geocoder coder = new Geocoder(this);
        try {
            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(address, 50);
            for(Address add : adresses){
                //if (statement) {//Controls to ensure it is right address such as country etc.
                    passengerLatLng = new LatLng(add.getLatitude(),add.getLongitude());
                    showLocation();
                    break;
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    private void setUpMapIfNeeded() {
        if (googleMap == null) {

            Log.e("", "Into null map");
            googleMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();


            
            //googleMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(
            //        MapActivity.this));

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
                        .position(passengerLatLng)
                        .title("Current Location")
                        .icon(BitmapDescriptorFactory
                               .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                               //.fromResource(R.drawable.ic_launcher)));

        //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        
         // Move the camera instantly to newLatLng with a zoom of 15.
         //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));

         // Zoom in, animating the camera.
         googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(passengerLatLng, 15), 1000, null);
         /*        googleMap
                    .animateCamera(CameraUpdateFactory.newLatLngZoom(
                            currentLatLng, 5));
       */
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
    
    public void request_ride(View view){
    	Intent intent = new Intent(getApplicationContext(),DriverList.class);
		startActivity(intent);
    }
}