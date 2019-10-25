package com.techelevator;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.dao.JDBCSiteDAO;


public class JDBCSiteDAOIntegrationTests extends DAOIntegrationTest{
	private JDBCSiteDAO dao = new JDBCSiteDAO(getDataSource());
	

	@Test
	public void get_site_by_id_works() {
		Site theSite = dao.getSiteBySiteId(getDummySiteId1());
		Assert.assertEquals(getDummySiteId1(), theSite.getSiteId());
	}
	
	@Test 
	public void get_all_sites_by_campground_works() {
		ArrayList<Site> sites = dao.getAllSitesByCampgroundId(getDummyCampgroundId1());
		ArrayList<Site> noSites = dao.getAllSitesByCampgroundId(getDummyCampgroundId2());
		
		Assert.assertEquals(2, sites.size());
		Assert.assertEquals(0, noSites.size());
	}
	
	@Test
	public void get_all_sites_by_park_works() {
		ArrayList<Site> sites = dao.getAllSitesByParkId(getDummyParkId1());
		ArrayList<Site> noSites = dao.getAllSitesByParkId(getDummyParkId2());
		
		Assert.assertEquals(2, sites.size());
		Assert.assertEquals(0, noSites.size());
	}
}
