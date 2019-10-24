package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Campground {
	
	String campgroundIDs;
	String name;
	LocalDate openTo;
	LocalDate openFrom;
	boolean isOpen;
	BigDecimal dailyFee;
	
	public Campground() {}

	public Campground(String campgroundIDs, String name, LocalDate openTo, LocalDate openFrom, boolean isOpen,
			BigDecimal dailyFee) {
		super();
		this.campgroundIDs = campgroundIDs;
		this.name = name;
		this.openTo = openTo;
		this.openFrom = openFrom;
		this.isOpen = isOpen;
		this.dailyFee = dailyFee;
	}
	public String getCampgroundIDs() {
		return campgroundIDs;
	}
	public String getName() {
		return name;
	}
	public LocalDate getOpenTo() {
		return openTo;
	}
	public LocalDate getOpenFrom() {
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
	public void setOpenTo(LocalDate openTo) {
		this.openTo = openTo;
	}
	public void setOpenFrom(LocalDate openFrom) {
		this.openFrom = openFrom;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}
}
