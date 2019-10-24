package com.techelevator.dao;

import java.util.ArrayList;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Reservation;
import com.techelevator.Site;

public class JDBCReservationDAO implements ReservationDAO{

	public boolean saveReservation(Reservation reservation) { // returns true if the reservation was saved 
		boolean saved = false;
		
		
		
		return saved;
	}
	
	public boolean isReservationAvailable(Reservation reservation) { // returns true if the reservation is available
		boolean available = false;
		
		
		
		return available;
	}
	
	public ArrayList<Reservation> getAllReservationsNext30() { //returns all reservations for next 30 days in some order
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();

		String sqlGetReservatiosForNext30Days = "SELECT reservation_id, site_id, name, from_date, to_date, create_date " + 
									"FROM reservation " +
									"WHERE now() >  " +
									"ORDER BY campground.name, site_number";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservatiosForNext30Days, );
		
		
		while (results.next()) {
			sites.add(mapRowToSite(results));
		}

		return sites;
		
	}

	
	private Reservation mapRowToReservation(SqlRowSet reservationNextRow) {
		Reservation theReservation = new Reservation();

		theReservation.setReservationId(reservationNextRow.getLong("reservation_id"));
		theReservation.setSiteId(reservationNextRow.getLong("site_id"));
		theReservation.setName(reservationNextRow.getString("name"));
		theReservation.setFromDate(reservationNextRow.getDate("from_date"));
		theReservation.setToDate(reservationNextRow.getDate("to_date"));
		theReservation.setCreateDate(reservationNextRow.getDate("create_date"));

		return theReservation;
	}
}
