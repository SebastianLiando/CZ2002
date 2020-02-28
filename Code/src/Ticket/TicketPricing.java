package Ticket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import User.UserCollection;

/**
 * This class loads and saves pricing data from txt file
 */

public class TicketPricing {
	ArrayList<UserAge> UserAgePrice = new ArrayList<UserAge>();
	ArrayList<Types> TypePrice= new ArrayList<Types>();
	ArrayList<Day> DayPrice =  new ArrayList<Day>();
	private static TicketPricing theInstance;

	/**
	 * loads pricing info from txt file
	 */
	private TicketPricing() {
		try {
			FileInputStream fi = new FileInputStream(new File("UserAgePrice.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			UserAgePrice = (ArrayList<UserAge>) oi.readObject();
			oi.close();
			fi.close();
			
			FileInputStream fi1 = new FileInputStream(new File("TypePrice.txt"));
			ObjectInputStream oi1 = new ObjectInputStream(fi1);
			TypePrice = (ArrayList<Types>) oi1.readObject();
			oi1.close();
			fi1.close();
			
			FileInputStream fi2 = new FileInputStream(new File("DayPrice.txt"));
			ObjectInputStream oi2 = new ObjectInputStream(fi2);
			DayPrice = (ArrayList<Day>) oi2.readObject();
			oi2.close();
			fi2.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns the created instance of TicketPricing
	 * @return TicketPricing object
	 */
	public static TicketPricing getInstance(){
		if (theInstance == null)
			theInstance = new TicketPricing();
		return theInstance;
	}

	/**
	 * create new instances of price modifiers
	 */

	public void load() {
		UserAge adult = new AdultPrice();
		UserAge child = new ChildPrice();
		UserAge student = new StudentPrice();
		Types silver= new SilverPrice();
		Types gold = new GoldPrice();
		Types platinum = new PlatinumPrice();
		Day weekday = new WeekdayPrice();
		Day weekend = new WeekendPrice();
		Day holiday = new HolidayPrice();
		UserAgePrice.add(adult);
		UserAgePrice.add(child);
		UserAgePrice.add(student);
		TypePrice.add(silver);
		TypePrice.add(gold);
		TypePrice.add(platinum);
		DayPrice.add(weekday);
		DayPrice.add(weekend);
		DayPrice.add(holiday);
	}

	/**
	 * @return array of User Age modifier objects
	 */
	public ArrayList<UserAge> getUserAge(){
		return UserAgePrice;
	}
	/**
	 * @return array of Type modifier objects
	 */
	public ArrayList<Types> getType(){
		return TypePrice;
	}
	/**
	 * @return array of Day modifier objects
	 */
	public ArrayList<Day> getDay(){
		return DayPrice;
	}

	/**
	 * sets new values of User Age modifier
	 * @param list new values of modifier
	 */
	public void setUserAge(ArrayList<UserAge> list){
		 UserAgePrice = list;
	}

	/**
	 * sets new values of Type modifier
	 * @param list new values of modifier
	 */
	public void setType(ArrayList<Types> list){
		TypePrice = list;
	}

	/**
	 * sets new values of Day modifier
	 * @param list new values of modifier
	 */
	public void setDay(ArrayList<Day> list){
		DayPrice = list;
	}

	/**
	 * sets new values of price modifiers
	 * @param ip array of new values to be inserted
	 * @param option which kind of modifier to be iserted into
	 */
	public void setPrice(ArrayList<IPrice> ip, int option){
		if (option == 1) {
			UserAgePrice.clear();
			for(IPrice iPrice : ip){
				UserAge u = (UserAge) iPrice;
				UserAgePrice.add(u);
			}
		}else if (option == 2){
			TypePrice.clear();
			for(IPrice iPrice : ip){
				Types t = (Types) iPrice;
				TypePrice.add(t);
			}
		}else if (option == 3){
			DayPrice.clear();
			for(IPrice iPrice : ip){
				Day d = (Day) iPrice;
				DayPrice.add(d);
			}
		}
	}

	/**
	 * saves current price modifier values into txt file.
	 */
	public void savePrice() {
		try {
			FileOutputStream f = new FileOutputStream(new File("UserAgePrice.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(UserAgePrice);
			o.close();
			f.close();
			
			FileOutputStream f1 = new FileOutputStream(new File("TypePrice.txt"));
			ObjectOutputStream o1 = new ObjectOutputStream(f1);
			o1.writeObject(TypePrice);
			o1.close();
			f1.close();
			
			FileOutputStream f2 = new FileOutputStream(new File("DayPrice.txt"));
			ObjectOutputStream o2 = new ObjectOutputStream(f2);
			o2.writeObject(DayPrice);
			o2.close();
			f2.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
	}
	
}
