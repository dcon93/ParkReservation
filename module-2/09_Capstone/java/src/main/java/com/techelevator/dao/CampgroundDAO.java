package com.techelevator.dao;

import java.util.ArrayList;

import com.techelevator.Campground;

public interface CampgroundDAO {
	
	public Campground getCampgroundById(Long campgroundId);
	public ArrayList<Campground> getAllCampgrounds(); // return all campgrounds in alphabetical order
	public ArrayList<Campground> getCampgroundsByParkId(Long parkId); // in alphabetical order
	
}
