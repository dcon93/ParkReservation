package com.techelevator;

import java.sql.Date;
import java.util.ArrayList;


import org.junit.Assert;
import org.junit.Test;

import com.techelevator.dao.JDBCReservationDAO;
import com.techelevator.dao.JDBCSiteDAO;


public class JDBCSiteDAOIntegrationTests extends DAOIntegrationTest{
	private JDBCSiteDAO dao = new JDBCSiteDAO(getDataSource());
	private JDBCReservationDAO daoReservation = new JDBCReservationDAO(getDataSource());
	

	@Test
	public void get_site_by_id_works() {
		Site theSite = dao.getSiteBySiteId(getDummySiteId1());
		Assert.assertEquals(getDummySiteId1(), theSite.getSiteId());
	}
	
	@Test 
	public void get_all_sites_by_campground() {
		Reservation reservation = new Reservation(getDummySiteId1(), "Reservation One", new Date(300,10,10), new Date(300,11, 10));
		
		ArrayList<Site> sites = dao.getAllSitesByCampgroundId(getDummyCampgroundId1(), reservation);
		
		daoReservation.saveReservation(reservation);
		
		ArrayList<Site> sitesAfterSavingRes = dao.getAllSitesByCampgroundId(getDummyCampgroundId1(), reservation);
		
		Assert.assertEquals(2, sites.size());
		Assert.assertEquals(1, sitesAfterSavingRes.size());
	}
	
	@Test 
	public void get_all_sites_by_park() {
		Reservation reservation = new Reservation(getDummySiteId1(), "Reservation One", new Date(300,10,10), new Date(300,11, 10));
		
		ArrayList<Site> sites = dao.getAllSitesByParkId(getDummyParkId1(), reservation);
		
		daoReservation.saveReservation(reservation);
		
		ArrayList<Site> sitesAfterSavingRes =  dao.getAllSitesByParkId(getDummyParkId1(), reservation);
		
		Assert.assertEquals(2, sites.size());
		Assert.assertEquals(1, sitesAfterSavingRes.size());
	}
	
	


}
