package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddSpecificReview extends Activity {
	
	TextView tvReviewPlaceName;
	EditText etReviewDetails;
	Button bAddReview;
	TextView tvAddReviewStatus;
	Place placeToReview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addreview);
		placeToReview = SpecificPlace.theSpecificPlace;
		
		tvReviewPlaceName = (TextView) findViewById(R.id.tvReviewPlaceName);
		etReviewDetails = (EditText) findViewById(R.id.etReviewDetails);
		bAddReview = (Button) findViewById(R.id.bAddReview);
		tvAddReviewStatus = (TextView) findViewById(R.id.tvAddReviewStatus);
		
		tvReviewPlaceName.setText(placeToReview.getPlaceName());
		
		bAddReview.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String reviewPlaceName = placeToReview.getPlaceName();
				String review = etReviewDetails.getText().toString();
				etReviewDetails.setText("");
				
				boolean worked = true;
				try{
				DatabaseHandler entry = new DatabaseHandler(AddSpecificReview.this);
				entry.open();
				entry.addReviewToDatabase(reviewPlaceName, review);
				entry.close();
				}
				catch (Exception e) {
					worked = false;
				}
				};
		});
	}

}
