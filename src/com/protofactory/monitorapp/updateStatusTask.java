package com.protofactory.monitorapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.Log;

public class updateStatusTask extends AsyncTask<String, Integer, Integer >  {
	
	private Exception exception;
	
	protected Integer doInBackground(String...data)
	{
        BufferedReader in = null;
        

    	try
    	{
    	HttpClient httpclient = new DefaultHttpClient();

    	HttpGet request = new HttpGet();
    	URI website = new URI("http://www.binaryspace.co.za/powerstatus/updatestatus.php?" + data[0]);
    	request.setURI(website);
    	HttpResponse response = httpclient.execute(request);
    	in = new BufferedReader(new InputStreamReader(
           response.getEntity().getContent()));
    	}
    	catch ( Exception e )
    	{
    		Log.e("powermonitor", "Error in http connection "+e.toString());
    	}		
		
		return  0;
	}
	
	

}
