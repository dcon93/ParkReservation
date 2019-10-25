package com.techelevator;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public abstract class DAOIntegrationTest {

	/*
	 * Using this particular implementation of DataSource so that every database
	 * interaction is part of the same database session and hence the same database
	 * transaction
	 */
	private static SingleConnectionDataSource dataSource;
	private static JdbcTemplate jdbc;
	private Long dummySiteId1;
	private Long dummySiteId2;
	private Long dummyCampgroundId1;
	private Long dummyCampgroundId2;
	private Long dummyParkId1;
	private Long dummyParkId2;

	/*
	 * Before any tests are run, this method initializes the datasource for testing.
	 */
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/*
		 * The following line disables autocommit for connections returned by this
		 * DataSource. This allows us to rollback any changes after each test
		 */
		dataSource.setAutoCommit(false);
		jdbc = new JdbcTemplate(dataSource);
	}

	@Before
	public void createTestData() {
		JdbcTemplate jdbc = new JdbcTemplate(getDataSource());
		
		// Adding 2 Parks
		dummyParkId1 = getNextParkId();
		String sqlAddDummyPark1 = "INSERT INTO park (park_id, name, area, location, visitors, description, establish_date) "
				+ "VALUES (?, 'Pittsburgh National Park', 200, 'Pennsylvania', 1000, 'Super duper park in Pittsburgh', '2019-01-01')";
		jdbc.update(sqlAddDummyPark1, dummyParkId1);

		dummyParkId2 = getNextParkId();
		String sqlAddDummyPark2 = "INSERT INTO park (park_id, name, area, location, visitors, description, establish_date) "
				+ "VALUES (?, 'Other National Park', 300, 'Pennsylvania', 1000, 'Super duper park in Lancaster', '2019-04-01')";
		jdbc.update(sqlAddDummyPark2, dummyParkId2);
		

		// Adding 2 Campgrounds, both for Park 1
		dummyCampgroundId1 = getNextCampgroundId();
		String sqlAddDummyCampground1 = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) "
				+ "VALUES (?, ?, 'Awesome Campground', '01', '12', '$10.00')";
		jdbc.update(sqlAddDummyCampground1, dummyCampgroundId1, dummyParkId1);

		dummyCampgroundId2 = getNextCampgroundId();
		String sqlAddDummyCampground2 = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) "
				+ "VALUES (?, ?, 'The Other One', '01', '12', '$10.00')";
		jdbc.update(sqlAddDummyCampground2, dummyCampgroundId2, dummyParkId1);
		

		// Adding 2 Sites, both for Campground 1 at Park 1
		dummySiteId1 = getNextSiteId();
		String sqlAddDummySite1 = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) "
				+ "VALUES (?, ?, 10, 5, true, 0, false)";
		jdbc.update(sqlAddDummySite1, dummySiteId1, dummyCampgroundId1);
		
		dummySiteId2 = getNextSiteId();
		String sqlAddDummySite2 = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) "
				+ "VALUES (?, ?, 11, 8, false, 50, true)";
		jdbc.update(sqlAddDummySite2, dummySiteId2, dummyCampgroundId1);

	}

	/*
	 * After all tests have finished running, this method will close the DataSource
	 */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/*
	 * After each test, we rollback any changes that were made to the database so
	 * that everything is clean for the next test
	 */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/*
	 * This method provides access to the DataSource for subclasses so that they can
	 * instantiate a DAO for testing
	 */
	protected static DataSource getDataSource() {
		return dataSource;
	}

	protected Long getNextSiteId() {
		SqlRowSet nextIdResult = jdbc.queryForRowSet("SELECT nextval('site_site_id_seq')");
		nextIdResult.next();
		return nextIdResult.getLong(1);
	}

	protected Long getNextCampgroundId() {
		SqlRowSet nextIdResult = jdbc.queryForRowSet("SELECT nextval('campground_campground_id_seq')");
		nextIdResult.next();
		return nextIdResult.getLong(1);
	}

	protected Long getNextParkId() {
		SqlRowSet nextIdResult = jdbc.queryForRowSet("SELECT nextval('park_park_id_seq')");
		nextIdResult.next();
		return nextIdResult.getLong(1);
	}

	public static JdbcTemplate getJdbc() {
		return jdbc;
	}

	public Long getDummySiteId1() {
		return dummySiteId1;
	}

	public Long getDummySiteId2() {
		return dummySiteId2;
	}

	public Long getDummyCampgroundId1() {
		return dummyCampgroundId1;
	}

	public Long getDummyCampgroundId2() {
		return dummyCampgroundId2;
	}

	public Long getDummyParkId1() {
		return dummyParkId1;
	}

	public Long getDummyParkId2() {
		return dummyParkId2;
	}

}
