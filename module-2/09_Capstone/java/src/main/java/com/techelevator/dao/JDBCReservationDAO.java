package com.techelevator.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Reservation;
import com.techelevator.Site;

public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean saveReservation(Reservation reservation) { // returns true if the reservation was saved

		boolean available = isReservationAvailable(reservation);

		if (available) {
			String sqlSaveReservation = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) "
					+ "VALUES (?, ?, ?, ?, ?, CURRENT_DATE)";
			reservation.setReservationId(getNextReservationId());
			jdbcTemplate.update(sqlSaveReservation, reservation.getReservationId(), reservation.getSiteId(),
					reservation.getName(), reservation.getFromDate(), reservation.getToDate());
		}

		return available;
	}
	
	public Reservation getReservation(Long reservationId) { // returns reservation with id
		Reservation theReservation = null;

		String sqlGetReservationById = "SELECT reservation_id, site_id, name, from_date, to_date, create_date " + 
									"FROM reservation WHERE reservation_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservationById, reservationId);
		if (results.next()) {
			theReservation = mapRowToReservation(results);
		}

		return theReservation;
	}

	public boolean isReservationAvailable(Reservation reservation) { // returns true if the reservation is available
		boolean available = false;
		ArrayList<Reservation> reservationsThatOverlap = new ArrayList<Reservation>();

		String sqlGetOverlappingReservations = "SELECT * FROM reservation WHERE (?, ?) OVERLAPS (from_date, to_date) AND site_id = ?";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetOverlappingReservations, reservation.getFromDate(),
				reservation.getToDate(), reservation.getSiteId());

		while (results.next()) {
			reservationsThatOverlap.add(mapRowToReservation(results));
		}

		if (reservationsThatOverlap.size() == 0) {
			available = true;
		}
		return available;
	}

	@Override
	public ArrayList<Reservation> getAllReservationsNext30(Long parkId) { //NEEDS TO BE FIXED returns all reservations for next ALL days in some order for given park
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();

		String sqlGetReservatiosForNext30Days = "SELECT reservation_id, site_id, reservation.name, from_date, to_date, create_date FROM reservation " +
											"JOIN site USING (site_id) " +
											"JOIN campground USING (campground_id) " +
											"WHERE campground.park_id = ? " + 
									        "AND (CURRENT_DATE, CURRENT_DATE + 30) OVERLAPS (from_date, to_date) " +
											"ORDER BY campground.name, site_number";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservatiosForNext30Days, parkId);
		
		while (results.next()) {
			reservations.add(mapRowToReservation(results));
		}

		return reservations;
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

	private Long getNextReservationId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('reservation_reservation_id_seq')");
		nextIdResult.next();
		return nextIdResult.getLong(1);
	}


}
