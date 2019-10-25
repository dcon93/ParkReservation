package com.techelevator.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Reservation;
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

	@Override
	public ArrayList<Site> getAllSitesByCampgroundId(Long campgroundId, Reservation reservation) { // in order by site number
		ArrayList<Site> sites = new ArrayList<Site>();

		String sqlGetSiteByCampgroundId = "SELECT site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities " + 
										  "FROM site JOIN campground USING (campground_id) " +
										  "WHERE campground_id = ? " +
										  "AND site_id NOT IN (SELECT DISTINCT site_id FROM reservation WHERE (?, ?) OVERLAPS (from_date, to_date)) " + 
										  "ORDER BY campground.name, site_number";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSiteByCampgroundId, campgroundId, reservation.getFromDate(), reservation.getToDate());
		
		
		while (results.next()) {
			sites.add(mapRowToSite(results));
		}

		return sites;

	}

	@Override
	public ArrayList<Site> getAllSitesByParkId(Long parkId, Reservation reservation) { // in order by campground and then by site number
		ArrayList<Site> sites = new ArrayList<Site>();

		String sqlGetSiteByParkId = "SELECT site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities " + 
									"FROM site JOIN campground USING (campground_id) " +
									"WHERE park_id = ? " +
									"AND site_id NOT IN (SELECT DISTINCT site_id FROM reservation WHERE (?, ?) OVERLAPS (from_date, to_date)) " + 
									"ORDER BY campground.name, site_number";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSiteByParkId, parkId, reservation.getFromDate(), reservation.getToDate());
		
		
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
