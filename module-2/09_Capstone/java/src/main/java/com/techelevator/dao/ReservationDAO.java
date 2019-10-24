package com.techelevator.dao;

public interface ReservationDAO {
	public boolean saveReservation(Reservation reservation); // returns true if the reservation was saved 
	public boolean isReservationAvailable(Reservation reservation); // returns true if the reservation is available
	public ArrayList<Reservation> getAllReservationsNext30(); //returns all reservations for next 30 days in some order
}
