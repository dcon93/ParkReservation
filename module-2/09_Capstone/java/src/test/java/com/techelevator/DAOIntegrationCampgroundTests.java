package com.techelevator;
import java.util.ArrayList;


import org.junit.Assert;
import org.junit.Test;

import com.techelevator.dao.JDBCCampgroundDAO;
import com.techelevator.dao.JDBCSiteDAO;

public class DAOIntegrationCampgroundTests extends DAOIntegrationTest {
	private JDBCCampgroundDAO dao = new JDBCCampgroundDAO(getDataSource());
	
	
	@Test
	public void get__campgrounds_by_campground_id_works() {
		ArrayList<Campground> campground = dao.getCampgroundByCampgroundId(getDummyCampgroundId1());
		ArrayList<Campground> secondCampground = dao.getCampgroundByCampgroundId(getDummyCampgroundId2());
		
		Assert.assertEquals(1, campground.size());
		Assert.assertEquals(1, secondCampground.size());
	}
	
	@Test
	public void get_campgrounds_by_park_id_works() {
		ArrayList<Campground> campground = dao.getCampgroundsByParkId(getDummyParkId1());
		ArrayList<Campground> noCampground = dao.getCampgroundsByParkId(getDummyParkId2());
		
		Assert.assertEquals(2, campground.size());
		Assert.assertEquals(0, noCampground.size());
		
	}
	
}
