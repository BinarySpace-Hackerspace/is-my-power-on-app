package com.protofactory.monitorapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {
    
	
	
	@Override
    public void onReceive(Context context, Intent intent) { 
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                            status == BatteryManager.BATTERY_STATUS_FULL;
    
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        
		String imei = "";
		TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		imei = tm.getDeviceId();
        
        if (isCharging != Globals.powerStatus)
        {
        	Globals.powerStatus = isCharging;
        	
	        if (isCharging)
	        {
	        	CharSequence text = "Power Restored!";
	        	int duration = Toast.LENGTH_SHORT;
	        	Toast toast = Toast.makeText(context, text, duration);
	        	toast.show();
	        	
	        	//SmsManager smsManager = SmsManager.getDefault();
	        	//smsManager.sendTextMessage(Globals.cellNr, null, "Power Restored!", null, null);
	        	
	        	new updateStatusTask().execute("status=1&imei=" + imei);
	        	
	        }
	        else
	        {
	        	CharSequence text = "Power Fail!";
	        	int duration = Toast.LENGTH_SHORT;
	        	Toast toast = Toast.makeText(context, text, duration);
	        	toast.show();
	        	
	        	//SmsManager smsManager = SmsManager.getDefault();
	        	//smsManager.sendTextMessage(Globals.cellNr, null, "Power Failed!", null, null);
	        	
	        	new updateStatusTask().execute("status=0&imei=" + imei);
	        	

	        	
	        }
        }
    }
}	
