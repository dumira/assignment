package com.example.testapp;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.testapp.Addhotel.HotelHttpPost;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Searchhotel extends Activity{
	
	
	private EditText CitySearch;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		//create the back button to the main page
		Button back = (Button) findViewById(R.id.back2);
		back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent =new Intent(getApplicationContext(),MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

			}
		});
		
		//search button
		CitySearch = (EditText)findViewById(R.id.searchcity);
		
		
		Button search = (Button) findViewById(R.id.searchbtn);
		search.setOnClickListener(new OnClickListener() {
			
			//function inside onclick of search button
			public void onClick(View arg0) {
				
				//get the serch input city value
				String city = CitySearch.getText().toString().trim();
				
				//convert in to json array
				String json = "";
				JSONObject jsonObject = new JSONObject();
	            try {
					jsonObject.accumulate("city", city);

		            
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	 
	            //convert JSONObject to JSON to String
	            json = jsonObject.toString();
				
	            //run asyncTask to post the data
	            new HotelSearhHttpPost().execute(new String[]{json});
				
			}});
		
		
	}
	
	
	public class HotelSearhHttpPost extends AsyncTask<String, String, String> 
    {    	
    	
    	@Override
    	protected String doInBackground(String... params) 	
    	{
    		 ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
    			
    		 //post the parameters
    		 postParameters.add(new BasicNameValuePair("json",params[0]));
			 
			 String response = null;
			 
			     try {
			    	 //get the response
					response = CustomHttpClient.executeHttpPost(
					   "http://192.168.1.2/json/search.php",postParameters);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			     
			     //convert resut in to string
			    String result = response.toString();
    		
    		
    		return result; }
    	
    
    	 protected void onPostExecute(String reSult){
    		 
    		 //setting the output string
    		 String returnString1 = null;
    		 
    		 //set output text view
    		 TextView output;
    		 
    		 output = (TextView) findViewById(R.id.setname);
    		   		 
    		 try{
                 returnString1 = "";
                 
                 //Get json data and convert in to raw data

           JSONArray jArray = new JSONArray(reSult);
                 for(int i=0;i<jArray.length();i++){
                         JSONObject json_data = jArray.getJSONObject(i);
                         Log.i("log_tag",
                        		 "name: "+json_data.getString("name")+
                                 ", address: "+json_data.getString("address")+
                                 ", city: "+json_data.getString("city")+
                                 ", latitude: "+json_data.getString("latitude")+
                                 ", longitude: "+json_data.getString("longitude")
                         );
                         returnString1 +="Name: "+json_data.getString("name")+"\n"+
                        		 		"Address: "+json_data.getString("address")+"\n"+
                                		 "City: "+json_data.getString("city")+"\n"+
                                 		 "Location: "+json_data.getString("latitude")+" / "+json_data.getString("longitude")+"\n\n"
                        		 		;                       
                 }
                 
                 
         }
         catch(JSONException e){
                 Log.e("log_tag", "Error parsing data "+e.toString());
         }
     
         try{
        	 
        	 //display the output
          output.setText(returnString1);

          
         }
         catch(Exception e){
          Log.e("log_tag","Error in Display!" + e.toString());;          
         }   
    	 }
    		 
    		 
    	 }
    	
    }


