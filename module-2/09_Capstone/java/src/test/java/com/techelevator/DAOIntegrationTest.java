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
	private Long dummySiteId;
	private Long dummyCampgroundId;
	private Long dummyParkId;

	/* Before any tests are run, this method initializes the datasource for testing. */
	@BeforeClass	
	private static void setup() {
		setupDataSource();
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
		//jdbc.update("INSERT INTO park )
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
	
	private Long getNextSiteId() {
		SqlRowSet nextIdResult = jdbc.queryForRowSet("SELECT nextval('seq_site_id')");
		nextIdResult.next();
		return nextIdResult.getLong(1);
	}
	
	private Long getNextCampgroundId() {
		SqlRowSet nextIdResult = jdbc.queryForRowSet("SELECT nextval('seq_campground_id')");
		nextIdResult.next();
		return nextIdResult.getLong(1);
	}

	
}
