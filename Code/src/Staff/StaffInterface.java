package Staff;

import Movie.MovieCollection;
import Movie.UpdateMovieInterface;
import Ticket.HolidayList;
import Ticket.TicketPricing;

import java.io.Console;
import java.util.Scanner;

/**
 * class that handles input of staff features
 */
public class StaffInterface {
	Scanner sc = new Scanner(System.in);
	StaffLogic SL = new StaffLogic();
	private String name;
	private int password;
	AddInterface addInterface= new AddInterface();
	SystemInterface systemInterface = new SystemInterface();
	EditCinemaInterface editCinemaInterface = new EditCinemaInterface();
	UpdateMovieInterface updateMovieInterface = new UpdateMovieInterface();
	MovieCollection movieCollection = MovieCollection.getInstance();
	HolidayList holidayList = HolidayList.getInstance();
	TicketPricing ticketPricing = TicketPricing.getInstance();

	/**
	 * method to handle input of staff credentials
	 */
	public void run() {
		int checks = 0;
		System.out.println("Name: ");
		name = sc.nextLine();
		System.out.println("Pin: ");
		password = sc.nextInt();
		sc.nextLine();

		while (SL.check(name, password)) {
			System.out.println("Wrong name or password");
			System.out.println("Type exit to exit");
			System.out.println("Name: ");
			name = sc.nextLine();
			if (name.toLowerCase().compareTo("exit") == 0) {
				checks =1;
				break;
			}
			System.out.println("Pin: ");
			password = sc.nextInt();
		}
		if(checks == 1) {
			return;
		}
		start();
	}

	/**
	 * main running program of this class
	 */
	public void start(){
		System.out.println("Choose action:");
		System.out.println("1. Add Movie");
		System.out.println("2. Update Movie");
		System.out.println("3. System Setting");
		System.out.println("4. Edit Cinema");
		System.out.println("0. Exit");
		int choice;
		choice = sc.nextInt();
		sc.nextLine();
		while(choice != 0) {
			switch (choice) {
				case 1:
					addInterface.start();
					break;
				case 2:
					updateMovieInterface.start();
					break;
				case 3:
					int settingChoice;
					do {
						System.out.println("Select what to change: ");
						System.out.println("1. Change pricing");
						System.out.println("2. Modify holiday dates");
						System.out.println("0. Back");
						settingChoice = sc.nextInt();
						switch (settingChoice){
							case 0:
								start();
								break;
							case 1:
								systemInterface.startPrice(ticketPricing);
								break;
							case 2:
								systemInterface.startHoliday(holidayList);
								break;
							default:
								System.out.println("Wrong choice! Please reenter");
								break;
						}
					}while(settingChoice > 2 || settingChoice < 0);
					break;
				case 4:
					editCinemaInterface.start();
					break;
				default:
					System.out.println("Error input! Please reenter");
			}
			System.out.println("Choose action:");
			System.out.println("1. Add Movie");
			System.out.println("2. Update Movie");
			System.out.println("3. System Setting");
			System.out.println("4. Edit Cinema");
			System.out.println("0. Exit");
			choice = sc.nextInt();
			sc.nextLine();
		}
		System.out.println("Thank you for using! Exiting...");
	}
}
