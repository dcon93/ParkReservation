package com.techelevator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.dao.JDBCReservationDAO;
import com.techelevator.dao.JDBCSiteDAO;


public class JDBCReservationDAOIntegrationTests extends DAOIntegrationTest{

	private JDBCReservationDAO dao = new JDBCReservationDAO(getDataSource());

	@SuppressWarnings("deprecation")
	@Test
	public void save_reservation_works() {
		Reservation reservation = new Reservation(getDummySiteId1(), "Reservation One", new Date(300,10,10), new Date(300,11, 10));
		dao.saveReservation(reservation);
		
		Reservation receivedReservation = dao.getReservation(reservation.getReservationId());
		
		doReservationsMatch(reservation, receivedReservation);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void is_reservation_available_works() {
		Reservation reservation = new Reservation(getDummySiteId1(), "Reservation One", new Date(300,10,10), new Date(300,11, 10));
		boolean firstResult = dao.isReservationAvailable(reservation);
		dao.saveReservation(reservation);
				
		Reservation conflictingReservation = new Reservation(getDummySiteId1(), "Reservation Two", new Date(300,10,10), new Date(300,11, 10));
		boolean secondResult = dao.isReservationAvailable(conflictingReservation);
		
		Assert.assertTrue("first reservation is fine", firstResult);
		Assert.assertFalse("second reservation conflicts", secondResult);	
	}
	
	@SuppressWarnings("deprecation")
	@Test 
	public void get_all_reservations_next_30_days() {
		ArrayList<Reservation> emptyList = dao.getAllReservationsNext30(getDummyParkId1());
	
		Reservation reservation = new Reservation(getDummySiteId1(), "Reservation One", new Date(119,10,29), new Date(119,10,31));
		dao.saveReservation(reservation);
		Reservation reservation2 = new Reservation(getDummySiteId2(), "Reservation Two", new Date(119,10,29), new Date(119,10,31));
		dao.saveReservation(reservation2);
		
		ArrayList<Reservation> twoRes = dao.getAllReservationsNext30(getDummyParkId1());
		
		Reservation reservationFarOut = new Reservation(getDummySiteId1(), "Reservation Next Month", new Date(120,10,19), new Date(120,10,22));
		dao.saveReservation(reservationFarOut);
		
		ArrayList<Reservation> stillTwoRes = dao.getAllReservationsNext30(getDummyParkId1());
		ArrayList<Reservation> testExisting = dao.getAllReservationsNext30((long)1);
		
		Assert.assertEquals(35, stillTwoRes.size());
		Assert.assertEquals(0, emptyList.size());
		Assert.assertEquals(2, twoRes.size());
		Assert.assertEquals(2, stillTwoRes.size());
		
	}
	
	private void doReservationsMatch(Reservation res1, Reservation res2) {
		Assert.assertEquals(res1.getReservationId(), res2.getReservationId());
		Assert.assertEquals(res1.getName(), res2.getName());
		Assert.assertEquals(res1.getSiteId(), res2.getSiteId());
		Assert.assertEquals(res1.getFromDate(), res2.getFromDate());
		Assert.assertEquals(res1.getToDate(), res2.getToDate());
	}
}
