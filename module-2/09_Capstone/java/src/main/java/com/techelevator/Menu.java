package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

		System.out.println("\nWelcome!\nSelect a Park for Further Details");

		Map<String, Park> parksMap = printAndMapParks(parkDAO.getAllParks());
		
		boolean badInput = true;
		while (badInput) {
			System.out.print(">>> ");
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

	public int parkInfo(Park chosenPark) {

		System.out.printf("%n%-16s %s National Park%n", "Park Name:", chosenPark.getName());
		System.out.printf("%-16s %s%n", "Location:", chosenPark.getLocation());
		System.out.printf("%-16s %s%n", "Established:", chosenPark.getDateEstablished());
		System.out.printf("%-16s %,d sq km%n", "Area:", chosenPark.getArea());
		System.out.printf("%-16s %,d%n", "Annual Visitors:", chosenPark.getVisitors());
		System.out.println();
		
		//print out description and wrap 
		String[] description = chosenPark.getDescriptionOfPark().split(" ");
		for(int i = 0; i < description.length; i++) {
			System.out.print(description[i] + " ");
			if(i % 10 == 0 && i != 0) {
				System.out.print("\n");
			}
		}
		System.out.println();

		System.out.println("\nSelect a Command");
		System.out.println("   1) View Campgrounds");
		System.out.println("   2) Search for Reservation");
		System.out.println("   3) Return to previous screen\n");
		System.out.print(">>> ");

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

		System.out.println("\n" + chosenPark.getName() + " National Park Campgrounds");
		System.out.println("    Name                Open       Close         DailyFee");
		List<Campground> campgrounds = campgroundDAO.getCampgroundsByParkId(chosenPark.getParkID());
		for (Campground camp : campgrounds) {
			System.out.print("#" + camp.getCampgroundID() + "  ");
			System.out.print(String.format("%-20s", camp.getName()));
			System.out.print(String.format("%-11s", camp.openFromMonth()));
			System.out.print(String.format("%-14s", camp.openToMonth()));
			System.out.println(String.format("$%.2f", camp.getDailyFee()));
		}

		System.out.println("\nSelect a Command");
		System.out.println("   1) Search for Available Reservation");
		System.out.println("   2) Return to Previous Screen\n");
		System.out.print(">>> ");

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

	public void makeReservationByPark(Park chosenPark) { // Displays campgrounds and lets user make reservation and
															// stuff.
		Reservation reservation = new Reservation();

		// Get dates and print available sites
		Map<String, Site> siteMap = getDatesAndDisplayOptions(reservation, chosenPark);
		if (siteMap == null) {
			return;
		}

		// Get and validate site from user
		Site chosenSite = getSiteChoiceFromUser(siteMap);
		if (chosenSite == null) {
			return;
		}

		reservation.setSiteId(chosenSite.getSiteId());

		// Get name and make reservation
		System.out.print("What name should the reservation be made under? >>> ");
		reservation.setName(userInput.nextLine());
		reservationDAO.saveReservation(reservation);
		System.out.println("\nThe reservation has been made, and the confirmation number is "
				+ reservation.getReservationId() + ".\n");
	}

	public void makeReservationByCampground(Park chosenPark) { // Displays campgrounds and lets user make reservation
																// and stuff.
		ArrayList<Campground> campgrounds = campgroundDAO.getCampgroundsByParkId(chosenPark.getParkID());

		// Display campgrounds and menu
		System.out.println("\nSearch For Campground Reservation");
		Map<String, Campground> campMap = printAndMapCampgrounds(campgrounds);

		Reservation reservation = new Reservation();

		// Get and validate campground
		Campground chosenCampground = getCampgroundFromUser(campMap);
		if (chosenCampground == null) {
			return;
		}

		// Get dates and print available sites
		Map<String, Site> siteMap = getDatesAndDisplayOptions(reservation, chosenCampground);
		if (siteMap == null) {
			return;
		}

		// Get and validate site from user
		Site chosenSite = getSiteChoiceFromUser(siteMap);
		if (chosenSite == null) {
			return;
		}

		reservation.setSiteId(chosenSite.getSiteId());

		// Get name and make reservation
		System.out.print("What name should the reservation be made under? >>> ");
		reservation.setName(userInput.nextLine());
		reservationDAO.saveReservation(reservation);
		System.out.println("\nThe reservation has been made, and the confirmation number is "
				+ reservation.getReservationId() + ".\n");

	}

	private Map<String, Park> printAndMapParks(ArrayList<Park> parks) {
		Map<String, Park> parkToNumber = new HashMap<String, Park>();

		for (int i = 0; i < parks.size(); i++) {
			System.out.println("   " + (i + 1) + ") " + parks.get(i));
			parkToNumber.put(String.valueOf(i + 1), parks.get(i));
		}

		parkToNumber.put("Q", new Park());
		System.out.println("   Q) quit\n");

		return parkToNumber;
	}

	private Map<String, Site> printAndMapSites(Campground campground, Reservation reservation) {

		Map<String, Site> result = new HashMap<String, Site>();

		ArrayList<Site> siteOptions = siteDAO.getAllSitesByCampgroundId(campground.getCampgroundID(), reservation);

		System.out.printf("%-15s" + "%-10s" + "%-12s" + "%-12s" + "%-8s" + "%-9s" + "%s", "Campground", "Site No.",
				"Max Occup.", "Accessible?", "RV Len", "Utility", "Cost");

		for (int i = 0; i < 5 && i < siteOptions.size(); i++) {
			Site s = siteOptions.get(i);
			Campground c = campgroundDAO.getCampgroundByCampgroundId(s.getCampgroundId());
			BigDecimal dailyFee = BigDecimal.valueOf(reservation.getReservationLengthInDays())
					.multiply(c.getDailyFee());
			System.out.printf("%n%-15s" + "%-10s" + "%-12s" + "%-12s" + "%-8s" + "%-9s" + "$%.2f", c.getName(),
					s.getSiteNumber(), s.getMaxOccupancy(), s.isAccessible(), s.getMaxRVLength(), s.isUtilities(),
					dailyFee);
			result.put(String.valueOf(s.getSiteNumber()), s);
		}

		result.put("0", new Site());

		return result;
	}

	private Map<String, Site> printAndMapSites(Park park, Reservation reservation) {

		Map<String, Site> result = new HashMap<String, Site>();
		ArrayList<Campground> camps = campgroundDAO.getCampgroundsByParkId(park.getParkID());

		System.out.printf("%-33s" + "%-10s" + "%-12s" + "%-12s" + "%-8s" + "%-9s" + "%s", "Campground", "Site No.",
				"Max Occup.", "Accessible?", "RV Len", "Utility", "Cost");

		for (Campground camp : camps) {
			ArrayList<Site> siteOptions = siteDAO.getAllSitesByCampgroundId(camp.getCampgroundID(), reservation);
			for (int i = 0; i < 5 && i < siteOptions.size(); i++) {
				Site s = siteOptions.get(i);
				Campground c = campgroundDAO.getCampgroundByCampgroundId(s.getCampgroundId());
				String campgroundAbbrev = c.getName().substring(0, 3);
				BigDecimal dailyFee = BigDecimal.valueOf(reservation.getReservationLengthInDays())
						.multiply(c.getDailyFee());
				System.out.printf("%n%-33s" + "%-10s" + "%-12s" + "%-12s" + "%-8s" + "%-9s" + "$%.2f", c.getName(),
						campgroundAbbrev + s.getSiteNumber(), s.getMaxOccupancy(), s.isAccessible(), s.getMaxRVLength(),
						s.isUtilities(), dailyFee);
				result.put((campgroundAbbrev + String.valueOf(s.getSiteNumber())), s);
			}
		}
		result.put("0", new Site());

		return result;
	}
	
	private Site getSiteChoiceFromUser(Map<String, Site> siteMap) {
		Site chosenSite = null;
		boolean badInput = true;
		while (badInput) {
			System.out.print("\n\nWhich site should be reserved (enter Site No. or 0 to cancel)? >>> ");
			chosenSite = siteMap.get(userInput.nextLine().trim());
			if (chosenSite == null) {
				System.out.print("Not valid input. Enter a site number.");
			} else {
				badInput = false;
			}
		}

		if (chosenSite.getCampgroundId() == null) {
			return null;
		}

		return chosenSite;
	}

	

	private Map<String, Campground> printAndMapCampgrounds(ArrayList<Campground> campgrounds) {
		Map<String, Campground> campgroundToNumber = new HashMap<String, Campground>();

		System.out.printf("%4s" + "%-20s" + "%-11s" + "%-14s" + "%s", "", "Name", "Open", "Close", "Daily Fee");

		for (int i = 0; i < campgrounds.size(); i++) {
			Campground c = campgrounds.get(i);

			String dailyFee = String.format("%.2f", c.getDailyFee().doubleValue());
			System.out.printf("%n#%-3s" + "%-20s" + "%-11s" + "%-14s" + "$" + "%s", String.valueOf(i + 1), c.getName(),
					c.openFromMonth(), c.openToMonth(), dailyFee);
			campgroundToNumber.put(String.valueOf(i + 1), c);
		}

		campgroundToNumber.put("0", new Campground());

		return campgroundToNumber;

	}

	private Campground getCampgroundFromUser(Map<String, Campground> campMap) {
		Campground chosenCampground = null;
		boolean badInput = true;
		while (badInput) {
			System.out.print("\n\nWhich campground (enter 0 to cancel)? >>> ");
			chosenCampground = campMap.get(userInput.nextLine());
			if (chosenCampground == null) {
				System.out.println("Not valid input. Enter a campground number.");
			} else {
				badInput = false;
			}
		}

		if (chosenCampground.getCampgroundID() == null) {
			return null;
		}

		return chosenCampground;
	}

	private Map<String, Site> getDatesAndDisplayOptions(Reservation reservation, Campground chosenCampground) {
		Map<String, Site> siteMap = null;
		boolean tryingDates = true;
		while (tryingDates) {
			// Get arrival and departure dates
			reservation.setFromDate(getArrivalDate());
			reservation.setToDate(getDepartureDate());

			if (reservation.getFromDate().compareTo(reservation.getToDate()) >= 0) {
				System.out.println("Your departure date must be after your arrival date.");
			} else {

				// Display sites that match reservation
				System.out.println("\nResults Matching Your Search Criteria");
				siteMap = printAndMapSites(chosenCampground, reservation);

				if (siteMap.size() > 1) {
					tryingDates = false;
				} else {
					System.out.print(
							"No sites available for those dates. Would you like to enter alternate date range? Y/N >>> ");
					boolean badInput = true;
					while (badInput) {
						String answer = userInput.nextLine();
						switch (answer) {
						case "Y":
							badInput = false;
							break;
						case "N":
							return null;
						default:
							System.out.println("Please enter 'Y' for Yes OR 'N' for No >>> ");
							break;
						}
					}
				}
			}
		}
		return siteMap;
	}

	private Map<String, Site> getDatesAndDisplayOptions(Reservation reservation, Park park) {
		Map<String, Site> siteMap = null;
		boolean tryingDates = true;
		while (tryingDates) {
			// Get arrival and departure dates
			reservation.setFromDate(getArrivalDate());
			reservation.setToDate(getDepartureDate());

			if (reservation.getFromDate().compareTo(reservation.getToDate()) >= 0) {
				System.out.println("Your departure date must be after your arrival date.");
			} else {

				// Display sites that match reservation
				System.out.println("\nResults Matching Your Search Criteria");
				siteMap = printAndMapSites(park, reservation);

				if (siteMap.size() > 1) {
					tryingDates = false;
				} else {
					System.out.print(
							"No sites available for those dates. Would you like to enter alternate date range? Y/N >>> ");
					boolean badInput = true;
					while (badInput) {
						String answer = userInput.nextLine();
						switch (answer) {
						case "Y":
							badInput = false;
							break;
						case "N":
							return null;
						default:
							System.out.println("Please enter 'Y' for Yes OR 'N' for No >>> ");
							break;
						}
					}
				}
			}
		}
		return siteMap;
	}

	private Date getArrivalDate() { // Get and validate arrival date
		Date arrivalDate = null;

		while (arrivalDate == null) {
			System.out.print("What is the arrival date? (DD/MM/YYYY) >>> ");
			String arrivalDateInput = userInput.nextLine();
			arrivalDate = turnStringIntoDate(arrivalDateInput);
			if (arrivalDate == null) {
				System.out.println("Invalid date format.");
			}
		}

		return arrivalDate;
	}

	private Date getDepartureDate() { // Get and validate arrival date
		Date departureDate = null;

		while (departureDate == null) {
			System.out.print("What is the departure date? (DD/MM/YYYY) >>> ");
			String departureDateInput = userInput.nextLine();
			departureDate = turnStringIntoDate(departureDateInput);
			if (departureDate == null) {
				System.out.println("Invalid date format.");
			}
		}

		return departureDate;
	}

	
	@SuppressWarnings("deprecation")
	private Date turnStringIntoDate(String date) { // returns Date when given string date in DD/MM/YYYY format, or if
													// the string is not valid for that, returns Date as null;

		int day;
		int month;
		int year;

		String[] dateParts = date.split("/");

		if (dateParts.length != 3) {
			return null;
		}

		try {
			month = Integer.parseInt(dateParts[0]);
		} catch (NumberFormatException e) {
			return null;
		}
		try {
			day = Integer.parseInt(dateParts[1]);
		} catch (NumberFormatException e) {
			return null;
		}
		try {
			year = Integer.parseInt(dateParts[2]);
		} catch (NumberFormatException e) {
			return null;
		}

		String simpleDate = year + "-" + month + "-" + day;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(simpleDate.trim());
		} catch (ParseException pe) {
			return null;
		}

		return new Date((year - 1900), (month - 1), day);
	}


}
