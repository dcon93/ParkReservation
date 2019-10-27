package com.techelevator;

import java.sql.Date;
import java.time.LocalDate;

public class Park {
	
	private Long parkID;
	private String name;
	private long area;
	private String location;
	private long visitors;
	private String descriptionOfPark;
	private Date dateEstablished;
	
	
	@Override
	public String toString() {
		return name;
	}
	
	
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
	
	

	
	
	
	
	
	public Long getParkID() {
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
