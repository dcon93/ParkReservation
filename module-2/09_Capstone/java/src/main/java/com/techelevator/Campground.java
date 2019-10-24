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
}
