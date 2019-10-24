package com.techelevator.dao;

import java.util.ArrayList;

import com.techelevator.Campground;

public interface CampgroundDAO {
	
	public ArrayList<Campground> getCampgroundByCampgroundId(Long campgroundId);
	public ArrayList<Campground> getCampgroundsByParkId(Long parkId); // return all campgrounds in alphabetical order
	
}
