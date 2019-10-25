package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.dao.JDBCCampgroundDAO;
import com.techelevator.dao.JDBCParkDAO;
import com.techelevator.dao.JDBCReservationDAO;
import com.techelevator.dao.JDBCSiteDAO;

public class CampgroundCLI {

	Menu menu;

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();
	}

	public CampgroundCLI(DataSource datasource) {
		menu = new Menu(System.in, System.out, datasource);

	}

	public void run() {		
		while (true) {
			Park chosenPark = menu.selectAPark();
			if (chosenPark == null) {
				System.exit(0);
			} else {
				boolean parkInfo = true;
				while (parkInfo) {
					int menuChoice = menu.parkInfo(chosenPark);

					switch (menuChoice) {
					case 3:
						parkInfo = false;
						break;
					case 2: 
						menu.makeReservationByPark(chosenPark);
						break;
					case 1:
						boolean makeReservation = menu.parkCampgrounds(chosenPark);
						if (makeReservation) {
							menu.makeReservationByCampground(chosenPark);
						}
						break;
					}

				}

			}

		}
	}
}
