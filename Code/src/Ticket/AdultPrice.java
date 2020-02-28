package Ticket;

/**
 * subclass used for adult-aged User Age price modifier
 */
public class AdultPrice extends UserAge{
	/**
	 * constructs this class
	 */
	public AdultPrice() {
		setPrice(1);
		name = "Adult";
	}

	/**
	 * returns price modifier for students
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
