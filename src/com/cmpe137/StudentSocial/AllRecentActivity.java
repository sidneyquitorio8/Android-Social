package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AllRecentActivity extends Activity {

	TextView activities;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allrecentactivity);
		activities = (TextView) findViewById(R.id.tvAllRecentActivity);
		DatabaseHandler entry = new DatabaseHandler(AllRecentActivity.this);
		
		entry.open();
		String result = entry.readStatusFromDatabase();
		entry.close();
		
		activities.setText(result);
	}

	
}
