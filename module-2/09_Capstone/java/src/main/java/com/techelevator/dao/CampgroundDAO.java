package com.techelevator.dao;

public interface CampgroundDAO {
	
	public getCampgroundById(Long campgroundId);
	public ArrayList<Campground> getAllCampgrounds(); // return all campgrounds in alphabetical order
	public ArrayList<Campground> getCampgroundsByParkId(Long parkId); // in alphabetical order
	
}
