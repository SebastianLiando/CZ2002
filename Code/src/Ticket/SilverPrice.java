package Ticket;

/**
 * subclass used for Silver Ticket Type price modifier
 */
public class SilverPrice extends Types{
	/**
	 * constructor for this class. Sets the default value
	 */
	public SilverPrice() {
		setPrice(1);
		name = "Silver";
	}

	/**
	 * returns price modifier for silver tickets
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
