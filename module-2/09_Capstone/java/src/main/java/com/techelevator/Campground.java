package com.techelevator;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Campground {
	
	long campgroundID;
	String name;
	String openTo;
	String openFrom;
	BigDecimal dailyFee;
	

	public long getCampgroundID() {
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
	

	public void setCampgroundID(long campgroundID) {
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
