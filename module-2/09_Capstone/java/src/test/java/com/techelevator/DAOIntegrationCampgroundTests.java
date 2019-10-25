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
		Campground campground = dao.getCampgroundByCampgroundId(getDummyCampgroundId1());
		Campground secondCampground = dao.getCampgroundByCampgroundId(getDummyCampgroundId2());
		
		Assert.assertSame(getDummyCampgroundId1(), campground.getCampgroundID());
		Assert.assertSame(getDummyCampgroundId2(), secondCampground.getCampgroundID());
	}
	
	@Test
	public void get_campgrounds_by_park_id_works() {
		ArrayList<Campground> campground = dao.getCampgroundsByParkId(getDummyParkId1());
		ArrayList<Campground> noCampground = dao.getCampgroundsByParkId(getDummyParkId2());
		
		Assert.assertEquals(2, campground.size());
		Assert.assertEquals(0, noCampground.size());
		
	}
	
	@Test
	public void list_comes_back_in_alphabetical_order() {
		ArrayList<Campground> campground = dao.getCampgroundsByParkId(getDummyParkId1());
		
		Assert.assertTrue(campground.size() > 1);
		
		String testName = campground.get(0).getName();
		
		for(int i = 1; i < campground.size(); i++) {
			String newName = campground.get(i).name;
			
			boolean nameComparison = testName.compareTo(newName) < 1;
			
			Assert.assertTrue(nameComparison);
			
			testName = newName;
			
			
		}
		
		
	}
	
}
