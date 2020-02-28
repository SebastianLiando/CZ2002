package Ticket;

/**
 * subclass used for Gold Ticket Type price modifier
 */
public class GoldPrice extends Types {
	/**
	 * constructor for this class. Sets the default value
	 */
	public GoldPrice() {
		setPrice(1.1);
		name = "Gold";
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
