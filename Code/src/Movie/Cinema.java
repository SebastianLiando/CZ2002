package Movie;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class holds all information for a cinema.
 */
public class Cinema implements Serializable {
	private String name;
	private double baseprice;
	private int[][] layout;
	private CinemaType type;
	private Map<LocalDate, ArrayList<MovieShowing>> movieShowlist;

	/**
	 * Generates the initial setup of the seat. 0 means the seat is not taken while 1 means the seat is booked out.
	 */
	public void generateSeat() {
		layout[0][0] = 0;
		
		for(int i=1;i<type.getWidth();i++)
			layout[i][0] =  i; 
		
		for(int i=1;i<type.getHeight();i++)
			layout[0][i] =  i;
		
		for(int i = 1;i<type.getWidth();i++)
			for(int j=1;j<type.getHeight();j++)
				layout[i][j] = 0;
	}

	/**
	 * Constructs a cinema
	 * @param n the name of the cinema
	 * @param c the cinema quality
	 * @param bp the base price of the cinema
	 */
	public Cinema(String n, CinemaType c, double bp) {
		name = n;
		type = c;
		layout = new int[type.getWidth()][type.getHeight()];
		baseprice = bp;
		movieShowlist = new HashMap<>();
		generateSeat();
	}
	//Accessors

	/**
	 * Returns the name of the cinema
	 * @return the name of the cinema
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the list of movies playing in the cinema
	 * @return the list of movies playing in the cinema
	 */
	public Map<LocalDate, ArrayList<MovieShowing>> getMovieShowlist(){
		return movieShowlist;
	}

	/**
	 * Returns what quality the cinema has
	 * @return the cinema's quality
	 */
	public CinemaType getType() {
		return type;
	}

	/**
	 * Returns the base price for a show for this cinema
	 * @return the base price for a show for this cinema
	 */
	public double getBasePrice() {
		return baseprice;
	}

	/**
	 * Returns the empty layout of the cinema
	 * @return the empty layout of the cinema
	 */
	public int[][] getLayout(){return layout;}
	//Mutators

	/**
	 * Modifies the base price of the cinema
	 * @param d the new base price
	 */
	public void setBasePrice(double d){baseprice = d;}

	/**
	 * Modifies the cinema quality
	 * @param c the new cinema quality
	 */
	public void setCinemaType(CinemaType c){type = c;}

	/**
	 * Saves changes made to shows in the cinema
	 * @param date the date which the shows are modified
	 * @param newList the modified list of shows
	 */
	public void updateMovieShowlist(LocalDate date, ArrayList<MovieShowing> newList){
		if (movieShowlist.containsKey(date))
			movieShowlist.replace(date, newList);
		else
			movieShowlist.put(date, newList);
	}
}
