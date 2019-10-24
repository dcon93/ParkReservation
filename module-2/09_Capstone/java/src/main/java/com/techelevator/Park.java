package com.techelevator;

import java.time.LocalDate;

public class Park {
	
	String parkID;
	String name;
	String area;
	String location;
	long visitors;
	String descriptionOfPark;
	LocalDate dateEstablished;
	
	public Park(String parkID, String name, String area, String location, long visitors, String descriptionOfPark,
			LocalDate dateEstablished) {
		this.parkID = parkID;
		this.name = name;
		this.area = area;
		this.location = location;
		this.visitors = visitors;
		this.descriptionOfPark = descriptionOfPark;
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
	public LocalDate getDateEstablished() {
		return dateEstablished;
	}
	
}
