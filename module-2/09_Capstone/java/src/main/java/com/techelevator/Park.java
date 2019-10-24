package com.techelevator;

import java.sql.Date;

public class Park {
	
	String parkID;
	String name;
	String area;
	String location;
	long visitors;
	String descriptionOfPark;
	Date dateEstablished;
	
	public Park() {}
	
	public Park(String parkID, String name, String area, String location, long visitors, String descriptionOfPark,
			Date dateEstablished) {
		this.parkID = parkID;
		this.name = name;
		this.area = area;
		this.location = location;
		this.visitors = visitors;
		this.descriptionOfPark = descriptionOfPark;
		this.dateEstablished = dateEstablished;
	}
	
	
	
	public void setParkID(String parkID) {
		this.parkID = parkID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setVisitors(long visitors) {
		this.visitors = visitors;
	}

	public void setDescriptionOfPark(String descriptionOfPark) {
		this.descriptionOfPark = descriptionOfPark;
	}

	public void setDateEstablished(Date dateEstablished) {
		this.dateEstablished = dateEstablished;
	}

	public String getParkID() {
		return parkID;
	}
	public void setPark_ID(String parkID) {
		this.parkID = parkID;
	}
	public String getName() {
		return name;
	}
	public String getArea() {
		return area;
	}
	public String getLocation() {
		return location;
	}
	public long getVisitors() {
		return visitors;
	}
	public String getDescriptionOfPark() {
		return descriptionOfPark;
	}
	public Date getDateEstablished() {
		return dateEstablished;
	}
	
}
