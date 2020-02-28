package Movie;
import Ticket.*;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

/**
 * handles main logic of movie extracting and sorting
 */
public class MovieLogic {
	MovieCollection mc = MovieCollection.getInstance();
	Scanner sc = new Scanner(System.in);
	HolidayList HL = HolidayList.getInstance();

	/***
	 * Reversely order the title of the movies in a cinema based on a numerical criteria.
	 * @param toSort This is the list of titles to be sorted
	 * @param comparator This is the "value" of the title with the corresponding index
	 * @return Returns the reversely sorted list of titles
	 */
	private ArrayList<String> reverseInsertionSort(ArrayList<String> toSort, ArrayList<Number> comparator){
		for(int i=1;i<comparator.size();i++){
			int j = i;
			//If left is smaller then swap
			while(j>=1 && comparator.get(j).doubleValue() > comparator.get(j-1).doubleValue()){
				Collections.swap(toSort, j, j-1);
				Collections.swap(comparator, j, j-1);
				j--;
			}
		}
		return toSort;
	}

	/**
	 * sort movie by title name
	 * @param shows list of showing movies
	 * @return sorted list
	 */
	public ArrayList<String> sortByName(Map<LocalDate, ArrayList<MovieShowing>> shows){
		ArrayList<String> solution = extractTitles(shows);
			
		Collections.sort(solution);
		return solution;
	}

	/**
	 * sort movie by number of sales
	 * @param shows list of showing movies
	 * @return sorted list
	 */
	public ArrayList<String> sortBySales(Map<LocalDate, ArrayList<MovieShowing>> shows){
		ArrayList<String> solution = extractTitles(shows);
		
		ArrayList<Number> salesList = new ArrayList<>();
		
		for(String title : solution) {
			try {
				salesList.add(mc.getMovieDetails(title).getSales());
			}catch(Exception e) {
				System.out.println("An error occurred");
			}
		}
		return reverseInsertionSort(solution, salesList);
	}

	/**
	 * sort movie by rating value
	 * @param shows list of showing movies
	 * @return sorted list
	 */
	public ArrayList<String> sortByRating(Map<LocalDate, ArrayList<MovieShowing>> shows){
		ArrayList<String> solution = extractTitles(shows);
		ArrayList<Number> ratingList = new ArrayList<>();
		
		for(String title : solution) {
			try {
				ratingList.add(avgRating(mc.getMovieDetails(title).getReviews()));
			}catch(Exception e) {
				System.out.println(e);
			}
		}

		return reverseInsertionSort(solution, ratingList);
	}

	/**
	 * counts average rating of a movie
	 * @param reviews all reviews of a movie
	 * @return average rating
	 */
	private Double avgRating(ArrayList<Review> reviews) {
		Double total = 0.0;
		for(Review r : reviews) {
			total += r.getRating();
		}
		return total/reviews.size();
	}

	/**
	 * extract all titles of showing title of the list
	 * @param shows list of shows currently showing
	 * @return titles of the shows
	 */
	private ArrayList<String> extractTitles(Map<LocalDate, ArrayList<MovieShowing>> shows){
		ArrayList<String> solution = new ArrayList<String>();
		for(ArrayList<MovieShowing> showList : shows.values()) 
			for(MovieShowing s : showList) 
				if (!solution.contains(s.getMovieTitle()))
					solution.add(s.getMovieTitle());
		return solution;
	}

	/**
	 * get detail of a given movie
	 * @param title title of details to be queried
	 * @return the details fo the movie
	 * @throws Exception if the input movie is not found
	 */
	public MovieClass getMovieDetails(String title) throws Exception {
		return mc.getMovieDetails(title);
	}

	/**
	 * get list of all movies available
	 * @return array of movies
	 */
	public ArrayList<MovieClass> getAllMovie() {
		return mc.getMovies();
	}

	/**
	 * applies all price modifiers to the base price
	 * @param UserAge user age class of ticket
	 * @param Type type of the ticket
	 * @param BasePrice base price of the ticket
	 * @param date date of ticket bought
	 * @return actual price that will be paid by the user
	 */
	public double countPrice(String UserAge, String Type, double BasePrice, LocalDate date ) {
		double UA = 1;
		double types =1;
		double day = 1;
		double GST = 0.9;

		TicketPricing TP = TicketPricing.getInstance();
		for(UserAge age : TP.getUserAge()) {
			if(age.getName().toLowerCase().compareTo(UserAge.toLowerCase()) == 0) {
				UA = age.getPrice();
				break;
			}
		}
		for(Types type : TP.getType()) {
			if(type.getName().toLowerCase().compareTo(Type.toLowerCase()) == 0) {
				types = type.getPrice();
				break;
			}
		}
		for(Day d : TP.getDay()) {
			if(HL.getAllHoliday().contains(date)) {
				if(d.getName().toLowerCase().compareTo("holiday") == 0) {
					day = d.getPrice();
				}
			}
			else if(date.getDayOfWeek() == DayOfWeek.SATURDAY|| date.getDayOfWeek() == DayOfWeek.SUNDAY ) {
				if(d.getName().toLowerCase().compareTo("weekend") == 0) {
					day = d.getPrice();
				}
			}else {
				if(d.getName().toLowerCase().compareTo("weekday") == 0) {
					day = d.getPrice();
				}
			}
		}
		return BasePrice*UA*types*day*GST;
	}
}
