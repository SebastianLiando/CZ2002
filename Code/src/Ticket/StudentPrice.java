package Ticket;

/**
 * subclass used for student-aged User Age price modifier
 */
public class StudentPrice extends UserAge{
	/**
	 * constructs this class
	 */
	public StudentPrice() {
		setPrice(0.95);
		name = "Student";
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
