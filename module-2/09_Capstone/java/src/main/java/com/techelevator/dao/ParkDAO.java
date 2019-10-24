package com.techelevator.dao;

public interface ParkDAO {
	
	public Park getParkById(Long parkId);
	public ArrayList<Park> getAllParks(); // return parks in alphabetical order in arraylist. 
	
}
