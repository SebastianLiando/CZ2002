package Ticket;

/**
 * This subclass of Day is used as modifier/multiplier for price in weekday
 */

public class WeekdayPrice extends Day {
	/**
	 * constructs this class
	 */
	public WeekdayPrice() {
		setPrice(1);
		name = "Weekday";
	}

	/**
	 * returns current set multiplier for weekdays
	 * @return current multiplier for weekdays
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * sets new multiplier for weekdays
	 * @param p new multiplier for weekdays
	 */
	public void setPrice(double p) {
		price = p;
	}
}
