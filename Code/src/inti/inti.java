package inti;
import User.UserCollection;
import User.UserInterface;
import Staff.StaffInterface;
import Ticket.HolidayList;
import Ticket.TicketPricing;

import java.io.File;
import java.util.Scanner;

import Movie.CineplexCollection;
import Movie.MovieCollection;

/**
 * This is the main class of the whole application. Upon execution, the <code>main</code> method will be invoked.
 */
public class inti {
	public static void main (String[] args) {
		/*File x = new File("CineplexCollection.txt");
		x.delete();
		CineplexCollection CC = CineplexCollection.getInstance();
		CC.saveCineplex();
		MovieCollection MC = MovieCollection.getInstance();
		MC.saveMovie();
		UserCollection UC = UserCollection.getInstance();
		UC.saveUser();*/
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--Welcome to MOBLIMA!--");
		System.out.println("Select option: ");
		System.out.println("1. Login User");
		System.out.println("2. Login Staff");
		int choice = sc.nextInt();
		while(choice< 1 || choice>2) {
			System.out.println("Wrong input, please try again.");
			System.out.println("Select option: ");
			System.out.println("1. Login User");
			System.out.println("2. Login Staff");
			choice = sc.nextInt();
		}
		if(choice == 1) {
			UserInterface UI = new UserInterface();
			UI.run();
		}else if(choice == 2){
			StaffInterface SI = new StaffInterface();
			SI.run();
		}
		CineplexCollection CC = CineplexCollection.getInstance();
		CC.saveCineplex();
		MovieCollection MC = MovieCollection.getInstance();
		MC.saveMovie();
		UserCollection UC = UserCollection.getInstance();
		UC.saveUser();
		HolidayList HL = HolidayList.getInstance();
		HL.saveAllHoliday();
		TicketPricing TP = TicketPricing.getInstance();
		TP.savePrice();
		System.out.println("See you next time.");
	}
}
