package User;
import java.util.Scanner;
import Movie.MovieInterface;

/**
 * This class is used to interact with the user if login as a user is chosen.
 */
public class UserInterface {
	private Scanner sc = new Scanner(System.in);
	private UserLogic UL = new UserLogic();
	private UserClass UC;
	private MovieInterface MI = new MovieInterface();

	public int choice;
	private String name;
	private int phone;
	private String email;

	/**
	 * Asks the user to input name, phone, and email address
	 */
	public void run() {
		System.out.println("Please fill the form below ");
		System.out.println("Name : ");
		name = sc.nextLine();
		System.out.println("Phone: ");
		phone = sc.nextInt();
		System.out.println("Email: ");
		sc.nextLine();
		email = sc.nextLine();

		try {
			UC = UL.getUser(name, phone, email);
		}catch(Exception e) {
			UC = UL.addUser(name, phone, email);
		}
		this.start(UC);
	}

	/**
	 * The main menu of the application
	 * @param UC the user using the application
	 */
	public void start(UserClass UC) {
		System.out.println("Menu");
		System.out.println("1. Cineplex List");
		System.out.println("2. Booking History");
		System.out.println("3. Switch User");
		System.out.println("4. Exit");
		choice = sc.nextInt();
		while(choice != 4) {
			switch(choice) {
				case 1:
					MI.start(UC);
					break;
				case 2:
					name = UC.getName();
					phone = UC.getPhone();
					System.out.println("Name: " + name);
					System.out.println("Phone: " +phone);
					UL.showBooking(UC);
					break;
				case 3:
					this.run();
					break;
				default:
					System.out.println("Wrong input, try again");
					this.start(UC);
					break;
			}
			System.out.println("Menu");
			System.out.println("1. Cineplex List");
			System.out.println("2. Booking History");
			System.out.println("3. Switch User");
			System.out.println("4. Exit");
			choice = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("Thank you for using our service");
		System.out.println("System terminate......");
	}
}
