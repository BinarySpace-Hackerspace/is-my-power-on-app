package com.protofactory.monitorapp;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class UpdateService extends Service {
	

	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
	    
		  IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		  Intent batteryStatus = this.registerReceiver(new PowerConnectionReceiver(), ifilter);
		  
		  
	    return Service.START_STICKY;
	  }

	  @Override
	  public IBinder onBind(Intent intent) {
	  //TODO for communication return IBinder implementation
	    return null;
	  }	

}
