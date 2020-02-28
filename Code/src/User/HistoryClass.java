package User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Movie.MovieShowing;

/**
 * This class stores a single past transaction that a certain user has made. A user may have none to multiple past transactions.
 */
public class HistoryClass implements Serializable{
	private String TIDs;
	private String CinemaName;
	private String MovieTitle;
	private LocalDate date;
	private double price;
	private int amount;
	private ArrayList<Integer[]> SeatList;

	/**
	 * Constructs a new transaction
	 * @param TID the ticket ID
	 * @param ticket the chosen show in a particular cinema on a particular date, encapsulated in <code>MovieShowing</code> class
	 * @param amount the number of tickets bought
	 * @param price the total price
	 * @param Seat the chosen seat positions
	 */
	public HistoryClass(String TID, MovieShowing ticket,int amount, double price,ArrayList<Integer[]> Seat) {
		this.TIDs = TID;
		CinemaName = ticket.getCinema();
		this.price = price;
		this.amount = amount;
		SeatList = Seat;
		date = ticket.getDate();
		MovieTitle = ticket.getMovieTitle();
	}

	/**
	 * Displays all information about the transaction. The following is an example of the output:
	 *
	 * TID: CCD201903031200
	 * Cinema name: XXI
	 * Movie Title: Avengers
	 * Date: 2019-03-03
	 * Price: 20.9
	 * Amount: 3
	 * Seat:
	 * 1,1
	 * 1,2
	 * 1,3
	 */
	public void printAll() {
		System.out.println("TID: "+ TIDs);
		System.out.println("Cinema name: "+ CinemaName);
		System.out.println("Movie Title: "+ MovieTitle);
		System.out.println("Date: "+ date);
		System.out.println("Price: "+ price);
		System.out.println("Amount: " + amount);
		System.out.println("Seat: ");
		for(Integer[] s : SeatList) {
			System.out.println(s[0] + "," + s[1]);
		}
		System.out.println();
		System.out.println();
		
	}
}
