package Movie;
import User.*;
import Ticket.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import User.UserInterface;

/**
 * handles the input of movie query, based on cineplex, cinema, and by various sorts.
 */
public class MovieInterface {
	Scanner sc = new Scanner(System.in);
	CineplexCollection cc = CineplexCollection.getInstance();
	Map<LocalDate, ArrayList<MovieShowing>> shows;
	LocalDate chosenDate;
	MovieLogic movieLogic = new MovieLogic();
	Cineplex chosenCineplex;
	Cinema chosenCinema;
	UserClass uc;
	int choice;

	/**
	 * handles which cineplex to be searched
	 * @param uc user currently logged in
	 */
	public void start(UserClass uc) {
		this.uc = uc;
		int tmp = 0;
		System.out.println("Select cineplex: ");
		for(Cineplex cineplex : cc.getCineplex()) {
			System.out.println((++tmp) + ". " + cineplex.getName());
		}
		System.out.println("0. Back");
		choice = sc.nextInt();
		if (choice == 0) {
			return;
		}
		else {
			chosenCineplex = cc.getCineplex().get(choice - 1);
			displayCinemas(chosenCineplex);
		}
	}

	/**
	 * display list of cinemas in the selected cineplex
	 * @param c selected cineplex
	 */
	private void displayCinemas(Cineplex c) {
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		int tmp = 0;
		
		cinemas = c.getCinemas();
		System.out.println("Select cinema: ");
		for(Cinema cinema : cinemas) {
			System.out.println((++tmp) + ". " + cinema.getName() + " (" + cinema.getType().toString() + ")");
		}
		System.out.println("0. Back");
		choice = sc.nextInt();
		while(choice < 0 || choice > tmp) {
			System.out.println("Invalid Option! Reenter: ");
			choice = sc.nextInt();
		}
		if (choice == 0) 
			return;
		else {
			chosenCinema = cinemas.get(choice - 1);
			displayMovies(chosenCinema);
		}
	}

	/**
	 * display all movies in the selected cinema
	 * @param cinema selected cinema
	 */
	private void displayMovies(Cinema cinema) {
		System.out.println("Choose display mode: ");
		System.out.println("1. By name");
		System.out.println("2. By sales");
		System.out.println("3. By rating");
		System.out.println("0. Back to menu");
		choice = sc.nextInt();
		while(choice < 0 || choice > 3) {
			System.out.println("Invalid Option! Reenter: ");
			choice = sc.nextInt();
		}
		
		Map<LocalDate, ArrayList<MovieShowing>> shows = cinema.getMovieShowlist();
		ArrayList<String> theMovie = new ArrayList<String>();
		
		switch(choice) {
		case 1:
			theMovie = movieLogic.sortByName(shows);
			break;
		case 2:
			theMovie = movieLogic.sortBySales(shows);
			break;
		case 3:
			theMovie = movieLogic.sortByRating(shows);
			break;
		case 0:
			return;
		}
		
		int tmp = 0;
		System.out.println("Select movie: ");
		if (theMovie.isEmpty())
			System.out.println("No movies added yet. Please stay tuned!");
		else
			for(String s : theMovie) {
				System.out.println((++tmp) + ". " +s);
			}
		System.out.println("0. Back");
		choice = sc.nextInt();
		while(choice < 0 || choice > tmp) {
			System.out.println("Invalid Option! Reenter: ");
			choice = sc.nextInt();
		}
		if (choice == 0) 
			return;
		else {
			MovieClass chosenMovie;
			try{
				chosenMovie = movieLogic.getMovieDetails(theMovie.get(choice-1));
				displayDetails(chosenMovie);
			}catch(Exception e) {
				System.out.println("An error occurred.");
			}
		}
	}

	/**
	 * display details of selected movie
	 * @param movie selected movie
	 */
	private void displayDetails(MovieClass movie) {
		System.out.println("Title   : " + movie.getTitle());
		System.out.println("Director: " + movie.getDirector());
		System.out.println("Casts   : ");
		for (String cast : movie.getCasts())
			System.out.println("  " + cast);
		System.out.println("Status  : " + movie.getStatus());
		System.out.println("User age: " + movie.getUserAge());
		System.out.println("Sales   : " + movie.getSales());
		System.out.println("Synopsis:\n" + movie.getSynopsis());
		System.out.println("------------Reviews-------------");
		ArrayList<Review> pastReviews = movie.getReviews();
		if (pastReviews.size() < 2){
			System.out.println("Not available yet! Currently there is " + pastReviews.size() + " review, it may not be accurate.");
		}else {
			float overallRating = 0;
			for (Review r : movie.getReviews()) {
				System.out.println(r.getReviewerName());
				System.out.printf("%.1f/10\n", r.getRating());
				System.out.println(r.getContent() + "\n");
				overallRating += r.getRating();
			}
			System.out.printf("---------Overall(%.1f/10)---------\n", overallRating/pastReviews.size());
		}
		System.out.println("Choose option: ");
		System.out.println("1. Book ");
		System.out.println("2. Give review");
		System.out.println("0. Back");
		choice = sc.nextInt();
		while(choice < 0 || choice > 2) {
			System.out.println("Invalid Option! Reenter: ");
			choice = sc.nextInt();
		}
		if (choice == 0)
			return;
		else if (choice == 1) {
			shows = chosenCinema.getMovieShowlist();
			int count = 0;
			ArrayList<LocalDate> DateList = new ArrayList<LocalDate>();
			for(Map.Entry<LocalDate, ArrayList<MovieShowing>> MovieMap : shows.entrySet()) {
				for(MovieShowing mm : MovieMap.getValue()) {
					if(mm.getMovieTitle().toLowerCase().compareTo(movie.getTitle().toLowerCase())== 0) {
						if(!DateList.contains(MovieMap.getKey())) {
						DateList.add(MovieMap.getKey());
						System.out.println((count+1) + " "+ MovieMap.getKey());
						count++;
						}
					}
				}
			}
			System.out.println("Choose Date:");
			int choice = sc.nextInt();
			chosenDate = DateList.get(choice-1);
			System.out.println(chosenDate);
			ArrayList<Integer> index = new ArrayList<Integer>();
			ArrayList<Double> pricesss = new ArrayList<Double>();
			count=1;
			int count1 = -1;
			
			for(Map.Entry<LocalDate, ArrayList<MovieShowing>> MovieMap : shows.entrySet()) {
				if(MovieMap.getKey() == chosenDate) {
					for(MovieShowing s : MovieMap.getValue()) {
						count1++;
						if(s.getMovieTitle().toLowerCase().compareTo(movie.getTitle().toLowerCase()) ==0 ) {
							index.add(count1);
							for(MovieClass c : movieLogic.getAllMovie()) {
								if(c.getTitle().toLowerCase().compareTo(movie.getTitle().toLowerCase()) == 0) {
									double price = movieLogic.countPrice(c.getUserAge(),chosenCinema.getType().toString(),chosenCinema.getBasePrice(),MovieMap.getKey());
									pricesss.add(price);
									System.out.println(count+". "+movie.getTitle() + " " + MovieMap.getKey()+ " "+s.getStartTime()+"-"+s.getEndTime()+" "+ c.getUserAge() + "  $S" + price);
									count++;
								}
							}
						}
					}
				}
			}
			int choice1;
			do {
				System.out.println("Choose which ticket");
				choice1 = sc.nextInt();
				if (choice1 < 1 || choice1> index.size())
					System.out.println("Error choice!");
			}while (choice1 < 1 || choice1 > index.size());
			MovieShowing chosenTicket = shows.get(chosenDate).get(index.get(choice1-1));
			System.out.println("Amount of ticket: ");
			int amount = sc.nextInt();
			selectLayout(amount,pricesss.get(choice1-1),chosenTicket);
		}else {
			Review rev = new Review();
			rev.setName(uc.getName());

			System.out.println("Enter your rating (1-10) : ");
			Double rating = sc.nextDouble();
			while (rating < 0 || rating > 10){
				System.out.println("Rating is out of bound!");
				System.out.println("Enter your rating (1-10) : ");
				rating = sc.nextDouble();
			}
			sc.nextLine();
			rev.setRating(rating);

			System.out.println("Enter your review : ");
			String content = sc.nextLine();
			rev.setContent(content);

			movie.addReviews(rev);
		}
	}

	/**
	 * handles seat selection of ticket buying
	 * @param amount amount of seats
	 * @param price price of seatss
	 * @param ticket ticket instance being bought
	 */
	private void selectLayout(int amount, double price, MovieShowing ticket) {
		int[][] copyLayout = ticket.getLayout();
		ArrayList<Integer[]> chosenSeat = new ArrayList<>();
		int height = copyLayout.length;
		int width = copyLayout[0].length;
		System.out.println(width + " " + height);
		for(int i = 0; i< height; i++) {
			for(int j = 0;j<width;j++) {
				if(j==5 || j == width-4) {
					System.out.printf("    %-2d ", copyLayout[i][j]);
				}else {
					System.out.printf("%-2d ",copyLayout[i][j]);
				}
			}
			System.out.println();
		}
		for(int i = 0; i<amount;i++) {
			System.out.println("Insert column of the seat");
			int column = sc.nextInt();
			System.out.println("Insert row of the seat");
			int row = sc.nextInt();
			while(column > width+1 || column < 1 || row > height+1 || row < 1) {
				System.out.println("Wrong input");
				System.out.println("Insert column of the seat");
				column = sc.nextInt();
				System.out.println("Insert row of the seat");
				row = sc.nextInt();
			}
			while(copyLayout[row][column] == 1) {
				System.out.println("The slot you entered is occupied!");
				System.out.println("Insert column of the seat");
				column = sc.nextInt();
				System.out.println("Insert row of the seat");
				row = sc.nextInt();
			}
			Integer [] seat = new Integer[2];
			seat[0] = column;
			seat[1] = row;
			chosenSeat.add(seat);
			copyLayout[row][column] = 1;
		}
		price *= amount;
		System.out.println("Title : " + ticket.getMovieTitle());
		System.out.println("Cinema: " + ticket.getCinema());
		System.out.println("Date  : "+ ticket.getDate().toString());
		System.out.println("Time  : "+ ticket.getTimespan());
		System.out.println("Amount: "+ amount);
		System.out.println("Price : "+ price);
		System.out.println();
		System.out.println("1. Purchase");
		System.out.println("0. Cancel");
		choice = sc.nextInt();
		while(choice<0 || choice> 1) {
			System.out.println("Wrong Input");
			System.out.println("1. Purchase");
			System.out.println("0. Cancel");
			choice = sc.nextInt();
		}
		if(choice == 1) {
			ticket.setLayout(copyLayout);
			String CinemaCode = "CCD";
			TicketInterface TI = new TicketInterface();
			System.out.println(ticket.getMovieTitle());
			TI.start(uc, CinemaCode, ticket,amount,price,chosenSeat);
		}else if(choice == 0) {
			return;
		}
	}
}
