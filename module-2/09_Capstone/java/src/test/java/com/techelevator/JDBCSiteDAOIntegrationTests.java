package com.techelevator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCSiteDAOIntegrationTests extends DAOIntegrationTest{
	JdbcTemplate j = new JdbcTemplate(getDataSource());
	

	@Test
	public void get_site_by_id_works() {
		String insertSite = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) " +
							"VALUES (?, 
		j.update()
	}
	
	private Long getNextSiteId() {
		SqlRowSet nextIdResult = j.queryForRowSet("SELECT nextval('seq_site_id')");
		nextIdResult.next();
		return nextIdResult.getLong(1);
	}
	
	private Long getNextCampgroundId() {
		SqlRowSet nextIdResult = j.queryForRowSet("SELECT nextval('seq_campground_id')");
		nextIdResult.next();
		return nextIdResult.getLong(1);
	}
	
}
