package com.cmpe137.StudentSocial;

public class Place {

	private String userName;
	private String placeName;
	private String placeAddress;
	private String placePrice;
	
	public Place(String aUserName, String aPlaceName, String aPlaceAddress, String aPlacePrice) {
		userName = aUserName;
		placeName = aPlaceName;
		placeAddress = aPlaceAddress;
		placePrice = aPlacePrice;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public String getPlaceAddress() {
		return placeAddress;
	}

	public String getPlacePrice() {
		return placePrice;
	}
	
}
