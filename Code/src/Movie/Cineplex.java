package Movie;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class holds information of a cineplex
 */
public class Cineplex implements Serializable {
	private String name;
	private String address;
	private ArrayList<Cinema> cinemas = new ArrayList<Cinema>();

	/**
	 * Constructs a cineplex
	 * @param n the name of the cineplex
	 * @param a the address of the cineplex
	 */
	public Cineplex(String n, String a) {
		name = n;
		address = a;
		CinemaType cs = new CinemaSilver();
		Cinema ca = new Cinema("Cinema1",cs,12);
		cinemas.add(ca);
	}
	/**
	 * Returns the name of a cineplex
	 * @return the name of a cineplex
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the cinemas in the cineplex
	 * @return the cinemas in the cineplex
	 */
	public ArrayList<Cinema> getCinemas(){
		return cinemas;
	}

	/**
	 * Adds a new cinema into the cineplex
	 * @param c the new cinema object
	 */
	public void addCinema(Cinema c) {
		cinemas.add(c);
		System.out.println(cinemas);
	}

	/**
	 * Updates information of a cinema in the cineplex
	 * @param updateCinema the updated cinema
	 * @param index index of cinema to be updated
	 */
	public void updateCinema (Cinema updateCinema, int index){
		cinemas.set(index, updateCinema);
	}
}
