package com.techelevator;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Campground {
	
	private Long campgroundID;
	private String name;
	private String openTo;
	private String openFrom;
	private BigDecimal dailyFee;
	
	
	public String openToMonth() {
		return changeToMonth(openTo);
	}
	
	public String openFromMonth() {
		return changeToMonth(openFrom);
	}
	
	private String changeToMonth(String monthNum) {
		String month;
		switch (monthNum) {
		case "01":
			month = "January";
			break;
		case "02":
			month = "February";
			break;
		case "03":
			month = "March";
			break;
		case "04": 
			month = "April";
			break;
		case "05": 
			month = "May";
			break;
		case "06": 
			month = "June";
			break;
		case "07": 
			month = "July";
			break;
		case "08":
			month = "August";
			break;
		case "09":
			month = "September";
			break;
		case "10":
			month = "October";
			break;
		case "11":
			month = "November";
			break;
		case "12":
			month = "December";
			break;
		default:
			month = monthNum;
			break;
		}
		
		return month;
	}

	public Long getCampgroundID() {
		return campgroundID;
	}
	public String getName() {
		return name;
	}
	public String getOpenTo() {
		return openTo;
	}
	public String getOpenFrom() {
		return openFrom;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	

	public void setCampgroundID(Long campgroundID) {
		this.campgroundID = campgroundID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOpenTo(String openTo) {
		this.openTo = openTo;
	}
	public void setOpenFrom(String openFrom) {
		this.openFrom = openFrom;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
}
