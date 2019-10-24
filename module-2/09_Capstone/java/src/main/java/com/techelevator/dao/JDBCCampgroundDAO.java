package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Campground;

public class JDBCCampgroundDAO implements CampgroundDAO {

	
	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public ArrayList<Campground> getCampgroundByCampgroundId(Long campgroundId) {
		ArrayList<Campground> campInfo = new ArrayList<>();
		String camp=("SELECT * FROM campground WHERE campground_id=?");
		SqlRowSet campNextRow = jdbcTemplate.queryForRowSet(camp,campgroundId);
		while(campNextRow.next()) {		
			Campground campground =mapRowToCampground(campNextRow);
			campInfo.add(campground);
		}
		return campInfo;

	}
	
	private Campground mapRowToCampground(SqlRowSet campNextRow){
		Campground campground;
		 campground = new Campground();
		campground.setCampgroundIDs(campNextRow.getLong("campground_id"));
		campground.setName(campNextRow.getString("name"));
		campground.setOpenFrom(campNextRow.getDate("open_from_mm"));
		campground.setOpenTo(campNextRow.getDate("open_to_mm"));
		campground.setDailyFee(campNextRow.getBigDecimal("daily_fee"));
		
		return campground;
		
	}

	@Override
	public ArrayList<Campground> getCampgroundsByParkId(Long parkId) {
		ArrayList<Campground> campInfo = new ArrayList<>();
		String camp=("SELECT * FROM campground WHERE park_id=?");
		SqlRowSet campNextRow = jdbcTemplate.queryForRowSet(camp,parkId);
		while(campNextRow.next()) {		
			Campground campground =mapRowToCampground(campNextRow);
			campInfo.add(campground);
		}
		return campInfo;
	}
}
