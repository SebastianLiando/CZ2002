package Ticket;

import java.io.Serializable;

/**
 * This class is used as base class for User Age modifier
 */

public class UserAge implements IPrice,Serializable{
	protected double price;
	protected String name;

	/**
	 * returns multiplier according to User Age
	 * @return base multiplier
	 */
	public double getPrice() {
		return 1;
	}

	/**
	 * returns name of costumer
	 * @return costumer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets new base multiplier
	 * @param d new multiplier to be set
	 */
	public void setPrice(double d) {price = d;}
}
