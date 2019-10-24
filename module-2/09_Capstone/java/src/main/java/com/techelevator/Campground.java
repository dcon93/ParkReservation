package com.techelevator;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Campground {
	
	String campgroundIDs;
	String name;
	Date openTo;
	Date openFrom;
	boolean isOpen;
	BigDecimal dailyFee;
	
	

	public String getCampgroundIDs() {
		return campgroundIDs;
	}
	public String getName() {
		return name;
	}
	public Date getOpenTo() {
		return openTo;
	}
	public Date getOpenFrom() {
		return openFrom;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public BigDecimal getDailyFee() {
		return dailyFee;
	}
	
	public void setCampgroundIDs(String campgroundIDs) {
		this.campgroundIDs = campgroundIDs;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOpenTo(Date openTo) {
		this.openTo = openTo;
	}
	public void setOpenFrom(Date openFrom) {
		this.openFrom = openFrom;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
}
