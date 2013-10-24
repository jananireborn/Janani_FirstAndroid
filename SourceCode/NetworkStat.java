package com.example.myfirstapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Janani Swaminathan
 * @version 1.0
 * @since 10/25/2013
 **/

public class NetworkStat {

// Declare the static variables	
	public static int TYPE_WIFI = 1;
	public static int TYPE_MOBILE = 2;
	public static int TYPE_ETHER = 3;
	public static int TYPE_NOT_CONNECTED = 0;

	
	public static int getConnectivityStatus(Context context) {
		
// Find the Internet Connectivity the android device is using 		
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (null != activeNetwork) {
			if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
				return TYPE_WIFI;
			if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
				
				return TYPE_MOBILE;
			if (activeNetwork.getType() == ConnectivityManager.TYPE_ETHERNET)
				return TYPE_ETHER; 
		}
		return TYPE_NOT_CONNECTED;
		
	}
/**
 * 
 * @param context contains the context of the page 
 * @return will return String 
 */
	public static String getConnectivityStatusString(Context context) {
		int conn = NetworkStat.getConnectivityStatus(context);
		String status = null;
// Display the text accordingly		
		if (conn == NetworkStat.TYPE_WIFI) {
			status = "Internet Connectivity ->Wifi";
		} else if (conn == NetworkStat.TYPE_MOBILE) {
			status = "Internet Connectivity ->Mobile";
		}
			else if (conn  == NetworkStat.TYPE_ETHER) {
				status = "Internet Connectivity ->Ethernet";
			
		} else if (conn == NetworkStat.TYPE_NOT_CONNECTED) {
			status = "Not connected to Internet";
		}
		return status;
	}

}
