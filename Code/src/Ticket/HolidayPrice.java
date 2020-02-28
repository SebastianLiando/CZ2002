package Ticket;

/**
 * subclass used for Holiday price modifier
 */
public class HolidayPrice extends Day {
	/**
	 * constructor for this class. Sets the default value
	 */
	public HolidayPrice() {
		setPrice(1.2);
		name = "Holiday";
	}
	/**
	 * returns price modifier for movie tickets showing at holidays
	 * @return price modifier
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * sets new modifier value
	 * @param p new modifier value
	 */
	public void setPrice(double p) {
		price = p;
	}
	
}
