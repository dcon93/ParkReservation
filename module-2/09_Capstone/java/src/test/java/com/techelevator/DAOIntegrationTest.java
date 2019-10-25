package com.techelevator;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public abstract class DAOIntegrationTest {

	/* Using this particular implementation of DataSource so that
	 * every database interaction is part of the same database
	 * session and hence the same database transaction */
	private static SingleConnectionDataSource dataSource;
	private static JdbcTemplate jdbc;
	private static Long dummySiteId;
	private static Long dummyCampgroundId;
	private static Long dummyParkId;

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass	
	private static void setup() {
		setupDataSource();
		setDummySiteId();
		setDummyCampgroundId();
		setDummyParkId();
		createTestData();
	}
	
	private static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		/* The following line disables autocommit for connections
		 * returned by this DataSource. This allows us to rollback
		 * any changes after each test */
		dataSource.setAutoCommit(false);
		jdbc = new JdbcTemplate(dataSource);
	}
	private static void createTestData() {
		JdbcTemplate jdbc = new JdbcTemplate(getDataSource());
		
		String sqlAddDummyPark = "INSERT INTO park (park_id, name, area, location, visitors, description, establish_date) " +
								"VALUES (?, 'Pittsburgh National Park', 200, 'Pennsylvania', 1000, 2019-01-01)";
		jdbc.update(sqlAddDummyPark, dummyParkId);
		//jdbc.update("INSERT INTO campground )
		//jdbc.update("INSERT INTO site)
	}
	

	/* After all tests have finished running, this method will close the DataSource */
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	/* This method provides access to the DataSource for subclasses so that
	 * they can instantiate a DAO for testing */
	protected static DataSource getDataSource() {
		return dataSource;
	}
	
	private static void setDummySiteId() {
		SqlRowSet nextIdResult = jdbc.queryForRowSet("SELECT nextval('seq_site_id')");
		nextIdResult.next();
		dummySiteId = nextIdResult.getLong(1);
	}
	
	private static void setDummyCampgroundId() {
		SqlRowSet nextIdResult = jdbc.queryForRowSet("SELECT nextval('seq_campground_id')");
		nextIdResult.next();
		dummyCampgroundId = nextIdResult.getLong(1);
	}

	private static void setDummyParkId() {
		SqlRowSet nextIdResult = jdbc.queryForRowSet("SELECT nextval('seq_park_id')");
		nextIdResult.next();
		dummyParkId = nextIdResult.getLong(1);
	}
	
}
