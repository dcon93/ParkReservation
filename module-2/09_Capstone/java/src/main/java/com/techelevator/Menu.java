package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;
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
	
<<<<<<< HEAD
	public Park selectAPark() {} // displays parks to user and returns their choice of park; returns null if they choose 'Q'
	
	
	
	public int parkInfo(Park chosenPark) {
		String choice = userInput.nextLine();
		int numberChoice = Integer.parseInt(choice);
		
		while (true)
		{
			System.out.printf("%-16s %s%n", "Park Name:", chosenPark.getName());
			System.out.printf("%-16s %s%n", "Location:", chosenPark.getLocation());
			System.out.printf("%-16s %s%n","Established:", chosenPark.getDateEstablished());
			System.out.printf("%-16s %,d sq km%n", "Area:", chosenPark.getArea());
			System.out.printf("%-16s %,d%n", "Annual Visitors:", chosenPark.getVisitors());
			System.out.println();
			System.out.println(chosenPark.getDescriptionOfPark());

			
			if (numberChoice == 3) {
				break;
			} else if (numberChoice == 1){
				
				parkCampgrounds(chosenPark);
			
			}  else if (numberChoice == 2){
				
				makeReservationByPark(chosenPark);
			} 
			}
		return numberChoice;
		}
		
		 // Displays park info and menu options 1-3. Returns user's menu choice (1-3);
	
	
	
	
=======
	public Park selectAPark() { // displays parks to user and returns their choice of park; returns null if they choose 'Q'
		Park choice = null;
		
		Map<String, Park> parksMap = printAndMapParks(parkDAO.getAllParks());
		
		boolean badInput = true;
		while (badInput) {
			System.out.print("Select a park >>> ");
			Park chosenPark = parksMap.get(userInput.nextLine());
			if(chosenPark != null) {
				if(chosenPark.getParkID() != null) {
					choice = chosenPark;
				}
				badInput = false;
			} else {
				System.out.println("Not valid input. Please enter a number to choose a park or 'Q' to quit.");
			}
		
		}
		
		return choice;
	}
	
	private Map<String, Park> printAndMapParks(ArrayList<Park> parks){
		Map<String, Park> parkToNumber = new HashMap<String, Park>();
		
		System.out.println("Select a Park for Further Details");

		for (int i = 0; i < parks.size(); i++) {
			System.out.println("   " + (i+1) + ") " + parks.get(i));
			parkToNumber.put(String.valueOf(i+1), parks.get(i));
		}
		
		parkToNumber.put("Q", new Park());		
		System.out.println("   Q) quit\n");
		
		return parkToNumber;
	}

	

	public int parkInfo(Park chosenPark) {} // Displays park info and menu options 1-3. Returns user's menu choice (1-3);
>>>>>>> 8f499ec9b4041317e6f134871a3ed3898436ec5a
	public boolean parkCampgrounds(Park chosenPark) {} // Displays  campgrounds for the given park; returns true if the user wants to make a reservation, false if they want to go to prev screen.
	
	
	
	public void makeReservationByCampground(Park chosenPark) {} // Displays  campgrounds and lets user make reservation and stuff.
	
	
	
	
	public void makeReservationByPark(Park chosenPark) {}// Displays  campgrounds and lets user make reservation and stuff.

}
