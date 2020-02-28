package Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * information object of showing of a movie
 */
public class MovieShowing implements Serializable{
	private int[][] layout; 
	private String movieTitle;
	private String cinemaName;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;

	/**
	 * constructor of this class
	 * @param cinema which cinema the movie is showing at
	 * @param movie what movie is playing in this instance of show
	 * @param showDate date of the movie showing
	 * @param startTime time of the movie showing
	 */
	public MovieShowing(Cinema cinema, MovieClass movie, LocalDate showDate, LocalTime startTime){
		movieTitle = movie.getTitle();
		layout = cinema.getLayout();
		date = showDate;
		this.startTime = startTime;
		endTime =startTime.plus(movie.getMinutesDuration(), ChronoUnit.MINUTES);
		cinemaName = cinema.getName();
	}

	//Accessors
	public String getMovieTitle() {
		return movieTitle;
	}
	public int[][] getLayout(){
		return layout;
	}
	public String getCinema() {
		return cinemaName;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime(){
		return endTime;
	}
	public LocalDate getDate()  {
		return date;
	}

	/**
	 * returns the duration of the movie showing
	 * @return
	 */
	public String getTimespan(){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		return startTime.format(dateTimeFormatter) + " - " + endTime.format(dateTimeFormatter);
	}

	/**
	 * set any input seat as taken
	 * @param l which seat to be set as taken
	 */
	//Mutators
	public void setLayout(int[][] l) {
		layout =l;
	}
}
