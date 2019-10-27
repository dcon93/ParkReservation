package com.techelevator;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.dao.JDBCParkDAO;

public class JDBCParkDAOIntegrationTests extends DAOIntegrationTest {
	
	private JDBCParkDAO dao = new JDBCParkDAO(getDataSource());
	
	
	@Test
	public void get__parks_by_park_id_works() {
		Park park = dao.getParkById(getDummyParkId1());
		Park secondPark = dao.getParkById(getDummyParkId2());
		
		Assert.assertSame(getDummyParkId1(), park.getParkID());
		Assert.assertSame(getDummyParkId2(), secondPark.getParkID());
	
}
	
	
	@Test
	public void get_all_parks_method_works() {
		ArrayList<Park> park = dao.getAllParks();
		
		Assert.assertEquals(5, park.size());
		//It's getting the two test ones and the three real ones.
		//So it is getting all of them that are inserted I don't think
		//You can select only the two test ones using this method which isn't what we want with it anyways I don't think
		
		
	}
	
	@Test
	public void get_all_parks_method_returns_alphabetically() {
		ArrayList<Park> park = dao.getAllParks();
		
		Assert.assertTrue(park.size() > 1);
		
		String testName = park.get(0).getName();
		
		for(int i = 1; i < park.size(); i++) {
			String newName = park.get(i).getName();
			
			boolean nameComparison = testName.compareTo(newName) < 1;
			
			Assert.assertTrue(nameComparison);
			
			testName = newName;
		}
	}
	
	}
