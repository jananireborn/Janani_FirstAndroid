package com.example.myfirstapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;

/**
 * @author Janani Swaminathan
 * @version 1.0
 * @since 10/25/2013
 **/

public class MainActivity extends Activity {

	// Public variable declaration
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	public final static String MESSAGE1 = "com.example.myfirstapp.MESSAGE1";
	public final static String MESSAGE2 = "com.example.myfirstapp.MESSAGE2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * @Param View contains the view details This Method will be invoked when the
	 *        Network Connectivity button is clicked
	 **/
	public void networkConnection(View view) {

		byte[] data;

		// Call the new Android application page
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		Context context = view.getContext();

		String message = NetworkStat.getConnectivityStatusString(context);
		// Mention the file name
		String filename = "Internet_connectivity.txt";

		// Generate the file path, here is it referred to the External storage
		File file = new File(Environment.getExternalStorageDirectory(),
				filename);

		try {

			FileOutputStream fos = new FileOutputStream(file);
			// Push the data to the file.
			if (message != null) {

				data = message.getBytes();
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

// String contains the path in which the file was written 		
		String rec = new String("Details downloaded to location: "
				+ file.toString());

// For passing the value to the next application page 		
		intent.putExtra(EXTRA_MESSAGE, message + "\n" + rec);
// Invoke the new Page 		
		startActivity(intent);

	}

/**    @Param View contains the view details 
  *  This Method will be invoked when the Application list button is clicked
**/
	public void appList(View view) throws IOException {

// Byte Data stream for writing the file content		
		byte[] data;

		String rec = null;
// Packmanager class is called to get the application list 
		
		PackageManager pm = getPackageManager();
		List<ApplicationInfo> apps = pm.getInstalledApplications(0);

// File name		
		String filename = "Application_List.txt";
		File file = new File(Environment.getExternalStorageDirectory(),
				filename);

// open the file outout stream 		
		FileOutputStream fos = new FileOutputStream(file);

		for (ApplicationInfo Ai : apps) {

			rec = "App Name: " + (String) pm.getApplicationLabel(Ai) + "\r\n";
// Loop through the data and write in the file 
			data = rec.getBytes();
			fos.write(data);

		}
		fos.flush();
		fos.close();
// Call the Android application page 
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		String message = new String("App Info. Downloaded to: "
				+ file.toString());
// Pass the data		
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);

	}


/**    @Param View contains the view details 
  *  This Method will be invoked when the Device Location button is clicked
**/
	
	public void location(View view) {

// Just invoke the new Android page 		
		Intent intent = new Intent(this, DisplayCoordinates.class);
		Context context = view.getContext();

		startActivity(intent);

	}

}
