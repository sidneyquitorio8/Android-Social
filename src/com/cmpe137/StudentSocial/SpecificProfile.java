package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SpecificProfile extends Activity{
	
	private Person currentPerson;
	TextView tvSpecificProfileName;
	TextView tvSpecificReviewInformation;
	TextView tvSpecificProfileActivities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.specificprofile);
		currentPerson = (Person)getIntent().getSerializableExtra("name");
		String currentUserName = "";
		if(currentPerson != null) {
			currentUserName = currentPerson.getUsername();
		}
		tvSpecificProfileName = (TextView) findViewById(R.id.tvSpecificProfileName);
		tvSpecificProfileName.setText(currentUserName + "'s Profile");
		
		tvSpecificProfileActivities = (TextView) findViewById(R.id.tvSpecificProfileActivities);
		
		DatabaseHandler entry = new DatabaseHandler(SpecificProfile.this);
		entry.open();
		String userReviews = entry.readUserReviews(currentUserName);
		entry.close();
		

		entry.open();
		String userStatuses = entry.readUserStatuses(currentUserName);
		entry.close();
		
		entry.open();
		String userPlaces = entry.readUserPlaces(currentUserName);
		entry.close();
		
		entry.open();
		String userEvents = entry.readUserEvents(currentUserName);
		entry.close();
		
		tvSpecificProfileActivities.setText(userReviews + "\n" + userStatuses + "\n" +
									userPlaces + "\n" + userEvents);
		
	}

}
