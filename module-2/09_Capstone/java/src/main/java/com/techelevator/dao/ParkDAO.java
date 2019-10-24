package com.techelevator.dao;

import java.util.ArrayList;

import com.techelevator.Park;

public interface ParkDAO {
	
	public Park getParkById(Long parkId);
	public ArrayList<Park> getAllParks(); // return parks in alphabetical order in arraylist. 
	
}
