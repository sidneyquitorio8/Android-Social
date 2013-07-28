package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SpecificPlace extends Activity {
	
	public static Place theSpecificPlace;
	private TextView tvSpecificPlaceName;
	private TextView tvSpecificPlaceAddress;
	private TextView tvSpecificPlacePrice;
	private Button bAddPlaceReviews;
	private Button bViewPlaceReviews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.specificplace);
		theSpecificPlace = ShowPlaces.specificPlace;
		tvSpecificPlaceName = (TextView) findViewById(R.id.tvSpecificPlaceName);
		tvSpecificPlaceAddress = (TextView) findViewById(R.id.tvSpecificPlaceAddressInformation);
		tvSpecificPlacePrice = (TextView) findViewById(R.id.tvSpecificPlacePriceInformation);
		bAddPlaceReviews = (Button) findViewById(R.id.bAddPlaceReviews);
		bViewPlaceReviews = (Button) findViewById(R.id.bViewPlaceReviews);
		
		tvSpecificPlaceName.setText(theSpecificPlace.getPlaceName());
		tvSpecificPlaceAddress.setText(theSpecificPlace.getPlaceAddress());
		tvSpecificPlacePrice.setText(theSpecificPlace.getPlacePrice());
		
		bAddPlaceReviews.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Class openedClass = Class.forName("com.cmpe137.StudentSocial.AddSpecificReview");
					Intent openedIntent = new Intent(SpecificPlace.this, openedClass);
					startActivity(openedIntent);
					
				}
				catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		bViewPlaceReviews.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					Class openedClass = Class.forName("com.cmpe137.StudentSocial.ViewSpecificReview");
					Intent openedIntent = new Intent(SpecificPlace.this, openedClass);
					startActivity(openedIntent);
					
				}
				catch(ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			}
		});
	}

}
