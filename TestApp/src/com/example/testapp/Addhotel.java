package com.example.testapp;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Addhotel<GeoPoint> extends Activity{
	
	GPSTracker gps;
	
	private EditText BankName,BankAddress,BankCity;
	
	
	GeoPoint geoPoint;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
		//add edit text areas
		BankName = (EditText)findViewById(R.id.name);
		BankAddress = (EditText)findViewById(R.id.address);
		BankCity = (EditText)findViewById(R.id.city);
		
		
		//button to submit data of hotel
		Button submit = (Button) findViewById(R.id.addbtn);
		submit.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				
				
            	gps = new GPSTracker(Addhotel.this);
	    		 if(!gps.canGetLocation()){
	    			 
	                    // can't get location
	                    // GPS or Network is not enabled
	                    // Ask user to enable GPS/network in settings
	                    gps.showSettingsAlert();
                    
	                       
	                }
				
	    		 //Get the location
	    		 double latitude = gps.getLatitude();
                 double longitude = gps.getLongitude();
                 
  
				
				String lat = ""+latitude;
				String lon= ""+longitude;
				
				Toast.makeText(getApplicationContext(), lat+" / "+lon, Toast.LENGTH_LONG).show();
				
				String nam = BankName.getText().toString().trim();
				String adrs = BankAddress.getText().toString().trim();
				String cty = BankCity.getText().toString().trim();
				

				//accumulate data in to a json array to send
				String json = "";
				JSONObject jsonObject = new JSONObject();
	            try {
					jsonObject.accumulate("name", nam);
					jsonObject.accumulate("address", adrs);
		            jsonObject.accumulate("city", cty);
		            jsonObject.accumulate("lat", lat);
		            jsonObject.accumulate("lon", lon);
		            
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	 
	            //convert JSONObject to JSON to String
	            json = jsonObject.toString();
				
				
	            //run the  asyncTask to post the data
				new HotelHttpPost().execute(new String[]{json});
				
				
			}});
		
		
		//button to come back to the main page
		Button back = (Button) findViewById(R.id.back1);
		back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent =new Intent(getApplicationContext(),MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
				
			}
		});
		

		
	}
	
	//asyncTask which post data
	 public class HotelHttpPost extends AsyncTask<String, String, String> 
	    {    	
	    	
	    	@Override
	    	protected String doInBackground(String... params) 	
	    	{
	    		 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
	    		
	    		 //add values to http post
	    		 postParameters.add(new BasicNameValuePair("json",params[0]));
 			 
 			 String response = null;
 			 
 			     try {	//get the response after sending data to specific location
						response = CustomHttpClient.executeHttpPost(
						   "http://192.168.1.2/json/add.php",postParameters);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 			     //convert response in to string
 			    String result = response.toString();
	    		
	    		
	    		return result; }
	    	
	    
	    	 protected void onPostExecute(String reSult){
	    		 
	    		 //tost the result
	    		 Toast t=Toast.makeText(getApplicationContext(), reSult.trim(), Toast.LENGTH_LONG);
                 t.show();
	    	 }
	    	
	    }
	 

}
