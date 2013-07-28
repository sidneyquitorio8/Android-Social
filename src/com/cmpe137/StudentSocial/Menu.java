package com.cmpe137.StudentSocial;

import java.io.Serializable;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity implements Serializable {
	
	private String[] classes = {"PostStatus", "SpecificProfile" ,"AllRecentActivity", "ShowPlaces", 
								"ShowEvents", "AddPlaces", "AddEvents", "Login" };
	
	private String[] classNames = {"Post Status", "Profile" , "All Statuses", "Show Places", 
			"Show Events", "Add Places", "Add Events","Logout" };
	
	public static String username;
	private Person currentPersonLoggedIn;
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classNames));
        currentPersonLoggedIn = (Person)getIntent().getSerializableExtra("name");
        username = currentPersonLoggedIn.getUsername();
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(position != 1) {
			try{
				Class openedClass = Class.forName("com.cmpe137.StudentSocial." + classes[position]);
				Intent openedIntent = new Intent(Menu.this, openedClass);
				startActivity(openedIntent);
				
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		//Create a person object then send it off into the intent
		else {
			Person currentUser = new Person(username);
			try{
				Class openedClass = Class.forName("com.cmpe137.StudentSocial." + classes[position]);
				Intent personIntent = new Intent(Menu.this, openedClass);
				Bundle b = new Bundle();
				b.putSerializable("name", currentUser);
				personIntent.putExtras(b);
				startActivity(personIntent);
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		
		}
    }
}