package Movie;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * class that holds a movie's detail information
 */
public class MovieClass implements Serializable{
	private ArrayList<String> casts;
	private String title;
	private String director;
	private String synopsis;
	private MovieStatus status;
	private int sales;
	private ArrayList<Review> reviews = new ArrayList<Review>();
	private String userAge;
	private int minutesDuration;

	/**
	 * constructor of the movie class
	 * @param c movie cast
	 * @param t movie title
	 * @param d movie director
	 * @param sy movie synopsis
	 * @param st movie showing status
	 * @param uA movie age rating
	 * @param duration movie duration
	 */
	public MovieClass(ArrayList<String> c, String t, String d, String sy, MovieStatus st, String uA, int duration) {
		casts = c;
		title = t;
		director = d;
		synopsis = sy;
		status = st;
		userAge = uA;
		minutesDuration = duration;
		sales = 0;
	}

	//Accessors

	/**
	 * returns title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * returns director
	 * @return director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * returns synopsis
	 * @return synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * returns sales number
	 * @return sales number
	 */
	public int getSales() {
		return sales;
	}

	/**
	 * returns movie reviews
	 * @return movie reviews
	 */
	public ArrayList<Review> getReviews(){
		return reviews;
	}

	/**
	 * returns movie showing status
	 * @return movie showing status
	 */
	public String getStatus() {
		return status.toString();
	}

	/**
	 * returns movie showing status
	 * @return movie showing status
	 */
	public MovieStatus getStatusClass(){return status;}

	/**
	 * returns movie age rating
	 * @return movie age rating
	 */
	public String getUserAge() {
		return userAge;
	}

	/**
	 * returns movie cast
	 * @return movie cast
	 */
	public ArrayList<String> getCasts(){
		return casts;
	}

	/**
	 * returns movie play duration
	 * @return movie play duration
	 */
	public int getMinutesDuration(){return minutesDuration;}

	//Mutators
	/**
	 * Updates number of sales
	 * @param m number of sales to be added
	 */
	public void addSales(int m) {
		sales = sales+m;
	}

	/**
	 * Adds new reviews
	 * @param r review to be added
	 */
	public void addReviews(Review r) {
		reviews.add(r);
	}

	/**
	 * Updates a movie's status
	 * @param ms new movie status
	 */
	public void setMovieStatus(MovieStatus ms){status = ms;}

	/**
	 * Updates a movie's user age rating
	 * @param s new user age rating
	 */
	public void setUserAge(String s){userAge = s;}
	
}
