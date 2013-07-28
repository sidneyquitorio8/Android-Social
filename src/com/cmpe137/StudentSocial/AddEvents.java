package com.cmpe137.StudentSocial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddEvents extends Activity {
	
	EditText etEventName;
	EditText etEventPrice;
	EditText etEventWhere;
	EditText etEventWhen;
	Button bAddEvent;
	TextView tvEventInfoHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addevents);
		bAddEvent = (Button) findViewById(R.id.bAddEvent);
		etEventName = (EditText) findViewById(R.id.etEventName);
		etEventWhere = (EditText) findViewById(R.id.etEventWhere);
		etEventPrice = (EditText) findViewById(R.id.etEventPrice);
		etEventWhen = (EditText) findViewById(R.id.etEventWhen);
		tvEventInfoHolder = (TextView) findViewById(R.id.tvEventInfoHolder);
		
		bAddEvent.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String eventName = etEventName.getText().toString();
				String eventAddress = etEventWhere.getText().toString();
				String eventPrice = etEventPrice.getText().toString();
				String eventWhen = etEventWhen.getText().toString();
				etEventName.setText("");
				etEventWhere.setText("");
				etEventPrice.setText("");
				etEventWhen.setText("");
				
				
				boolean worked = true;
				try{
				DatabaseHandler entry = new DatabaseHandler(AddEvents.this);
				entry.open();
				entry.addEventToDatabase(eventName, eventWhen, eventAddress, eventPrice);
				entry.close();
				}
				catch (Exception e) {
					worked = false;
				}
				finally {
					if(worked) {
						tvEventInfoHolder.setText("It worked");
					}
				}
				};
		});
	}

}
