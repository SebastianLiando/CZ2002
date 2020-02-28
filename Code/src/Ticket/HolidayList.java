package Ticket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import User.UserClass;
import User.UserCollection;

/**
 * loads and saves dates of holiday to and from txt file
 */
public class HolidayList {
	public ArrayList<Holiday> AllHoliday = new ArrayList<Holiday>();
	private static HolidayList theInstance;

	/**
	 * loads dates from txt file
	 */
	private HolidayList() {
		try {
			FileInputStream fi = new FileInputStream(new File("Holiday.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			AllHoliday = (ArrayList<Holiday>) oi.readObject();
			oi.close();
			fi.close();

		} catch (FileNotFoundException e) {
			System.out.println(e);
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns the created instance of HolidayList object
	 * @return the HolidayList instance
	 */
	public static HolidayList getInstance() {
		if (theInstance == null)
			theInstance = new HolidayList();
		return theInstance;
	}

	/**
	 * returns array of all holiday dates
	 * @return array containing all holiday dates
	 */
	public ArrayList<LocalDate> getAllHoliday(){
		ArrayList<LocalDate> HolidayDate = new ArrayList<LocalDate>();
		for(Holiday H : AllHoliday) {
			HolidayDate.add(LocalDate.of(2019, H.getMonth(), H.getDate()));
		}
		return HolidayDate;
	}

	/**
	 * returns raw object array of holidays
	 * @return array of holiday objects
	 */
	public ArrayList<Holiday> getAllHolidayRaw(){
		return AllHoliday;
	}

	/**
	 * adds new holiday into the holiday list
	 * @param h new holiday object to be inserted
	 */
	public void addHoliday(Holiday h){
		AllHoliday.add(h);
	}

	/**
	 * saves current holiday list into txt file
	 */
	public void saveAllHoliday() {
		try {
			new FileOutputStream("Holiday.txt").close();
			FileOutputStream f = new FileOutputStream(new File("Holiday.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(AllHoliday);

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
}
