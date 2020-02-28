package User;

import java.io.Serializable;
import java.util.ArrayList;
import Movie.MovieShowing;

/**
 * This class holds the information of a user.
 */
public class UserClass implements Serializable{
	
	private String name;
	private int phone;
	private String email;
	private ArrayList<HistoryClass> history;

	/**
	 * Constructs a user
	 * @param n the name of the user
	 * @param p the phone number of the user
	 * @param e the email address of the user
	 */
	public UserClass(String n, int p, String e) {
		name = n;
		phone = p;
		email = e;
		history = new ArrayList<HistoryClass>();
	}

	/**
	 * Returns the name of a user
	 * @return the name of a user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the phone number of a user
	 * @return the phone number of a user
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * Returns the email address of a user
	 * @return the email address of a user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Records a transaction to the transaction history of a user
	 * @param TID the ticket ID
	 * @param ticket the chosen show in a particular cinema on a particular date, encapsulated in <code>MovieShowing</code> class
	 * @param amount the number of tickets
	 * @param price the total price
	 * @param Seat the chosen seat positions
	 */
	public void addBooking(String TID, MovieShowing ticket,int amount, double price,ArrayList<Integer[]> Seat) {
		HistoryClass HC = new HistoryClass(TID,ticket,amount,price,Seat);
		history.add(HC);
		return;
	}

	/**
	 * Returns the list of past transactions a user has made
	 * @return the list of past transactions a user has made
	 */
	public ArrayList<HistoryClass> getBooking(){
		return history;
	}
	
}
