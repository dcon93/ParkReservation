package com.techelevator.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Site;


public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Site getSiteBySiteId(Long siteId) {
		Site theSite = null;

		String sqlGetSiteBySiteId = "SELECT site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities " + 
									"FROM site WHERE site_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSiteBySiteId, siteId);
		if (results.next()) {
			theSite = mapRowToSite(results);
		}

		return theSite;

	}

	public ArrayList<Site> getAllSitesByCampgroundId(Long campgroundId) { // in order by site number
		ArrayList<Site> sites = new ArrayList<Site>();

		String sqlGetSiteByCampgroundId = "SELECT site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities " + 
										  "FROM site WHERE campground_id = ? " +
										  "ORDER BY site_number";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSiteByCampgroundId, campgroundId);
		
		
		while (results.next()) {
			sites.add(mapRowToSite(results));
		}

		return sites;

	}

	public ArrayList<Site> getAllSitesByParkId(Long parkId) { // in order by campground and then by site number
		ArrayList<Site> sites = new ArrayList<Site>();

		String sqlGetSiteByParkId = "SELECT site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities " + 
									"FROM site JOIN campground USING (campground_id) " +
									"WHERE park_id = ? " +
									"ORDER BY campground.name, site_number";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSiteByParkId, parkId);
		
		
		while (results.next()) {
			sites.add(mapRowToSite(results));
		}

		return sites;

	}

	private Site mapRowToSite(SqlRowSet siteNextRow) {
		Site theSite = new Site();

		theSite.setSiteId(siteNextRow.getLong("site_id"));
		theSite.setCampgroundId(siteNextRow.getLong("campground_id"));
		theSite.setSiteNumber(siteNextRow.getInt("site_number"));
		theSite.setMaxOccupancy(siteNextRow.getInt("max_occupancy"));
		theSite.setAccessible(siteNextRow.getBoolean("accessible"));
		theSite.setMaxRVLength(siteNextRow.getInt("max_rv_length"));
		theSite.setUtilities(siteNextRow.getBoolean("utilities"));

		return theSite;
	}

}
