package Ticket;

/**
 * This subclass of Day is used as modifier/multiplier for price in weekends
 */
public class WeekendPrice extends Day{
	/**
	 * constructs this class
	 */
	public WeekendPrice() {
		setPrice(1.2);
		name = "Weekend";
	}

	/**
	 * returns current set multiplier for weekends
	 * @return current multiplier for weekends
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * sets new multiplier for weekends
	 * @param p new multiplier for weekends
	 */
	public void setPrice(double p) {
		price = p;
	}
}
