package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPlaces extends Activity {
	
	EditText etPlacePrice;
	EditText etPlaceWhere;
	EditText etPlaceName;
	Button bAddPlace;
	TextView tvPlaceInfoHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addplaces);
		bAddPlace = (Button) findViewById(R.id.bAddPlace);
		etPlaceWhere = (EditText) findViewById(R.id.etPlaceWhere);
		etPlacePrice = (EditText) findViewById(R.id.etPlacePrice);
		etPlaceName = (EditText) findViewById(R.id.etPlaceName);
		tvPlaceInfoHolder = (TextView) findViewById(R.id.tvPlaceInfoHolder);
		
		bAddPlace.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String placeName = etPlaceName.getText().toString();
				String placeAddress = etPlaceWhere.getText().toString();
				String placePrice = etPlacePrice.getText().toString();
				etPlaceName.setText("");
				etPlaceWhere.setText("");
				etPlacePrice.setText("");
				
				
				boolean worked = true;
				try{
				DatabaseHandler entry = new DatabaseHandler(AddPlaces.this);
				entry.open();
				entry.addPlaceToDatabase(placeName, placeAddress, placePrice);
				entry.close();
				}
				catch (Exception e) {
					worked = false;
				}
				finally {
					if(worked) {
						tvPlaceInfoHolder.setText("It worked");
					}
				}
				};
		});
	}

}
