package com.techelevator;

import java.sql.Date;
import java.time.LocalDate;

public class Park {
	
	long parkID;
	String name;
	long area;
	String location;
	long visitors;
	String descriptionOfPark;
	Date dateEstablished;
	
	
	
	
	
	public void setName(String name) {
		this.name = name;
	}





	public void setArea(long area) {
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
	
	

	
	
	
	
	
	public long getParkID() {
		return parkID;
	}
	public void setPark_ID(long parkID) {
		this.parkID = parkID;
	}
	public String getName() {
		return name;
	}
	public long getArea() {
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
