package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Park;

public class JDBCParkDAO implements ParkDAO{

	
	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
		

	}
		@Override
		public Park getParkById(Long parkId) {
			Park thePark = null;
			String sqlFindCityById = "SELECT id, name, area, location, visitors, description, establish_date " + "FROM park " + "WHERE id = ?";
			SqlRowSet results = this.jdbcTemplate.queryForRowSet(sqlFindCityById, parkId);

			if (results.next()) {
				thePark = mapRowToPark(results);
			}
			return thePark;
		}
		
		@Override
		public ArrayList<Park> getAllParks() {
				ArrayList<Park> parkInfo = new ArrayList<>();
				String parks = "SELECT * FROM park ";
				SqlRowSet parkNextRow = jdbcTemplate.queryForRowSet(parks);
				while(parkNextRow.next()) {
					parkInfo.add(mapRowToPark(parkNextRow));
				}
				return parkInfo;
			}
		
		
		
		private Park mapRowToPark(SqlRowSet parkNextRow) {
			Park thePark;
			 thePark = new Park();
			
			
			thePark.setPark_ID(parkNextRow.getLong("park_id"));
			thePark.setName(parkNextRow.getString("name"));
			thePark.setLocation(parkNextRow.getString("location"));
			thePark.setDateEstablished(parkNextRow.getDate("establish_date"));
			thePark.setArea(parkNextRow.getLong("area"));
			thePark.setVisitors(parkNextRow.getLong("visitors"));
			thePark.setDescriptionOfPark(parkNextRow.getString("description"));
			
			
			return thePark;
		}
}
