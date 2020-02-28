package Movie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import User.UserClass;

/**
 * This singleton class is used to hold all cineplex information.
 */
public class CineplexCollection {
	private static CineplexCollection theInstance = null;
	private ArrayList<Cineplex> cineplexCollection = new ArrayList<Cineplex>();

	/**
	 * Constructs the record by loading objects from a text file named "CineplexCollection.txt"
	 */
	private CineplexCollection(){
		try {

			FileInputStream fi = new FileInputStream(new File("CineplexCollection.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			cineplexCollection = (ArrayList<Cineplex>) oi.readObject();
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
	public static CineplexCollection getInstance(){
		if (theInstance == null)
			theInstance = new CineplexCollection();

		return theInstance;
	}

	/**
	 * Returns the list of cineplex information
	 * @return the list of cineplex information
	 */
	public ArrayList<Cineplex> getCineplex(){
		return cineplexCollection;
	}
	
	public void addCineplex(String n, String a) {
		Cineplex CineplexNew = new Cineplex(n,a);
		cineplexCollection.add(CineplexNew);
	}

	/**
	 * Updates a corresponding user entry
	 * @param updatedCineplex the updated version of the cineplex
	 * @param index the index where the user cineplex is located
	 */
	public void updateCineplex(int index, Cineplex updatedCineplex){
		cineplexCollection.set(index, updatedCineplex);
	}


	/**
	 * Writes back to the text file "UserCollection.txt"
	 */
	public void saveCineplex() {
		try {
			FileOutputStream f = new FileOutputStream(new File("CineplexCollection.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(cineplexCollection);

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
