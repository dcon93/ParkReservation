package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.sql.DataSource;

import com.techelevator.dao.JDBCCampgroundDAO;
import com.techelevator.dao.JDBCParkDAO;
import com.techelevator.dao.JDBCReservationDAO;
import com.techelevator.dao.JDBCSiteDAO;

public class Menu {
	JDBCParkDAO parkDAO;
	JDBCSiteDAO siteDAO;
	JDBCCampgroundDAO campgroundDAO;
	JDBCReservationDAO reservationDAO;
	Scanner userInput;
	OutputStream out;

	public Menu(InputStream input, OutputStream output, DataSource datasource) {
		userInput = new Scanner(input);
		out = output;
		parkDAO = new JDBCParkDAO(datasource);
		siteDAO = new JDBCSiteDAO(datasource);
		campgroundDAO = new JDBCCampgroundDAO(datasource);
		reservationDAO = new JDBCReservationDAO(datasource);
	}

	public Park selectAPark() { // displays parks to user and returns their choice of park; returns null if they
								// choose 'Q'
		Park choice = null;

		Map<String, Park> parksMap = printAndMapParks(parkDAO.getAllParks());

		boolean badInput = true;
		while (badInput) {
			System.out.print("Select a park >>> ");
			Park chosenPark = parksMap.get(userInput.nextLine());
			if (chosenPark != null) {
				if (chosenPark.getParkID() != null) {
					choice = chosenPark;
				}
				badInput = false;
			} else {
				System.out.println("Not valid input. Please enter a number to choose a park or 'Q' to quit.");
			}

		}

		return choice;
	}
	
	private Map<String, Park> printAndMapParks(ArrayList<Park> parks) {
		Map<String, Park> parkToNumber = new HashMap<String, Park>();

		System.out.println("Select a Park for Further Details");

		for (int i = 0; i < parks.size(); i++) {
			System.out.println("   " + (i + 1) + ") " + parks.get(i));
			parkToNumber.put(String.valueOf(i + 1), parks.get(i));
		}

		parkToNumber.put("Q", new Park());
		System.out.println("   Q) quit\n");

		return parkToNumber;
	}
	
	public int parkInfo(Park chosenPark) {

		System.out.printf("%-16s %s%n", "Park Name:", chosenPark.getName());
		System.out.printf("%-16s %s%n", "Location:", chosenPark.getLocation());
		System.out.printf("%-16s %s%n", "Established:", chosenPark.getDateEstablished());
		System.out.printf("%-16s %,d sq km%n", "Area:", chosenPark.getArea());
		System.out.printf("%-16s %,d%n", "Annual Visitors:", chosenPark.getVisitors());
		System.out.println();
		System.out.println(chosenPark.getDescriptionOfPark());

		System.out.print("1) View Campgrounds");
		System.out.print("2) Search for Reservation");
		System.out.print("3) Return to previous screen");
		System.out.print("Select a choice >>> ");

		while (true) {
			String choice = userInput.nextLine();

			if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
				int numberChoice = Integer.parseInt(choice);
				return numberChoice;
			} else {
				System.out.println("Please select an option using a 1, 2, or 3");
			}
		}
	}


	public boolean parkCampgrounds(Park chosenPark) {

		System.out.println(chosenPark.getName() + " National Park Campgrounds");
		System.out.println("Id  Name                               Open       Close         DailyFee");
		List<Campground> campgrounds = campgroundDAO.getCampgroundsByParkId(chosenPark.getParkID());
		for (Campground camp : campgrounds) {
			System.out.print("#" + camp.getCampgroundID() + "  ");
			System.out.print(String.format("%-35s", camp.getName()));
			System.out.print(String.format("%-11s", camp.getOpenFrom()));
			System.out.print(String.format("%-14s", (camp.getOpenTo())));
			System.out.println(String.format("%-25s", "$" + camp.getDailyFee()));

			System.out.print("1( Search for Available Reservation");
			System.out.print("2( Return to Previous Screen");
		}
		while (true) {

			String choice = userInput.nextLine();

			if (choice.equals("1")) {
				return true;
			}
			if (choice.equals("2")) {
				return false;
			} else {
				System.out.println("Please make another choice using a 1 or a 2");
			}

		}
	}


	
	public void makeReservationByCampground(Park chosenPark) { // Displays campgrounds and lets user make reservation
																// and stuff.
		ArrayList<Campground> campgrounds = campgroundDAO.getCampgroundsByParkId(chosenPark.getParkID());
		Map<String, Campground> campMap = printAndMapCampgrounds(campgrounds);

		boolean badInput = true;
		while (badInput) {
			System.out.print("Which campground (enter 0 to cancel)?");
			Campground chosenCampground = campMap.get(userInput.nextLine());
			if (chosenCampground == null) {
				System.out.println("Not valid input.");
			} else {
				badInput = false;
			}
		}

	}

	private Map<String, Campground> printAndMapCampgrounds(ArrayList<Campground> campgrounds) {
		Map<String, Campground> campgroundToNumber = new HashMap<String, Campground>();

		System.out.println("Search For Campground Reservation");
		System.out.printf("%-8s" + "%-15s" + "%-12s" + "%-12s" + "%-12s", "", "Name", "Open", "Close", "Daily Fee");

		for (int i = 0; i < campgrounds.size(); i++) {
			Campground c = campgrounds.get(i);

			String dailyFee = String.format("%.2f", c.getDailyFee().doubleValue());
			System.out.printf("%-8s" + "%-15s" + "%-12s" + "%-12s" + "$" + "%-12s", String.valueOf(i + 1), c.getName(),
					c.openFromMonth(), c.openToMonth(), dailyFee);
			campgroundToNumber.put(String.valueOf(i + 1), c);
		}

		campgroundToNumber.put("0", new Campground());

		return campgroundToNumber;

	}

	
	public void makeReservationByPark(Park chosenPark) {
	}// Displays campgrounds and lets user make reservation and stuff.

}
