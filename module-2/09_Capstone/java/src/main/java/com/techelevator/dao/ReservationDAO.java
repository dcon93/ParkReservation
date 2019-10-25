package com.techelevator.dao;

import java.util.ArrayList;

import com.techelevator.Reservation;

public interface ReservationDAO {
	public boolean saveReservation(Reservation reservation); // returns true if the reservation was saved 
	public boolean isReservationAvailable(Reservation reservation); // returns true if the reservation is available
	public ArrayList<Reservation> getAllReservationsNext30(Long parkId); //returns all reservations for a park for next 30 days in some order
}
