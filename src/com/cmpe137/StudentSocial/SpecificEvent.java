package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SpecificEvent extends Activity {
	
	private Event theSpecificEvent;
	private TextView tvSpecificEventName;
	private TextView tvSpecificEventTime;
	private TextView tvSpecificEventAddress;
	private TextView tvSpecificEventPrice;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.specificevent);
		
		tvSpecificEventName = (TextView) findViewById(R.id.tvSpecificEventName);
		tvSpecificEventTime = (TextView) findViewById(R.id.tvSpecificEventTimeInformation);
		tvSpecificEventAddress = (TextView) findViewById(R.id.tvSpecificEventAddressInformation);
		tvSpecificEventPrice = (TextView) findViewById(R.id.tvSpecificEventPriceInformation);
		
		theSpecificEvent = ShowEvents.theSpecificEvent;
		tvSpecificEventName.setText(theSpecificEvent.getEventName());
		tvSpecificEventTime.setText(theSpecificEvent.getEventTime());
		tvSpecificEventAddress.setText(theSpecificEvent.getEventAddress());
		tvSpecificEventPrice.setText(theSpecificEvent.getEventPrice());
		
	}

}
