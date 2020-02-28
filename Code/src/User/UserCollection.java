package User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Movie.MovieClass;

/**
 * This singleton class is used to hold all recorded user's information.
 */
public class UserCollection {
	private static UserCollection theInstance;
	private ArrayList<UserClass> UserIdentity =  new ArrayList<UserClass>();

	/**
	 * Constructs the record by loading objects from a text file named "UserCollection.txt"
	 */
	private UserCollection(){
		try {

			FileInputStream fi = new FileInputStream(new File("UserCollection.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			UserIdentity = (ArrayList<UserClass>) oi.readObject();
			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns this class
	 * @return this class
	 */
	public static UserCollection getInstance(){
		if (theInstance == null)
			theInstance = new UserCollection();
		return theInstance;
	}

	/**
	 * Returns the list of user information
	 * @return the list of user information
	 */
	public ArrayList<UserClass> getUser(){
		return UserIdentity;
	}
	
	public void addUser(String name, int phone, String email) {
		UserClass UserNew = new UserClass(name,phone,email);
		UserIdentity.add(UserNew);
	}

	/**
	 * Updates a corresponding user entry
	 * @param updatedUser the updated version of the user
	 * @param userIdx the index where the user data is located
	 */
	public void updateUser(UserClass updatedUser, int userIdx){
		UserIdentity.set(userIdx, updatedUser);
		//Save data to file should be here
		
	}

	/**
	 * Writes back to the text file "UserCollection.txt"
	 */
	public void saveUser() {
		try {
			new FileOutputStream("UserCollection.txt").close();
			FileOutputStream f = new FileOutputStream(new File("UserCollection.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(UserIdentity);

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
}
