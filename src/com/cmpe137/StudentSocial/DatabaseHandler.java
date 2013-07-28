package com.cmpe137.StudentSocial;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler {
	
	private static final String DATABASE_NAME = "DatabaseHandlerDb";
	private static final int DATABASE_VERSION = 6;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	//Statuses Table Variables
	private static final String STATUSES_TABLE = "statuses";
	public static final String STATUSES_USERNAME = "s_username";
	public static final String STATUSES_STATUS = "s_status";
	
	//Places Table Variables
	private static final String PLACES_TABLE = "places";
	public static final String PLACES_USERNAME = "p_username";
	public static final String PLACES_PLACENAME = "p_placename";
	public static final String PLACES_PRICE = "p_price";
	public static final String PLACES_ADDRESS = "p_address";
	
	//Events Table Variables
	private static final String EVENTS_TABLE = "events";
	public static final String EVENTS_USERNAME = "e_username";
	public static final String EVENTS_EVENTNAME = "e_eventsname";
	public static final String EVENTS_PRICE = "e_price";
	public static final String EVENTS_ADDRESS = "e_address";
	public static final String EVENTS_TIME = "e_time";
	
	//Reviews Table Variables
	private static final String REVIEWS_TABLE = "reviews";
	public static final String REVIEWS_USERNAME = "r_username";
	public static final String REVIEWS_PLACENAME = "r_placename";
	public static final String REVIEWS_REVIEWDETAILS = "r_reviewdetails";
	
	//Login Table Variables
	private static final String LOGIN_TABLE = "logins";
	public static final String LOGIN_USERNAME = "l_username";
	public static final String LOGIN_PASSWORD = "l_password";
	
	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// Create Statuses Table
			db.execSQL("CREATE TABLE " + STATUSES_TABLE + " (" +
					STATUSES_USERNAME + " TEXT NOT NULL, " + 
					STATUSES_STATUS + " TEXT NOT NULL);"			
			);
			//Create Places Table
			db.execSQL("CREATE TABLE " + PLACES_TABLE + " (" +
					PLACES_USERNAME + " TEXT NOT NULL, " + 
					PLACES_PLACENAME + " TEXT NOT NULL, " +
					PLACES_ADDRESS + " TEXT NOT NULL, " +
					PLACES_PRICE + " TEXT NOT NULL);"
			);
			
			//Create Events Table
			db.execSQL("CREATE TABLE " + EVENTS_TABLE + " (" +
					EVENTS_USERNAME + " TEXT NOT NULL, " + 
					EVENTS_EVENTNAME + " TEXT NOT NULL, " +
					EVENTS_TIME + " TEXT NOT NULL, " +
					EVENTS_ADDRESS + " TEXT NOT NULL, " +
					EVENTS_PRICE + " TEXT NOT NULL);"
			);
			
			// Create Statuses Table
			db.execSQL("CREATE TABLE " + REVIEWS_TABLE + " (" +
					REVIEWS_USERNAME + " TEXT NOT NULL, " + 
					REVIEWS_PLACENAME + " TEXT NOT NULL, " + 
					REVIEWS_REVIEWDETAILS + " TEXT NOT NULL);"			
			);
			
			//Create Login Table
			db.execSQL("CREATE TABLE " + LOGIN_TABLE + " (" +
					LOGIN_USERNAME + " TEXT NOT NULL, " + 
					LOGIN_PASSWORD + " TEXT NOT NULL);"			
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + STATUSES_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + PLACES_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + REVIEWS_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + LOGIN_TABLE);
			onCreate(db);
			
		}
		
	}
	
	public DatabaseHandler(Context c) {
		ourContext = c;
	}
	
	public DatabaseHandler open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;		
	}
	
	public void close() {
		ourHelper.close();
	}

	public long addStatusToDatabase(String status) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(STATUSES_USERNAME, Menu.username);
		cv.put(STATUSES_STATUS, status);
		return ourDatabase.insert(STATUSES_TABLE, null, cv);
		
	}

	public String readStatusFromDatabase() {
		// TODO Auto-generated method stub
		String[] columns = new String[] {STATUSES_USERNAME, STATUSES_STATUS};
		Cursor c = ourDatabase.query(STATUSES_TABLE, columns, null, null, null, null, null);
		String result = "";
		
		int iUserName = c.getColumnIndex(STATUSES_USERNAME);
		int iStatus = c.getColumnIndex(STATUSES_STATUS);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result = result + c.getString(iUserName) + " wrote: " + c.getString(iStatus) + "\n";
		}
		
		return result;
	}

	public long addPlaceToDatabase(String placeName, String placeAddress,
			String placePrice) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(PLACES_USERNAME, Menu.username);
		cv.put(PLACES_PLACENAME, placeName);
		cv.put(PLACES_ADDRESS, placeAddress);
		cv.put(PLACES_PRICE, placePrice);
		return ourDatabase.insert(PLACES_TABLE, null, cv);
		
	}

	public List<Place> readPlacesFromDatabase() {
		// TODO Auto-generated method stub
		List<Place> listOfPlaces = new ArrayList<Place>();
		
		String[] columns = new String[] {PLACES_USERNAME, PLACES_PLACENAME, PLACES_ADDRESS,
										PLACES_PRICE};
		Cursor c = ourDatabase.query(PLACES_TABLE, columns, null, null, null, null, null);
		
		
		int iUserName = c.getColumnIndex(PLACES_USERNAME);
		int iPlaceName = c.getColumnIndex(PLACES_PLACENAME);
		int iAddress = c.getColumnIndex(PLACES_ADDRESS);
		int iPrice = c.getColumnIndex(PLACES_PRICE);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Place placeToAdd = new Place(c.getString(iUserName), c.getString(iPlaceName),
								c.getString(iAddress), c.getString(iPrice));
			listOfPlaces.add(placeToAdd);
		}
		
		return listOfPlaces;
	}
	
	public long addReviewToDatabase(String reviewPlaceName, String reviewDetails) {
		ContentValues cv = new ContentValues();
		cv.put(REVIEWS_USERNAME, Menu.username);
		cv.put(REVIEWS_PLACENAME, reviewPlaceName);
		cv.put(REVIEWS_REVIEWDETAILS, reviewDetails);
		return ourDatabase.insert(REVIEWS_TABLE, null, cv);
	}

	public long addEventToDatabase(String eventName, String eventWhen,
			String eventAddress, String eventPrice) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(EVENTS_USERNAME, Menu.username);
		cv.put(EVENTS_EVENTNAME, eventName);
		cv.put(EVENTS_TIME, eventWhen);
		cv.put(EVENTS_ADDRESS, eventAddress);
		cv.put(EVENTS_PRICE, eventPrice);
		return ourDatabase.insert(EVENTS_TABLE, null, cv);
		
	}

	public List<Event> readEventsFromDatabase() {
		// TODO Auto-generated method stub
		List<Event> listOfEvents = new ArrayList<Event>();
		
		String[] columns = new String[] {EVENTS_USERNAME, EVENTS_EVENTNAME, EVENTS_TIME, EVENTS_ADDRESS,
				EVENTS_PRICE};
		Cursor c = ourDatabase.query(EVENTS_TABLE, columns, null, null, null, null, null);
		String result = "";

		int iUserName = c.getColumnIndex(EVENTS_USERNAME);
		int iEventName = c.getColumnIndex(EVENTS_EVENTNAME);
		int iTime = c.getColumnIndex(EVENTS_TIME);
		int iAddress = c.getColumnIndex(EVENTS_ADDRESS);
		int iPrice = c.getColumnIndex(EVENTS_PRICE);

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			Event eventToAdd = new Event(c.getString(iUserName), c.getString(iEventName), c.getString(iTime),
								c.getString(iAddress), c.getString(iPrice));
			listOfEvents.add(eventToAdd);
			
		}
		return listOfEvents;
	}

	public String readSpecificPlaceReviewsFromDatabase(String placeName) {
		// TODO Auto-generated method stub
		String[] columns = new String[] {REVIEWS_USERNAME, REVIEWS_PLACENAME, REVIEWS_REVIEWDETAILS};
		String sqlPlaceName = "\"" + placeName + "\"";
		Cursor c = ourDatabase.query(REVIEWS_TABLE, columns, REVIEWS_PLACENAME + "=" + sqlPlaceName, null, null, null, null);
		String result = "";
		
		int iUserName = c.getColumnIndex(REVIEWS_USERNAME);
		int iReviewDetails = c.getColumnIndex(REVIEWS_REVIEWDETAILS);
		
		if(c != null) {
			
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result = result + c.getString(iUserName) + " wrote: " + c.getString(iReviewDetails) + "\n";
			}
		}
		return result;
	}
	
	public List<String> getAllUsernames() {
		List<String> listOfUsernames = new ArrayList<String>();
		
		String[] columns = new String[] {LOGIN_USERNAME, LOGIN_PASSWORD};
		Cursor c = ourDatabase.query(LOGIN_TABLE, columns, null, null, null, null, null);
		
		int iUserName = c.getColumnIndex(LOGIN_USERNAME);
		int iPassword = c.getColumnIndex(LOGIN_PASSWORD);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			String username = c.getString(iUserName);
			listOfUsernames.add(username);
		}
		return listOfUsernames;
	}
	
	public long addUserToDatabase(String username, String password) {
		ContentValues cv = new ContentValues();
		cv.put(LOGIN_USERNAME, username);
		cv.put(LOGIN_PASSWORD, password);
		return ourDatabase.insert(LOGIN_TABLE, null, cv);
	}

	public boolean isUserAuthenticated(String username, String password) {
		// TODO Auto-generated method stub
		boolean authenticated = false;
		String[] columns = new String[] {LOGIN_USERNAME, LOGIN_PASSWORD};
		String sqlUsername = "\"" + username + "\"";
		Cursor c = ourDatabase.query(LOGIN_TABLE, columns, LOGIN_USERNAME + "=" + sqlUsername, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
			String passwordAttachedToUsername = c.getString(1);
			if(passwordAttachedToUsername.equals(password)) {
				authenticated = true;
			}
		}
		
		return authenticated;
	}
	
	public String readUserReviews(String userName) {
		// TODO Auto-generated method stub
		String[] columns = new String[] {REVIEWS_USERNAME, REVIEWS_PLACENAME, REVIEWS_REVIEWDETAILS};
		String sqlUserName = "\"" + userName + "\"";
		Cursor c = ourDatabase.query(REVIEWS_TABLE, columns, REVIEWS_USERNAME + "=" + sqlUserName, null, null, null, null);
		String result = "";
				
		int iUserName = c.getColumnIndex(REVIEWS_USERNAME);
		int iReviewPlace = c.getColumnIndex(REVIEWS_PLACENAME);
		int iReviewDetails = c.getColumnIndex(REVIEWS_REVIEWDETAILS);
		if(c != null) {
					
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result = result + c.getString(iUserName) + " wrote review for " + 
						c.getString(iReviewPlace) + ": " +c.getString(iReviewDetails) + "\n";
			}
		}
		return result;
	}
	
	public String readUserStatuses(String userName) {
		// TODO Auto-generated method stub
		String[] columns = new String[] {STATUSES_USERNAME, STATUSES_STATUS};
		String sqlUserName = "\"" + userName + "\"";
		Cursor c = ourDatabase.query(STATUSES_TABLE, columns, STATUSES_USERNAME + "=" + sqlUserName, null, null, null, null);
		String result = "";
				
		int iUserName = c.getColumnIndex(STATUSES_USERNAME);
		int iStatus = c.getColumnIndex(STATUSES_STATUS);
		if(c != null) {
					
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result = result + c.getString(iUserName) + " wrote status: " + c.getString(iStatus) + "\n";
			}
		}
		return result;
	}
	
	public String readUserPlaces(String userName) {
		// TODO Auto-generated method stub
		String[] columns = new String[] {PLACES_USERNAME, PLACES_PLACENAME, PLACES_ADDRESS, 
							PLACES_PRICE};
		String sqlUserName = "\"" + userName + "\"";
		Cursor c = ourDatabase.query(PLACES_TABLE, columns, PLACES_USERNAME + "=" + sqlUserName, null, null, null, null);
		String result = "";
				
		int iUserName = c.getColumnIndex(PLACES_USERNAME);
		int iPlaceName = c.getColumnIndex(PLACES_PLACENAME);
		if(c != null) {
					
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result = result + c.getString(iUserName) + " added place: " + c.getString(iPlaceName) + "\n";
			}
		}
		return result;
	}
	
	public String readUserEvents(String userName) {
		// TODO Auto-generated method stub
		String[] columns = new String[] {EVENTS_USERNAME, EVENTS_EVENTNAME, EVENTS_TIME, 
				EVENTS_ADDRESS, EVENTS_PRICE};
		String sqlUserName = "\"" + userName + "\"";
		Cursor c = ourDatabase.query(EVENTS_TABLE, columns, EVENTS_USERNAME + "=" + sqlUserName, null, null, null, null);
		String result = "";
				
		int iUserName = c.getColumnIndex(EVENTS_USERNAME);
		int iEventName = c.getColumnIndex(EVENTS_EVENTNAME);
		if(c != null) {
					
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result = result + c.getString(iUserName) + " added event: " + 
						c.getString(iEventName) + "\n";
			}
		}
		return result;
	}
	
	
	
	
	

}
