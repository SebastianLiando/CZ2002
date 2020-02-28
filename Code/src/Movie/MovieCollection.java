package Movie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import User.UserClass;

/**
 * This singleton class is used to hold all movie information.
 */
public class MovieCollection {
	private static MovieCollection theInstance = null;
	private ArrayList<MovieClass> movieCollection = new ArrayList<>();

	/**
	 * Constructs the record by loading objects from a text file named "MovieCollection.txt"
	 */
	private MovieCollection(){
		try {

			FileInputStream fi = new FileInputStream(new File("MovieCollection.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			movieCollection = (ArrayList<MovieClass>) oi.readObject();
			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns this class
	 * @return this class
	 */
	public static MovieCollection getInstance(){
		if (theInstance == null)
			theInstance = new MovieCollection();
		return  theInstance;
	}

	/**
	 * Returns the list of movie information
	 * @return the list of movie information
	 */
	public ArrayList<MovieClass> getMovies(){
		return movieCollection;
	}

	/**
	 * returns detail of searched movie
	 * @param title title of searched movie
	 * @return details of searched movie
	 * @throws Exception if searched movie not in index
	 */
	public MovieClass getMovieDetails(String title) throws Exception {
		for(MovieClass m : movieCollection) {
			if (title.toLowerCase().compareTo(m.getTitle().toLowerCase())== 0) {
				return m;
			}
		}
		throw new Exception();
	}

	/**
	 * adds new movie to the list
	 * @param c movie cast
	 * @param t movie title
	 * @param d movie director
	 * @param sy movie synopsis
	 * @param st movie status
	 * @param uA movie user age rating
	 * @param duration movie duration
	 */
	public void addMovie(ArrayList<String> c, String t, String d, String sy, MovieStatus st, String uA, int duration) {
		MovieClass MovieNew = new MovieClass(c, t, d, sy, st, uA, duration);
		movieCollection.add(MovieNew);
		
	}

	/**
	 * updates movie
	 * @param newMovieClass new object of the movie
	 * @param index index of movie to be updated
	 */
	public void updateMovieCollection(MovieClass newMovieClass, int index){
		movieCollection.set(index, newMovieClass);
		try {
			FileOutputStream f = new FileOutputStream(new File("MovieCollection.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(movieCollection);

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} 
	}

	/**
	 * saves current movie list into txt file
	 */
	public void saveMovie() {
		try {
			new FileOutputStream("MovieCollection.txt").close();
			FileOutputStream f = new FileOutputStream(new File("MovieCollection.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(movieCollection);

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("Error initializing stream");
		}
	}
}
