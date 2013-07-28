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

public class ShowPlaces extends ListActivity {
	
	public static Place specificPlace;
	private List<Place> listOfPlaces;
	private String[] placeNames;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		DatabaseHandler entry = new DatabaseHandler(ShowPlaces.this);
		
		//Gets list of places in database
		entry.open();
		listOfPlaces = entry.readPlacesFromDatabase();
		entry.close();
		
		//Creates an array to display the list items
		placeNames = new String[listOfPlaces.size()];
		for(int i = 0; i < listOfPlaces.size(); i++) {
			Place currentPlaceToAddToArrayAdapter = listOfPlaces.get(i);
			placeNames[i] = currentPlaceToAddToArrayAdapter.getUserName() + " added place: " + currentPlaceToAddToArrayAdapter.getPlaceName();
		}
        setListAdapter(new ArrayAdapter<String>(ShowPlaces.this, android.R.layout.simple_list_item_1, placeNames));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		specificPlace = listOfPlaces.get(position);
		try{
			Class openedClass = Class.forName("com.cmpe137.StudentSocial.SpecificPlace");
			Intent openedIntent = new Intent(ShowPlaces.this, openedClass);
			startActivity(openedIntent);
			
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
