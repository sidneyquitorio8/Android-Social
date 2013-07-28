package com.cmpe137.StudentSocial;

import java.io.Serializable;

public class Person implements Serializable {
	
	private String user;
	
	public Person(String aUser) {
		user = aUser;
	}

	public String getUsername() {
		return user;
	}
	
	public String getUsersEvents() {
		String allUsersEvents = "";
		return allUsersEvents;
	}
	
	public String getUsersStatuses() {
		String allUsersStatuses = "";
		return allUsersStatuses;
	}
	
	public String getUsersPlaces() {
		String allUsersPlaces = "";
		return allUsersPlaces;
	}
	
	public String getUsersReviews() {
		String allUsersReviews = "";
		return allUsersReviews;
	}

}
