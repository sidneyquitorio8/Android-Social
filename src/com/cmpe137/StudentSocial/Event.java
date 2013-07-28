package com.cmpe137.StudentSocial;

public class Event {
	
	private String userName;
	private String eventName;
	private String eventTime;
	private String eventAddress;
	private String eventPrice;
	
	public Event(String aUserName, String aEventName, String aEventTime, String aEventAddress, String aEventPrice) {
		userName = aUserName;
		eventName = aEventName;
		eventTime = aEventTime;
		eventAddress = aEventAddress;
		eventPrice = aEventPrice;
	}

	public String getUserName() {
		return userName;
	}

	public String getEventName() {
		return eventName;
	}

	public String getEventTime() {
		return eventTime;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public String getEventPrice() {
		return eventPrice;
	}

}
