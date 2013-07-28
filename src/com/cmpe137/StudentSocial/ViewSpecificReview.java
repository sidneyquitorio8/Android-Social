package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewSpecificReview extends Activity {
	
	private Place specificPlace;
	TextView tvSpecificReviewName;
	TextView tvSpecificReviewInformation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewspecificreview);
		specificPlace = SpecificPlace.theSpecificPlace;
		tvSpecificReviewName = (TextView) findViewById(R.id.tvSpecificReviewName);
		tvSpecificReviewInformation = (TextView) findViewById(R.id.tvSpecificReviewInformation);
		
		DatabaseHandler entry = new DatabaseHandler(ViewSpecificReview.this);
		entry.open();
		String result = entry.readSpecificPlaceReviewsFromDatabase(specificPlace.getPlaceName());
		entry.close();
		
		tvSpecificReviewName.setText("Reviews for " + specificPlace.getPlaceName() + ":");
		tvSpecificReviewInformation.setText(result);
		
	}

}
