package Ticket;

/**
 * subclass used for Platinum Ticket Type price modifier
 */
public class PlatinumPrice extends Types{
	/**
	 * constructor for this class. Sets the default value
	 */
	public PlatinumPrice() {
		setPrice(1.5);
		name = "Platinum";
	}

	/**
	 * returns price modifier for platinum tickets
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
