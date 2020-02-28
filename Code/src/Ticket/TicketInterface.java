package Ticket;
import User.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Movie.*;

/**
 * This class receives input from user for booking new tickets and showing ticket booking history.
 */
public class TicketInterface {
	UserClass UC;
	UserLogic UL;
	MovieShowing Ticket;
	Scanner sc = new Scanner(System.in);

	/**
	 * The running function of this class
	 * @param uc the user using the application
	 * @param CinemaCode part of Ticket ID to refer to the cinema where the booking is made
	 * @param ticket the ticket currently being bought by the user
	 * @param amount amount of ticket bought by the user
	 * @param price total price of ticket bought by the user
	 * @param Seat array of seat chosen by the user
	 */
	public void start(UserClass uc, String CinemaCode, MovieShowing ticket,int amount, double price, ArrayList<Integer[]> Seat) {
		LocalDateTime NowTime = LocalDateTime.now();
		UC = uc;
		String TID = CinemaCode + NowTime.getYear() +""+ NowTime.getMonthValue()+""+ NowTime.getDayOfMonth()+""+NowTime.format(DateTimeFormatter.ofPattern("HH"))+""+NowTime.format(DateTimeFormatter.ofPattern("mm"));
		System.out.println("TID   : "+TID);
		System.out.println("Title : " + ticket.getMovieTitle());
		System.out.println("Cinema: " + ticket.getCinema());
		System.out.println("Date  : "+ ticket.getDate().toString());
		String timespan = ticket.getStartTime().format(DateTimeFormatter.ofPattern("hh:mm"))
				+ " - " + ticket.getEndTime().format(DateTimeFormatter.ofPattern("hh:mm"));
		System.out.println("Time  : "+ timespan);
		System.out.println("Amount: "+ amount);
		System.out.println("Price : $S"+ price);
		UL = new UserLogic();
		UL.addBooking( uc,  TID,  ticket, amount,  price,Seat);
		MovieCollection MC = MovieCollection.getInstance();
		for(MovieClass m :MC.getMovies()) {
			if(m.getTitle().compareTo(ticket.getMovieTitle())==0) {
				m.addSales(amount);
			}
		}
		System.out.println("=====================");
		System.out.println("1. Book again");
		System.out.println("2. Show Booking History");
		System.out.println("3. Exit");
		int choice = sc.nextInt();
		while(choice <1 || choice > 3) {
			System.out.println("Wrong input");
			System.out.println("1. Book again");
			System.out.println("2. Show Booking History");
			System.out.println("3. Exit");
			choice = sc.nextInt();
		}
		switch(choice) {
			case 1:
				break;
			case 2:
				UL.showBooking(uc);
				System.out.println("1. Homepage");
				System.out.println("2. Exit");
				int choice1 = sc.nextInt();
				while(choice1 <1 || choice1>2) {
					System.out.println("Wrong Input");
					System.out.println("1. Homepage");
					System.out.println("2. Exit");
					choice1 = sc.nextInt();
				}
				if(choice1==1) {
					break;
				}else if (choice1 == 2) {
					System.exit(0);
				}
				break;
			case 3:
				System.exit(0);
				break;
		}
	}
	
}
