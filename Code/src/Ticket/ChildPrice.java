package Ticket;


/**
 * subclass used for child-aged User Age price modifier
 */
public class ChildPrice extends UserAge{
	/**
	 * constructs this class
	 */
	public ChildPrice() {
		setPrice(0.9);
		name = "Child";
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
