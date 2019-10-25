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
	


}
