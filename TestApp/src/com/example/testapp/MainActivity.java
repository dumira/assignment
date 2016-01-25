package com.example.testapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//button to go to add hotel menu page
		Button addhotel = (Button) findViewById(R.id.addhotel);
		addhotel.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent =new Intent(getApplicationContext(),Addhotel.class);
				startActivity(intent);
			}
		});
		
		//button to go to Search hotel menu page
		Button search = (Button) findViewById(R.id.searchhotel);
		search.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent =new Intent(getApplicationContext(),Searchhotel.class);
				startActivity(intent);
			}
		});
		
		
		//Button to Exit from application
		Button btnexit =(Button)findViewById(R.id.exit);
        
		btnexit.setOnClickListener(new OnClickListener() {
		                    		public void onClick(View arg0) {
		                    			finish();
		                                System.exit(0);
		                    		}
		                    	});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
