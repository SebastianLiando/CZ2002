package User;

import java.util.ArrayList;

import Movie.MovieShowing;

/**
 * This class provides the required actions used in <code>UserInterface</code> class.
 */
public class UserLogic {
	UserCollection UC = UserCollection.getInstance();

	/**
	 * Provides the correct user from a collection of users according to the given parameters
	 * @param name the name of the user
	 * @param phone the phone number of the user
	 * @param email the email address of the user
	 * @return the corresponding <code>UserClass</code> object
	 * @throws Exception if the user with the given details does not exist
	 */
	public UserClass getUser(String name, int phone, String email) throws Exception {
		for(UserClass u:UC.getUser()) {
			if(u.getName().toLowerCase().compareTo(name.toLowerCase()) == 0  && u.getPhone() == phone && u.getEmail().compareTo(email) == 0) {
				return u;
			}
		}
		throw new Exception();
	}

	/**
	 * Stores a new user information to the collection of users
	 * @param name the new user's name
	 * @param phone the new user's phone number
	 * @param email the new user's email address
	 * @return the new user object
	 */
	public UserClass addUser(String name, int phone, String email) {
		UserClass UCL = new UserClass(name, phone, email);
		UC.addUser(name,phone,email);
		return UCL;
	}

	/**
	 * Appends a purchase history to the user's transaction record
	 * @param uc the user that purchases
	 * @param TID the ticket ID
	 * @param ticket the chosen show in a particular cinema on a particular date, encapsulated in <code>MovieShowing</code> class
	 * @param amount the number of tickets bought
	 * @param price the total price
	 * @param Seat the chosen seat positions
	 */
	public void addBooking(UserClass uc, String TID, MovieShowing ticket,int amount, double price,ArrayList<Integer[]> Seat) {
		ArrayList<UserClass> listOfUser = new ArrayList<UserClass>();
		listOfUser = UC.getUser();
		int i =0;
		for(UserClass u: listOfUser) {
			if(u.getName().compareTo(uc.getName()) == 0 && u.getEmail().compareTo(uc.getEmail())== 0 && u.getPhone() == uc.getPhone()) {
				u.addBooking(TID, ticket,amount, price,Seat);
				UC.updateUser(u, i);
			}
			i++;
		}
	}

	/**
	 * Displays the user's purchase history if any
	 * @param uc the user having the record
	 */
	public void showBooking(UserClass uc) {
		/*ArrayList<HistoryClass> AllBook = new ArrayList<HistoryClass>();
		System.out.println(uc.getBooking());
		AllBook = uc.getBooking();
		System.out.println(AllBook);
		for(HistoryClass h : AllBook) {
			h.printAll();
		}*/
		int count=0;
		ArrayList<UserClass> listOfUser = new ArrayList<UserClass>();
		listOfUser = UC.getUser();
		for(UserClass u: listOfUser) {
			if(u.getName().toLowerCase().compareTo(uc.getName().toLowerCase()) == 0 && u.getEmail().compareTo(uc.getEmail())== 0 && u.getPhone() == uc.getPhone()) {
				
				ArrayList<HistoryClass> AllBook = new ArrayList<HistoryClass>();
				AllBook = u.getBooking();
				for(HistoryClass h : AllBook) {
					count++;
					h.printAll();
				}
			}
		}
		if(count==0) {
			System.out.println("No History Yet");
		}
	}
}
