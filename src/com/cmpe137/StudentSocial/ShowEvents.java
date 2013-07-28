package com.cmpe137.StudentSocial;

import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowEvents extends ListActivity {

	public static Event theSpecificEvent;
	private List<Event> listOfEvents;
	private String[] eventNames;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		DatabaseHandler entry = new DatabaseHandler(ShowEvents.this);
		entry.open();
		listOfEvents = entry.readEventsFromDatabase();
		entry.close();
		
		eventNames = new String[listOfEvents.size()];
		for(int i = 0; i < listOfEvents.size(); i++) {
			Event currentEventToAddToArrayAdapter = listOfEvents.get(i);
			eventNames[i] = currentEventToAddToArrayAdapter.getUserName() + " added event: " + currentEventToAddToArrayAdapter.getEventName();
		}
        setListAdapter(new ArrayAdapter<String>(ShowEvents.this, android.R.layout.simple_list_item_1, eventNames));
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		theSpecificEvent = listOfEvents.get(position);
		try{
			Class openedClass = Class.forName("com.cmpe137.StudentSocial.SpecificEvent");
			Intent openedIntent = new Intent(ShowEvents.this, openedClass);
			startActivity(openedIntent);
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
