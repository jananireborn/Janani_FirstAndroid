package com.example.myfirstapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DisplayCoordinates extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		
// Class for locating the device's coordinates 		
		LocationManager locationManager;
		LocationListener locationListener;
		Location location; // location
		
// flag for GPS status
		boolean isGPSEnabled = false;
// flag for network status
		
		boolean isNetworkEnabled = false;
// Variables declaration
		
		double lat, Long;
		//mContext = context;
		String Latitude = null;
		String Longitude = null;

		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
// getting GPS status
		isGPSEnabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

// getting network status
		isNetworkEnabled = locationManager 	
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		if (isNetworkEnabled) {

			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			
			if (location != null) {
				lat = location.getLatitude();
				Latitude = Double.toString(lat);
				Long = location.getLongitude();
				Longitude = Double.toString(Long);

			}
		}		
		String provider;
		
// check whether the values are filled if not dont write the file		
		if (Latitude == null) {
		
		Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location1 = locationManager.getLastKnownLocation(provider);
	    if ( location1 != null ){
	    int lat1 = (int) (location1.getLatitude());
	    int lng = (int) (location1.getLongitude());
	    Latitude = Integer.toString(lat1) ;
	    Longitude = Integer.toString(lng) ;
	    
	    }
		}
		
		
	    // Get the message from the intent 
		Intent intent = getIntent();     

		
		byte[] data;
		String filename = "Location_finder.txt";

		File file = new File(Environment.getExternalStorageDirectory(),
				filename);

		try {
			FileOutputStream fos = new FileOutputStream(file);
			if ( Longitude != null ) {
			data = Latitude.getBytes();
			fos.write(data);
			
			
			data = Longitude.getBytes();
			fos.write(data);			
			
			fos.flush();
			fos.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String rec = new String("Details downloaded to location: "
				+ file.toString());	
		
				
	    
		// Create the text view     
	    TextView textView1 = new TextView(this);     
	    textView1.setTextSize(20);     
	    textView1.setText("Latitude:" +  Latitude + "\n" + "Longitude:" + Longitude + "\n" + rec);  
		// Set the text view as the activity layout  
	    setContentView(textView1); 		
		
			
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_coordinates, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
